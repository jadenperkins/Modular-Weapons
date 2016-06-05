package example

import parts.Joint
import parts.Part

/**
 * Created by Andy on 6/5/2016.
 */
class LowerReceiver : Part("Lower Receiver") {

    companion object {
        val jointBarrel = Joint("Barrel", 1.0, 0.0, 0.0)
        val jointStock = Joint("Stock", -1.0, 0.0, 0.0)
        val jointSight = Joint("Sight", 0.0, 1.0, 0.0)
        val jointMagazine = Joint("Magazine", 0.0, -1.0, 0.0)
    }

    private val attachmentBarrel = Barrel()
    private val attachmentStock = Part("Collapsible Stock", this)
    private val attachmentSight = Part("Red Dot Sight", this)
    private val attachmentMagazine = Part("Extended Magazine", this)

    init {
        joints.addAll(arrayOf(jointBarrel, jointStock, jointSight, jointMagazine))
        addAttachment(jointBarrel, attachmentBarrel)
        addAttachment(jointStock, attachmentStock)
        addAttachment(jointSight, attachmentSight)
        addAttachment(jointMagazine, attachmentMagazine)
    }

}
