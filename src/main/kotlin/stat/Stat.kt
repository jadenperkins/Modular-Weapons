package stat

/**
 * Created by gtrpl on 6/5/2016.
 */
class Stat<T>(private var value: T, val clone: (T) -> T, val combine: (T, T) -> T) {

    fun get(): T = this.value

    fun <A : Stat<*>> `is`(c: Class<A>): Boolean = c.isAssignableFrom(this.javaClass)

    fun <A : Stat<*>> `as`(c: Class<A>): A = c.cast(this)

}