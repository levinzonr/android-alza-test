package cz.levinzonr.spotistats.domain.interactors

interface Interactor<I, O> {
    suspend operator fun invoke(input: I): O
}

typealias NoInputInteractor<O> = Interactor<Unit, O>
