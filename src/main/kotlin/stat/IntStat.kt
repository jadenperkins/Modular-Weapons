package stat

/**
 * Created by Andy on 6/5/2016.
 */
class IntStat(val value: Int = 0) : Stat {

    override val rawValue: Any = value

    override fun nativeCombine(stat: Stat): IntStat {
        return IntStat((stat as IntStat).value + this.value)
    }

    override fun toString(): String {
        return "IntStat(value=$value)"
    }

}