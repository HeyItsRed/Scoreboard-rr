package Scoreboard.rr;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import Scoreboard.rr.board.App;
import Scoreboard.rr.board.Row;
import Scoreboard.rr.board.ScoreboardHolder;
import Scoreboard.rr.util.ConfigControl;
import Scoreboard.rr.util.Func;

public class CommandManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
    	final Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        if(!(commandSender instanceof Player))
        {
            commandSender.sendMessage("This is a player-only command!");
        } else {

            Player player = (Player) commandSender;

            if(args.length < 1)
            {
                Func.msg(player, "Too few arguments!");
                help(player);
            } else {
                 if(args[0].equalsIgnoreCase("reload")) {
                    if(Func.perm(player, "reload"))
                    {
                        Main.disolveBoards();
                        ConfigControl.get().reloadConfigs();
                        Main.loadBoards();
                        Func.smsg(player, "Scoreboard reloaded");
                    }
                } else if(args[0].equalsIgnoreCase("toggle")) {
                        if(!Session.sb_off_players.contains(player)) {
                            Session.sb_off_players.add(player);
                            player.setScoreboard(Main.empty);
                    } else {
                        Session.sb_off_players.remove(player);
                    }
                        Func.smsg(player, "Scoreboard toggled");
                }  else if(args[0].equalsIgnoreCase("alloff")) {
                	 if(Func.perm(player, "admin")) {
                		 for (Player p : players) {
                    if(!Session.sb_off_players.contains(p)) {
                        Session.sb_off_players.add(p);
                        p.setScoreboard(Main.empty);
                }
                		 }
                    Func.smsg(player, "Scoreboards off");
                	 }
                } else if(args[0].equalsIgnoreCase("allon")) {
               	 if(Func.perm(player, "admin")) {
            		 for (Player p : players) {
                if(Session.sb_off_players.contains(p)) {
                	Session.sb_off_players.remove(p);
            }
            		 }
                Func.smsg(player, "Scoreboards on");
            	 }
            } else {
                    Func.msg(player,"Unknown command!");
                    help(player);
                }
            }
        }

        return false;
    }

    private void help(Player player)
    {
        Func.smsg(player, "/sb reload (Reload config and application)");
        Func.smsg(player, "/sb toggle (Toggle your scoreboard on or off)");
        Func.smsg(player, "/sb alloff (Turn all scoreboards off)");
        Func.smsg(player, "/sb allon (Turn all scoreboards on)");
    }
}
