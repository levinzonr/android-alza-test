package cz.levinzonr.spotistats.database.converters

import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun listToString(list: List<String?>) : String {
        return if (list.isEmpty()) "" else list.filterNotNull().joinToString(SEPARATOR) { "" }
    }

    @TypeConverter
    fun stringToList(string: String) : List<String> {
        return string.split(SEPARATOR)
    }

    companion object {
        private const val SEPARATOR = ","
    }
}