package com.example.plugin;

import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class TemplatePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Kitin Entity Optimizer has been loaded!");
    }
}