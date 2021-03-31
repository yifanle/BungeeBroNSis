package com.herosteve.bungeesister.subscribe;

import com.herosteve.bungeesister.main.Main;
import redis.clients.jedis.JedisPubSub;

public class CommandSub extends JedisPubSub {

    private Main plugin;

    public CommandSub(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public void onMessage(String channel, String message) {
        if(message==null || message.equals(""))
            return;
        System.out.println("Command: '"+message+"' is being planned");
        //执行命令
        plugin.execute(message);
    }
}
