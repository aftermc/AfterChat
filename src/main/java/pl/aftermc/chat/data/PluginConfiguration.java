package pl.aftermc.chat.data;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Exclude;
import eu.okaeri.configs.annotation.Header;
import eu.okaeri.configs.exception.OkaeriException;
import pl.aftermc.chat.util.TimeUtil;


@Header("Konfiguracja pluginu MyAsperaChat")
@Header("Kod źródłowy: https://github.com/aftermc/AfterChat")
@Header("")
@Header("Uprawnienia:")
@Header("achat.notifyupdate - Informacja o nowej wersji pluginu po wejściu na serwer")
@Header("achat.nocooldown- Gracz z tym uprawnieniem może pisać na czacie bez ograniczenia czasowego")
@Header("achat.nochatoff- Gracz z tym uprawnieniem może pisać na czacie gdy jest wyłączony")
@Header("achat.chat.command - Dostęp do komendy /chat")
@Header("achat.chat.on - Dostęp do komendy /chat on")
@Header("achat.chat.off - Dostęp do komendy /chat off")
@Header("achat.chat.clear - Dostęp do komendy /chat clear")
@Header("achat.chat.cooldown - Dostęp do komendy /chat cooldown")
public class PluginConfiguration extends OkaeriConfig {

    @Comment("Czy czat ma być włączony? (Można ustawić komendą /chat on/off")
    public boolean chatEnable = true;

    @Comment("Co jaki czas można pisać na czacie? (np. 5s - 5 sekund)")
    public String chatCooldown = "5s";
    @Exclude
    public long cfgChatCooldown;

    @Override
    public OkaeriConfig load() throws OkaeriException {
        super.load();
        this.cfgChatCooldown = TimeUtil.getTimeWithString(this.chatCooldown);
        return this;
    }
}
