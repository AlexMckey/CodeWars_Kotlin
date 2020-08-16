package morsecode

val code = ".... . -.--   .--- ..- -.. ."

val MorseCode = mapOf("...." to "H","." to "E","-.--" to "Y",".---" to "J","..-" to "U","-.." to "D")
MorseCode["...."]

code.trim()
    .split("   ")
    .map{it
        .split(" ")
        .map{MorseCode[it] ?: ""}
        .joinToString("")}
    .joinToString(" ")