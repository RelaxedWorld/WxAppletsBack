package com.zsy.bookkeeping.utils;

import com.alibaba.fastjson.JSON;
import com.zsy.bookkeeping.model.Constants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Map;

public class CommonUtils {

    public static Map getSessionDataByJsCode(String jsCode) {
        RestTemplate restTemplate = new RestTemplate();
        //填充短信模板参数，生成短信发送内容
        Object[] arguments = {
                Constants.WX_APP_ID,
                Constants.WX_APP_SECRET,
                jsCode
        };
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String strbody = restTemplate.exchange(MessageFormat.format(Constants.WX_API_URL, arguments), HttpMethod.GET, entity, String.class).getBody();
        return JSON.parseObject(strbody, Map.class);
    }

    /**
     * @param request 请求
     * @return IP Address
     */
    public static String getIpAddrByRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static void main(String[] args) {
        Map result = CommonUtils.getSessionDataByJsCode("021UEl5G0unrAj2oKi6G0tK65G0UEl5E");
        System.out.println(result.toString());
    }
}



