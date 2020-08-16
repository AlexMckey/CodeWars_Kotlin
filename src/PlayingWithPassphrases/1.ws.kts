val s = "BORN IN 2015!"
val n = 1

val alphabet = ('A'..'Z').toList()
//fun shift(char: Char, cnt: Int):Char = alphabet[(char-'A'+n)%alphabet.size]
fun shift(char: Char, cnt: Int):Char = 'A'+(char-'A'+cnt)%('Z'-'A'+1)
shift(s[0],1)
'Z'-'A'+1
'A'+(s[0]-'A'+n)%('Z'-'A'+1)
shift('Z',1)

fun complement9(char: Char):Char = '0'+9-(char-'0')

complement9('1')
complement9('9')
complement9('4')

fun lowerOdd(idx: Int, char: Char):Char = if (idx % 2 != 0) char.toLowerCase() else char

lowerOdd(0,'C')
lowerOdd(1,'G')

s.map { shift(it,0) }
fun encodeChar(idx: Int, char: Char): Char {
    if (char.isLetter()) {
        val ch = 'A'+(char-'A'+n)%('Z'-'A'+1)
        return if (idx % 2 != 0) ch.toLowerCase() else ch
    } else if (char.isDigit())
        return '0'+9-(char-'0')
    else return char}
s.mapIndexed { index, c ->  encodeChar(index,c) }.reversed().joinToString("")