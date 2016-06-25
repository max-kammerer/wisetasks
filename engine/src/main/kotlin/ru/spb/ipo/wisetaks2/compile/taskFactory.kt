package ru.spb.ipo.wisetaks2.compile

import ru.spb.ipo.engine.task.*
import ru.spb.ipo.engine.task.ServerTask
import ru.spb.ipo.engine.utils.Parser
import ru.spb.ipo.engine.utils.RationalNumber
import ru.spb.ipo.wisetaks2.Task
import ru.spb.ipo.wisetaks2.Value

class KotlinTaskFactory : TaskFactory {

    override fun createServerTask(taskFile: String?, problemId: Long): ServerTask? {
        val task = compileAndGetTask(taskFile!!)
        return ServerTask(task, problemId)
    }

    override fun createClientTask(title: String?, description: String?, genParams: MutableMap<out Any?, out Any?>?, problemId: Long, buttons: MutableList<String>?): ClientTask? {
        throw UnsupportedOperationException()
    }

    override fun createXmlTask(fileName: String?): XmlTask? {
        throw UnsupportedOperationException()
    }
}

class ServerTask(val task: Task, val problemId: Long): ServerTask {

    override fun verify(ct: ClientTask): Boolean {
        //TODO process params
        val newParams = java.util.HashMap<String, String>()
        ct.genParams.forEach {
            val value = it.value
            when(value) {
                is Value<*> -> newParams.put(it.key as String, value.value.toString())
                else -> newParams.put(it.key as String, value.toString())
            }

        }

        val userAnswer = Preprocessor.insertParameters2Answer(ct.getAnswer(), newParams)
        val parser = Parser()
        val pvs: Array<out RationalNumber>? = parser.parseUserAnswer(userAnswer)

        return task.verifier!!().verify(pvs!![0])
    }

    override fun getTitle(): String? {
        return task.title
    }

    override fun getClientTask(): ClientTask? {
        val p = hashMapOf<String, Any?>()
        task.parameters.forEach {
            val v = it.values[0]
            it.text = v.text
            it.value = v.value
        }
        task.parameters.forEach{ p.put(it.name, Value(it.value, it.text))}
        return ClientTaskImpl(getTitle(), task.(task.description)(), p, problemId, arrayListOf<String>())
    }

    override fun getClientTaskWithParameters(parameters: MutableMap<out Any?, out Any?>?): ClientTask? {
        throw UnsupportedOperationException()
    }
}