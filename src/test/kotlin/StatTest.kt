import org.jetbrains.spek.api.Spek
import stat.StatBase
import stat.StatSet
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Created by Andy on 6/5/2016.
 */
class StatTest : Spek({
    describe("stats") {

        val statSet = StatSet()

        statSet.addVal(StatBase.SPEED, 2.0)
        statSet.addVal(StatBase.SILENCE, 4)

        it("should be able to contain a map of stat types and the stat values") {
//            assertTrue(statSet[StatBase.SPEED] is StatDouble)
//            assertTrue(statSet[StatBase.SILENCE] is StatInt)
            assertEquals(statSet[StatBase.SPEED]?.get(), 2.0)
            assertEquals(statSet[StatBase.SILENCE]?.get(), 4)
        }

//        it("should only accept stats of the expect type for that stat type") {
//            assertFailsWith(IllegalArgumentException::class, {
//                statSet.addVal(StatBase.SPEED, 2)
//            })
//            assertFailsWith(IllegalArgumentException::class, {
//                statSet.addVal(StatBase.SILENCE, 2.0)
//            })
//        }

        it ("should be able to combine stats effectively, in a non-mutative way") {
//            val stat1 = StatInt(2)
//            val stat2 = StatInt(3)
//            val out = stat1.add(stat2)

//            assertTrue(stat1 != out)
//            assertTrue(stat2 != out)
//            assertEquals(stat1.get(), 2)
//            assertEquals(stat2.get(), 3)
//            assertEquals(out.get(), 5)
        }

//        it ("should be able to combine stat sets, in a non-mutative way") {
//            val statSet2 = StatSet()
//            statSet2.addVal(StatBase.SPEED, 3.0)
//            statSet2.addVal(StatBase.SILENCE, 5)
//
//            val out = statSet.combine(statSet2)
//
//            assertTrue(statSet2 != statSet)
//            assertTrue(statSet2 != statSet)
//            assertEquals(statSet[StatBase.SPEED]?.get(), 2.0)
//            assertEquals(statSet[StatBase.SILENCE]?.get(), 4)
//            assertEquals(statSet2[StatBase.SPEED]?.get(), 3.0)
//            assertEquals(statSet2[StatBase.SILENCE]?.get(), 5)
//            assertEquals(out[StatBase.SPEED]?.get(), 5.0)
//            assertEquals(out[StatBase.SILENCE]?.get(), 9)
//        }
    }
})
