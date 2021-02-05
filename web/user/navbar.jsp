<%-- 
    Document   : navbar
    Created on : Feb 4, 2021, 4:11:13 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form name="home" action="../MainController" method="POST">
    <input type="hidden" name="action" value=""/>   
    <!--header-->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <button type="button" onclick="Home()" class="nav-link btn btn-link">Home</button>
                </li>
            </ul>
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item active">
                     <button type="button"  class="nav-link btn btn-link">Cart</button>
                </li>
                <li class="nav-item active">
                    <div class="dropdown">
                        <button type="button" class="nav-link btn btn-link dropdown-toggle" data-toggle="dropdown">
                            <font color="#feba10" >${sessionScope.ACC.name}</font>
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#">Invoice</a>
                            <a href="../Logout" class="dropdown-item" >Logout</a>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</form>
