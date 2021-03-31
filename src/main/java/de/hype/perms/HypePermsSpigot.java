package de.hype.perms;

import de.hype.perms.commands.RangCommand;
import de.hype.perms.utils.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class HypePermsSpigot extends JavaPlugin {

    private static HypePermsSpigot instance;

    @Override
    public void onEnable() {
        instance = this;

        new MySQL("127.0.0.1", "root", "", "test");

        Objects.requireNonNull(getCommand("rang")).setExecutor(new RangCommand());

        MySQL.connect();
    }

    public static HypePermsSpigot getInstance() {
        return instance;
    }

    public String getPrefix() {
        return "§cHypePerms §8| §7";
    }
}
