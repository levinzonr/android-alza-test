package cz.levinzonr.spotistats.domain.interactors


class PostsInteractor() : BaseAsyncInteractor<List<String>> {

    override suspend fun invoke(): List<String> {
        return listOf()
    }
}