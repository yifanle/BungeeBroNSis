package com.herosteve.bungeebrother.commands;

import com.herosteve.bungeebrother.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author zuul
 * @desc 用于处理/bungeee命令
 */
public class Bungee implements CommandExecutor {

    private Main plugin;    //用于回调Main中的方法

    public Bungee(Main plugin) {
        this.plugin = plugin;
    }

    /**
     * @desc /bc 不需要一个特殊的发送者去发送
     * @param commandSender 命令发送者
     * @param cmd           command对象
     * @param cmdLabel      命令内容：/bc
     * @param args          命令参数：bungee命令
     * @return
     */
    public boolean onCommand(CommandSender commandSender, Command cmd, String cmdLabel, String[] args) {
        if (commandSender instanceof Player && !commandSender.hasPermission("bungeeBrother.bc")){
            return false;
        }
        String[] data = args;
        if (data.length == 0) {
            System.out.println(this.plugin.getSystemPrefix() + "Command is empty");
            return false;
        }
        StringBuffer commandSb = new StringBuffer();
        commandSb.append(data[0]);
        if (data.length > 1)
            for (int i = 1; i != data.length; i++){
                commandSb.append(" "+data[i]);
            }
        String command = commandSb.toString();
        this.plugin.sendCommand(command);
        return false;
    }
}
