package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") : String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time
    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()) : String {
    val diff = date.time - this.time
    if (diff >= 0) {
        return when (diff) {
            in 0..SECOND -> "только что"
            in SECOND..45 * SECOND -> "несколько секунд назад"
            in 45 * SECOND..75 * SECOND -> "минуту назад"
            in 75 * SECOND..45 * MINUTE ->
                "${Math.round((diff.toDouble() / MINUTE))} ${when (Math.round((diff.toDouble() / MINUTE))) {in 2..4 -> "минуты" else -> "минут" }} назад"
            in 45 * MINUTE..75 * MINUTE -> "час назад"
            in 75 * MINUTE..22 * HOUR ->
                "${Math.round(diff.toDouble() / HOUR)} ${when (Math.round(diff.toDouble() / HOUR)) {in 2..4 -> "часа" else -> "часов"}} назад"
            in 22 * HOUR..26 * HOUR -> "день назад"
            in 26 * HOUR..360 * DAY ->
                "${Math.round(diff.toDouble() / DAY)} ${when (Math.round(diff.toDouble() / DAY)) {in 2..4 -> "дня" else -> "дней"}} назад"
            else -> "более года назад"
        }
    } else {
        return when (-diff) {
            in 0..SECOND -> "только что"
            in SECOND..45 * SECOND -> "через несколько секунд"
            in 45 * SECOND..75 * SECOND -> "через минуту"
            in 75 * SECOND..45 * MINUTE ->
                "через ${-Math.round((diff.toDouble() / MINUTE))} ${when (-Math.round(diff.toDouble() / MINUTE)) {in 2..4 -> "минуты" else -> "минут"}}"
            in 45 * MINUTE..75 * MINUTE -> "через час"
            in 75 * MINUTE..22 * HOUR ->
                "через ${-Math.round(diff.toDouble() / HOUR)} ${when (-Math.round(diff.toDouble() / HOUR)) {in 2..4 -> "часа" else -> "часов"}}"
            in 22 * HOUR..26 * HOUR -> "день назад"
            in 26 * HOUR..360 * DAY ->
                "через ${-Math.round(diff.toDouble() / DAY)} ${when (-Math.round(diff.toDouble() / DAY)) {in 2..4 -> "дня" else -> "дней"}}"
            else -> "более чем через год"
        }
    }
}