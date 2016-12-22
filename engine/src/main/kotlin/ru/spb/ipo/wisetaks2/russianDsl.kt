package ru.spb.ipo.wisetaks2

import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1

fun задача(init: Task.() -> Unit): Task {
    return task(init)
}

fun Task.описание(init: ParameterContainer.() -> String): ParameterContainer {
    return description(init)
}

inline fun <reified T: Verifier> Task.верификатор(crossinline init: T.() -> Unit) {
    return verifier(init)
}

fun <T : Any> ParameterContainer.параметр(name: String, init: Parameter<T>.() -> Unit) {
    return parameter(name, init)
}

fun <T: Any> ParameterContainer.параметр(name: String, vararg values: T) : T {
    return parameter(name, *values)
}

fun <T: Any> ParameterContainer.параметр(name: String, vararg values: Value<T>): Parameter<T> {
    return parameter(name, *values)
}

fun <T : Any> Parameter<T>.значение(init: Value<T>.() -> T): Value<T> {
    return value(init)
}

val <T : Any> Parameter<T>.текст by DVal(Parameter<T>::text)

val <T : Any> Parameter<T>.значение: T
        get() = this.value

var Task.название by DVar(Task::title)

var <E> SourceSetBasedVerifier<E>.исходноеМножество: SourceSet<E> by DVar(SourceSetBasedVerifier<E>::sourceSet)

class DVar<T, R, P: KMutableProperty1<T, R>>(val kmember: P) {
    operator fun getValue(t: T, p: KProperty<*>): R {
        return kmember.get(t)
    }

    operator fun setValue(t: T, p: KProperty<*>, s: R) {
        kmember.set(t, s)
    }
}

class DVal<T, R, out P: KProperty1<T, R>>(val kmember: P) {
    operator fun getValue(t: T, p: KProperty<*>): R {
        return kmember.get(t)
    }
}