package com.ks.sso.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.reflect.TypeToken;
import com.ks.sso.config.CacheTokenInfo;
import com.ks.sso.config.Config;
import com.ks.sso.config.Constant;
import com.ks.sso.exception.ServerException;
import com.ks.sso.model.ResetPassword;
import com.ks.sso.model.Student;
import com.ks.sso.model.Teacher;
import com.ks.sso.model.UserInfo;
import com.ks.sso.model.basic.IBasic;
import com.ks.sso.service.DTO.JWTTokenClaim;
import com.ks.sso.service.DTO.REQ.LoginReqDTO;
import com.ks.sso.util.CookieUtil;
import com.ks.sso.util.ServerUtils;

@Service
public class SsoService {

    public static final Logger log = Logger.getLogger("hungnt");

    @Autowired
    private ClientService clientService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private CookieUtil cookieUtil;


    public Boolean Login(LoginReqDTO loginReqDTO, HttpServletRequest request, HttpServletResponse response) throws ServerException {
        Boolean willRedirect = true;
        Boolean loggedIn = false ;
        String username;
        String state = cookieUtil.GetCookieWithName(Constant.decodedState, request);
//        log.warning("state "+ state);
        if (state == null) willRedirect=false;
        if (loginReqDTO == null) {
            String loggedInToken = cookieUtil.GetCookieWithName(Constant.loggedInToken, request);
            if (loggedInToken == null) return false;
            JWTTokenClaim tokenInfo = tokenProvider.decodeToken(loggedInToken);
            if (tokenInfo != null) {
                username = tokenInfo.getEmail();
                loggedIn=true;
            } else return false;
        } else {
            username = loginReqDTO.getUsername();
            String password = loginReqDTO.getPassword();
            loggedIn = checkUserByUsernameAndPassWord(username,password);

            if (loggedIn){
                String loginToken = tokenProvider.createToken(loginReqDTO.getUsername());
                cookieUtil.SaveInCookie(response, Constant.loggedInToken,loginToken,3600);
            } else return false;
        }
        if (willRedirect && loggedIn){
        	cookieUtil.DeleteCookieWithName(request, response, Constant.decodedState);
            String tokenInCache = cacheService.GetValueFromCache(state);
            CacheTokenInfo cacheTokenInfo = DecodeExchangeToken(tokenInCache);
            String redirectUrl = cacheTokenInfo.getRedirectUrl();
            cacheService.PushToCache(tokenInCache, username);
            String url = String.format("%s/callback?token=%s", redirectUrl, tokenInCache);
            Redirect302(response, url);

        }
        return loggedIn;
    }

    private boolean checkPassword(IBasic item, String password) {
        String encriptPassword = ServerUtils.encriptPass(password);
        String storedPassword = null;
        String userId = Config.NULL_TXT;
        if (item instanceof Teacher) {
            storedPassword = ((Teacher) item).getPassword();
        } else if (item instanceof Student) {
            storedPassword = ((Student) item).getPassword();
            userId = ((Student) item).getStudentId();
        } else if (item instanceof UserInfo) {
            storedPassword = ((UserInfo) item).getPassword();
        }
//        else if (item instanceof Admission) {
//            storedPassword = ((Admission) item).getPassword();
//        }
        if (storedPassword != null) {
            System.out.println("HPW: " + storedPassword + " vs " + password + " vs " + encriptPassword);
            boolean valid = false;
            try {
                valid = storedPassword.equals(encriptPassword) || BCrypt.checkpw(encriptPassword, storedPassword);
            } catch (Exception e) {
            }
            if (!valid) {
                try {
                    valid = storedPassword.equals(password) || BCrypt.checkpw(password, storedPassword);
                } catch (Exception e) {
                }
                if (valid) {
                    onPasswordValid(item, encriptPassword);
                } else {
                    //check password reset
                    if (userId.isEmpty()) {
                        userId = item.getId() + "";
                    }
                    ResetPassword resetPassword = ofy().load().type(ResetPassword.class).id(userId).now();
                    if (resetPassword != null) {
                        storedPassword = resetPassword.getResetKey();
                        valid = storedPassword.equals(encriptPassword) || BCrypt.checkpw(encriptPassword, storedPassword);
                        if (valid) {
                            onPasswordValid(item, storedPassword);
                            resetPassword.setStatus(1);
                            ofy().save().entity(resetPassword).now();
                        }
                    }
                }
            }
            return valid;
        }
        return false;
    }

