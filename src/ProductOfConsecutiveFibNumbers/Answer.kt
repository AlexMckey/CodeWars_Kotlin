package ProductOfConsecutiveFibNumbers

private fun fibonacci() = sequence {
    var prevCur = 0L to 1L
    while (true) {
        yield (prevCur.first)
        prevCur = prevCur.second to prevCur.first + prevCur.second
    }
}

fun productFib(prod:Long):LongArray {
    var p = fibonacci().zipWithNext().dropWhile { it.first*it.second < prod }.take(1).first()
    return longArrayOf(p.first, p.second, if (p.first*p.second == prod) 1 else 0)
}