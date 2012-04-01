package de.codeinfection.quickwango.AntiGuest.Preventions.Bukkit;

import de.codeinfection.quickwango.AntiGuest.AntiGuestBukkit;
import de.codeinfection.quickwango.AntiGuest.Prevention;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerFishEvent;

/**
 * Prevents fishing
 *
 * @author Phillip Schichtel
 */
public class FishPrevention extends Prevention
{
    public FishPrevention()
    {
        super("fish", AntiGuestBukkit.getInstance());
    }

    @Override
    public Configuration getDefaultConfig()
    {
        Configuration config = super.getDefaultConfig();

        config.set("message", "&4You are not allowed to fish!");

        return config;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void handle(PlayerFishEvent event)
    {
        prevent(event, event.getPlayer());
    }
}
