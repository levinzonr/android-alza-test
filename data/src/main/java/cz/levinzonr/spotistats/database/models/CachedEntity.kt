package cz.levinzonr.spotistats.database.models

abstract class CachedEntity{
    abstract val id: String
    var timestamp: Long = System.currentTimeMillis()
}