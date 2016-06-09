package stat

/**
 * Created by gtrpl on 6/5/2016.
 */
class StatDouble(`val`: Double) : Stat<Double>(`val`) {
    override fun add(other: Stat<Double>): Stat<Double> = StatDouble(this.get() + other.get())
}