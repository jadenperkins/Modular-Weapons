package stat

/**
 * Created by gtrpl on 6/5/2016.
 */
class StatFloat(`val`: Float) : Stat<Float>(`val`) {
    override fun add(other: Stat<Float>): Stat<Float> = StatFloat(this.get() + other.get())
}