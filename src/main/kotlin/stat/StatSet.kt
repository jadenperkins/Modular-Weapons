package stat

import java.util.*

/**
 * Created by Jaden on 9/17/2015.
 */
class StatSet {
    val statsRaw: HashMap<StatBase<*>, Stat<*>> = HashMap()

    fun <T> add(stat: StatBase<T>, s: Stat<T>): StatSet {
        this.statsRaw[stat] = s
        return this
    }

    fun <T> addVal(stat: StatBase<T>, `val`: T): StatSet = this.add(stat, stat.from(`val`))

    operator fun <A> get(key: StatBase<A>): Stat<A> = key.defaultValue.javaClass.cast(this.statsRaw.getOrDefault(key, key.defaultValue))

    fun <A> value(key: StatBase<A>): A = this[key].get()

    fun copy(): StatSet {
        val set = StatSet()
        set.statsRaw.putAll(this.statsRaw)
        return set
    }
}