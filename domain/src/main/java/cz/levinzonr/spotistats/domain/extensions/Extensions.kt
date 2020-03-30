package cz.levinzonr.spotistats.domain.extensions

import cz.levinzonr.spotistats.domain.interactors.Interactor
import cz.levinzonr.spotistats.domain.interactors.CompleteResult
import cz.levinzonr.spotistats.domain.interactors.Fail
import cz.levinzonr.spotistats.domain.interactors.Success

inline fun <T> T.guard(block: T.() -> Unit): T {
    if (this == null) block(); return this
}

inline fun <T> Interactor<CompleteResult<T>>.safeInteractorCall(block: () -> T): CompleteResult<T> {
    return try {
        Success(block.invoke())
    } catch (t: Throwable) {
        Fail(t)
    }
}