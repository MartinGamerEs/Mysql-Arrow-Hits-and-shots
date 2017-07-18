package com.isnakebuzz.minecloz.datatransfer;

import com.isnakebuzz.minecloz.api.Main;
import java.sql.*;

public class DataAPI{
    
    public static boolean playerExists(final String uuid) {
        try {
            final ResultSet rs = MySQL.query("SELECT * FROM Data_API WHERE Name='" + uuid + "'");
            return rs.next() && rs.getString("Name") != null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    final static String dfault = "Default";
    
    public static void createPlayer(final String uuid) {
        if (!playerExists(uuid)) {
            MySQL.update("INSERT INTO Data_API (Name, Ashots, Ahits) VALUES ('" + uuid + "', '0', '0');");
        }
    }
    
    public static Long getAShots(final String uuid) {
        long i = Long.valueOf(0L);
        if (playerExists(uuid)) {
            try {
                final MySQL mysql = Main.mysql;
                final ResultSet rs = MySQL.query("SELECT * FROM Data_API WHERE Name='" + uuid + "'");
                if (rs.next()) {
                    rs.getLong("Ashots");
                }
                i = Long.valueOf(rs.getLong("Ashots"));
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
    
    public static Long getAhits(final String uuid) {
        long i = Long.valueOf(0L);
        if (playerExists(uuid)) {
            try {
                final MySQL mysql = Main.mysql;
                final ResultSet rs = MySQL.query("SELECT * FROM Data_API WHERE Name='" + uuid + "'");
                if (rs.next()) {
                    rs.getLong("Ahits");
                }
                i = Long.valueOf(rs.getLong("Ahits"));
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
    
    public static void setAshots(final String uuid, final Long ashots) {
        if (playerExists(uuid)) {
            final MySQL mysql = Main.mysql;
            MySQL.update("UPDATE Data_API SET Ashots='" + ashots + "' WHERE Name='" + uuid + "'");
        }
    }
    
    public static void setAhits(final String uuid, final Long Ahits) {
        if (playerExists(uuid)) {
            final MySQL mysql = Main.mysql;
            MySQL.update("UPDATE Data_API SET Ahits='" + Ahits + "' WHERE Name='" + uuid + "'");
        }
    }
    
   public static void addAshots(final String uuid, final Long Ashots) {
        if (playerExists(uuid)) {
            setAshots(uuid, getAShots(uuid) + Ashots);
        }
    }
    
    public static void addAhits(final String uuid, final Long Ahits) {
        if (playerExists(uuid)) {
            setAhits(uuid, getAhits(uuid) + Ahits);
        }
    }
}
