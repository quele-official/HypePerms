package de.hype.perms.commands;

import de.hype.perms.HypePermsSpigot;
import de.hype.perms.utils.Rang;
import de.hype.perms.utils.RangSQL;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RangCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2) {
                Player targetPlayer = Bukkit.getPlayer(args[0]);
                if (targetPlayer == null) {
                    player.sendMessage(new TextComponent(HypePermsSpigot.getInstance().getPrefix() + "§cDieser Spieler ist derzeit nicht auf dem Netzwerk§8!"));
                    return false;
                }
                Rang rang = Rang.getRangByName(args[1]);
                if(rang != null) {
                    String rangColor = rang.getPrefix().substring(0, 2);
                    if (RangSQL.getRangId(player.getUniqueId().toString()) < 7) {
                        player.sendMessage(HypePermsSpigot.getInstance().getPrefix() + "§7Nicht genug §cRechte§8.");
                        return false;
                    }
                    RangSQL.setRang(targetPlayer.getUniqueId().toString(), rang);
                    player.sendMessage(HypePermsSpigot.getInstance().getPrefix() + "§7Du hast dem Spieler " + rangColor + targetPlayer.getName()
                            + " §7die Gruppe " + rangColor + rang.getName() + " §7gesetzt§8.");
                    targetPlayer.sendMessage(HypePermsSpigot.getInstance().getPrefix() + "§7Du hast einen neuen Rang erhalten§8!");
                    targetPlayer.sendMessage(HypePermsSpigot.getInstance().getPrefix() + "§aNeuer Rang §8» " + rangColor + rang.getName());
                    targetPlayer.sendTitle("§aNeuer Rang", "§7Rang §8» " + rangColor + rang, 3, 120, 3);
                    targetPlayer.sendMessage(HypePermsSpigot.getInstance().getPrefix() + "§7Damit du deinen neuen Rang erhälst musst du den Server neubeitreten§8.");
                    targetPlayer.getWorld().strikeLightningEffect(targetPlayer.getLocation());
                } else {
                    player.sendMessage(HypePermsSpigot.getInstance().getPrefix() + "§7Der angegebene §6Rang §7existiert leider nicht§8.");
                }

            } else {
                player.sendMessage(new TextComponent(HypePermsSpigot.getInstance().getPrefix() + "§cNutze /rang <Spieler> <Rang>§8!"));
            }
        } else {
            if (args.length == 2) {
                Player targetPlayer = Bukkit.getPlayer(args[0]);
                if (targetPlayer == null) {
                    sender.sendMessage(new TextComponent(HypePermsSpigot.getInstance().getPrefix() + "§cDieser Spieler ist derzeit nicht auf dem Netzwerk§8!"));
                    return false;
                }
                Rang rang = Rang.getRangByName(args[1]);
                if(rang != null) {
                    String rangColor = rang.getPrefix().substring(0, 2);
                    RangSQL.setRang(targetPlayer.getUniqueId().toString(), rang);
                    sender.sendMessage(HypePermsSpigot.getInstance().getPrefix() + "§7Du hast dem Spieler " + rangColor + targetPlayer.getName()
                            + " §7die Gruppe " + rangColor + rang.getName() + " §7gesetzt§8.");
                } else {
                    sender.sendMessage(HypePermsSpigot.getInstance().getPrefix() + "§7Der angegebene §6Rang §7existiert leider nicht§8.");
                }

//was passieren soll

            } else {
                sender.sendMessage(new TextComponent(HypePermsSpigot.getInstance().getPrefix() + "§cNutze /rang <Spieler> <Rang>§8!"));
            }
        }

        return false;
    }

    public boolean isExist(Rang rang) {
        return Rang.getRangs().contains(rang);
    }
}

