package MoleculeToAtoms

fun getAtoms_perfect(formula: String): Map<String, Int> {
    var str = formula
    val regex = Regex("""(\(\w+\)|\{\w+}|\[\w+])(\d*)""")
    while (regex.find(str) != null) {
        str = regex.replace(str) { it.groupValues[1].drop(1).dropLast(1).repeat(it.groupValues[2].toIntOrNull() ?: 1) }
    }
    if (str.any{it in "(){}[]"} || Regex("""[^A-Z][a-z]+""").find(str) != null) throw IllegalArgumentException()
    return Regex("""([A-Z][a-z]*)(\d*)""").findAll(str)
        .groupBy(keySelector={it.groupValues[1]},valueTransform={it.groupValues[2].toIntOrNull()?:1})
        .map{it.key to it.value.sum()}
        .toMap()
}