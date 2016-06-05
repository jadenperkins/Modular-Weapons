package stat

/**
 * Created by Andy on 6/5/2016.
 */
class DoubleStat(var value: Double = 0.0) : Stat {

    override val rawValue: Any = value

    override fun combine(stat: Stat): Stat {
        if (stat !is DoubleStat) {
            throw IllegalArgumentException()
        }
        return DoubleStat(stat.value + this.value)
    }

    override fun toString(): String {
        return "DoubleStat(value=$value)"
    }

}