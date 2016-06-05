package parts

import stat.StatSet
import java.util.*

/**
 * Created by Andy on 6/5/2016.
 */
open class Part(val name: String, var parentPart: Part? = null) {

    val joints: MutableList<Joint> = ArrayList()
    var attachments: MutableMap<Joint, Part> = HashMap()
    var stats: StatSet = StatSet()

    fun getCombinedStats(): StatSet {
        var latestStats = stats;
        for ((joint, part) in attachments) {
            latestStats = latestStats.combine(part.getCombinedStats())
        }
        return latestStats
    }

    fun isBase(): Boolean = parentPart == null

    fun addAttachment(joint: Joint, part: Part): Boolean {
        if (!joints.contains(joint)) {
            return false;
        } else if (attachments.containsKey(joint)) {
            return false;
        }
        part.parentPart = this
        attachments.put(joint, part)
        return true;
    }

    override fun toString(): String {
        var strings: List<String> = ArrayList()

        strings += "Part: $name, Parent: ${parentPart?.name}"
        if (joints.isNotEmpty()) {
            strings += "${joints.size} joints:"
        }
        for (joint in joints) {
            strings += "\tJoint: $joint"
        }
        if (attachments.isNotEmpty()) {
            strings += "${attachments.size} attachments:"
        }
        for ((joint, attachment) in attachments) {
            val attachSplit = attachment.toString().split("\n");
            for (attachLine in attachSplit) {
                strings += "\t$attachLine"
            }
        }
        val combinedStats = getCombinedStats()
        for ((statType, stat) in combinedStats) {
            strings += "\tCombined stats: [$statType] $stat"
        }

        return strings.joinToString("\n")
    }

}
