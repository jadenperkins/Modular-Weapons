package stat

import kotlin.reflect.KClass

/**
 * Created by Andy on 6/5/2016.
 */
enum class StatType(val representativeStatClass: KClass<out Stat>) {
    SPEED(DoubleStat::class),
    ACCURACY(DoubleStat::class),
    SILENCE(IntStat::class),
    CAPACITY(IntStat::class)
}