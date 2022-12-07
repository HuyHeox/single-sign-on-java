<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>index</title>
 </head>
 <body>
    <h1>Welcome ${user.username}</h1>
        <form  action="/user/check/role1" method="GET" >
             <th><input type="submit" value="Role 1" name="button" /></th>
         </form>
         <form  action="/user/check/role2" method="GET">
             <th><input type="submit" value="Role 2" name="button" /></th>
         </form>
         <form  action="/user/check/role3" method="GET">
             <th><input type="submit" value="Role 3" name="button" /></th>
         </form>
         <br/><br/>
         <a href="/user/login">login</a>
         <a href="/user/logout">logout</a>
 </body>
 </html>
