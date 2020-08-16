package morsecode

fun decodeRegexBits(bits: String): String {
    val pbits = bits.trim('0')
    val rate = Regex("(0+|1+)")
        .findAll(pbits)
        .map { it.value.length }
        .min() ?: 1
    return pbits
        .replace("1".repeat(rate),"1")
        .replace("0".repeat(rate),"0")
        .replace("111","-")
        .replace("1",".")
        .replace("0".repeat(7)," ".repeat(3))
        .replace("000", " ")
        .replace("0","")
}