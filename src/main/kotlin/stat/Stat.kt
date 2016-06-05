package stat

/**
 * Created by Andy on 6/5/2016.
 */
interface Stat {

    fun combine(stat: Stat): Stat;

    val rawValue: Any

}