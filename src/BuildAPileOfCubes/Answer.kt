package BuildAPileOfCubes

object ASum {
    private tailrec fun sumq(check: Long, n: Long = 1L, sum: Long = 0L): Long =
        when {
            sum*sum == check -> n-1
            sum*sum > check -> -1
            else -> sumq(check, n+1, sum + n)
        }
    fun findNb(m: Long): Long = sumq(m)
}