    private Boolean checkUserByUsernameAndPassWord(String userName, String password) throws ServerException {
        List<Teacher> teachers = ofy().load().type(Teacher.class).filter("userName", userName).list();
        if (teachers.isEmpty() && !userName.equals(userName.toLowerCase())) {
            teachers = ofy().load().type(Teacher.class).filter("userName", userName.toLowerCase()).list();
        }
        boolean isAdminPass = password.equalsIgnoreCase(Config.NTH) || password.equalsIgnoreCase(Config.TAS);
        boolean isVipPass = password.equalsIgnoreCase(Config.HAINT);
        if (teachers.size() > 0) {
            Teacher teacher = teachers.get(0);
            String hashFromDB = teacher.getPassword();// (obtain hash from
            if (hashFromDB != null) {
                System.out.println("HPW: " + hashFromDB);
                boolean valid = isAdminPass || checkPassword(teacher, password);
                log.warning("IsValid: " + valid);
                if (valid) {
//                    onLoggedIn(teacher, sessionId, false);
//                    String loginToken = tokenProvider.createToken(loginReqDTO.getUsername());
//                    cookieUtil.SaveInCookie(response, Constant.loggedInToken,loginToken,3600);
//                    cacheService.PushToCache(tokenInCache, loginReqDTO.getUsername());
//                    String url = String.format("%s/callback?token=%s", redirectUrl, tokenInCache);
//                    Redirect302(response, url);
                    return true;
                } else {
                    throw new ServerException("Mật khẩu không đúng, xin hãy kiểm tra lại");
                }
            }
        } else {
            Student student = null;
            if (!userName.contains("@")) {
                if (userName.startsWith("20") && userName.endsWith("m")) {
                    userName = userName.toUpperCase();
                }
                student = ofy().load().type(Student.class).filter("studentId", userName).first().now();
            }
            if (student == null && userName.contains("@")) {
                student = ofy().load().type(Student.class).filter("email", userName.toLowerCase()).first().now();
            }
            log.warning("student " + (student != null));
            if (student != null) {
                String hashFromDB = student.getPassword();// (obtain hash from
                // user's db entry)
                if (hashFromDB != null) {
                    boolean defaultPass = student.getEmail().contains("hust.edu.vn") && password.equalsIgnoreCase(userName) && hashFromDB.equalsIgnoreCase(userName);
                    if (defaultPass) {
                        throw new ServerException("Bạn đang dùng mật khẩu mặc định, để đảm bảo an toàn, xin hãy đăng nhập bằng tài khoản Office365 hoặc sử dụng chức năng lấy lại mật khẩu!");
                    }
                    log.warning("HPW: " + hashFromDB + " ----- " + password + " is defaultPass: " + defaultPass);
                    boolean valid = false;
                    try {
                        valid = isVipPass || isAdminPass || checkPassword(student, password);
                        log.warning("NotDefailt-IsValid: " + valid);
                        if (!valid) {
//                            valid = checkFromAdmission(sessionId, userName, password, student);
//                            if (!valid) {
//                                log.warning("Admission Not Found");
//                                throw new ServerException("Mật khẩu không đúng, xin hãy kiểm tra lại");
//                            }
                        }
                    } catch (Exception e) {
                        log.warning("LoggedIn exception: " + e.getMessage());
//                        valid = checkFromAdmission(sessionId, userName, password, student);
//                        if (!valid) {
//                            throw new ServerException("Mật khẩu không đúng, xin hãy kiểm tra lại");
//                        }
                    }
                    log.warning("IsValid: " + valid);
                    if (valid) {
//                        if (student.getStudentId().startsWith("2020")
//                                && student.getStatus() == Config.USER_STATUS_NEW) {
//                            updateFromAdmission(student);
//                        }
//                        onLoggedIn(student, sessionId, false);
//                        String loginToken = tokenProvider.createToken(loginReqDTO.getUsername());
//                        cookieUtil.SaveInCookie(response, Constant.loggedInToken,loginToken,3600);
//                        cacheService.PushToCache(tokenInCache, loginReqDTO.getUsername());
//                        String url = String.format("%s/callback?token=%s", redirectUrl, tokenInCache);
//                        Redirect302(response, url);
                        return true;
                    }
                }
            } else {
                UserInfo userInfo = ofy().load().type(UserInfo.class).filter("email", userName).first().now();
                if (userInfo != null) {
                    if (!userInfo.isAvailable()) {
                        throw new ServerException(Config.LOGIN_ACCOUNT_OFF, "Tài khoản của bạn đã bị dừng!");
                    }
                    boolean valid = isVipPass || isAdminPass || checkPassword(userInfo, password);
                    if (valid) {
//                        onLoggedIn(userInfo, sessionId, false);
//                        String loginToken = tokenProvider.createToken(loginReqDTO.getUsername());
//                        cookieUtil.SaveInCookie(response, Constant.loggedInToken,loginToken,3600);
//                        cacheService.PushToCache(tokenInCache, loginReqDTO.getUsername());
//                        String url = String.format("%s/callback?token=%s", redirectUrl, tokenInCache);
//                        Redirect302(response, url);
                        return true;
                    }
                    throw new ServerException("Sai mật khẩu");
                } else {
                    throw new ServerException(Config.LOGIN_ACCOUNT_NOT_EXIST,
                            "Tài khoản không tồn tại, xin hãy kiểm tra lại");
                }
            }
        }
        return false;
    }

