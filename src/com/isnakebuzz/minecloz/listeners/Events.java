package com.isnakebuzz.minecloz.listeners;

import com.isnakebuzz.minecloz.api.*;
import com.isnakebuzz.minecloz.datatransfer.DataAPI;
import java.io.IOException;
import jdk.nashorn.internal.ir.ContinueNode;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener{
    
    @EventHandler
    public void JoinRegisterNewPlayer(PlayerJoinEvent e){
        final String name = e.getPlayer().getName();
        if (!DataAPI.playerExists(name)){
            DataAPI.createPlayer(name);
        }
    }
    
    @EventHandler
    public void ArrowShot(EntityShootBowEvent e){
        if (e.getEntity() instanceof Player){
            final String p = e.getEntity().getName();
            DataAPI.addAshots(p, 1L);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void ArrowHits(final EntityDamageByEntityEvent e) throws IOException {
        if (e.getEntity() instanceof Player){
            if (e.getDamager() instanceof Arrow) {
                final Arrow a = (Arrow)e.getDamager();
                if (a.getShooter() instanceof Player) {
                    a.getShooter();
                    final Player p = (Player)a.getShooter();
                    String p2 = p.getName();
                    DataAPI.addAhits(p2, 1L);
                }
            }
        }
    }
    
}
