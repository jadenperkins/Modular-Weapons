package stat

/**
 * Created by gtrpl on 6/5/2016.
 */
class StatInt(`val`: Int) : Stat<Int>(`val`) {
    override fun add(other: Stat<Int>): Stat<Int> = StatInt(this.get() + other.get())
}