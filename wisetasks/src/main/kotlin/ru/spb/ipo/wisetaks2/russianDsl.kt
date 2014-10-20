package ru.spb.ipo.wisetaks2

import ru.spb.ipo.engine.sets
import ru.spb.ipo.engine.sets.SetIterator
import java.util.ArrayList
import kotlin.reflect.KMemberProperty
import kotlin.reflect.KMutableMemberProperty

fun задача(init: Task.() -> Unit): Task {
    return task(init)
}

fun Task.описание(init: ParameterContainer.() -> String): ParameterContainer {
    return description(init)
}

fun <T> Task.верификатор(clazz: Class<T>, init: T.() -> Unit) {
    return verifier(clazz, init)
}

fun ParameterContainer.параметр<T>(name: String, init: Parameter<T>.() -> Unit) {
    return parameter(name, init)
}

fun ParameterContainer.параметр<T>(name: String, vararg values: T) : T {
    return parameter(name, *values)
}

fun ParameterContainer.параметр<T>(name: String, vararg values: Value<T>): Parameter<T> {
    return parameter(name, *values)
}

fun <T> Parameter<T>.значение(init: Value<T>.() -> T): Value<T> {
    return value(init)
}

val <T> Parameter<T>.текст by DVal(Parameter<T>::text)

val <T> Parameter<T>.значение: T
        get() = this.value

var Task.название by DVar(Task::title)

var <E, T: SourceSet<E>> SourceSetBasedVerifier<E, T>.исходноеМножество: T by DVar(SourceSetBasedVerifier<E, T>::sourceSet)

class DVar<T, R, P: KMutableMemberProperty<T, R>>(val kmember: P) {
    fun get(t: T, p: PropertyMetadata): R {
        return kmember.get(t)
    }

    fun set(t: T, p: PropertyMetadata, s: R) {
        kmember.set(t, s)
    }
}

class DVal<T, R, P: KMemberProperty<T, R>>(val kmember: P) {
    fun get(t: T, p: PropertyMetadata): R {
        return kmember.get(t)
    }
}