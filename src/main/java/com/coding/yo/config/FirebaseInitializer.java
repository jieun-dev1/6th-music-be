package com.coding.yo.config;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseInitializer {

    @Bean
    public FirebaseAuth getFirebaseAuth(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(FirebaseApp.getInstance());
        return firebaseAuth;
    }
}