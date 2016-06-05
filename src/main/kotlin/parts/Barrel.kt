package parts

/**
 * Created by Andy on 6/5/2016.
 */
class Barrel : Part("Barrel") {

    companion object {
        var jointSilencer = Joint("Silencer", 2.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    }

    val attachmentSilencer = Part("Silencer", this)

    init {
        joints.addAll(arrayOf(jointSilencer))
        addAttachment(jointSilencer, attachmentSilencer)
    }
}