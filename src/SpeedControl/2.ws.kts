import kotlin.math.floor

val x = doubleArrayOf(0.0, 0.19, 0.5, 0.75, 1.0, 1.25, 1.5, 1.75, 2.0, 2.25)
val s = 15

fun toMH(delta_distance: Double, delta_time: Int): Double =
    (3600 * delta_distance) / delta_time

x.toList().zipWithNext { a, b -> floor(toMH(b-a, s)) }.maxOrNull()?.toInt()