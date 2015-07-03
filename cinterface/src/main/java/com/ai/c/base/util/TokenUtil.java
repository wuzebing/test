package com.ai.c.base.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.ai.c.base.encrypt.Cryptogram;
import com.ai.c.base.entity.Token;

/**
 * @author zouning
 * @time 2014-7-30 上午10:04:05
 * 
 */

public final class TokenUtil
{
    /** 连接符 */
    private static final String CONNECTOR = "$";
    
    private static final String SPLIT = "\\$";
    
    private static final long expireDuration = 24 * 60 * 60 * 1000;
    
    /**
     * 生成KToken
     * 
     * @param sysId
     *            发起认证平台编码
     * @param sysKey
     *            发起认证平台的密钥
     * @param userId
     *            用户唯一标识
     * @param username
     *            用户名称
     * @param userType
     *            用户类型
     * @param passwordType
     *            密码类型
     * @param expireTime
     *            失效时间,yyyy-MM-dd HH:mm:ss
     * @param ip
     *            用户登录
     * @return null 如果生成失败
     */
    public static String generateToken(String sysId, String sysKey, String userId, String username, String userType,
        String passwordType, String userAttribute, String statusId, String provinceid, String areaid, String jobId)
    {
        try
        {
            Date now = new Date(System.currentTimeMillis() + expireDuration);
            String expireTime = TimestampUtil.parserDateToString(now);
            // digest
            String digest = generateDigest(sysId, userId, username, userType, passwordType, expireTime);
            
            // auth
            StringBuilder sb = new StringBuilder();
            sb.setLength(0);
            sb.append(userId).append(CONNECTOR);
            sb.append(username).append(CONNECTOR);
            sb.append(userType).append(CONNECTOR);
            sb.append(passwordType).append(CONNECTOR);
            sb.append(expireTime).append(CONNECTOR);
            sb.append(userAttribute).append(CONNECTOR);
            sb.append(statusId).append(CONNECTOR);
            sb.append(provinceid).append(CONNECTOR);
            sb.append(areaid).append(CONNECTOR);
            sb.append(jobId).append(CONNECTOR);
            sb.append(digest);
            String auth = Cryptogram.encryptByKey(sb.toString(), sysKey);
            
            // token
            return URLEncoder.encode(sysId + auth, "UTF-8");
            // return sysId + auth;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 解释token
     * 
     * @param sysId
     * @param sysKey
     * @param token
     * @return Token,<br/>
     *         {@link Token#getStatusCode()}为200表示正确返回,不返回null值
     */
    public static Token getToken(String sysId, String sysKey, String token)
    {
        if (token == null || token.length() == 0)
        {
            return new Token(602);// 非法token
        }
        if (!token.startsWith(sysId))
        {
            return new Token(601);// Token的生成平台与当前平台不一致
        }
        try
        {
            token = URLDecoder.decode(token, "UTF-8");
            String auth = token.substring(sysId.length());
            String mingwen = Cryptogram.decryptByKey(auth, sysKey);
            String[] fields = mingwen.split(SPLIT);
            // sb.append(userId).append(CONNECTOR);
            // sb.append(username).append(CONNECTOR);
            // sb.append(userType).append(CONNECTOR);
            // sb.append(passwordType).append(CONNECTOR);
            // sb.append(expireTime).append(CONNECTOR);
            // sb.append(userAttribute).append(CONNECTOR);
            // sb.append(statusId).append(CONNECTOR);
            // sb.append(digest);
            
            // 解释token
            Token tokenObj = new Token();
            tokenObj.setSysId(sysId);
            tokenObj.setUserId(fields[0]);
            tokenObj.setUserName(fields[1]);
            tokenObj.setUserType(fields[2]);
            tokenObj.setPasswordType(fields[3]);
            tokenObj.setExpireTime(fields[4]);
            tokenObj.setUserAttribute(fields[5]);
            tokenObj.setStatusId(fields[6]);
            tokenObj.setProvinceId(fields[7]);
            tokenObj.setAreaId(fields[8]);
            tokenObj.setJobId(fields[9]);
            String digestFromToken = fields[10];
            if (digestFromToken == null || digestFromToken.length() == 0)
            {
                return new Token(602);
            }
            String digestFromGeneration = tokenObj.generateDigest();
            
            if (!digestFromToken.equals(digestFromGeneration))
            {
                return new Token(602);
            }
            
            // 校验失效日期
            Date expireTime = TimestampUtil.parserToDate(tokenObj.getExpireTime());
            if (System.currentTimeMillis() > expireTime.getTime())
            {
                tokenObj.setStatusCode(603);
                return tokenObj;
            }
            
            tokenObj.setStatusCode(200);
            return tokenObj;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new Token(604);// 解密失败
        }
        
    }
    
    /**
     * 生成摘要,是哈希值,不可逆
     * 
     * @param sysId
     * @param userId
     * @param username
     * @param userType
     * @param passwordType
     * @param expireTime
     * @param ip
     * @return
     * @throws Exception
     */
    public static String generateDigest(String sysId, String userId, String username, String userType,
        String passwordType, String expireTime)
        throws Exception
    {
        StringBuilder sb = new StringBuilder();
        // digest
        sb.append(sysId).append(userId).append(username).append(userType);
        sb.append(passwordType).append(expireTime);
        String digest = Cryptogram.getBase64HashString(sb.toString());
        return digest;
    }
    
    /**
     * 获取Token对象
     * <li>先查看request是否存在token属性</li>
     * <li>若不存在，根据request生成Token对象</li>
     * 
     * @param request
     * @return
     */
    public static Token getToken(HttpServletRequest request){
    	Token token = (Token) request.getAttribute("token");
    	if(token == null){
    		token = GetTokenUtil.getToken(request);
    	}
    	return token;
    }
    
}
