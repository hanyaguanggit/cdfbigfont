package cn.com.ktm.mt.model.redis;

import java.util.List;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import cn.com.ktm.mt.model.util.utils.SpringContextUtil;
import cn.com.ktm.mt.model.util.utils.ValidUtil;
import redis.clients.jedis.Jedis;

/**
 * reids 队列
 * 
 * @author wjq
 *
 */

public class RedisQueue  {
    private static RedisQueue redisQueue = null;
    private Integer db;
    private RedisPoolConfig redisPoolConfig;
    private static final ConcurrentMap<Integer, RedisQueue> redisMap   = new ConcurrentHashMap<>();

    public static synchronized RedisQueue db(Integer db) {
        RedisQueue redisQueue = redisMap.get(db);
        if (redisQueue == null) {
            redisQueue = new RedisQueue(db);
            RedisQueue l = redisMap.putIfAbsent(db, redisQueue);
            if (l != null) {
                redisQueue = l;
            }
        }
        return redisQueue;
    }

    public static synchronized RedisQueue db() {
        if (redisQueue == null) {
            redisQueue = new RedisQueue();
        }
        return redisQueue;
    }

    private RedisQueue(Integer db) {
        this.db = db;
    }

    private RedisQueue() {
    }

    /**
     * 获取一个 jedis 客户端
     * 
     * @return
     */
    private Jedis getJedis() {
        redisPoolConfig = SpringContextUtil.getBean(RedisPoolConfig.class);

        return redisPoolConfig.getJedis(db);
    }

    /**
     * List: list入对列
     *
     * @param key
     * @param value
     */
    public void lstPush(String key, String value) {
        // 本地缓存后入队列
        Jedis jedis = getJedis();
        try {
            if (!ValidUtil.isNullStr(value)) {
                jedis.rpush(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * List: 入对列
     *
     * @param key
     * @param value
     *            一次多条
     */
    public void lstPushs(String key, String... value) {
        Jedis jedis = getJedis();
        try {
            jedis.rpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * List: 出对列
     *
     * @param key
     */
    public String lstPop(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.lpop(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * List: 移除指定key下size个value元素 size=2为从顶部移除值为value的2个元素
     * size=-2为从底部移除值为value的2个元素
     *
     * @param key
     * @param size
     * @param value
     */
    public void lstRemove(String key, long size, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.lrem(key, size, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * List: 获取list列表
     *
     * @param key
     * @param start
     *            0
     * @param end
     *            -1 可查全部
     * @return
     */
    public List<String> lstAll(String key, long start, long end) {
        Jedis jedis = getJedis();
        try {
            return jedis.lrange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * List: list size
     *
     * @param key
     * @return
     */
    public Long lstSize(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.llen(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            jedis.close();
        }
    }

    /**
     * List: 返回列表 key 中，下标为 index 的元素
     *
     * @param key
     * @param index
     * @return
     */
    public String lstIndex(String key, int index) {
        Jedis jedis = getJedis();
        try {
            return jedis.lindex(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * List: 裁剪指定范围的list,也就是只保留这个范围内的数据
     *
     * @param key
     * @param start
     * @param end
     */
    public void lstTrim(String key, long start, long end) {
        Jedis jedis = getJedis();
        try {
            jedis.ltrim(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

}
