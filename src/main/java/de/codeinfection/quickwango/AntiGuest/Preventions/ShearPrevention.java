package de.codeinfection.quickwango.AntiGuest.Preventions;

import de.codeinfection.quickwango.AntiGuest.AntiGuest;
import de.codeinfection.quickwango.AntiGuest.Prevention;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerShearEntityEvent;

/**
 *
 * @author Phillip
 */
public class ShearPrevention extends Prevention
{

    public ShearPrevention()
    {
        super("shear", AntiGuest.getInstance());
    }

    @Override
    public ConfigurationSection getDefaultConfig()
    {
        ConfigurationSection config = super.getDefaultConfig();

        config.set("message", "&4You are not allowed to shear animals!");

        return config;
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void handle(PlayerShearEntityEvent event)
    {
        prevent(event, event.getPlayer());
    }
}