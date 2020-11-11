package Scoreboard.rr.board.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import Scoreboard.rr.Main;
import Scoreboard.rr.board.App;
import Scoreboard.rr.board.ScoreboardHolder;

public class EDeintergrate implements Listener {

    private App app;

    public EDeintergrate(App app)
    {
        this.app = app;
    }

    @EventHandler
    public void Deintergrate(PlayerQuitEvent e)
    {
        if(app == null) return;
        app.unregisterHolder(e.getPlayer());
        e.getPlayer().setScoreboard(Main.empty);
    }

}
