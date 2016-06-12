package stat

import java.util.*

/**
 * Created by Jaden on 9/17/2015.
 */
class StatSet : Iterable<Map.Entry<StatBase<*>, Any>> {

    val statsRaw: MutableMap<StatBase<*>, Any> = HashMap()

    fun <T> add(statBase: StatBase<T>, value: T): StatSet {
        //TODO: Possibly check to see if that StatBase already exists in the map
        statsRaw.put(statBase, statBase.makeStat(value) as Any)
        return this
    }

    operator fun <T> get(statBase: StatBase<T>): T {
        @Suppress("UNCHECKED_CAST") //https://kotlinlang.org/docs/reference/inline-functions.html#reified-type-parameters
        return statsRaw[statBase] as T
    }

    operator fun <T> set(statBase: StatBase<T>, value: T): StatSet {
        return add(statBase, value)
    }

    fun <T> getModifiedValue(statBase: StatBase<T>): T {
        return statBase.getModifiedValue(this)
    }

    fun copy(): StatSet {
        val set = StatSet()
        set.statsRaw.putAll(this.statsRaw)
        return set
    }

    fun combine(otherSet: StatSet): StatSet {
        val retSet = StatSet()
        for ((statBase, value) in statsRaw) {
            @Suppress("UNCHECKED_CAST")
            retSet[statBase as StatBase<Any>] = statBase.combine(value, otherSet[statBase])
        }
        return retSet
    }

    override fun iterator(): Iterator<Map.Entry<StatBase<*>, Any>> = statsRaw.iterator()
}