package cn.com.ktm.mt.model.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import cn.com.ktm.mt.model.util.utils.JsonUtil;
import cn.com.ktm.mt.model.util.utils.SpringContextUtil;
import cn.com.ktm.mt.model.util.utils.ValidUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

/**
 * redis 缓存
 * 
 * @author wjq
 *
 */

public class RedisCache  {
    private static RedisCache                               redisCache = null;
    private Integer                                         db;
    private RedisPoolConfig                                 redisPoolConfig;

    private static final ConcurrentMap<Integer, RedisCache> redisMap   = new ConcurrentHashMap<>();

    /**
     * 
     * db:根据索引库获取一个redisCache <br/>
     * 
     * @author wjq
     * @param db
     *            索引库
     * @return
     * @since JDK 1.8
     */
    public static synchronized RedisCache db(Integer db) {
        RedisCache redisCache = redisMap.get(db);
        if (redisCache == null) {
            redisCache = new RedisCache(db);
            RedisCache l = redisMap.putIfAbsent(db, redisCache);
            if (l != null) {
                redisCache = l;
            }
        }
        return redisCache;
    }

    /**
     * 
     * db:获取一个redisCache <br/>
     * 
     * @author wjq
     * @param db
     *            索引库
     * @return
     * @since JDK 1.8
     */
    public static synchronized RedisCache db() {
        if (redisCache == null) {
            redisCache = new RedisCache();
        }
        return redisCache;
    }

    private RedisCache(Integer db) {
        this.db = db;
    }

    private RedisCache() {
    }

