package heyits.red.Scoreboard.rr.board.events;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import heyits.red.Scoreboard.rr.Main;
import heyits.red.Scoreboard.rr.Session;
import heyits.red.Scoreboard.rr.board.App;
import heyits.red.Scoreboard.rr.board.ScoreboardHolder;
import heyits.red.Scoreboard.rr.util.Func;

public class EIntergrate implements Listener {

    private App app;

    public EIntergrate(App app)
    {
        this.app = app;
    }

    @EventHandler
    public void Intergrate(PlayerJoinEvent e)
    {

        if(app == null || !app.isdefault) return;
        if(e.getPlayer().isOp() && !Session.isuptodate)
        	try {
        	URL url = new URL("https://raw.githubusercontent.com/HeyItsRed/Scoreboard-rr/master/Version.txt");
    	HttpURLConnection con = (HttpURLConnection) url.openConnection();
    	con.setRequestMethod("GET");
    	con.setRequestProperty("Content-Type", "application/json");
    	con.setConnectTimeout(5000);
    	con.setReadTimeout(5000);

        String version = new BufferedReader(new InputStreamReader(
                con.getInputStream())).readLine();
            e.getPlayer().sendMessage(Func.color("&cYou are running an outdated version of Scoreboard\n&aCurrent Version:&f " + Session.currentversion + ", &bLatest Version:&f " + version + " \n&cPlease update as soon as possible for performance gain, security- or bugfixes."));
        	} catch (Exception ex) {
                ex.printStackTrace();
            }
        new ScoreboardHolder(app, e.getPlayer());
    }

}
