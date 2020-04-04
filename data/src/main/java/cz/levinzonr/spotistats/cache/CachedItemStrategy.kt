package cz.levinzonr.spotistats.cache

import cz.levinzonr.spotistats.database.models.CachedEntity

open class CachedItemStrategy<T : CachedEntity>(
        configuration: CachingConfiguration
) : CachingStrategy<T>(configuration) {
    override fun cacheIsValid(item: T?): Boolean {
        return item != null
    }

    override fun cacheIsValidAndNotExpired(item: T?): Boolean {
        return item != null && item.isValidNow(cacheValidityTime)
    }
}