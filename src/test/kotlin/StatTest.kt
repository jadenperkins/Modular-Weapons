import org.jetbrains.spek.api.Spek
import stat.DoubleStat
import stat.IntStat
import stat.StatSet
import stat.StatType
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

/**
 * Created by Andy on 6/5/2016.
 */
class StatTest : Spek({
    describe("stats") {

        val statSet = StatSet()

        statSet.put(StatType.SPEED, DoubleStat(2.0))
        statSet.put(StatType.SILENCE, IntStat(4))

        it("should be able to contain a map of stat types and the stat values") {
            assertTrue(statSet[StatType.SPEED] is DoubleStat)
            assertTrue(statSet[StatType.SILENCE] is IntStat)
            assertEquals(statSet[StatType.SPEED]?.rawValue, 2.0)
            assertEquals(statSet[StatType.SILENCE]?.rawValue, 4)
        }

        it("should only accept stats of the expect type for that stat type") {
            assertFailsWith(IllegalArgumentException::class, {
                statSet.put(StatType.SPEED, IntStat(2))
            })
            assertFailsWith(IllegalArgumentException::class, {
                statSet.put(StatType.SILENCE, DoubleStat(2.0))
            })
        }

        it ("should be able to combine stats effectively, in a non-mutative way") {
            val stat1 = IntStat(2)
            val stat2 = IntStat(3)
            val out = stat1.combine(stat2)

            assertTrue(stat1 != out)
            assertTrue(stat2 != out)
            assertEquals(stat1.value, 2)
            assertEquals(stat2.value, 3)
            assertEquals(out.rawValue, 5)
        }

        it ("should be able to combine stat sets, in a non-mutative way") {
            val statSet2 = StatSet()
            statSet2.put(StatType.SPEED, DoubleStat(3.0))
            statSet2.put(StatType.SILENCE, IntStat(5))

            val out = statSet.combine(statSet2)

            assertTrue(statSet2 != statSet)
            assertTrue(statSet2 != statSet)
            assertEquals(statSet[StatType.SPEED]?.rawValue, 2.0)
            assertEquals(statSet[StatType.SILENCE]?.rawValue, 4)
            assertEquals(statSet2[StatType.SPEED]?.rawValue, 3.0)
            assertEquals(statSet2[StatType.SILENCE]?.rawValue, 5)
            assertEquals(out[StatType.SPEED]?.rawValue, 5.0)
            assertEquals(out[StatType.SILENCE]?.rawValue, 9)
        }
    }
})
