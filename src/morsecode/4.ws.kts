package morsecode

import kotlin.math.ceil

val bits = "0000000011011010011100000110000001111110100111110011111100000000000111011111111011111011111000000101100011111100000111110011101100000100000"
val pbits =bits.trim('0')

tailrec fun rle(rem: CharSequence, prev: Char, cnt: Int = 1, acc: List<Pair<Char, Int>> = emptyList()): List<Pair<Char, Int>>{
    if (rem.isEmpty())  return acc + (prev to cnt)
    val cur = rem.first()
    return if (prev == cur) rle(rem.drop(1),cur,cnt+1,acc)
    else rle(rem.drop(1),cur,1,acc + (prev to cnt))
}

val signalPairs = rle(pbits.drop(1),pbits.first())
val avgRate = signalPairs.map { it.second }.average()
avgRate
val avgSignalPairs = signalPairs.map { it.first to ceil(it.second / avgRate).toInt() }
avgSignalPairs
fun signalToMorseCode(signal: Pair<Char,Int>): String {
    val ch = signal.first
    val tick = signal.second
    return if (ch == '1') when (tick) {
        in 1..1 -> "."
        else -> "-"
    } else when (tick) {
        in 1..1 -> ""
        in 2..3 -> " "
        else -> "   "
    }
}
val res = avgSignalPairs.map { signalToMorseCode(it) }.joinToString("")
res
decodeMorse(res)
