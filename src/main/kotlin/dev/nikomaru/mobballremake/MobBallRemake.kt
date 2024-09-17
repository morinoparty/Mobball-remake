package dev.nikomaru.mobballremake

import dev.nikomaru.mobballremake.listener.MobBallThrowListener
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import org.incendo.cloud.execution.ExecutionCoordinator
import org.incendo.cloud.paper.LegacyPaperCommandManager
import org.incendo.cloud.setting.ManagerSetting
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

open class MobBallRemake : JavaPlugin() {

    companion object {
        lateinit var plugin: MobBallRemake
            private set
    }
    override fun onEnable() {
        // Plugin startup logic
        plugin = this
        setCommand()
        setEvent()
        setupKoin()
    }

    private fun setupKoin() {
        val appModule = module {
            single<MobBallRemake> { this@MobBallRemake }
        }

        GlobalContext.getOrNull() ?: GlobalContext.startKoin {
            modules(appModule)
        }
    }
    override fun onDisable() {
        // Plugin shutdown logic
    }

    private fun setEvent() {
        server.pluginManager.registerEvents(MobBallThrowListener(), this)
    }

    fun setCommand() {
        val commandManager = LegacyPaperCommandManager.createNative(
            this,
            ExecutionCoordinator.simpleCoordinator()
        )

        commandManager.settings().set(ManagerSetting.ALLOW_UNSAFE_REGISTRATION, true)

        val annotationParser =
            org.incendo.cloud.annotations.AnnotationParser(commandManager, CommandSender::class.java)

        annotationParser.parse()
    }
}