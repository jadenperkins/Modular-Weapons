package stat

import java.util.*

/**
 * Created by Andy on 6/5/2016.
 */
class StatSet : HashMap<StatType, Stat>() {

    override fun put(key: StatType, value: Stat): Stat? {
        if (value.javaClass.kotlin != key.representativeStatClass) {
            throw IllegalArgumentException("Tried to set an invalid stat for this stat type")
        }
        return super.put(key, value)
    }

    /**
     * Creates a new StatSet from this and the supplied set. Stats that exist in both are combined
     */
    fun combine(other: StatSet): StatSet {
        val newSet = StatSet()
        newSet.putAll(this)
        for ((type, stat) in other) {
            val existingStat = newSet[type]
            if (existingStat != null) {
                newSet[type] = existingStat.combine(stat)
            } else {
                newSet.put(type, stat)
            }
        }
        return newSet
    }
}