<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
  <br/><br/>


  <a href="/sso/login">Login</a> 
  <form name='notificationRequestDto' action="/notification/topic" method='POST'>
    		<table>
    			<tr>
    				<td>target:</td>
    				<td><input type='text' name='target'></td>
    			</tr>
    			<tr>
    				<td>title:</td>
    				<td><input type='text' name='title' /></td>
    			</tr>
    			<tr>
                    				<td>content</td>
                    				<td><input type='text' name='content'></td>
                 </tr>
    				<td colspan='2'><input name="submit" type="submit" value="submit" /></td>
    			</tr>
    		</table>

    </form>


</body>
</html>