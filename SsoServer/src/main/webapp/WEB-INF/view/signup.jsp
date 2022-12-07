<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>index</title>
 </head>
 <body>
     <form name='signupReqDTO' action="/user/signup" method="POST">
        Username: <input type="text" name="username"/><br/><br/>
        Password: <input type="password" name="password"/><br/><br/>
        <input type="checkbox" name="selected" value="ROLE_ADMIN"> ROLE_ADMIN <br/><br/>
        <input type="checkbox" name="selected" value="ROLE_CONTENT"> ROLE_CONTENT <br/><br/>
        <input type="checkbox" name="selected" value="ROLE_LEADER"> ROLE_LEADER <br/><br/>
        <input type="checkbox" name="selected" value="ROLE_USER"> ROLE_USER <br/><br/>
        <input type="submit" value="sign up"/>
        </form>
 </body>
 </html>
