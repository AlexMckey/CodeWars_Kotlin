package MoleculeToAtoms

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
//import java.util.*
import kotlin.test.assertFailsWith

@ExtendWith(Parameterized::class)
class SolutionTest(atoms: List<String>, nums: List<Int>, private val formula: String, private val name: String) {
    private val expected: Map<String, Int>

    init {
        val exp = HashMap<String, Int>()
        for (i in atoms.indices) exp[atoms[i]] = nums[i]
        this.expected = exp
    }

    @Test
    fun testMolecule() {
        if (expected.isEmpty()) assertFailsWith<IllegalArgumentException>(String.format("Your function should throw an IllegalArgumentException for a wrong formula: %s", formula)) {
            getAtoms(formula)
        } else assertEquals(String.format("Should parse %s: %s", name, formula), expected, getAtoms(formula))
    }

    companion object {
        @Parameters
        @JvmStatic
        fun data(): List<Array<out Any>> =
            listOf(arrayOf(listOf("H", "O"), listOf(2, 1), "H2O", "water"),
                arrayOf(listOf("B", "H"), listOf(2, 6), "B2H6", "dihydroboran"),
                arrayOf(listOf("C", "H", "O"), listOf(6, 12, 6), "C6H12O6", "glucose"))
    }
}