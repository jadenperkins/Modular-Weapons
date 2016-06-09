package stat

/**
 * Created by gtrpl on 6/5/2016.
 */
abstract class Stat<T>(private val value: T) {
    fun get(): T = this.value

    fun <A : Stat<*>> `is`(c: Class<A>): Boolean = c.isAssignableFrom(this.javaClass)

    fun <A : Stat<*>> `as`(c: Class<A>): A = c.cast(this)

    abstract fun add(other: Stat<T>): Stat<T>
}