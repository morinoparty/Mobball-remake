package dev.nikomaru.mobballremake.listener

import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent
import dev.nikomaru.mobballremake.MobBallRemake
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.persistence.PersistentDataType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MobBallThrowListener: Listener, KoinComponent {
    private val plugin : MobBallRemake by inject()

    companion object {
        const val MOB_BALL_KEY = "mob_ball:ball_type"
    }
    @EventHandler
    fun onMobBallThrow(event: PlayerLaunchProjectileEvent) {
        // Handle the event here
        val snowball = event.itemStack
        val pdc = snowball.itemMeta?.persistentDataContainer ?: return
        if (pdc.has(NamespacedKey.fromString(MOB_BALL_KEY)!!, PersistentDataType.STRING)) {
            val type = pdc.get(NamespacedKey.fromString(MOB_BALL_KEY)!!, PersistentDataType.STRING)
            val player = event.player
            plugin.logger.info("Player ${player.name} threw a $type mob ball.")
            player.sendRichMessage("<color:red> 現在この機能は利用できません。今しばらくお待ちください。ご迷惑をお掛けして申し訳ございません。")
            event.isCancelled = true
        }
    }
}