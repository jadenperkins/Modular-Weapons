
import com.jadencode.main.content.ContentLoader
import com.jadencode.main.generate.item.ItemGenerator
import org.jetbrains.spek.api.Spek
import parts.Joint
import parts.Part
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Created by Andy on 6/5/2016.
 */
class RandomItemTest : Spek({
    describe("an item") {
        ContentLoader.load();

        val item = ItemGenerator().generate(Random(11181993L), 10);

        it("should have a name") {
            assertNotNull(item.displayName)
        }
        it("should have info") {
            assertNotNull(item.displayInfo)
        }
        it("should have an item type") {
            assertNotNull(item.itemType)
        }
        it("should have a quality") {
            assertNotNull(item.qualityLevel)
        }
        it("should only have stats that the item type cares about") {
            assert(item.statSet.statsRaw.keys.containsAll(item.itemType.statSet.statsRaw.keys))
        }
    }
})