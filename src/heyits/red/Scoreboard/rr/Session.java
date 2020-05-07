package heyits.red.Scoreboard.rr;

import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Session {

    // Dependencies
    // PlaceholderAPI = dependency ID 0
    public static String[] dependencies = {"PlaceholderAPI"};
    public static ArrayList<String> enabled_dependencies = new ArrayList<>();

    // Objects
    public static Main plugin = null;

    // Bools
    public static boolean isuptodate = false;

    // Blocked users
    public static ArrayList<Player> disabled_players = new ArrayList<>();
    public static ArrayList<Player> re_enable_players = new ArrayList<>();

    /**
     * Are we up to date?
     * @param resourceId
     */
    public static void isUpToDate(String resourceId) {
        try {
        	URL url = new URL("https://raw.githubusercontent.com/HeyItsRed/Scoreboard-rr/master/Version.txt");
        	HttpURLConnection con = (HttpURLConnection) url.openConnection();
        	con.setRequestMethod("GET");
        	con.setRequestProperty("Content-Type", "application/json");
        	con.setConnectTimeout(5000);
        	con.setReadTimeout(5000);

            String version = new BufferedReader(new InputStreamReader(
                    con.getInputStream())).readLine();
            if (version.equalsIgnoreCase(plugin.getDescription().getVersion())) {
                isuptodate =  true;
            } else {
                isuptodate =  false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            isuptodate =  false;
        }
    }

}