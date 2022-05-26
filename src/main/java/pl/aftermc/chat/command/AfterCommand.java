package pl.aftermc.chat.command;

import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.command.CommandSender;
import pl.aftermc.chat.AfterChat;
import pl.aftermc.chat.util.ChatUtil;

@Command("afterchat")
public class AfterCommand extends CommandBase {

    public AfterCommand(final AfterChat plugin){
        plugin.getCommandManager().register(this);
        this.plugin = plugin;
    }
    private final AfterChat plugin;

    @Default
    public void afterchatCommand(final CommandSender sender) {
        ChatUtil.sendMessage(sender, "&cTen serwer używa pluginu &6AfterChat v" + plugin.getDescription().getVersion() + "!" +
                "\n&cPlugin do pobrania na githubie: &6https://github.com/aftermc/AfterChat/releases");
    }
}
