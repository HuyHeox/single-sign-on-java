//package com.ks.sso.config;
//
//
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.messaging.FirebaseMessaging;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Objects;
//
//@Configuration
//public class FirebaseConfig {
//	@Autowired
//    ResourceLoader resourceLoader;
//	
//	Resource resource = resourceLoader.getResource("classpath:serviceAccountKey.json");
//
//	InputStream input = resource.getInputStream();
//
//	File file = resource.getFile();
//	
//    @Bean
//    FirebaseMessaging firebaseMessaging() throws IOException {
//        ClassLoader classLoader = FirebaseApplication.class.getClassLoader();
//		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
//
//        FileInputStream serviceAccount =
//                new FileInputStream(file.getAbsolutePath());
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://actvn-edu-default-rtdb.firebaseio.com/")
//                .build();
//
//        FirebaseApp app = FirebaseApp.initializeApp(options);
//        return FirebaseMessaging.getInstance(app);
//    }
//
//}
