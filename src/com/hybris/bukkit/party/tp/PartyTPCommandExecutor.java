package com.hybris.bukkit.party.tp;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import org.bukkit.Server;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Player;

import com.hybris.bukkit.party.GroupManager;
import com.hybris.bukkit.party.Group;

import java.util.ArrayList;

public class PartyTPCommandExecutor implements CommandExecutor{
	
	private GroupManager manager;
	private Server server;
	
	protected PartyTPCommandExecutor(PartyTP plugin, GroupManager manager){
		this.manager = manager;
		this.server = plugin.getServer();
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){// TODO Register
		
		if(sender.getServer() != this.server){
			sender.sendMessage("You have to be in the same server (LOGGED COMMAND)");
			return false;
		} // You have to be in the same server
		
		if(!Player.class.isAssignableFrom(sender.getClass())){
			sender.sendMessage("You have to be a Player");
			return false;
		} // You have to be a Player
		
		Player senderPlayer = (Player)sender;
		
		if(!command.getName().equalsIgnoreCase("partytp")){
			sender.sendMessage("You have to say /partytp");
			return false;
		} // You have to say /partytp
		
		Group senderGroup = this.manager.isLeader(senderPlayer);
		if(senderGroup != null){
			ArrayList<Player> members = senderGroup.getMembers();
			if(this.server instanceof CraftServer){
				if(((CraftServer)this.server).dispatchCommand(sender, "tp " + senderPlayer.getName() + " " + senderPlayer.getName())){
					for(Player member : members){
						if(!member.getName().equals(senderPlayer.getName())){
							((CraftServer)this.server).dispatchCommand(sender, "tp " + member.getName() + " " + senderPlayer.getName());
						}
					}
					sender.sendMessage("Teleported all your members");
					return true;
				}
				else{
					sender.sendMessage("You cannot teleport your members");
					return true;
				}
			}
			else{
				sender.sendMessage("Your server isn't a CraftBukkit server");
				return true;
			}
		}
		else{
			sender.sendMessage("You are not leader of a party");
			return true;
		}
		
	}
	
}