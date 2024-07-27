package dev.nikomaru.mobballremake

import dev.nikomaru.mobballremake.listener.MobBallThrowListener
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.context.GlobalContext
import org.koin.dsl.module
import revxrsal.commands.bukkit.BukkitCommandHandler
import revxrsal.commands.ktx.supportSuspendFunctions

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

    fun setEvent() {
        server.pluginManager.registerEvents(MobBallThrowListener(), this)
    }

    fun setCommand() {
        val handler = BukkitCommandHandler.create(this)

        handler.setSwitchPrefix("--")
        handler.setFlagPrefix("--")
        handler.supportSuspendFunctions()

        handler.setHelpWriter { command, actor ->
            java.lang.String.format(
                """
                <color:yellow>command: <color:gray>%s
                <color:yellow>usage: <color:gray>%s
                <color:yellow>description: <color:gray>%s
                
                """.trimIndent(),
                command.path.toList(),
                command.usage,
                command.description,
            )
        }
    }
}