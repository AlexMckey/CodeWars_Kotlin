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
    if (g.groups[0] != null) println("open:${g.groups[0]!!.value}")
    if (g.groups[3] != null) println("close:${g.groups[3]!!.value}")
    if (g.groups[4] != null) println("gcnt:${g.groups[4]!!.value}")
    if (g.groups[1] != null) {
        print("atom:${g.groups[1]!!.value}")
        if (g.groups[2]!= null) println(g.groups[2]!!.value)
        else println()
    }
}