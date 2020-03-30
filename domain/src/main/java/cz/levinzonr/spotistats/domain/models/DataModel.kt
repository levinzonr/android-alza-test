package cz.levinzonr.spotistats.domain.models

interface DataModel<T: DomainModel> {
    fun toDomain() : T
}