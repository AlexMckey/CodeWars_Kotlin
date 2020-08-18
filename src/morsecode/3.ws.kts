package morsecode

val str = "00011001100110011000000110000001111110011001111110011111100000000000000110011111100111111001111110000001100110011111100000011111100110011000000110000"
val pbits = str.trim('0')
val r = "(0+|1+)".toRegex()
val rate = r.findAll(pbits).map { it.value.length }.minOrNull() ?: 1
rate
val res = pbits
    .replace("1".repeat(rate),"1")
    .replace("0".repeat(rate),"0")
    .replace("111","-")
    .replace("1",".")
    .replace("0".repeat(7)," ".repeat(3))
    .replace("000", " ")
    .replace("0","")
res