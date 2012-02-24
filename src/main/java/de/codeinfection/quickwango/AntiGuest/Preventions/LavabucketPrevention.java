package de.codeinfection.quickwango.AntiGuest.Preventions;

import de.codeinfection.quickwango.AntiGuest.AntiGuest;
import de.codeinfection.quickwango.AntiGuest.Prevention;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

/**
 *
 * @author Phillip
 */
public class LavabucketPrevention extends Prevention
{

    public LavabucketPrevention()
    {
        super("lavabucket", AntiGuest.getInstance());
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void empty(PlayerBucketEmptyEvent event)
    {
        handle(event);
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void fill(PlayerBucketFillEvent event)
    {
        handle(event);
    }
    
    public void handle(PlayerBucketEvent event)
    {
        if (event.getBucket() == Material.LAVA_BUCKET)
        {
            prevent(event, event.getPlayer());
        }
    }
}
