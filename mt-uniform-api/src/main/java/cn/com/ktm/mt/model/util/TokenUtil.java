package cn.com.ktm.mt.model.util;

import cn.com.ktm.mt.model.redis.RedisCache;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {

	private static final Logger LOG = LoggerFactory.getLogger(TokenUtil.class);

	public static String createToken(String phone, long id, String name, String userType, String operateFlg) {
		String userToken = Jwts.builder()
				.setSubject("GuGong")
				.setExpiration(new Date(new Date().getTime() + 1000 * 60 * 60 * 24 * 600))
				.claim("phone", phone)
				.claim("id", id)
				.claim("name", name)
				.claim("userType", userType)
				.claim("operateFlg", operateFlg)

				.signWith(SignatureAlgorithm.HS512, CommonConstants.SECRETKEY).compact();

		return userToken;
	}

	public static String getPhone(String token) throws Exception {
		Claims claims = Jwts.parser().setSigningKey(CommonConstants.SECRETKEY).parseClaimsJws(token).getBody();
		Map map = new HashMap();
		String phone = (String) claims.get("phone");
		return phone;
	}

	public static long getUserId(String token) throws Exception {

		Claims claims = Jwts.parser().setSigningKey(CommonConstants.SECRETKEY).parseClaimsJws(token).getBody();
		Map map = new HashMap();
		long id = (Integer) claims.get("id");
		return id;
	}

	public static String getOperateFlg(String token) throws Exception {
		Claims claims = Jwts.parser().setSigningKey(CommonConstants.SECRETKEY).parseClaimsJws(token).getBody();
		Map map = new HashMap();
		String operateFlg = (String) claims.get("operateFlg");
		return operateFlg;
	}

	public static String getName(String token) throws Exception {
		Claims claims = Jwts.parser().setSigningKey(CommonConstants.SECRETKEY).parseClaimsJws(token).getBody();
		Map map = new HashMap();
		String name = (String) claims.get("name");
		return name;
	}

	public static String getUserType(String token) throws Exception {
		Claims claims = Jwts.parser().setSigningKey(CommonConstants.SECRETKEY).parseClaimsJws(token).getBody();
		Map map = new HashMap();
		String name = (String) claims.get("userType");
		return name;
	}

	/**
	 * 验证token
	 *
	 * @param token token
	 * @param DEFAULT_DB_INDEX redis
	 * @return String[]{onlineUserId,phone}
	 * @throws Exception
	 */
	public static String[] validationToken(String token, Integer DEFAULT_DB_INDEX) {
		try {
			long userId = 0;
			userId = TokenUtil.getUserId(token);
			String phone = TokenUtil.getPhone(token);
			String userType = TokenUtil.getUserType(token);
			String strKey = AppConstants.REDIS_TOKEN_PREFIX + userId;
			RedisCache redisCache = RedisCache.db(DEFAULT_DB_INDEX);
			Boolean checkLogin = redisCache.exists(strKey);
			if (!checkLogin) {
				return null;
			}
			String onlineUserId = String.valueOf(userId);
			return new String[]{onlineUserId,phone,userType};
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
