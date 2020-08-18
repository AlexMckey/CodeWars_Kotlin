abstract class CF
data class E(val a: String, val cnt: Int = 1): CF() {
    override fun toString(): String {
        return "$a${if (cnt == 1) "" else cnt.toString()}"
    }
}
data class G(val elems: List<CF>, val cnt: Int = 1): CF() {
    override fun toString(): String {
        return if (cnt == 1) elems.joinToString("") { it.toString() }
        else elems.joinToString("","(",")$cnt") { it.toString() }
    }
}

val w = G(listOf(E("H",2),E("O")))
w.toString()

val m = G(listOf(E("K",4),G(listOf(E("O"),E("N"),G(listOf(E("S"),E("O",3)),2)),2)))
m.toString()

val r = """(?<open>[(\[{])|(?:(?<atom>[A-Z][a-z]*)(?<acnt>\d*))|(?:(?<close>[)\]}])(?<gcnt>\d*))""".toRegex()
val s = "H2O"
r.containsMatchIn(s)
r.findAll(s).joinToString(""){it.value}
r.findAll("Mg{OH)2").joinToString(""){it.value}
r.findAll("K4[ON(SO3)2]2").joinToString(""){it.value}

for (g in r.findAll("Mg{OH)2")){
    if (g.groups["open"] != null) println("open:${g.groups["open"]!!.value}")
    if (g.groups["close"] != null) println("close:${g.groups["close"]!!.value}")
    if (g.groups["gcnt"] != null) println("gcnt:${g.groups["gcnt"]!!.value}")
    if (g.groups["atom"] != null) {
        print("atom:${g.groups["atom"]!!.value}")
        if (g.groups["acnt"]!= null) println(g.groups["acnt"]!!.value)
        else println()
    }
}