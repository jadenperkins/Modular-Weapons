package stat

/**
 * Created by Andy on 6/5/2016.
 */
class IntStat(val value: Int = 0) : Stat {

    override val rawValue: Any = value

    override fun combine(stat: Stat): IntStat {
        if (stat !is IntStat) {
            throw IllegalArgumentException()
        }
        return IntStat(stat.value + this.value)
    }

    override fun toString(): String{
        return "IntStat(value=$value)"
    }

}