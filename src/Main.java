package me.Ninjoh.LimitedEnchanting;


import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main plugin;

    @Override
    public void onEnable()
    {
        // Fired when the server enables the plugin

        // Used to use JavaPlugin methods in listener classes.
        plugin = this;

        // Register events
        getServer().getPluginManager().registerEvents(new me.Ninjoh.LimitedEnchanting.EnchantListener(), this);

        // Generate config..
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable()
    {
        //Fired when the server stops and disables all plugins

        // Prevent memory leaks.
        // Make sure this is executed as last thing on disable!
        plugin = null;
    }
}