import MoleculeToAtoms.getAtoms
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


@DisplayName("Count the number of atoms of each element contained in the molecule")
class MoleculeToAtomsTest() {
    @DisplayName("Should calculate the correct count of atoms of elements")
    @ParameterizedTest(name = "molecule:{0} = {1} => {2}")
    @MethodSource("formulaProvider")
    fun testMolecule(moleculaName: String, formula: String, expected: String?) {
        val ex: Map<String, Int>? = expected
            ?.split(",")
            ?.associate { it.split("=").let { it[0] to it[1].toInt() } }
        if (ex == null) {
            val except = assertThrows<IllegalArgumentException>{ getAtoms(formula) }
            assertTrue(except.message!!.contains(formula))
        } else {
            val res = getAtoms(formula)
            ex.entries.forEach{
                assertEquals(res[it.key],it.value)
            }
        }
    }

    companion object {
        @JvmStatic
        private fun formulaProvider(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of("Water", "H2O", "H=2,O=1"),
                Arguments.of("MagnesiumHydroxide", "Mg(OH)2", "Mg=1,O=2,H=2"),
                Arguments.of("FremySalt", "K4[ON(SO3)2]2", "K=4,O=14,N=2,S=4"),
                Arguments.of("Error", "Mg{OH)2", null)
            )
        }
    }
}