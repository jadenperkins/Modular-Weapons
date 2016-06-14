package example

import parts.Joint
import parts.Part
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

        attachmentSilencer.stats.add(StatBase.SILENCE, 2)

        this.stats.add(StatBase.ACCURACY, 3.5)
        this.stats.add(StatBase.SPEED, -1.5)
        this.stats.add(StatBase.SILENCE, 1)

        addAttachment(jointSilencer, attachmentSilencer)
    }
}