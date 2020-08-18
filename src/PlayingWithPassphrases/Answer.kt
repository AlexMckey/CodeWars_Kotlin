package PlayingWithPassphrases

object PlayPass {

    private fun encodeChar(idx: Int, char: Char, shift: Int): Char {
        return if (char.isLetter()) {
            val ch = 'A'+(char-'A'+shift)%('Z'-'A'+1)
            if (idx % 2 != 0) ch.toLowerCase() else ch
        } else if (char.isDigit())
            '0'+9-(char-'0')
        else char
    }

    fun playPass(s: String, n: Int): String =
        s.mapIndexed{ idx,c -> encodeChar(idx,c,n) }.reversed().joinToString("")
}