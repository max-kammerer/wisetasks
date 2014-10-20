package ru.spb.ipo.wisetaks2

import kotlin.properties.Delegates
import java.util.ArrayList
import ru.spb.ipo.engine.sets.SetIterator
import ru.spb.ipo.engine.elements.Element
import ru.spb.ipo.engine.elements.ContainerElement
import ru.spb.ipo.wisetaks2.annotations.translate
import ru.spb.ipo.engine.sets

/**
 * Created by mike on 10/14/14.
 */

fun d<T>() = Delegates.notNull<T>()

public fun Element.get(index: Int): Element {
    return this.getElementAt(index)!!
}

//public fun Element<T>.iterator(): Iterator<T> {
//    object: Iterator<T> {
//
//    }
//}

abstract public class SourceSet<T> {
    abstract fun iterator() : Iterator<T>
}

class Value<T>(var value: T = null as T, var text: String? = null)

class Parameter<T>(val name: String) {
    val values = arrayListOf<Value<T>>()
    var text: String? = null
    var value: T = null
}

open class ParameterContainer() {
    var parameters = arrayListOf<Parameter<Any>>()

}

class Task : ParameterContainer() {

    var title by d<String>()

    var description by d<ParameterContainer.() -> String>()

    public var verifier: (()-> Verifier)? = null

}

fun task(init: Task.() -> Unit): Task {
    val task = Task()
    task.init()
    return task;
}

fun Task.description(init: ParameterContainer.() -> String): ParameterContainer {
    val parameter = ParameterContainer()
    this.description = init
    return parameter;
}

fun <T> Task.verifier(clazz: Class<T>, init: T.() -> Unit) {
    this.verifier = {
        val newInstance = clazz.newInstance()
        newInstance.init();
        newInstance as Verifier
    }
}

fun ParameterContainer.parameter<T>(name: String, init: Parameter<T>.() -> Unit) {
    val parameter = Parameter<T>(name)
    parameter.init()
    parameters.add(parameter as Parameter<Any>)
}

fun ParameterContainer.parameter<T>(name: String, vararg values: T) : T {
    val parameter = Parameter<T>(name)
    for (i in values) {
        parameter.values.add(Value(i))
    }

    parameters.add(parameter as Parameter<Any>)
    return parameter.values[0].value
}

fun ParameterContainer.parameter<T>(name: String, vararg values: Value<T>): Parameter<T> {
    val parameter = Parameter<T>(name)
    for (i in values) {
        parameter.values.add(i)
    }
    parameters.add(parameter as Parameter<Any>)
    return parameter
}

fun <T> Parameter<T>.value(init: Value<T>.() -> T): Value<T> {
    val value = Value<T>()
    val pValue = value.init()
    value.value = pValue
    return value
}

fun <V: sets.Set, T> V.toSet(): SourceSet<T> {
    val setIterator: SetIterator = this.iterator()!!
    return object : SourceSet<T>() {
        override fun iterator(): Iterator<T> {
            return object : Iterator<T> {
                override fun next(): T {
                    return setIterator.next() as T
                }
                override fun hasNext(): Boolean {
                    return setIterator.hasNext()
                }
            }
        }
    }
}

fun <T : sets.Set> T.times(times: Int): List<sets.Set?> {
    val result: ArrayList<sets.Set?> = arrayListOf()
    for (i in 1..times) {
        result.add(this.clone())
    }
    return result;

}