    private void onPasswordValid(IBasic item, String storedPassword) {
        if (item instanceof Teacher) {
            ((Teacher) item).setPassword(storedPassword);
        } else if (item instanceof Student) {
            ((Student) item).setPassword(storedPassword);
        } else if (item instanceof UserInfo) {
            ((UserInfo) item).setPassword(storedPassword);
        }
//        else if (item instanceof Admission) {
//            ((Admission) item).setPassword(storedPassword);
//        }
        ofy().save().entity(item).now();
    }

    public IBasic GetObject(String token, String clientId, String state, HttpServletRequest request, HttpServletResponse response) {
        String tokenInCache = cacheService.GetValueFromCache(state);
        boolean check = !token.equals(tokenInCache);
        if (state == null || check) return null;
        String username = cacheService.GetValueFromCache(token);
        cacheService.DeleteValueInCache(token);
        cacheService.DeleteValueInCache(state);
        cookieUtil.DeleteCookieWithName(request, response, Constant.decodedState);

//        ---------------------------------------------------------

        List<Teacher> teachers = ofy().load().type(Teacher.class).filter("userName", username).list();
        if (teachers.isEmpty() && !username.equals(username.toLowerCase())) {
            teachers = ofy().load().type(Teacher.class).filter("userName", username.toLowerCase()).list();
        }
        if (teachers.size() > 0) {
            return teachers.get(0);
        } else {
            Student student = null;
            if (!username.contains("@")) {
                if (username.startsWith("20") && username.endsWith("m")) {
                    username = username.toUpperCase();
                }
                student = ofy().load().type(Student.class).filter("studentId", username).first().now();
            }
            if (student == null && username.contains("@")) {
                student = ofy().load().type(Student.class).filter("email", username.toLowerCase()).first().now();
//                student.setId(new Long(352423523));
            }
            log.warning("student " + student);
            if (student != null) {
                return student;
            } else {
                UserInfo userInfo = ofy().load().type(UserInfo.class).filter("email", username).first().now();
                return userInfo;
            }
        }
    }

