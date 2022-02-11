package com.coding.yo.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    public static String getAuthorizationToken(String header) {
        //Authorization: Bearer <Access_token>
        if (header == null || !header.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid authorization header");
        }
        //Remove Bearer from string
        String[] parts = header.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid authorization header");
        }

        //Get token
        return parts[1];
    }
 //       }

    //Httprequest에서 Auhorization이라는 Header를 찾아서 Bearer 이후의 토큰을 찾는 메서드.위의 메서드가 호출되고, 최종적으로 token이 리턴됨
    public static String getAuthorizationToken(HttpServletRequest request) {
        return getAuthorizationToken(request.getHeader("Authorization"));
    }
    }

