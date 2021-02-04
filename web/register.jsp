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

            max-width: 600px;
            height: 680px;
            border: 1px solid #9C9C9C;
            background-color: #EAEAEA;
        }
        #login .container #login-row #login-column #login-box #login-form {
            padding: 10px;
        }
        #login .container #login-row #login-column #login-box #login-form #register-link {
            margin-top: -65px;
        }
    </style>
    <body>
        <div id="login">
            <h3 class="text-center text-white pt-5">Register form</h3>
            <div class="container">
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" class="col-md-6">
                        <div id="login-box" class="col-md-12">
                            <form  action="RegisterSvl" method="post" name="regis" id="demo-form"> 
                                <br>
                                <div class="form-group">
                                    <label for="email" class="text-info" >Email:</label><i style="color:red">*</i><br>                      
                                    <input type="email" name="email" id="email" class="form-control" required="" value="${sessionScope.ACC.email}">
                                    <i><font color="red"><span id="err_email"></span></font></i><br>
                                    <label for="password" class="text-info">Password:</label><i style="color:red">*</i><br>
                                    <input type="password" name="password" id="password" class="form-control" required="" value="${sessionScope.ACC.password}">
                                    <i><font color="red"><span id="err_pass"></span></font></i><br>
                                    <label for="password" class="text-info">Confirm Password:</label><i style="color:red">*</i><br>
                                    <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" required="" value="${sessionScope.ACC.confirmPass}">
                                    <i><font color="red"><span id="err_confirmPass"></span></font></i><br>
                                    <label for="phone" class="text-info">Phone:</label><i style="color:red">*</i><br>
                                    <input type="tel" name="phone" id="phone" class="form-control" required="" value="${sessionScope.ACC.phone}"  pattern="[0-9]{10}">
                                    <i><font color="red"><span id="err_phone"></span></font></i><br>
                                    <label for="name" class="text-info">Name:</label><i style="color:red">*</i><br>
                                    <input type="text" name="name" id="name" class="form-control" required="" value="${sessionScope.ACC.name}">
                                    <i><font color="red"><span id="err_name"></span></font></i><br>
                                    <label for="address" class="text-info">Address:</label><i style="color:red">*</i><br>
                                    <input type="text" name="address" id="address" class="form-control" required="" value="${sessionScope.ACC.address}">
                                    <i><font color="red"><span id="err_addr"></span></font></i><br>
                                    <span><i style="color: red">
                                            ${sessionScope.ACC.accountError} </i>
                                    </span>
                                </div>
                                <div class="form-group">
                                    <button class="btn btn-secondary btn-md" style="margin-left: 30%"><a href="index.jsp" style="color:white;text-decoration: none;">Back</a></button>
                                    <input type="button" onclick="register()" class="btn btn-info btn-md" value="Register" style="margin-left: 5%">
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
    <script>
        function clear() {
            document.getElementById('err_email').innerHTML = '';
            document.getElementById('err_pass').innerHTML = '';
            document.getElementById('err_confirmPass').innerHTML = '';
            document.getElementById('err_phone').innerHTML = '';
            document.getElementById('err_name').innerHTML = '';
            document.getElementById('err_addr').innerHTML = '';
        }
        function register() {
            clear();
            var email = document.getElementById("email").value;
            var pass = document.getElementById("password").value;
            var confirmPass = document.getElementById("confirmPassword").value;
            var phone = document.getElementById("phone").value;
            var name = document.getElementById("name").value;
            var address = document.getElementById("address").value;
            var flag = 1;
            //check email
            if (email.trim().length == 0) {
                document.getElementById('err_email').innerHTML = 'Must fill and do not contained only space characters!';
                flag = 0;
            } else if (email.length <= 50) {
                var pattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
                if (!email.match(pattern)) {
                    document.getElementById('err_email').innerHTML = 'Wrong email format!';
                    flag = 0;
                }
            } else if (email.length > 50) {
                document.getElementById('err_email').innerHTML = 'Max length is 50 chars!';
                flag = 0;
            }
            //Check pass
            var length = pass.length;
            if (pass.trim().length == 0) {
                document.getElementById('err_pass').innerHTML = 'Must fill and do not contained only space characters!';
                flag = 0;
            } else if (length > 50 || length < 6) {
                document.getElementById('err_pass').innerHTML = 'Length is 6-50!';
                flag = 0;
            }
            //check confirm pass
            var length = pass.length;
            if (pass.length == 0) {
                document.getElementById('err_cofirmPass').innerHTML = 'Must fill!';
                flag = 0;
            } else if (confirmPass != pass) {
                document.getElementById('err_confirmPass').innerHTML = 'Not match to password';
                flag = 0;
            }
            //check phone
            var pattern = '(09|03|07|08|05)+([0-9]{8})';
            if (!phone.match(pattern)) {
                document.getElementById('err_phone').innerHTML = 'Only contains 10 numbers';
                flag = 0;
            }
            //check name
            if (name.trim().length == 0) {
                document.getElementById('err_name').innerHTML = 'Must fill and do not contained only space characters!';
                flag = 0;
            } else if (name.length > 200) {
                document.getElementById('err_name').innerHTML = 'Max length is 200 chars!';
                flag = 0;
            }
            //check adress
            if (address.trim().length == 0) {
                document.getElementById('err_addr').innerHTML = 'Must fill and do not contained only space characters!';
                flag = 0;
            } else if (address.length > 500) {
                document.getElementById('err_addr').innerHTML = 'Max length is 500 chars';
                flag = 0;
            }

            //FINAL
            if (flag == 1) {
                document.forms['regis'].submit();
            }
        }
    </script>
</html>
