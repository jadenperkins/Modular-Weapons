package stat

import java.util.function.Function

/**
 * Created by gtrpl on 6/5/2016.
 */
class StatBase<T>(val statName: String, val defaultValue: Stat<T>, private val generator: (T) -> Stat<T>) {
    fun from(`val`: T): Stat<T> = this.generator.invoke(`val`)

    companion object {

        val DAMAGE_SLASH = StatBase("damageSlash", StatFloat(0f), { a -> StatFloat(a!!) })
        val DAMAGE_PIERCE = StatBase("damagePierce", StatFloat(0f), { a -> StatFloat(a!!) })
        val DAMAGE_BLUNT = StatBase("damageBlunt", StatFloat(0f), { a -> StatFloat(a!!) })
    }
}
