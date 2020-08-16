package SpeedControl

import kotlin.math.floor

private fun toMH(delta_distance: Double, delta_time: Int): Double =
    (3600 * delta_distance) / delta_time

fun gps(s:Int, x:DoubleArray):Int =
    x.toList()
        .zipWithNext { a, b -> floor(toMH(b-a, s)) }
        .max()?.toInt() ?: 0