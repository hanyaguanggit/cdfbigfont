package cn.com.ktm.mt.model.redis;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * redis 配置
 * @author wjq
 *
 */
@Component
@ConfigurationProperties(prefix = "redis", ignoreUnknownFields = false)
@PropertySource("classpath:redis.properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisProperties {

    public int DEFAULT_DB_INDEX; //默认db
    public String HOST_IP; //redis主机IP
    public int HOST_PORT; //redis 端口
    public String PASSWORD;//redis密码
    public int MAX_TOTAL;
    public int MAX_IDLE;
    public int MAX_WAIT_MILLS;
    public boolean TEST_ON_BORROW;

    //redis key 命名规则：分类（CATEGORY）+ 数据结构(map/list/set/pubsub) + _key + _其他标识
    //例：public/map_user 用户信息map, map里是public/map_user_1 用户ID=1的mapkey
    public static String CATEGORY_PUBLIC = "public/"; //通用
    public static String CATEGORY_PRESELL = "presell/"; //预售
    public static String CATEGORY_LOCATION = "location/";//现场

}
