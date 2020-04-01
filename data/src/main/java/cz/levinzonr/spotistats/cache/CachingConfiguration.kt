package cz.levinzonr.spotistats.cache



class CachingConfiguration(
        val remoteFallback: RemoteFallback,
        val cacheValidityTime: Long
) {

    enum class RemoteFallback {
        RETURN_CACHE, THROW_ERROR
    }
}