package pl.aftermc.chat.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.aftermc.chat.AfterChat;
import pl.aftermc.chat.object.User;
import pl.aftermc.chat.util.ChatUtil;
import pl.aftermc.chat.util.TimeUtil;

public final class PlayerChat implements Listener {

    public PlayerChat(final AfterChat plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private final AfterChat plugin;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        if(event.isCancelled()) return;
        Player player = event.getPlayer();
        User user = this.plugin.getUserData().getOrCreate(player);

        if(!player.hasPermission("achat.nochatoff")) {
            if(!this.plugin.getPluginConfiguration().chatEnable) {
                event.setCancelled(true);
                ChatUtil.sendMessage(player, this.plugin.getMessageConfiguration().chatIsDisable);
                return;
            }
        }
        if(!player.hasPermission("achat.nocooldown")){
            if(!user.isWriteChat()) {
                event.setCancelled(true);
                ChatUtil.sendMessage(player, this.plugin.getMessageConfiguration().chatCooldown.replace("%time%", TimeUtil.getDuration(user.getWriteChat() - System.currentTimeMillis())));
                return;
            }
            user.setWriteChat(System.currentTimeMillis() + this.plugin.getPluginConfiguration().cfgChatCooldown);
        }
    }
}
