package stat

/**
 * Created by Andy on 6/5/2016.
 */
interface Stat {

    fun combine(stat: Stat): Stat {
        if (this.javaClass.kotlin != stat.javaClass.kotlin) {
            throw IllegalArgumentException("Wrong stat type. Got: ${stat.javaClass.simpleName} expected: ${this.javaClass.simpleName}")
        }
        return nativeCombine(stat)
    }

    fun nativeCombine(stat: Stat): Stat;

    val rawValue: Any

}