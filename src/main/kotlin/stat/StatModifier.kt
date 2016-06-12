package stat

class StatModifier<T>(val priority: Int, val modify: (T, T) -> T) {

    companion object {
        val DOUBLE_ADD = StatModifier<Double>(1, { a, b -> a + b })
        val DOUBLE_MULTIPLY = StatModifier<Double>(0, { a, b -> a * b })
        val INT_ADD = StatModifier<Int>(1, { a, b -> a + b })
        val INT_MULTIPLY = StatModifier<Int>(0, { a, b -> a * b })
    }

}