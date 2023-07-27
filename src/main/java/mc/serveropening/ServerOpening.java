package mc.serveropening;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ServerOpening extends Plugin implements Listener {
    private final long targetTimestamp = 1690473600000L; // This timestamp corresponds to 12:00 PM EST on 7/27/2023 using this website https://www.unixtimestamp.com/

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, this);
    }

    @EventHandler
    public void onPlayerLogin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        String playerName = player.getName();
        long currentTime = System.currentTimeMillis();

        if (!playerName.equalsIgnoreCase("NewAmazingPVP") && currentTime < targetTimestamp) {
            long remainingTime = targetTimestamp - currentTime;
            String formattedTime = formatTime(remainingTime);
            player.disconnect(ChatColor.RED + "The server is currently closed.\n" +
                    "It will open on 12:00 PM EST on 7/27/2023.\n" +
                    "Time remaining: " + ChatColor.YELLOW + formattedTime + "\n" +
                    ChatColor.GREEN + "Meanwhile, make sure to join the discord server if you haven't already: " +
                    ChatColor.AQUA + "https://discord.gg/PN8egFY3ap");
        }
    }

    private String formatTime(long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(new Date(timeInMillis));
    }
}
