package BuildAPileOfCubes

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class  ASumTest {

    private fun testing(n: Long, expected: Long) {
        var actual = ASum.findNb(n)
        assertEquals(expected, actual)
    }
    @Test
    fun fixedTests() {
        testing(56396345062501, -1)
        testing(6132680780625, 2225)
        testing(28080884739601, -1)

    }
}