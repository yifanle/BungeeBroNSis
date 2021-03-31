# BungeeBroNSis
基于Redis订阅和发布的BungeeBrothers改版:sparkling_heart:<br>
```console
                  ____                                ____            _   _ ____  _     
                 | __ ) _   _ _ __   __ _  ___  ___  | __ ) _ __ ___ | \ | / ___|(_)___ 
                 |  _ \| | | | '_ \ / _` |/ _ \/ _ \ |  _ \| '__/ _ \|  \| \___ \| / __|
                 | |_) | |_| | | | | (_| |  __/  __/ | |_) | | | (_) | |\  |___) | \__ \
                 |____/ \__,_|_| |_|\__, |\___|\___| |____/|_|  \___/|_| \_|____/|_|___/
                                    |___/                                                       
                 v1.0.0-beta                                                code by zuul
```
>功能&不同<br>

- BungeeBroNSis包含两个插件,提供spigot控制台及玩家向bungeeCord端发送命令的功能<br>
- 与BungeeBrothers相比支持了 0 在线人数时仍可发送命令
>使用方法
- 将BungeeBrother项目mvn -package 打成jar后放入spigot的plugin内
- 将BungeeSister项目mvn -package 打成jar后放入bungeeCord的plugin内
- 也可以直接下载[BungeeBroNSis-release](https://github.com/BigTreasureD/BungeeBroNSis/releases/tag/v1.0.0-beta)
- **启动服务器前先启动redis

>使用命令:`/bc`
- spigot控制台 
```yml
# bc <bungeeCord命令> bungeeCord命令前不需要加"/"符号
# 例如：bc send player1 server2
```
- Player
```yml
# 玩家需要具有bungeeBrother.bc权限

# /bc <bungeeCord命令> bungeeCord命令前不需要加"/"符号
# 例如：/bc send player1 server2
```
>redis配置信息
- spigot端
```properties
#最大活动对象数
redis.pool.maxTotal=32
#最大能够保持idel状态的对象数
redis.pool.maxIdle=16
#最小能够保持idel状态的对象数
redis.pool.minIdle=8
#当池内没有返回对象时，最大等待时间
redis.pool.maxWaitMillis=10000
#“空闲链接”检测线程，检测的周期，毫秒数。如果为负值，表示不运行“检测线程”。默认为-1.
redis.pool.timeBetweenEvictionRunsMillis=30000
#向调用者输出“链接”对象时，是否检测它的空闲超时；
redis.pool.testWhileIdle=true
# 对于“空闲链接”检测线程而言，每次检测的链接资源的个数。默认为3.
redis.pool.numTestsPerEvictionRun=8
#redis服务器的IP
redis.host=localhost
#redis服务器的Port
redis.port=6379
```
- bungeeCord端
```properties
#redis服务器的IP
redis.host=localhost
#redis服务器的Port
redis.port=6379
```
