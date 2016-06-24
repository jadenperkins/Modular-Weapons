
import com.jadencode.main.content.ContentLoader
import com.jadencode.main.generate.weapon.WeaponGenerator
import org.jetbrains.spek.api.Spek
import parts.Joint
import parts.Part
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Created by Andy on 6/5/2016.
 */
class RandomWeaponTest : Spek({
    describe("a weapon") {
        ContentLoader.load();
        val weapon = WeaponGenerator().generate(Random(11181993L), 10);

        it("should have a name") {
            assertNotNull(weapon.displayName)
        }
        it("should have info") {
            assertNotNull(weapon.displayInfo)
        }
        it("should have a weapon type") {
            assertNotNull(weapon.weaponType)
        }
        it("should have the correct number of parts") {
            assertEquals(weapon.weaponType.weaponPartTypes.size, weapon.partsList.size)
        }
        it("should have the correct part types") {
            for (partType in weapon.weaponType.weaponPartTypes) {
                assertEquals(partType, weapon.getPart(partType).weaponPart.type)
            }
        }
        it("should have a quality") {
            assertNotNull(weapon.qualityLevel)
        }
        it("should only have stats that the weapon type cares about") {
            assert(weapon.statSet.statsRaw.keys.containsAll(weapon.weaponType.statSet.statsRaw.keys))
        }
    }
})