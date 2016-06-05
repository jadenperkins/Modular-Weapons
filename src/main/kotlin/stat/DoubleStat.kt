package stat

/**
 * Created by Andy on 6/5/2016.
 */
class DoubleStat(var value: Double = 0.0) : Stat {

    override val rawValue: Any = value

    override fun nativeCombine(stat: Stat): DoubleStat {
        return DoubleStat((stat as DoubleStat).value + this.value)
    }

    override fun toString(): String {
        return "DoubleStat(value=$value)"
    }

}