package cn.com.ktm.mt.model.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;


@Component
public class RedisPoolConfig {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 获取一个 jedis 客户端
     *
     * @return
     */
    public Jedis getJedis(Integer db) {
        Jedis resource = getPool().getResource();
        if (db == null) {
        	System.out.print("sssssssssssssssss11111111111111111111111111111111"+redisProperties.getHOST_IP());
            resource.select(redisProperties.DEFAULT_DB_INDEX);// 选择默认DB
        } else {
            resource.select(db);
        }
        return resource;
    }

    
    /**
     * 获取一个 jedis 客户端
     *
     * @return
     */
    public Jedis getJedis() {
        Jedis resource = getPool().getResource();
        resource.select(redisProperties.DEFAULT_DB_INDEX);// 选择默认DB
        return resource;
    }

    private static JedisPool pool = null;

    /**
     * 构建redis连接池
     */
    public JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            // 如果赋值为-1，则表示不限制；
            config.setMaxTotal(redisProperties.MAX_TOTAL);
            // 控制一个pool最多有多少个状态为idle空闲的的jedis实例
            config.setMaxIdle(redisProperties.MAX_IDLE);
            config.setMaxWaitMillis(redisProperties.MAX_WAIT_MILLS);
            config.setTestOnBorrow(redisProperties.TEST_ON_BORROW);
            if (StringUtils.isEmpty(redisProperties.PASSWORD)) {
                pool = new JedisPool(config, redisProperties.HOST_IP, redisProperties.HOST_PORT);
            } else {
                pool = new JedisPool(config, redisProperties.HOST_IP, redisProperties.HOST_PORT,
                        Protocol.DEFAULT_TIMEOUT, redisProperties.PASSWORD);
            }
        }
        return pool;
    }
}
