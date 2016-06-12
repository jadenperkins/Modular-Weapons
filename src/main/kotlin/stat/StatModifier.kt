package stat

class StatModifier<T>(val modify: (T, T) -> T) {

    companion object {
        val DOUBLE_ADD = StatModifier<Double>({ a, b -> a + b })
        val DOUBLE_MULTIPLY = StatModifier<Double>({ a, b -> a * b })
        val INT_ADD = StatModifier<Int>({ a, b -> a + b })
        val INT_MULTIPLY = StatModifier<Int>({ a, b -> a * b })
    }

}