package ru.spb.ipo.wisetaks2

import ru.spb.ipo.engine.elements.Element
import ru.spb.ipo.engine.sets.Set
import ru.spb.ipo.engine.sets.SetIterator
import java.util.*
import kotlin.properties.Delegates

/**
 * Created by mike on 10/14/14.
 */

fun <T : Any> d() = Delegates.notNull<T>()

operator fun Element.get(index: Int): Element {
    return this.getElementAt(index)!!
}

abstract class SourceSet<T> {
    abstract fun iterator(): Iterator<T>
}

class Value<T>(var value: T = null as T, var text: String? = null)

class Parameter<T : Any>(val name: String) {
    val values = arrayListOf<Value<T>>()
    var text: String? = null
    lateinit var value: T
}

open class ParameterContainer {
    var parameters = arrayListOf<Parameter<Any>>()
}

class Task : ParameterContainer() {

    var title by d<String>()

    var description by d<ParameterContainer.() -> String>()

    var verifier: (() -> Verifier)? = null

}

fun task(init: Task.() -> Unit): Task {
    val task = Task()
    task.init()
    return task
}

fun Task.description(init: ParameterContainer.() -> String): ParameterContainer {
    val parameter = ParameterContainer()
    this.description = init
    return parameter
}

inline fun <reified V : Verifier> Task.verifier(crossinline init: V.() -> Unit) {
    this.verifier = {
        val newInstance = V::class.java.newInstance()
        newInstance.init()
        newInstance
    }
}

fun <T : Any> ParameterContainer.parameter(name: String, init: Parameter<T>.() -> Unit) {
    val parameter = Parameter<T>(name)
    parameter.init()
    parameters.add(parameter as Parameter<Any>)
}

fun <T : Any> ParameterContainer.parameter(name: String, vararg values: T): T {
    val parameter = Parameter<T>(name)
    for (i in values) {
        parameter.values.add(Value(i))
    }

    parameters.add(parameter as Parameter<Any>)
    return parameter.values[0].value as T
}

fun <T : Any> ParameterContainer.parameter(name: String, vararg values: Value<T>): Parameter<T> {
    val parameter = Parameter<T>(name)
    for (i in values) {
        parameter.values.add(i)
    }
    parameters.add(parameter as Parameter<Any>)
    return parameter
}

fun <T : Any> Parameter<T>.value(init: Value<T>.() -> T): Value<T> {
    val value = Value<T>()
    val pValue = value.init()
    value.value = pValue
    return value
}

fun <E> Set.toSet(): SourceSet<E> {
    val setIterator: SetIterator = this.iterator()
    return object : SourceSet<E>() {
        override fun iterator(): Iterator<E> {
            return object : Iterator<E> {
                override fun next(): E {
                    return setIterator.next() as E
                }

                override fun hasNext(): Boolean {
                    return setIterator.hasNext()
                }
            }
        }
    }
}

fun <S : Set> S.times(times: Int): List<Set> {
    val result: ArrayList<Set> = arrayListOf()
    for (i in 1..times) {
        result.add(this.clone())
    }
    return result

}