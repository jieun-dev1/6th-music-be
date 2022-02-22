package com.coding.yo.util;

import org.springframework.security.core.Authentication;

/**
 * Custom Interface 통해 user 가져오는 방법
 * (1) DI 를 완전히 활용하고 (2) AUTHENTICATION 을 어디서든 불러오기 위해서 (@CONTROLLER BEAN 포함) FACADE STATIC ACCESS 를 숨기는 것이다.
 */
public interface AuthenticationFacade {
    Authentication getAuthentication();
}
