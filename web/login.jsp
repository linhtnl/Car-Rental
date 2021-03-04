<%-- 
    Document   : login
    Created on : Jan 23, 2021, 2:59:24 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #17a2b8;
            height: 100vh;
        }
        #login .container #login-row #login-column #login-box {
            margin-top: 30px;
            max-width: 600px;
            height: 450px;
            border: 1px solid #9C9C9C;
            background-color: #EAEAEA;
        }
        #login .container #login-row #login-column #login-box #login-form {
            padding: 20px;
        }
        #login .container #login-row #login-column #login-box #login-form #register-link {
            margin-top: -85px;
        }
    </style>
    <body>
        <div id="login">
            <h3 class="text-center text-white pt-5">Login form</h3>
            <div class="container">
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" class="col-md-6">
                        <div id="login-box" class="col-md-12">
                            <form  action="LoginSvl" method="post" id="demo-form"> 
                                <br>
                                <div class="form-group">
                                    <label for="email" class="text-info" >Email:</label><br>
                                    <input type="text" name="email" id="email" class="form-control" required="" value="${sessionScope.ACC.email}">
                                </div>

                                <div class="form-group">
                                    <label for="password" class="text-info">Password:</label><br>
                                    <input type="password" name="password" id="password" class="form-control" required="" value="${sessionScope.ACC.password}">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col"><a href="register.jsp" >Create new account</a></div>
                                        <div class="col"><i><font color="red">
                                                ${sessionScope.ACC.password_Error} ${sessionScope.ACC.accountError} </i></div> 
                                    </div>
                                    <br>
                                    <!-- reCAPTCHA -->
                                    <div class="g-recaptcha" 
                                         data-sitekey="6LcX7EkaAAAAAFfunBTq3DspHw9GAQrMy3zLzG2H"></div><br>
                                    <button class="btn btn-info btn-md" style="margin-left: 30%"><a href="index.jsp" style="color:white;text-decoration: none;">Back</a></button>
                                    <button type="submit"  style="margin-left: 50%;margin-top: -69px" class="btn btn-primary">Login </button>
                                </div>
                            </form>
                            <span>
                            </span>       
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <!-- reCAPTCHA Libary -->
    <script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
  
</html>
