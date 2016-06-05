import org.jetbrains.spek.api.Spek
import parts.LowerReceiver
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Created by Andy on 6/5/2016.
 */
class LowerReceiverTest : Spek({
    describe("a lower receiver") {

        val lower = LowerReceiver()

        it("should should have 4 joints") {
            assertEquals(lower.joints.size, 4)
        }

        it ("should have 4 attachments") {
            assertEquals(lower.attachments.size, 4)
        }

        it("should be a base part") {
            assertTrue(lower.isBase())
        }
    }
})