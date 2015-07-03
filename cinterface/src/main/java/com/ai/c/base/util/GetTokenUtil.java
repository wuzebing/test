package com.ai.c.base.util;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.c.base.entity.Token;
import com.ai.c.cinterface.par.service.ParSystemService;

public class GetTokenUtil
{
    @Autowired
    private ParSystemService parSystemService;
    
    private static GetTokenUtil getTokenUtil;
    
    @PostConstruct
    public void init()
    {
        getTokenUtil = this;
        getTokenUtil.parSystemService = this.parSystemService;
    }
    
    public static Token getToken(String sysId, String tokenStr)
    {
        String sysKey = getTokenUtil.parSystemService.getSysKeyBySysId(sysId);
        Token token = TokenUtil.getToken(sysId, sysKey, tokenStr);
        return token;
    }
    
    public static Token getToken(HttpServletRequest request)
    {
        String tokenStr = request.getParameter("token");
        String sysId = request.getParameter("sysId");
        Token token = GetTokenUtil.getToken(sysId, tokenStr);
        return token;
    }
}
