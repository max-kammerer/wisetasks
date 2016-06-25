package ru.spb.ipo.wisetaks2.compile

import com.intellij.openapi.util.Disposer
import jdk.internal.org.objectweb.asm.ClassReader
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.cli.common.messages.PrintingMessageCollector
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler
import org.jetbrains.kotlin.cli.jvm.config.JVMConfigurationKeys
import org.jetbrains.kotlin.cli.jvm.config.addJvmClasspathRoot
import org.jetbrains.kotlin.cli.jvm.config.addJvmClasspathRoots
import org.jetbrains.kotlin.cli.jvm.repl.ReplInterpreter
import org.jetbrains.kotlin.codegen.GeneratedClassLoader
import org.jetbrains.kotlin.codegen.state.GenerationStateEventCallback
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.addKotlinSourceRoot
import org.jetbrains.kotlin.resolve.diagnostics.DiagnosticSuppressor
import org.jetbrains.kotlin.script.KotlinScriptDefinitionProvider
import org.jetbrains.kotlin.script.ScriptNameUtil
import org.jetbrains.kotlin.script.StandardScriptDefinition
import org.jetbrains.kotlin.util.ExtensionProvider
import org.jetbrains.kotlin.utils.PathUtil
import org.jetbrains.org.objectweb.asm.util.Textifier
import ru.spb.ipo.wisetaks2.Task
import java.io.File
import java.io.FileReader
import java.net.URLClassLoader


fun compile(taskPath: String): Class<*> {
    val classpathEntries = System.getProperty("java.class.path").split(File.pathSeparator);

    val configuration = CompilerConfiguration().apply {
        put<MessageCollector>(CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY, PrintingMessageCollector.PLAIN_TEXT_TO_SYSTEM_ERR)
        addKotlinSourceRoot(taskPath)
        addJvmClasspathRoots(classpathEntries.map { File(it) })
        addJvmClasspathRoot(PathUtil.getPathUtilJar())
        put(JVMConfigurationKeys.MODULE_NAME, "wisetasks")
    }

    val disposable = Disposer.newDisposable()
    val environment = KotlinCoreEnvironment.createForProduction(disposable, configuration, EnvironmentConfigFiles.JVM_CONFIG_FILES)
    KotlinScriptDefinitionProvider.getInstance(environment.project).addScriptDefinition(StandardScriptDefinition)
    ExtensionProvider.create(DiagnosticSuppressor.EP_NAME)

//    val compiledScript = KotlinToJVMBytecodeCompiler.compileScript(configuration, PathUtil.getKotlinPathsForCompiler(), environment)
//    return compiledScript!!
    val state = KotlinToJVMBytecodeCompiler.analyzeAndGenerate(environment, GenerationStateEventCallback.DO_NOTHING)!!
    val nameForScript = ScriptNameUtil.generateNameByFileName(environment.getSourceFiles()[0].script!!.name!!, "kts")
    val classLoader = GeneratedClassLoader(state.factory, ClassLoader.getSystemClassLoader());
    return classLoader.loadClass(nameForScript)
}

fun compileAndGetTask(taskPath: String): Task {
    val clazz = compile(taskPath)
    val result = clazz.getDeclaredMethod("getзадача")
    return result.invoke(clazz.getConstructor(Array<String>::class.java).newInstance(arrayOf<String>())) as Task
}

fun main(args: Array<String>) {
    compileAndGetTask("en/bilets.kts/bilets.kts")
}