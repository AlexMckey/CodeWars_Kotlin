//import org.junit.jupiter.api.Assertions.assertEquals
import morsecode.decodeBits
import morsecode.decodeBitsAdvanced
import morsecode.decodeMorse
import morsecode.decodeRegexBits
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DecodeTheMorseCodeTests {
    @Test
    fun DecodeMorseTest() {
        assertEquals("HEY JUDE", decodeMorse(".... . -.--   .--- ..- -.. ."))
    }
    @Test
    fun DecodeBitsTest() {
        assertEquals("HEY JUDE", decodeMorse(decodeBits("1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011")))
    }
    @Test
    fun DecodeRegexBitsTest() {
        assertEquals("HEY JUDE", decodeMorse(decodeRegexBits("1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011")))
    }
    @Test
    fun DecodeRealBitsTest() {
        assertEquals("HEY JUDE", decodeMorse(decodeBitsAdvanced("0000000011011010011100000110000001111110100111110011111100000000000111011111111011111011111000000101100011111100000111110011101100000100000")))
    }
}