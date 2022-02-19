package com.coding.yo.security.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FirebaseConfig {
    /**
     * Admin SDK 인증 절차 (https://firebase.google.cn/docs/admin/migrate-auth-50?hl=ko)
     * 기존 코드: GoogleCredentials.getApplicationDefault() 로 Google_Application_Credentials 환경변수 접근 시도 -> 실패.
     * ClassPathResource 로 파일 경로 찾고 읽어옴 (getInputStream)
     * GoogleCredentials.fromStream: JSON 포맷의 구글 Credential 가져옴
     * FirebaseApp 은 FirebaseSDK가 진입할 때 거치는 포인트고 FirebaseAPI 는 이 앱을 사용하는 것이 default. 따라서 초기화 필요.
     *
     * To do: KEY 없을 때의 예외처리 코드 필요.
     */
    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {

        ClassPathResource key = new ClassPathResource("serviceAccountKey.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(key.getInputStream()))
                .build();
        FirebaseApp.initializeApp(options);
        return FirebaseAuth.getInstance(FirebaseApp.getInstance());
    }

}


