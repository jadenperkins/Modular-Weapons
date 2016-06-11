package stat

import java.util.*

/**
 * Created by gtrpl on 6/5/2016.
 */
class StatBase<T>(val name: String,
                  val defaultValue: T,
                  val clone: (T) -> T,
                  val combine: (T, T) -> T) {

    init {
        ALL_STATS.add(this)
    }

    fun makeStatInstance(value: T = defaultValue): Stat<T> {
        return Stat(clone(value), clone, combine)
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
    }
}
