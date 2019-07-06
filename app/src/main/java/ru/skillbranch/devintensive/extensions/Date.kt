package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR   = 60 * MINUTE
const val DAY    = 24 * HOUR

fun Date.format(pattern:String = "HH:mm:ss dd:MM:yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time
    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR    -> value * HOUR
        TimeUnits.DAY       -> value * DAY
    }
    this.time = time
    return this
}


fun Date.humanizeDiff(date: Date = Date()): String  {
    val diff = date.time - this.time
    return when(diff) {
        in 0 .. SECOND -> "только что"
        in SECOND .. 45 * SECOND  -> "несколько секунд назад"
        in SECOND *45 .. SECOND * 75  -> "минуту назад"
        in SECOND *75 .. 45 * MINUTE -> "${TimeUnits.MINUTE.plural((diff / MINUTE).toInt())} назад"
        in 45 * MINUTE .. 75 * MINUTE -> "час назад"
        in 75 * MINUTE .. 22 * HOUR -> "${TimeUnits.HOUR.plural((diff / HOUR).toInt())} назад"
        in 22 * HOUR .. 26 * HOUR -> "день назад"
        in 26 * HOUR .. 360 * DAY -> "${TimeUnits.DAY.plural((diff / DAY).toInt())} назад"
        else -> "более года назад"
    }
}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;
    fun plural(value: Int): String {

        val rangeType = when {
            value in (5..20) || value % 10 in (5..10) -> 3
            value % 10 == 1 -> 1
            value % 10 in (2..4) -> 2
            else -> 3
        }

        return when (this) {
            SECOND -> when (rangeType) {
                1 -> "$value секунду"
                2 -> "$value секунды"
                3 -> "$value секунд"
                else -> "er1ошибка"
            }
            MINUTE -> when (rangeType) {
                1 -> "$value минуту"
                2 -> "$value минуты"
                3 -> "$value минут"
                else -> "er2ошибка"
            }
            HOUR -> when (rangeType) {
                1 -> "$value час"
                2 -> "$value часа"
                3 -> "$value часов"
                else -> "er3ошибка"
            }
            DAY -> when (rangeType) {
                1 -> "$value день"
                2 -> "$value дня"
                3 -> "$value дней"
                else -> "er4ошибка"
            }
        }
    }
}