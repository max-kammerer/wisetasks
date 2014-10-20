package ru.spb.ipo.wisetaks2.compile

import org.jetbrains.jet.lang.parsing.JetScriptDefinition
import org.jetbrains.jet.config.CompilerConfiguration
import org.jetbrains.jet.cli.common.CLIConfigurationKeys
import org.jetbrains.jet.config.CommonConfigurationKeys
import org.jetbrains.jet.cli.jvm.JVMConfigurationKeys
import org.jetbrains.jet.cli.common.messages.MessageCollector
import org.jetbrains.jet.cli.common.messages.MessageCollectorPlainTextToStream
import com.intellij.openapi.util.Disposer
import org.jetbrains.jet.lang.resolve.AnalyzerScriptParameter
import org.jetbrains.jet.codegen.ScriptCodegen
import org.jetbrains.jet.cli.jvm.compiler.JetCoreEnvironment
import org.jetbrains.jet.lang.parsing.JetScriptDefinitionProvider
import org.jetbrains.jet.cli.jvm.compiler.KotlinToJVMBytecodeCompiler
import org.jetbrains.jet.codegen.state.GenerationState
import org.jetbrains.jet.utils.PathUtil
import org.jetbrains.jet.utils.KotlinPathsFromHomeDir
import org.jetbrains.jet.lang.resolve.name.FqName
import org.jetbrains.jet.lang.resolve.ScriptNameUtil
import org.jetbrains.jet.codegen.GeneratedClassLoader
import java.net.URLClassLoader
import java.net.URL
import kotlin.modules.AllModules
import ru.spb.ipo.wisetaks2.Task
import java.io.File


fun compile(taskPath: String): Class<*> {
    val messageCollector = MessageCollectorPlainTextToStream.PLAIN_TEXT_TO_SYSTEM_ERR
    val rootDisposable = Disposer.newDisposable()

    val configuration = CompilerConfiguration()
    val file = PathUtil.getJarPathForClass(javaClass<PathUtil>())

    val classpath = System.getProperty("java.class.path");
    val classpathEntries = classpath.split(File.pathSeparator);

    configuration.put<MessageCollector>(CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY, messageCollector)
    configuration.add<String>(CommonConfigurationKeys.SOURCE_ROOTS_KEY, taskPath)
    //configuration.addAll(JVMConfigurationKeys.CLASSPATH_KEY, PathUtil.getJdkClassesRoots())
    configuration.addAll(JVMConfigurationKeys.CLASSPATH_KEY, classpathEntries.map { File(it) })
    configuration.add(JVMConfigurationKeys.CLASSPATH_KEY, file)

    val environment = JetCoreEnvironment.createForProduction(rootDisposable, configuration)
    JetScriptDefinitionProvider.getInstance(environment.getProject()).markFileAsScript(environment.getSourceFiles().get(0))

    val state = KotlinToJVMBytecodeCompiler.analyzeAndGenerate(environment)
    val nameForScript: FqName = ScriptNameUtil.classNameForScript(environment.getSourceFiles().get(0).getScript())
    val classLoader = GeneratedClassLoader(state.getFactory(), URLClassLoader(array<URL>(file.toURI().toURL()), javaClass<AllModules>().getClassLoader()));
    return classLoader.loadClass(nameForScript.asString())

}

fun compileAndGetTask(taskPath: String): Task {
    val clazz = compile(taskPath)
    val result = clazz.getDeclaredField("rv")
    return result.get(clazz.newInstance()) as Task
}

fun main(args: Array<String>) {
    compileAndGetTask("/home/mike/devel/projects/opro/wisetasks/wisetasks/src/main/kotlin/ru/spb/ipo/wisetaks2/tasks/en/bilets.kts/bilets.kts")
}