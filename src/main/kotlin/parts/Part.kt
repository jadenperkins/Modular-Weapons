package parts

import java.util.*

/**
 * Created by Andy on 6/5/2016.
 */
open class Part(val name: String, var parentPart: Part? = null) {

    val joints: MutableList<Joint> = ArrayList()
    var attachments: MutableMap<Joint, Part> = HashMap()

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
        var str = "Part: $name, Parent: ${parentPart?.name}"
        if (joints.isNotEmpty()) {
            str += "\n${joints.size} joints:"
        }
        for (joint in joints) {
            str += "\n\tJoint: $joint"
        }
        if (attachments.isNotEmpty()) {
            str += "\n${attachments.size} attachments:\n"
        }
        for ((joint, attachment) in attachments) {
            val attachSplit = attachment.toString().split("\n");
            for (attachLine in attachSplit) {
                str += "\t$attachLine\n"
            }
        }
        return str
    }

}
