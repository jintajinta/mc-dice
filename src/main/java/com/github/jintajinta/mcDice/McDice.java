package com.github.jintajinta.mcDice;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.Random;

public final class McDice extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("McDice enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("dice")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("このコマンドはプレイヤーのみ使用できます。");
                return true;
            }
            Player player = (Player) sender;
            int dice = new Random().nextInt(6) + 1;
            String message = player.getName() + " がサイコロを振った！ 出目: " + dice;
            // 半径10ブロック以内のプレイヤーにブロードキャスト
            player.getWorld().getPlayers().stream()
                .filter(p -> p.getLocation().distance(player.getLocation()) <= 10)
                .forEach(p -> p.sendMessage(message));
            return true;
        }
        return false;
    }
}
