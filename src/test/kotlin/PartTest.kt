
import org.jetbrains.spek.api.Spek
import parts.Joint
import parts.Part
import stat.DoubleStat
import stat.IntStat
import stat.StatType
import kotlin.test.assertEquals
import kotlin.test.assertFalse

/**
 * Created by Andy on 6/5/2016.
 */
class PartTest : Spek({
    describe("a part") {

        val testPart = Part("TestPart")
        val jointTop = Joint("Top joint", 0.0, 1.0, 0.0)
        val jointLeft = Joint("Left joint", -1.0, 0.0, 0.0)
        val jointRight = Joint("Right joint", 1.0, 0.0, 0.0)
        val jointBottom = Joint("Bottom joint", 0.0, -1.0, 0.0)

        val attachmentTop = Part("Top")
        val attachmentLeft = Part("Left")
        val attachmentRight = Part("Right")
        val attachmentBottom = Part("Bottom")

        attachmentTop.stats.put(StatType.ACCURACY, DoubleStat(1.0))
        attachmentLeft.stats.put(StatType.ACCURACY, DoubleStat(1.0))
        attachmentRight.stats.put(StatType.SPEED, DoubleStat(3.0))
        attachmentBottom.stats.put(StatType.SILENCE, IntStat(4))

        testPart.joints.addAll(arrayOf(jointTop, jointLeft, jointRight, jointBottom))
        testPart.addAttachment(jointTop, attachmentTop)
        testPart.addAttachment(jointLeft, attachmentLeft)
        testPart.addAttachment(jointRight, attachmentRight)
        testPart.addAttachment(jointBottom, attachmentBottom)

        it("should have a name") {
            assertEquals(testPart.name, "TestPart")
        }

        it ("should be able to have multiple joints") {
            assertEquals(testPart.joints.size, 4)
        }

        it ("shouldn't be able to add an attachment without a valid joint") {
            assertFalse(testPart.addAttachment(Joint("New joint"), Part("New part")))
        }

        it ("shouldn't be able to add an attachment if it's already been added") {
            assertFalse(testPart.addAttachment(jointTop, attachmentRight))
        }

        it ("should be able to have multiple attachments") {
            assertEquals(testPart.attachments.size, 4)
        }

        it ("should associate as the parent to attachments when they are added") {
            for ((joint, attachment) in testPart.attachments) {
                assertEquals(attachment.parentPart, testPart)
            }
        }

        it ("should be able to combine stat sets of all the attachments") {
            val combinedStats = testPart.getCombinedStats()
            assertEquals(combinedStats[StatType.ACCURACY]?.rawValue, 2.0)
            assertEquals(combinedStats[StatType.SPEED]?.rawValue, 3.0)
            assertEquals(combinedStats[StatType.SILENCE]?.rawValue, 4)
        }
    }
})