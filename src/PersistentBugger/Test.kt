package PersistentBugger

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class TestPersistence {
    @Test
    fun `Basic Tests`() {
        assertEquals(3, persistence(39))
        assertEquals(0, persistence(4))
        assertEquals(2, persistence(25))
        assertEquals(4, persistence(999))
    }
}