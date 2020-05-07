package heyits.red.Scoreboard.rr.triggers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import heyits.red.Scoreboard.rr.Main;
import heyits.red.Scoreboard.rr.board.App;
import heyits.red.Scoreboard.rr.board.ScoreboardHolder;

public class onCombat implements Listener {

    @EventHandler
    public void onCombat(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player)
        {
            Player damager = (Player) e.getDamager();
            damager.setScoreboard(Main.empty);
            for(App app : Main.apps.values())
                app.unregisterHolder(damager);

            Main.apps.get("combat").registerHolder(new ScoreboardHolder(Main.apps.get("combat"), damager));
        }
    }

}
