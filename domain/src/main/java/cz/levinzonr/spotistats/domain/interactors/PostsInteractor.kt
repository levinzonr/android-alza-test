package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.models.Post
import cz.levinzonr.spotistats.repositories.PostRepository

class PostsInteractor(
    private val postRepository: PostRepository
) : BaseAsyncInteractor<List<Post>> {

    override suspend fun invoke(): List<Post> {
        return postRepository.getPosts(true)
    }
}