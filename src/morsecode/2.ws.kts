package morsecode

val str = "1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011"
//val str = "110011"
tailrec fun rle(rem: CharSequence, prev: Char, cnt: Int = 1, acc: List<Pair<Char, Int>> = emptyList()): List<Pair<Char, Int>>
{
    if (rem.isEmpty())  return acc + (prev to cnt)
    val cur = rem.first()
    if (prev == cur) return rle(rem.drop(1),cur,cnt+1,acc)
    else return rle(rem.drop(1),cur,1,acc + (prev to cnt))
}
val res = rle(str.drop(1), str.first())
res
val period = res.minByOrNull { it.second }?.second ?: 1
val code = res.map { it.first to it.second / period }
fun signalToMorseCode(signal: Pair<Char,Int>): String {
    return when (signal) {
        '1' to 1 -> "."
        '1' to 3 -> "-"
        '0' to 1 -> ""
        '0' to 3 -> " "
        '0' to 7 -> "   "
        else -> ""
    }
}
signalToMorseCode('1' to 1)
val morseCode = code.map{ signalToMorseCode(it) }.joinToString("")
morseCode
//"···· · -·--   ·--- ··- -·· ·"
val morseStr = decodeMorse(morseCode)
morseStr
