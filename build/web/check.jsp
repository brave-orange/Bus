<%-- 
    Document   : check
    Created on : 2017-6-28, 13:17:27
    Author     : yongcheng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>


        <title>管理员验证</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">

        <link rel="stylesheet" type="text/css" href="css/index.css">
        <script type="text/javascript" src="js/index.js">
           
        </script>

    </head>

    <body>
        <div class="div">

            <div class="signin"><h3>管理员验证</h3></div>
            <form id="checkForm" action="Login" method="post" >
                <div><input type="password" id="checkPwd" name="checkPwd" placeholder="请输入管理员验证码" /></div>

                <input  type="button" onclick="check1()"  class="btn"  value="登录"/>

            </form>
        </div>


    </body>
</html>

