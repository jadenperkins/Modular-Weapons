
import org.jetbrains.spek.api.Spek
import stat.StatBase
import stat.StatSet
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Created by Andy on 6/5/2016.
 */
class StatTest : Spek({
    describe("stats") {

        val statSet = StatSet()

        statSet.add(StatBase.SPEED, 2.0)
        statSet.add(StatBase.SILENCE, 4)

        it("should be able to contain a map of stat types and the stat values") {
            assertTrue(statSet[StatBase.SPEED] is Double)
            assertTrue(statSet[StatBase.SILENCE] is Int)
            assertEquals(statSet[StatBase.SPEED], 2.0)
            assertEquals(statSet[StatBase.SILENCE], 4)
        }

        it ("should be able to combine stat sets, in a non-mutative way") {
            val statSet2 = StatSet()
            statSet2.add(StatBase.SPEED, 3.0)
            statSet2.add(StatBase.SILENCE, 5)

            val out = statSet.combine(statSet2)

            assertTrue(statSet2 != statSet)
            assertTrue(statSet2 != statSet)
            assertEquals(statSet[StatBase.SPEED], 2.0)
            assertEquals(statSet[StatBase.SILENCE], 4)
            assertEquals(statSet2[StatBase.SPEED], 3.0)
            assertEquals(statSet2[StatBase.SILENCE], 5)
            assertEquals(out[StatBase.SPEED], 5.0)
            assertEquals(out[StatBase.SILENCE], 9)
        }

        it ("should be able to modify other stats of the same type") {
            statSet.add(StatBase.POWER, 1.0)
            statSet.add(StatBase.SPELL_POWER, 3.0)

            assertEquals(statSet.getModifiedValue(StatBase.POWER), 4.0)
        }
    }
})
