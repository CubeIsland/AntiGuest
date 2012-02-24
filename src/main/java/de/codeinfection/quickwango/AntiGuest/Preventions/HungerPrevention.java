package de.codeinfection.quickwango.AntiGuest.Preventions;

import de.codeinfection.quickwango.AntiGuest.AntiGuest;
import de.codeinfection.quickwango.AntiGuest.Prevention;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

/**
 *
 * @author Phillip
 */
public class HungerPrevention extends Prevention
{

    public HungerPrevention()
    {
        super("hunger", AntiGuest.getInstance());
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void handle(EntityDamageEvent event)
    {
        if (event.getCause() == DamageCause.STARVATION)
        {
            final Entity entity = event.getEntity();
            if (entity instanceof Player)
            {
                prevent(event, (Player)entity);
            }
        }
    }
}
