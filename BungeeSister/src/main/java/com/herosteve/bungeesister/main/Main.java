package com.herosteve.bungeesister.main;

import com.herosteve.bungeesister.subscribe.CommandSub;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main extends Plugin {

    private String systemPrefix = "[BungeeSister]: ";   //用于区分console内容

    private final String configPath = "redis.properties";   //config路径

    private JedisPool jedisPool;

    private Jedis jedis;

    public void onEnable() {
        //start logo
        printLogo(true);
        //初始化jedis
        initJedis();
        //订阅redis通道
        listening();
    }

    private void listening() {
        getProxy().getScheduler().runAsync(this, new Runnable() {
            public void run() {
                jedis = jedisPool.getResource();
                jedis.subscribe(new CommandSub(Main.this),"bungeeCord");
            }
        });
    }

    private void initJedis() {
        try {
            //加载配置文件
            InputStream in = Main.class.getClassLoader().getResourceAsStream(this.configPath);
            Properties props = new Properties();
            props.load(in);
            //配置pool
            jedisPool = new JedisPool(props.getProperty("redis.host"), Integer.parseInt(props.getProperty("redis.port")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void onDisable() {
        jedisPool.close();
        jedis.close();
        System.out.println(systemPrefix + "Disabled");
    }
    private void printLogo(boolean start){
        if(start){
            System.out.println(systemPrefix +"\n"+
                    "   ___                           _____     __         \n" +
                    "  / _ )__ _____  ___ ____ ___   / __(_)__ / /____ ____\n" +
                    " / _  / // / _ \\/ _ `/ -_) -_) _\\ \\/ (_-</ __/ -_) __/\n" +
                    "/____/\\_,_/_//_/\\_, /\\__/\\__/ /___/_/___/\\__/\\__/_/   \n" +
                    "               /___/                                  ");
            System.out.println("                                         \n" +
                    "                                       _          | \n" +
                    " v-1.0.0-beta               code by — /_ |_| |_| | \n" +
                    "                                                       ");
        }else{
            System.out.println(systemPrefix+ "Disabled");
        }
    }

    public void execute(String command) {
        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), command);
    }
}
