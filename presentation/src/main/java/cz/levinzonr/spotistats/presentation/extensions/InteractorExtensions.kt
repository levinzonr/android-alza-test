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


interface ResultInteractor<I, O> : Interactor<I, CompleteResult<O>>

private class ResultInteractorImpl<I, O>(private val interactor: Interactor<I, O>) :
    ResultInteractor<I, O> {
    override suspend fun invoke(input: I): CompleteResult<O> {
        return try {
            Success(interactor(input))
        } catch (e: Exception) {
            Fail(e)
        }
    }
}


fun <I, O> Interactor<I, O>.asResult(): ResultInteractor<I, O> {
    return ResultInteractorImpl(this)
}

suspend fun <I, O> runInteractor(
        input: I,
        interactor: Interactor<I, O>,
        coroutineContext: CoroutineContext = Dispatchers.IO
): O {
    return withContext(coroutineContext) { interactor(input) }
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