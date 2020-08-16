package morsecode

private tailrec fun rle(rem: CharSequence,
                        prev: Char,
                        cnt: Int = 1,
                        acc: List<Pair<Char, Int>> = emptyList()):
        List<Pair<Char, Int>>
{
    if (rem.isEmpty())  return acc + (prev to cnt)
    val cur = rem.first()
    return if (prev == cur) rle(rem.drop(1), cur, cnt + 1, acc)
    else rle(rem.drop(1), cur, 1, acc + (prev to cnt))
}

private fun signalToMorseCode(signal: Pair<Char,Int>): String {
    return when (signal) {
        '1' to 1 -> "."
        '1' to 3 -> "-"
        '0' to 1 -> ""
        '0' to 3 -> " "
        '0' to 7 -> "   "
        else -> ""
    }
}

fun decodeBits(bits: String): String {
    val preparedSignal = bits.dropWhile { it == '0' }.dropLastWhile { it == '0' }
    val rlePairs = rle(preparedSignal.drop(1), preparedSignal.first())
    val rate = rlePairs.minBy { it.second }?.second ?: 1
    return rlePairs
        .map { it.first to it.second / rate }
        .joinToString("") { signalToMorseCode(it) }
}