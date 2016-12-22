package ru.spb.ipo.wisetaks2

import ru.spb.ipo.engine.utils.RationalNumber
import java.math.BigInteger

/**
 * Created by mike on 10/14/14.
 */
abstract class SourceSetBasedVerifier<E> : Verifier() {
    protected var filter by d<(E) -> Boolean>()

    var sourceSet by d<SourceSet<E>>()

    fun filter(p : (E) -> Boolean) {
        filter = p
    }
}

class CountVerifier<E> : SourceSetBasedVerifier<E>() {

    override fun verify(result: ru.spb.ipo.engine.utils.RationalNumber): Boolean {
        val iterator = sourceSet.iterator()
        var vResult = 0L
        for (i in iterator) {
            if (filter(i)) {
                vResult++
            }
        }
        print(vResult)
        return RationalNumber(BigInteger.valueOf(vResult)) == result
    }
}

abstract class Verifier : ParameterContainer() {

    abstract fun verify(result: ru.spb.ipo.engine.utils.RationalNumber = RationalNumber(0)): Boolean
}