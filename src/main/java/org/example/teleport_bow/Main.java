package org.example.teleport_bow;

import org.bukkit.plugin.java.JavaPlugin;
import org.example.teleport_bow.command.CreateTeleportBowCommand;
import org.example.teleport_bow.handler.BowEventHandler;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("bow").setExecutor(new CreateTeleportBowCommand());
        getServer().getPluginManager().registerEvents(new BowEventHandler(this), this);
    }
}
