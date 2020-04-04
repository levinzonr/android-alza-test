package cz.levinzonr.spotistats.domain.extensions

fun Double.percentOf(total: Int) : Int {
    return (this * 100 / total).toInt()
}