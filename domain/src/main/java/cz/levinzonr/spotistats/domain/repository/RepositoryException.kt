package cz.levinzonr.spotistats.domain.repository

data class RepositoryException(
    val code: Int = CODE_ALZA,
    val errorBody: String? = null,
    val msg: String
) : RuntimeException(msg)

const val CODE_ALZA =2