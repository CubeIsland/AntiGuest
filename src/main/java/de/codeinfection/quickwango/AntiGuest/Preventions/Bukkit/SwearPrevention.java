package de.codeinfection.quickwango.AntiGuest.Preventions.Bukkit;

import de.codeinfection.quickwango.AntiGuest.AntiGuestBukkit;
import de.codeinfection.quickwango.AntiGuest.Prevention;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.bukkit.Server;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerChatEvent;

/**
 *
 * @author CodeInfection
 */
public class SwearPrevention extends Prevention
{
    private List<Pattern> swearPatterns;

    public SwearPrevention()
    {
        super("swear", AntiGuestBukkit.getInstance());
    }

    @Override
    public ConfigurationSection getDefaultConfig()
    {
        ConfigurationSection config = super.getDefaultConfig();

        config.set("message", "&4Hey! Don't swear!");
        config.set("words", new String[] {
            "hitler",
            "nazi",
            "asshole",
            "shit",
            "fuck"
        });

        return config;
    }

    @Override
    public void enable(final Server server, ConfigurationSection config)
    {
        super.enable(server, config);
        this.swearPatterns = new ArrayList<Pattern>();
        for (String word : config.getStringList("words"))
        {
            AntiGuestBukkit.debug("word: " + word);
            this.swearPatterns.add(compile(word));
        }
    }

    @Override
    public void disable()
    {
        super.disable();
        this.swearPatterns.clear();
        this.swearPatterns = null;
    }

    private static Pattern compile(String string)
    {
        return Pattern.compile("\\b" + Pattern.quote(string) + "\\b", Pattern.CASE_INSENSITIVE);
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void handle(PlayerChatEvent event)
    {
        final Player player = event.getPlayer();
        if (!can(player))
        {
            final String message = event.getMessage();
            for (Pattern regex : this.swearPatterns)
            {
                if (regex.matcher(message).find())
                {
                    sendMessage(player);
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
}