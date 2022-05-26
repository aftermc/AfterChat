package pl.aftermc.chat.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.aftermc.chat.AfterChat;
import pl.aftermc.chat.util.ChatUtil;

public class PlayerJoin implements Listener {

    public PlayerJoin(final AfterChat plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private final AfterChat plugin;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(final PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission("achat.notifyupdate")) {
            if(this.plugin.isNewPluginUpdate()) {
                ChatUtil.sendMessage(player, "&3AfterChat &8&l- &cDostępna jest nowa wersja pluginu!");
                ChatUtil.sendURLMessage(player, "&3AfterChat &8&l- &aKliknij na wiadomość aby pobrać najnowszą wersję z githuba", "https://github.com/aftermc/AfterChat/releases");
            }
        }
    }
}