package cz.levinzonr.spotistats.domain.interactors


class PostsInteractor() : Interactor<List<String>> {

    override suspend fun invoke(): List<String> {
        return listOf()
    }
}