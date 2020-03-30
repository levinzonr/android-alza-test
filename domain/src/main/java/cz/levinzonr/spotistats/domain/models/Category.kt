package cz.levinzonr.spotistats.domain.models

data class Category(
        val id: String,
        val name: String,
        val imageUrl: String?
) : DomainModel