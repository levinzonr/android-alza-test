package cz.levinzonr.spotistats.database.models

abstract class CachedEntity {
    abstract val id: String
    var timestamp: Long = System.currentTimeMillis()


    fun isValidNow(maxCacheLife: Long): Boolean {
        return System.currentTimeMillis() - timestamp <= maxCacheLife
    }
}