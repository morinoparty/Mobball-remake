package dev.nikomaru.mobballremake.utils.coroutines

import dev.nikomaru.mobballremake.MobBallRemake
import org.bukkit.plugin.java.JavaPlugin
import kotlin.coroutines.CoroutineContext

object DispatcherContainer {
    private var asyncCoroutine: CoroutineContext? = null
    private var syncCoroutine: CoroutineContext? = null

    /**
     * Gets the async coroutine context.
     */
    val async: CoroutineContext
        get() {
            if (asyncCoroutine == null) {
                asyncCoroutine = AsyncCoroutineDispatcher(JavaPlugin.getPlugin(MobBallRemake::class.java))
            }

            return asyncCoroutine!!
        }

    /**
     * Gets the sync coroutine context.
     */
    val sync: CoroutineContext
        get() {
            if (syncCoroutine == null) {
                syncCoroutine = MinecraftCoroutineDispatcher(JavaPlugin.getPlugin(MobBallRemake::class.java))
            }

            return syncCoroutine!!
        }
}