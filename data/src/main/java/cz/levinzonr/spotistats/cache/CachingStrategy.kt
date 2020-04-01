package cz.levinzonr.spotistats.cache

import java.lang.Exception

abstract class CachingStrategy<T>(configuration: CachingConfiguration) {

    protected val cacheValidityTime = configuration.cacheValidityTime
    private val remoteFallback = configuration.remoteFallback

    private lateinit var cachedSource: suspend () -> T?
    private lateinit var remoteSource: suspend () -> T
    private lateinit var updateCacheAction: suspend (T) -> Unit

    abstract fun cacheIsValid(item: T?): Boolean
    abstract fun cacheIsValidAndNotExpired(item: T?): Boolean


    fun setCachingSource(block: suspend () -> T?): CachingStrategy<T> {
        cachedSource = block
        return this
    }

    fun setRemoteSource(block: suspend () -> T): CachingStrategy<T> {
        remoteSource = block
        return this
    }

    fun setOnUpdateItems(block: suspend (T) -> Unit): CachingStrategy<T> {
        updateCacheAction = block
        return this
    }

    suspend fun apply(): T {
        val cached = cachedSource.invoke()
        return if (cached != null && cacheIsValidAndNotExpired(cached)) {
            cached
        } else {
            tryFetchingRemote()
        }
    }

    private suspend fun tryFetchingRemote(): T {
        return try {
            remoteSource.invoke().also { updateCacheAction.invoke(it) }
        } catch (e: Exception) {
            when (remoteFallback) {
                CachingConfiguration.RemoteFallback.RETURN_CACHE -> {
                    val cached = cachedSource.invoke()
                    if (cached != null && cacheIsValid(cached)) {
                        cached
                    } else {
                        throw e
                    }
                }
                CachingConfiguration.RemoteFallback.THROW_ERROR -> {
                    throw e
                }
            }
        }
    }

    private suspend fun getCachedDataIfValid(): T? {
        val cached = cachedSource.invoke()
        return if (cacheIsValid(cached)) {
            cached
        } else {
            null
        }
    }
}