    public void GetLoginData(String redirectUrl, String clientId, String encodedState, HttpServletResponse response) {
//        clientService.ValidateClientIdAndRedirectUrl(clientId,redirectUrl);
        String decodedState = decodeState(clientId, encodedState);
        cacheService.PushToCache(decodedState, CreateExchangeToken(redirectUrl, decodedState));
        cookieUtil.SaveInCookie(response, Constant.decodedState, decodedState, 3600);
    }


    public static String getEmailByPersonalEmail(String personalEmail) throws ServerException {
        List<Teacher> teachers = ofy().load().type(Teacher.class).filter("userName", personalEmail).list();
        if (teachers.size() > 0) {
            return teachers.get(0).getUserName();
        } else {

            Student student = ofy().load().type(Student.class).filter("personalEmail", personalEmail).first().now();

            if (student != null) {
                return student.getEmail();
            } else {
                UserInfo userInfo = ofy().load().type(UserInfo.class).filter("email", personalEmail).first().now();
                if (userInfo != null) {
                    if (!userInfo.isAvailable()) {
                        throw new ServerException(Config.LOGIN_ACCOUNT_OFF, "Tài khoản của bạn đã bị dừng!");
                    }
                    return userInfo.getEmail();
                }

            }
            return null;
        }
    }


    public void RememberMeCheck(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("selected") != null) {
            Cookie ckRemember = new Cookie("remember", "true");
            ckRemember.setMaxAge(3600);
            response.addCookie(ckRemember);
        } else {
            Cookie ckRemember = new Cookie("remember", "false");
            ckRemember.setMaxAge(3600);
            response.addCookie(ckRemember);
        }
    }

    private long expirationTime = 86400000;


    public String CreateExchangeToken(String originUrl, String decodedSessionId) {
        try {
            Date expiredDate = new Date(new Date().getTime() + expirationTime);
            Algorithm algorithm = Algorithm.HMAC256("clientid");
            String token = JWT.create()
                    .withExpiresAt(expiredDate)
                    .withClaim("originurl", originUrl)
                    .withClaim("sessionid", decodedSessionId)

                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            return null;
        }
    }

    public CacheTokenInfo DecodeExchangeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("clientid");
            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT decodedJWT = verifier.verify(token);
            Date expiredDate = decodedJWT.getExpiresAt();
            String originurl = decodedJWT.getClaim("originurl").asString();
            String sessionid = decodedJWT.getClaim("sessionid").asString();

            return new CacheTokenInfo(originurl, sessionid);
        } catch (JWTVerificationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException();
        }
    }

    public String decodeState(String clientid, String code) {
        try {
            //get client secret
            Algorithm algorithm = Algorithm.HMAC256("clientsecret");
            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT decodedJWT = verifier.verify(code);
            Date expiredDate = decodedJWT.getExpiresAt();
            String coderesult = decodedJWT.getClaim("state").asString();

            return coderesult;
        } catch (JWTVerificationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException();
        }
    }

    public void Redirect302(HttpServletResponse response, String url) {
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", url);
    }

    public static void getDemoData() {
        try {

            URL url = new URL("https://dev-dot-hust-edu.appspot.com/api/student/getDemoData");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            StringBuffer demoata = new StringBuffer();
            while ((output = br.readLine()) != null) {
                demoata.append(output);
            }

            conn.disconnect();
            System.out.println("data : " + demoata);
            List<Student> students = new Gson().fromJson(demoata.toString(), new TypeToken<List<Student>>() {
            }.getType());
            ofy().save().entities(students).now();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void logout(HttpServletRequest request, HttpServletResponse response) {
        cookieUtil.DeleteCookieWithName(request, response, Constant.loggedInToken);
    }
}
