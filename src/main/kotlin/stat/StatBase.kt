package stat

import java.util.function.Function

/**
 * Created by gtrpl on 6/5/2016.
 */
class StatBase<T>(val statName: String, val defaultValue: Stat<T>, private val generator: (T) -> Stat<T>) {
    fun from(`val`: T): Stat<T> = this.generator.invoke(`val`)

    companion object {

        val ACCURACY = StatBase("accuracy", StatDouble(0.0), { a -> StatDouble(a!!) })
        val SPEED = StatBase("speed", StatDouble(0.0), { a -> StatDouble(a!!) })
        val SILENCE = StatBase("silence", StatInt(0), { a -> StatInt(a!!) })
    }
}
