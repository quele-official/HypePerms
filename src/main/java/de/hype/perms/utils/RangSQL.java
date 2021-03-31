package de.hype.perms.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RangSQL {

    public static void setRang(String uuid, Rang rang) {
        removeOldRang(uuid);

        MySQL.update("UPDATE Rang SET Rang= '" + rang.getName() + "' WHERE UUID= '" + uuid + "'");
    }

    public static String getRangName(String uuid) {
        try {
            ResultSet result = MySQL.getResult("SELECT * FROM rang WHERE UUID= '" + uuid + "'");

            assert result != null;
            if (result.next()) {
                return result.getString("Rang");
            }
        } catch (SQLException ignored) {
            return "Spieler";
        }
        return "Spieler";
    }

    public static Rang getRang(String uuid) {
        try {
            ResultSet result = MySQL.getResult("SELECT * FROM rang WHERE UUID= '" + uuid + "'");

            assert result != null;
            if (result.next()) {
                return Rang.valueOf(result.getString("Rang"));
            }
        } catch (SQLException ignored) {
            return Rang.Spieler;
        }
        return Rang.Spieler;
    }

    public static Integer getRangId(String uuid) {
        try {
            ResultSet result = MySQL.getResult("SELECT * FROM rang WHERE UUID= '" + uuid + "'");

            assert result != null;
            if (result.next()) {
                return Rang.valueOf(result.getString("Rang")).getId();
            }
        } catch (SQLException ignored) {
            return Rang.Spieler.getId();
        }
        return Rang.Spieler.getId();
    }

    public static void removeOldRang(String uuid) {
        try {
            ResultSet result = MySQL.getResult("SELECT * FROM rang WHERE UUID= '" + uuid + "'");

            assert result != null;
            if (result.next()) {
                MySQL.update("UPDATE rang SET Rang= 'Spieler' WHERE UUID= '" + uuid + "'");
            }
        } catch (SQLException ignored) {

        }
    }
}