    /**
     * 获取一个 jedis 客户端
     * 
     * 
     * @return
     */
    private Jedis getJedis() {
        redisPoolConfig = SpringContextUtil.getBean(RedisPoolConfig.class);
        return redisPoolConfig.getJedis(db);
    }

    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }



    /**
     * 增量+1
     *
     * @param key
     * @return
     */
    public int incrby(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.incrBy(key, 1).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        } finally {
            jedis.close();
        }
    }

    public <T> T get(String key, Class<T> beanClass) {
        String value = get(key);
        if (value == null || value.length() == 0) {
            return null;
        }
        return JsonUtil.fromString(value, beanClass);
    }

    /**
     * 获取redis value (String)
     *
     * @param key
     *         键
     * @param field
     *         字段
     *
     * @return String
     */
    public String hGet(String key, String field) {
        Jedis jedis = getJedis();
        try {
            return jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * 通过key删除
     *
     * @param key
     */
    public void del(String key) {
        Jedis jedis = getJedis();
        try {
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * 添加key value
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        Jedis jedis = getJedis();
        try {
            if (!ValidUtil.isNullStr(value)) {
                jedis.set(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public void set(String key, Object bean) {
        if (bean == null) {
            // TODO : return 或者 删除这个 key
        }
        set(key, JsonUtil.toJson(bean));
    }

    /**
     * 添加key value 并且设置存活时间
     *
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key, String value, int liveTime) {
        Jedis jedis = getJedis();
        try {
            if (!ValidUtil.isNullStr(value)) {
                jedis.set(key, value);
                jedis.expire(key, liveTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * 检查key是否已经存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
    }
    
    /**
     * 检查key是否已经存在
     *
     * @param key
     * @return
     */
    public boolean hexists(String key,String objValue) {
        Jedis jedis = getJedis();
        try {
            return jedis.hexists(key,objValue);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param liveTime
     *            秒
     */
    public void expire(String key, int liveTime) {
        Jedis jedis = getJedis();
        try {
            jedis.expire(key, liveTime);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * 指定具体的过期时间点 例：2020年02月02日02点02分02秒
     *
     * @param key
     * @param timestamp
     */
    public void expireAt(String key, long timestamp) {
        Jedis jedis = getJedis();
        try {
            jedis.expireAt(key, timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * 控制 同一个key 一秒钟只能保存一次
     *
     * @param key
     * @param value
     * @param liveTime
     * @return
     */
    public boolean setNxt(String key, String value, int liveTime) {
        boolean flag = true;
        Jedis jedis = getJedis();
        try {
            if (!ValidUtil.isNullStr(value)) {
                if (jedis.setnx(key, value).intValue() == 1) {
                    jedis.expire(key, liveTime);
                    flag = true;
                } else {
                    flag = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = true;
        } finally {
            jedis.close();
        }
        return flag;
    }

    /**
     * 增量+1
     *
     * @param key
     * @return
     */
    public Long incr(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.incr(key);
            // } catch (Exception e) {
            // e.printStackTrace();
            // return 1; // 这里不应该返回 1, 而是应该让异常抛出去。
        } finally {
            jedis.close();
        }
    }

    /**
     * 通过正则匹配keys
     *
     * @param pattern
     * @return
     */
    public Set<String> pattern(String pattern) {
        Jedis jedis = getJedis();
        try {
            return jedis.keys(pattern);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * 追加字符
     *
     * @param key
     * @param value
     */
    public void append(String key, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.append(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * 排序(适用set,sortset,list这3种数据类型)
     *
     * @param key
     * @param sortingParams
     */
    public List<String> sort(String key, SortingParams sortingParams) {
        Jedis jedis = getJedis();
        try {
            return jedis.sort(key, sortingParams);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * Map: 添加map
     *
     * @param key
     * @param map
     */
    public void mapSets(String key, Map<String, String> map) {
        Jedis jedis = getJedis();
        try {
            jedis.hmset(key, map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * Map: 添加
     *
     * @param key
     * @param mapKey
     * @param mapValue
     */
    public void mapSet(String key, String mapKey, String mapValue) {
        Jedis jedis = getJedis();
        try {
            jedis.hset(key, mapKey, mapValue);
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * Map: 删除指定map集合内的数据
     *
     * @param key
     * @param mapKeys
     */
    public void mapDels(String key, String... mapKeys) {
        Jedis jedis = getJedis();
        try {
            jedis.hdel(key, mapKeys);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * Map: 遍历集合
     *
     * @param key
     * @return
     */
    public List<String> mapGets(String key, String... mapKeys) {
        Jedis jedis = getJedis();
        try {
            return jedis.hmget(key, mapKeys);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }
    /**
     * Map: 遍历集合
     *
     * @param key
     * @return
     */
    public List<String> MGets(String... mapKeys) {
        Jedis jedis = getJedis();
        try {
            return jedis.mget(mapKeys);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * Map: 获取某个map的value
     *
     * @param key
     * @param mapKey
     * @return
     */
    public String mapGet(String key, String mapKey) {
        Jedis jedis = getJedis();
        try {
            return jedis.hget(key, mapKey);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * Map: 遍历key下所有的hash数据
     *
     * @param key
     * @return
     */
    public List<String> mapGetValueAll(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.hvals(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * Map: 遍历key下所有的hash数据(包含map的key与value值)
     *
     * @param key
     * @return
     */
    public Map<String, String> mapGetAll(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.hgetAll(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * Map: 遍历key下所有的map key 值
     *
     * @param key
     * @return
     */
    public Set<String> mapGetKeys(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.hkeys(key);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * Map: 获取集合大小
     *
     * @param key
     * @return
     */
    public Long mapSize(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.hlen(key);
        } catch (Exception e) {

            e.printStackTrace();
            return 0L;
        } finally {
            jedis.close();
        }
    }

    /**
     * Map: 增量 increment
     *
     * @param key
     * @param mapKey
     * @param increment
     * @return
     */
    public Long mapIncrby(String key, String mapKey, int increment) {
        Jedis jedis = getJedis();
        try {
            return jedis.hincrBy(key, mapKey, increment);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return 0L;
    }

    /**
     * Set: 添加元素
     *
     * @param key
     * @param values
     *            可多个value
     */
    public void setAdds(String key, String... values) {
        Jedis jedis = getJedis();
        try {
            jedis.sadd(key, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * Set: 遍历集合
     *
     * @param key
     * @return
     */
    public Set<String> setGet(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.smembers(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }

    /**
     * Set: 删除集合里的元素
     *
     * @param key
     * @param value
     */
    public void setRemove(String key, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.srem(key, value);
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * Set: 集合大小
     *
     * @param key
     * @return
     */
    public Long setSize(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.scard(key);
        } catch (Exception e) {

            e.printStackTrace();
            return 0L;
        } finally {
            jedis.close();
        }
    }

    /**
     * Set: 判断集合中某元素是否存在
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean setExist(String key, String value) {
        Jedis jedis = getJedis();
        try {
            return jedis.sismember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jedis.close();
        }
    }

    /**
     * 发布消息
     *
     * @param channel
     * @param message
     * @return
     */
    public Long pub(String channel, String message) {
        Jedis jedis = getJedis();
        try {
            return jedis.publish(channel, message);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }


    /**
     * 基于 Redis 的锁管理器
     * 
     * @author wyf
     * @date 2017年1月23日 上午9:17:44
     * @Description
     * @version
     *
     */
    public abstract class RedisLockManagement {

        /**
         * 根据 key 来创建 redis 锁，类似于行级锁。
         * 
         * @param lockKey
         */
        public void invokeInLock(String lockKey) {
            // TODO : 实现 Redis 事务
            run();
        }

        /**
         * 事务内运行的方法
         */
        public abstract void run();
    }

}
