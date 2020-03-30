package cz.levinzonr.spotistats.presentation.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.levinzonr.spotistats.domain.interactors.Interactor
import cz.levinzonr.spotistats.domain.interactors.CompleteResult
import cz.levinzonr.spotistats.domain.interactors.Fail
import cz.levinzonr.spotistats.domain.interactors.InteractorResult
import cz.levinzonr.spotistats.domain.interactors.Loading
import cz.levinzonr.spotistats.domain.interactors.Success
import cz.levinzonr.spotistats.domain.interactors.Uninitialized
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

interface LiveDataInteractor<T> : Interactor<Unit> {
    val liveData: LiveData<InteractorResult<T>>
}

interface ResultInteractor<T> : Interactor<CompleteResult<T>>

@ExperimentalCoroutinesApi
interface ChannelInteractor<T> : Interactor<Unit> {
    fun receive(): ReceiveChannel<InteractorResult<T>>
}

interface RxInteractor<T> : Interactor<Unit> {
    fun observe(): Flowable<InteractorResult<T>>
}

interface FlowInteractor<T> : Interactor<Flow<InteractorResult<T>>>

private class LiveDataInteractorImpl<T>(private val interactor: Interactor<out T>) :
    LiveDataInteractor<T> {
    private val mutableLiveData = MutableLiveData<InteractorResult<T>>().apply {
        postValue(Uninitialized)
    }

    override val liveData = mutableLiveData
    override suspend operator fun invoke() {
        mutableLiveData.postValue(Loading())
        try {
            val result = interactor()
            mutableLiveData.postValue(Success(result))
        } catch (t: Throwable) {
            mutableLiveData.postValue(Fail(t))
        }
    }
}

@ExperimentalCoroutinesApi
private class ChannelInteractorImpl<T>(private val interactor: Interactor<out T>) :
    ChannelInteractor<T> {

    private val channel = BroadcastChannel<InteractorResult<T>>(Channel.CONFLATED).apply {
        offer(Uninitialized)
    }

    override fun receive() = channel.openSubscription()

    override suspend operator fun invoke() {
        channel.offer(Loading())
        try {
            channel.offer(Success(interactor()))
        } catch (t: Throwable) {
            channel.offer(Fail(t))
        }
    }
}

private class ResultInteractorImpl<T>(private val interactor: Interactor<out T>) :
    ResultInteractor<T> {
    override suspend fun invoke(): CompleteResult<T> {
        return try {
            Success(interactor())
        } catch (e: Exception) {
            Fail(e)
        }
    }
}

private class RxInteractorImpl<T>(private val interactor: Interactor<out T>) :
    RxInteractor<T> {
    override fun observe() = subject.toFlowable(BackpressureStrategy.LATEST)!!
    private val subject = BehaviorSubject.createDefault<InteractorResult<T>>(Uninitialized)
    override suspend operator fun invoke() {
        subject.onNext(Loading())
        try {
            subject.onNext(Success(interactor()))
        } catch (t: Throwable) {
            subject.onError(t)
        }
    }
}

private class FlowInteractorImpl<T>(private val interactor: Interactor<out T>) :
    FlowInteractor<T> {
    override suspend fun invoke(): Flow<InteractorResult<T>> {
        return flow {
            emit(Loading<T>())
            try {
                emit(Success(interactor()))
            } catch (t: Throwable) {
                emit(Fail(t))
            }
        }
    }
}

fun <T> Interactor<T>.asResult(): ResultInteractor<T> {
    return ResultInteractorImpl(this)
}

fun <T> Interactor<T>.asLiveData(): LiveDataInteractor<T> {
    return LiveDataInteractorImpl(this)
}

@ExperimentalCoroutinesApi
fun <T> Interactor<T>.asChannel(): ChannelInteractor<T> {
    return ChannelInteractorImpl(this)
}

fun <T> Interactor<T>.asRx(): RxInteractor<T> {
    return RxInteractorImpl(this)
}

fun <T> Interactor<T>.asFlow(): FlowInteractor<T> {
    return FlowInteractorImpl(this)
}

fun CoroutineScope.launchInteractor(
        interactor: Interactor<Unit>,
        coroutineContext: CoroutineContext = Dispatchers.IO
) {
    launch(coroutineContext) { interactor() }
}

suspend fun <T> runInteractor(
        interactor: Interactor<T>,
        coroutineContext: CoroutineContext = Dispatchers.IO
): T {
    return withContext(coroutineContext) { interactor() }
}

suspend fun <T, R> InteractorResult<T>.isSuccess(block: suspend (T) -> R): InteractorResult<T> {
    if (this is Success) {
        block(this.data)
    }
    return this
}

suspend fun <T, R> InteractorResult<T>.isError( block: suspend (throwable: Throwable) -> R): InteractorResult<T> {
    if (this is Fail) {
        block(this.throwable)
    }
    return this
}