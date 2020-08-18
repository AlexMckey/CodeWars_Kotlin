import BuildAPileOfCubes.ASum
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class  ASumTest {

    private fun testing(n: Long, expected: Long) {
        val actual = ASum.findNb(n)
        assertEquals(expected, actual)
    }
    @Test
    fun fixedTests() {
        testing(56396345062501, -1)
        testing(6132680780625, 2225)
        testing(28080884739601, -1)

    }
}