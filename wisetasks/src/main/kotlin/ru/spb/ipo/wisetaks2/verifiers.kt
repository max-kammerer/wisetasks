package ru.spb.ipo.wisetaks2

import ru.spb.ipo.engine.utils.RationalNumber
import java.math.BigInteger

/**
 * Created by mike on 10/14/14.
 */
public abstract open class SourceSetBasedVerifier<E, T : SourceSet<E>> : Verifier() {
    var sourceSet by d<T>()
}

public class CountVerifier<E, T : SourceSet<E>> : SourceSetBasedVerifier<E, T>() {
    var filter by d<(E) -> Boolean>()

    override fun verify(result: ru.spb.ipo.engine.utils.RationalNumber): Boolean {
        val iterator = sourceSet.iterator()
        var vResult = 0L;
        for (i in iterator) {
            if (filter(i)) {
                vResult++
            }
        }
        print(vResult)
        return RationalNumber(BigInteger.valueOf(vResult)) == result;
    }
}

abstract public class Verifier : ParameterContainer() {

    abstract fun verify(result: ru.spb.ipo.engine.utils.RationalNumber = RationalNumber(0)): Boolean
}

class A<T>

class B<T> {
    val b: T? = null
}

fun <T> test( init : A<T>.() -> T) {
    A<T>().init()
}

fun main3(args: Array<String>) {
    test { A<String>.() ->
      "123"
    }
}