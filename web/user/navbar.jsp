<%-- 
    Document   : navbar
    Created on : Feb 4, 2021, 4:11:13 PM
    Author     : ASUS
--%>
<style>
    .navbar{background:#222222;}
    .nav-item::after{content:'';display:block;width:0px;height:2px;background:#fec400;transition: 0.2s;}
    .nav-item:hover::after{width:100%;}
    .navbar-dark .navbar-nav .active > .nav-link, .navbar-dark .navbar-nav .nav-link.active, .navbar-dark .navbar-nav .nav-link.show, .navbar-dark .navbar-nav .show > .nav-link,.navbar-dark .navbar-nav .nav-link:focus, .navbar-dark .navbar-nav .nav-link:hover{color:#fec400;}
    .nav-link{padding:15px 5px;transition:0.2s;}
    .dropdown-item.active, .dropdown-item:active{color:#212529;}
    .dropdown-item:focus, .dropdown-item:hover{background:#fec400;}
    h3.h3{text-align:center;margin:1em;text-transform:capitalize;font-size:1.7em;}
</style>
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
                    <button type="button" onclick="Cart()" class="nav-link btn btn-link">Cart</button>
                </li>
                <li class="nav-item active">
                    <div class="dropdown">
                        <button type="button" class="nav-link btn btn-link dropdown-toggle" data-toggle="dropdown">
                            <font color="#feba10" >${sessionScope.ACC.name}</font>
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="javascript:Invoice()">Invoice</a>
                            <a href="../Logout" class="dropdown-item" >Logout</a>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</form>
<script>
    function Home() {
        document.forms['home'].action.value = 'Init';
        document.forms['home'].submit();
    }
    function Cart() {
        // alert(1);
        document.forms['home'].action.value = 'Cart';
        document.forms['home'].submit();
    }
    function Invoice() {
        //alert(1);
        document.forms['home'].action.value = 'Invoice';
        document.forms['home'].submit();
    }
</script>
