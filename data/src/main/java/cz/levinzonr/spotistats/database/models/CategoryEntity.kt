package cz.levinzonr.spotistats.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.levinzonr.spotistats.domain.models.Category
import cz.levinzonr.spotistats.domain.models.DataModel

@Entity
data class CategoryEntity(

        @PrimaryKey
        override val id: String,
        val name: String,
        val imageUrl: String
) : CachedEntity(), DataModel<Category> {

    override fun toDomain(): Category {
        return Category(
                id = id,
                name = name,
                imageUrl = imageUrl
        )
    }
}