package stat

import java.util.*

/**
 * Created by gtrpl on 6/5/2016.
 */
//T is the class of the actual stat value
class StatBase<T>(val name: String,
                  val defaultValue: T,
                  private val clone: (T) -> T,
                  val combine: (T, T) -> T,
                  vararg val modifiers: Pair<StatBase<T>, StatModifier<T>>) {

    init {
        ALL_STATS.add(this)
    }

    fun getModifiedValue(statSet: StatSet): T {
        val currentValue = if (statSet.has(this)) statSet[this] else defaultValue
        var modifiedValue = clone(currentValue)
        modifiers.sortBy { it.second.priority }
        for ((otherStatBase, modifier) in modifiers) {
            modifiedValue = modifier.modify(modifiedValue, otherStatBase.getModifiedValue(statSet))
        }
        return modifiedValue
    }

    fun makeStat(value: T = defaultValue): T {
        return clone(value)
    }

    companion object {
        val ALL_STATS = ArrayList<StatBase<*>>()

        var DOUBLE_CLONE = { it: Double -> it }
        val DOUBLE_COMBINE = { a: Double, b: Double -> (a + b) }
        val INT_CLONE = { it: Int -> it }
        val INT_COMBINE = { a: Int, b: Int -> (a + b) }

        val ACCURACY = StatBase("Accuracy", 0.0, DOUBLE_CLONE, DOUBLE_COMBINE)
        val SPEED = StatBase("Speed", 0.0, DOUBLE_CLONE, DOUBLE_COMBINE)
        val SILENCE = StatBase("Silence", 0, INT_CLONE, INT_COMBINE)
        val SPELL_POWER = StatBase("Spell Power", 1.0, DOUBLE_CLONE, DOUBLE_COMBINE)

        val POWER = StatBase("Power", 1.0, DOUBLE_CLONE, DOUBLE_COMBINE,
                Pair(SPELL_POWER, StatModifier.DOUBLE_ADD),
                Pair(ACCURACY, StatModifier.DOUBLE_MULTIPLY)
        )
    }
}

