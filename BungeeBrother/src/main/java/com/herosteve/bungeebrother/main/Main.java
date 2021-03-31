package com.herosteve.bungeebrother.main;

import com.herosteve.bungeebrother.commands.Bungee;
import com.herosteve.bungeebrother.config.Config;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zuul
 * @desc 置于spigot的插件 使spigot开启信道，并将bungee命令放入信道内等待bungeeCord监听信道内的命令
 */
public class Main extends JavaPlugin {

    private String systemPrefix = "[BungeeBrother]: ";   //用于区分console内容

    private final String configPath = "redis.properties";   //config路径

    private Config config = null;                //配置对象

    private JedisPool jedisPool = null;


    public void onEnable(){
        //加载bungee、bungeePlayer命令，加载配置文件
        loadSystem();
        //plugin start
        printLogo(true);
    }

    private void loadSystem() {
        //初始化配置对象
        initConfig();
        //初始化redisPool
        initRedisPool();
        //初始化命令对象
        getCommand("bc").setExecutor(new Bungee(this));
    }

    private void initRedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //一大堆参数
        poolConfig.setMaxIdle(config.getMaxIdle());
        poolConfig.setMaxTotal(config.getMaxTotal());
        poolConfig.setMinIdle(config.getMinIdle());
        poolConfig.setMaxWaitMillis(config.getMaxWaitMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(config.getTimeBetweenEvictionRunsMillis());
        poolConfig.setTestWhileIdle(config.getTestWhileIdle());
        poolConfig.setNumTestsPerEvictionRun(config.getNumTestsPerEvictionRun());

        jedisPool = new JedisPool(poolConfig,config.getHost(),config.getPort());
    }

    private void initConfig() {
        try {
            InputStream in = Main.class.getClassLoader().getResourceAsStream(this.configPath);
            Properties props = new Properties();
            props.load(in);
            config = new Config(String.valueOf(props.getProperty("redis.host")),Integer.parseInt(props.getProperty("redis.port")),Integer.parseInt(props.getProperty("redis.pool.maxTotal")),Integer.parseInt(props.getProperty("redis.pool.maxIdle")),Integer.parseInt(props.getProperty("redis.pool.minIdle")),Long.parseLong(props.getProperty("redis.pool.maxWaitMillis")),Long.parseLong(props.getProperty("redis.pool.timeBetweenEvictionRunsMillis")),Boolean.parseBoolean(props.getProperty("redis.pool.testWhileIdle")),Integer.parseInt(props.getProperty("redis.pool.numTestsPerEvictionRun")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @desc            发送命令给bungeeCord
     * @param command   要发送给bungeeCord的命令
     */
    public void sendCommand(String command){
        Jedis jedis = null;
        try{
            //发送到redis订阅通道
            jedis = jedisPool.getResource();
            jedis.publish("bungeeCord",command);

            System.out.println(this.systemPrefix+ "Command '"+command+"' Send Success!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null)
                jedis.close();
        }

    }

    public String getSystemPrefix() {
        return this.systemPrefix;
    }

    public void onDisable(){
        printLogo(false);
    }

    private void printLogo(boolean start){
        if(start){
            System.out.println(systemPrefix +"\n"+
                    "   ___                           ___           __  __          \n" +
                    "  / _ )__ _____  ___ ____ ___   / _ )_______  / /_/ /  ___ ____\n" +
                    " / _  / // / _ \\/ _ `/ -_) -_) / _  / __/ _ \\/ __/ _ \\/ -_) __/\n" +
                    "/____/\\_,_/_//_/\\_, /\\__/\\__/ /____/_/  \\___/\\__/_//_/\\__/_/   \n" +
                    "               /___/                                           ");
            System.out.println("                                         \n" +
                    "                                       _          | \n" +
                    " v-1.0.0-beta               code by — /_ |_| |_| | \n" +
                    "                                                       ");
        }else{
            System.out.println(systemPrefix+ "Disabled");
        }
    }

}
