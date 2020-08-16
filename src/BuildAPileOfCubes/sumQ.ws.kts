package BuildAPileOfCubes

import java.math.BigDecimal
import java.math.MathContext

tailrec fun sumq(check: Long, n: Int = 1, sum: Long = 0L): Int =
    when {
        sum*sum == check -> n-1
        sum*sum > check -> -1
        else -> sumq(check, n+1, sum + n)
    }

sumq(1071225)
sumq(91716553919377)

@OptIn(kotlin.ExperimentalStdlibApi::class)
(1 .. 50).scan(Triple<Int,Long,Long>(1,1L,1L)) { acc, i -> Triple(i,acc.second+i, acc.second*acc.second) }

val x1: BigDecimal = BigDecimal(91716553919377)
x1.sqrt(MathContext(10))