package example

import parts.Joint
import parts.Part
import stat.StatDouble
import stat.StatInt
import stat.StatBase

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

        attachmentSilencer.stats.addVal(StatBase.SILENCE, 2)

        this.stats.addVal(StatBase.ACCURACY, 3.5)
        this.stats.addVal(StatBase.SPEED, -1.5)
        this.stats.addVal(StatBase.SILENCE, 1)

        addAttachment(jointSilencer, attachmentSilencer)
    }
}