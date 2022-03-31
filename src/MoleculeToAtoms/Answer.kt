package MoleculeToAtoms

import java.lang.IllegalArgumentException

abstract class Molecule {
    abstract fun countAtoms(): Map<String,Int>
}
data class Atom(val a: String, val cnt: Int = 1): Molecule() {
    override fun countAtoms(): Map<String, Int> = mapOf(a to cnt)
    override fun toString(): String {
        return "$a${if (cnt == 1) "" else cnt.toString()}"
    }
}
data class Group(val elems: List<Molecule>, val cnt: Int = 1, val open: String = "", val close: String = ""): Molecule() {
    override fun countAtoms(): Map<String, Int> =
        elems.flatMap { it.countAtoms().toList() }
            .groupBy({it.first}, {it.second})
            .mapValues { it.value.sum() * cnt }
    override fun toString(): String {
        return if (cnt == 1) elems.joinToString("") { it.toString() }
        else elems.joinToString("", open,"$close$cnt") { it.toString() }
    }
}
data class Error(val error: String = ""): Molecule() {
    override fun countAtoms(): Map<String, Int> = emptyMap()
    override fun toString(): String = error
}

private fun parseA(rem: String): Pair<String,String> {
    return if (rem.isEmpty() || !rem.first().isLetter()) "#A" to rem
    else if (rem.length == 1 && rem.first().isUpperCase()) rem.substring(0,1) to rem.substring(1)
    else if (rem.length > 1 && rem[0].isUpperCase() && rem[1].isLowerCase()) rem.substring(0,2) to rem.substring(2)
    else if (rem.length > 1 && rem[0].isUpperCase() && (!rem[1].isLetter() || rem[1].isUpperCase())) rem.substring(0,1) to rem.substring(1)
    else "#A" to rem
}

private fun parseC(rem: String): Pair<String,String> {
    if (rem.isEmpty() || !rem.first().isDigit()) return "#C" to rem
    val s = rem.takeWhile { it.isDigit() }
    return s to rem.substring(s.length)
}

private fun parseG(rem: String): Pair<String,String> {
    val brackets = mapOf('[' to ']', '{' to '}', '(' to ')')
    if (rem.isEmpty() || !brackets.keys.contains(rem.first())) return "#G" to rem
    val b = rem.first()
    var bc = 1
    var i = 1
    while (bc > 0) {
        val bi = rem.indexOfAny(listOf(b, brackets[b]!!).toCharArray(), i)
        if (bi < 0) return "#G" to rem
        if (rem[bi] == b) bc++
        if (rem[bi] == brackets[b]) bc--
        i = bi+1
    }
    return rem.substring(0,i) to rem.substring(i)
}

private fun parseAtom(rem: String): Pair<Molecule,String> {
    val (a,ra) = parseA(rem)
    if (a.first() == '#') return Error(a) to ra
    val (c,rc) = parseC(ra)
    return if (c.first() == '#') return Atom(a) to rc
    else Atom(a,c.toInt()) to rc
}

private fun parseAny(rem: String): Pair<Molecule,String> {
    val (g,rg) = parseGroup(rem)
    return if (g is Error) {
        parseAtom(rem)
    } else
        g to rg
}

private fun parseMany(rem: String): Pair<List<Molecule>,String> {
    var acc = emptyList<Molecule>()
    var newRem: String = rem
    do {
        val (any,rany) = parseAny(newRem)
        acc = acc + any
        newRem = rany
    } while (newRem.isNotEmpty() && acc.last() !is Error)
    return acc to newRem
}

private fun parseGroup(rem: String): Pair<Molecule,String> {
    val acc: List<Molecule>
    val newRem: String
    val (g,rg) = parseG(rem)
    if (g.first() == '#' || g.length < 3) return Error(g) to rg
    else {
        val pm = parseMany(g.substring(1,g.length-1))
        acc = pm.first
        newRem = pm.second
        if (acc.last() is Error) return acc.last() to newRem
    }
    val (c,rc) = parseC(rg)
    return if (c.first() == '#') Group(acc, open = g.take(1), close = g.takeLast(1)) to rc
    else Group(acc,c.toInt(),g.take(1),g.takeLast(1)) to rc
}

private fun parseMolecula(s: String): Molecule {
    val (m,rm) = parseMany(s)
    return if (m.last() is Error && rm.isNotEmpty()) throw IllegalArgumentException("Invalid formula: $s")//m.last()
    else Group(m)
}

fun getAtoms(formula: String): Map<String, Int> {
    return parseMolecula(formula).countAtoms()
}