package com.hybris.bukkit.party.tp;

import com.hybris.bukkit.party.api.RequiredPartyPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.Plugin;
import java.util.logging.Logger;
import com.hybris.bukkit.party.api.GroupManager;

/**
* PartyTP plugin for Bukkit (supports Party)
* @author Hybris95
*/
public class PartyTP extends RequiredPartyPlugin
{
	
	private PartyTPCommandExecutor cE;
	
	public void onLoad(){
		super.onLoad();
	}

	public void onEnable(){
		this.log.info("[PartyTP] enabling...");
		super.onEnable();
		
		this.cE = new PartyTPCommandExecutor(this, this.getManager());
		this.getServer().getPluginCommand("partytp").setExecutor(this.cE);
		this.log.info("[PartyTP] enabled!");
	}
	
	public void onDisable(){
		this.log.info("[PartyTP] disabling...");
		super.onDisable();
		this.cE = null;
		this.log.info("[PartyTP] disabled!");
	}
	
	
}