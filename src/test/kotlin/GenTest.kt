
import com.jadencode.main.constants.Materials
import com.jadencode.main.constants.WeaponParts
import com.jadencode.main.generate.weapon.WeaponGenerator
import org.jetbrains.spek.api.Spek
import java.util.*

/**
 * Created by Andy on 6/5/2016.
 */
class GenTest : Spek({
    describe("a weapon") {
        Materials.load()
        WeaponParts.generateWeaponParts();
        val weapon = WeaponGenerator().generate(Random(), 10);
        it ("should have a name") {
            assert(weapon.displayName != null && !weapon.displayName.isEmpty())
        }

        it ("should have one or more parts") {
            assert(weapon.weaponParts.values.size >= 1)
        }

        it ("should have stats") {
            assert(weapon.statSet.statsRaw.values.size > 0)
        }
    }
})