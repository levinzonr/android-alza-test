package cz.levinzonr.spotistats.cache

import cz.levinzonr.spotistats.database.models.CachedEntity

class CachedItemListStrategy<T : CachedEntity>(
        configuration: CachingConfiguration
) : CachingStrategy<List<T>>(configuration) {

    override fun cacheIsValid(item: List<T>?): Boolean {
        return !item.isNullOrEmpty()
    }

    override fun cacheIsValidAndNotExpired(item: List<T>?): Boolean {
        return !item.isNullOrEmpty() && item.any { it.isValidNow(cacheValidityTime) }
    }
}

