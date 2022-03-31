import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class speedControlKtTest {
    @Test
    fun gps() {
        println("Fixed Tests: gps")
        var x = doubleArrayOf(0.0, 0.23, 0.46, 0.69, 0.92, 1.15, 1.38, 1.61)
        testing(SpeedControl.gps(20, x), 41)
        x = doubleArrayOf(0.0, 0.11, 0.22, 0.33, 0.44, 0.65, 1.08, 1.26, 1.68, 1.89, 2.1, 2.31, 2.52, 3.25)
        testing(SpeedControl.gps(12, x), 219)
    }
    companion object {
        private fun testing(actual:Int, expected:Int) {
            assertEquals(expected, actual)
        }
    }
}