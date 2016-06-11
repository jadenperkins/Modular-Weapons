package stat

import java.util.*

/**
 * Created by Jaden on 9/17/2015.
 */
class StatSet : Iterable<Map.Entry<StatBase<*>, Stat<*>>> {

    val statsRaw: HashMap<StatBase<*>, Stat<*>> = HashMap()

    fun <T> add(statBase: StatBase<T>, stat: Stat<T>): StatSet {
        //TODO: Possibly check to see if that StatBase already exists in the map
        statsRaw[statBase] = stat
        return this
    }

    fun <T> addVal(stat: StatBase<T>, value: T): StatSet = add(stat, stat.makeStatInstance(value))

    operator fun <T> get(statBase: StatBase<T>): Stat<T> {
        @Suppress("UNCHECKED_CAST") //https://kotlinlang.org/docs/reference/inline-functions.html#reified-type-parameters
        return statsRaw.getOrDefault(statBase, statBase.makeStatInstance()) as Stat<T>
    }

    operator fun <T> set(statBase: StatBase<T>, value: Stat<T>): StatSet {
        return add(statBase, value)
    }

    fun <A> value(key: StatBase<A>): A = this[key].get()

    fun copy(): StatSet {
        val set = StatSet()
        set.statsRaw.putAll(this.statsRaw)
        return set
    }

    fun combine(otherSet: StatSet): StatSet {
        val retSet = StatSet()
        statsRaw.forEach { statBase, stat ->
            retSet[statBase] = combine(statBase!!, stat!!, otherSet[statBase])
        }
        return retSet
    }

    fun <T> combine(statBase: StatBase<T>, first: Stat<T>, second: Stat<T>): Stat<T> {
        return statBase.makeStatInstance(statBase.combine(first.get(), second.get()))
    }

    override fun iterator(): Iterator<Map.Entry<StatBase<*>, Stat<*>>> = statsRaw.iterator()
}