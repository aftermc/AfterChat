package pl.aftermc.chat.command;

import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import pl.aftermc.chat.AfterChat;
import pl.aftermc.chat.data.MessageConfiguration;
import pl.aftermc.chat.data.PluginConfiguration;
import pl.aftermc.chat.util.ChatUtil;
import pl.aftermc.chat.util.TimeUtil;

@Command("chat")
public class ChatCommand extends CommandBase {

    public ChatCommand(final AfterChat plugin){
        plugin.getCommandManager().register(this);
        this.plugin = plugin;
    }
    private final AfterChat plugin;

    @Default
    @Permission("chat.command")
    public void chatCommand(final CommandSender sender, final String[] args) {
        PluginConfiguration pluginConfiguration = this.plugin.getPluginConfiguration();
        MessageConfiguration messageConfiguration = this.plugin.getMessageConfiguration();

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("on")) {
                if(!sender.hasPermission("achat.chat.on")) {
                    ChatUtil.sendMessage(sender, messageConfiguration.noPermission.replace("%permission%", "machat.chat.on"));
                    return;
                }
                if(pluginConfiguration.chatEnable) {
                    ChatUtil.sendMessage(sender, messageConfiguration.chatAlreadyOn);
                    return;
                }
                pluginConfiguration.chatEnable = true;
                pluginConfiguration.save();
                ChatUtil.sendBroadcast(messageConfiguration.chatOnBroadcast.replace("%admin%", sender.getName()));
            } else if(args[0].equalsIgnoreCase("off")) {
                if(!sender.hasPermission("achat.chat.off")) {
                    ChatUtil.sendMessage(sender, messageConfiguration.noPermission.replace("%permission%", "machat.chat.off"));
                    return;
                }
                pluginConfiguration.chatEnable = false;
                pluginConfiguration.save();
                ChatUtil.sendBroadcast(messageConfiguration.chatOffBroadcast.replace("%admin%", sender.getName()));
            } else if(args[0].equalsIgnoreCase("clear")) {
                if(!sender.hasPermission("achat.chat.clear")) {
                    ChatUtil.sendMessage(sender, messageConfiguration.noPermission.replace("%permission%", "machat.chat.clear"));
                    return;
                }
                if(!pluginConfiguration.chatEnable) {
                    ChatUtil.sendMessage(sender, messageConfiguration.chatAlreadyOff);
                    return;
                }
                Bukkit.getOnlinePlayers().forEach(players -> {
                    for (int i = 0; i < 100; ++i) {
                        players.sendMessage("       ");
                    }
                });
                ChatUtil.sendBroadcast(messageConfiguration.chatClearBroadcast.replace("%admin%", sender.getName()));
            } else {
                ChatUtil.sendMessage(sender, messageConfiguration.chatHelp);
            }
        } else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("cooldown")) {
                if(!sender.hasPermission("achat.chat.cooldown")) {
                    ChatUtil.sendMessage(sender, messageConfiguration.noPermission.replace("%permission%", "machat.chat.cooldown"));
                    return;
                }
                pluginConfiguration.chatCooldown = args[1];
                pluginConfiguration.cfgChatCooldown = TimeUtil.getTimeWithString(args[1]);
                pluginConfiguration.save();
                ChatUtil.sendBroadcast(messageConfiguration.chatCooldownBroadcast.replace("%cooldown%", args[1]).replace("%admin%", sender.getName()));
            }else {
                ChatUtil.sendMessage(sender, messageConfiguration.chatHelp);
            }
        } else {
            ChatUtil.sendMessage(sender, messageConfiguration.chatHelp);
        }
    }
}
