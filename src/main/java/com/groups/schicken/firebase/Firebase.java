package com.groups.schicken.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.FileInputStream;

//@Configuration
public class Firebase {
//    @PostConstruct
    public void initialize() throws Exception {
        File file = new ClassPathResource("firebaseKey.json").getFile();

        FileInputStream serviceAccount =
                new FileInputStream(file);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("schicken-6e89b.appspot.com")
                .build();

        FirebaseApp.initializeApp(options);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
