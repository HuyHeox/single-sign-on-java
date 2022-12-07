//package com.ks.sso.config;
//
//import com.googlecode.objectify.ObjectifyFactory;
//import com.googlecode.objectify.ObjectifyService;
//import com.ks.sso.model.User;
//
//import javax.annotation.Nonnull;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//
//public class ObjectifyInitializer implements ServletContextListener {
//
//    @Override
//    public void contextInitialized(@Nonnull final ServletContextEvent SCE) {
//        ObjectifyFactory oFactory = ObjectifyService.factory();
//        oFactory.register(User.class);
//        oFactory.begin();
//        ObjectifyService.init(new ObjectifyFactory(
//                DatastoreOptions.newBuilder()
//                        .setProjectId("PROJECT_ID")
//                        .build()
//                        .getService()
//        ));
//    }
//
//}
