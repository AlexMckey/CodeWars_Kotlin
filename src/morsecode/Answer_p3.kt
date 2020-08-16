package morsecode

import kotlin.math.ceil

private tailrec fun rle(rem: CharSequence, prev: Char, cnt: Int = 1, acc: List<Pair<Char, Int>> = emptyList()): List<Pair<Char, Int>>{
    if (rem.isEmpty())  return acc + (prev to cnt)
    val cur = rem.first()
    return if (prev == cur) rle(rem.drop(1), cur, cnt + 1, acc)
    else rle(rem.drop(1), cur, 1, acc + (prev to cnt))
}
private fun signalToMorseCode(signal: Pair<Char,Int>): String {
    val ch = signal.first
    val tick = signal.second
    return if (ch == '1') when (tick) {
        1 -> "."
        else -> "-"
    } else when (tick) {
        1 -> ""
        in 2..3 -> " "
        else -> "   "
    }
}

fun decodeBitsAdvanced(bits: String): String {
    val pbits = bits.trim('0')
    val signalPairs = rle(pbits.drop(1), pbits.first())
    val avgRate = signalPairs.map { it.second }.average()
    val avgSignalPairs = signalPairs.map { it.first to ceil(it.second / avgRate).toInt() }
    return avgSignalPairs.joinToString("") { signalToMorseCode(it) }
}