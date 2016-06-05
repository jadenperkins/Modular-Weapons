package example

import parts.Joint
import parts.Part
import stat.DoubleStat
import stat.IntStat
import stat.StatType

/**
 * Created by Andy on 6/5/2016.
 */
class Barrel : Part("Barrel") {

    companion object {
        var jointSilencer = Joint("Silencer", 2.0, 0.0, 0.0)
    }

    val attachmentSilencer = Part("Silencer", this)

    init {
        joints.addAll(arrayOf(jointSilencer))

        attachmentSilencer.stats.put(StatType.SILENCE, IntStat(2))

        this.stats.put(StatType.ACCURACY, DoubleStat(3.5))
        this.stats.put(StatType.SPEED, DoubleStat(-1.5))
        this.stats.put(StatType.SILENCE, IntStat(1))

        addAttachment(jointSilencer, attachmentSilencer)
    }
}