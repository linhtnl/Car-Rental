<%-- 
    Document   : viewCar
    Created on : Feb 3, 2021, 11:38:23 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Car </title>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <style>
            .navbar{background:#222222;}
            .nav-item::after{content:'';display:block;width:0px;height:2px;background:#fec400;transition: 0.2s;}
            .nav-item:hover::after{width:100%;}
            .navbar-dark .navbar-nav .active > .nav-link, .navbar-dark .navbar-nav .nav-link.active, .navbar-dark .navbar-nav .nav-link.show, .navbar-dark .navbar-nav .show > .nav-link,.navbar-dark .navbar-nav .nav-link:focus, .navbar-dark .navbar-nav .nav-link:hover{color:#fec400;}
            .nav-link{padding:15px 5px;transition:0.2s;}
            .dropdown-item.active, .dropdown-item:active{color:#212529;}
            .dropdown-item:focus, .dropdown-item:hover{background:#fec400;}
            h3.h3{text-align:center;margin:1em;text-transform:capitalize;font-size:1.7em;}

            /********************* shopping Demo-1 **********************/
            .product-grid{font-family:Raleway,sans-serif;text-align:center;padding:0 0 72px;border:1px solid rgba(0,0,0,.1);overflow:hidden;position:relative;z-index:1}
            .product-grid .product-image{position:relative;transition:all .3s ease 0s}
            .product-grid .product-image a{display:block}
            .product-grid .product-image img{width:100%;height:auto}
            .product-grid .pic-1{opacity:1;transition:all .3s ease-out 0s ; }
            .product-grid:hover .pic-1{opacity:1}
            .product-grid .pic-2{opacity:0;position:absolute;top:0;left:0;transition:all .3s ease-out 0s}
            .product-grid:hover .pic-2{opacity:1}
            .product-grid .social{width:150px;padding:0;margin:0;list-style:none;opacity:0;transform:translateY(-50%) translateX(-50%);position:absolute;top:60%;left:50%;z-index:1;transition:all .3s ease 0s}
            .product-grid:hover .social{opacity:1;top:50%}
            .product-grid .social li{display:inline-block}
            .product-grid .social li a{color:#fff;background-color:#333;font-size:16px;line-height:40px;text-align:center;height:40px;width:40px;margin:0 2px;display:block;position:relative;transition:all .3s ease-in-out}
            .product-grid .social li a:hover{color:#fff;background-color:#ef5777}
            .product-grid .social li a:after,.product-grid .social li a:before{content:attr(data-tip);color:#fff;background-color:#000;font-size:12px;letter-spacing:1px;line-height:20px;padding:1px 5px;white-space:nowrap;opacity:0;transform:translateX(-50%);position:absolute;left:50%;top:-30px}
            .product-grid .social li a:after{content:'';height:15px;width:15px;border-radius:0;transform:translateX(-50%) rotate(45deg);top:-20px;z-index:-1}
            .product-grid .social li a:hover:after,.product-grid .social li a:hover:before{opacity:1}
            .product-grid .product-discount-label,.product-grid .product-new-label{color:#fff;background-color:#ef5777;font-size:12px;text-transform:uppercase;padding:2px 7px;display:block;position:absolute;top:10px;left:0}
            .product-grid .product-discount-label{background-color:#333;left:auto;right:0}
            .product-grid .rating{color:#FFD200;font-size:12px;padding:12px 0 0;margin:0;list-style:none;position:relative;z-index:-1}
            .product-grid .rating li.disable{color:rgba(0,0,0,.2)}
            .product-grid .product-content{background-color:#fff;text-align:center;padding:12px 0;margin:0 auto;position:absolute;left:0;right:0;bottom:-27px;z-index:1;transition:all .3s}
            .product-grid:hover .product-content{bottom:0}
            .product-grid .title{font-size:13px;font-weight:400;letter-spacing:.5px;text-transform:capitalize;margin:0 0 10px;transition:all .3s ease 0s}
            .product-grid .title a{color:#828282}
            .product-grid .title a:hover,.product-grid:hover .title a{color:#ef5777}
            .product-grid .price{color:#333;font-size:17px;font-family:Montserrat,sans-serif;font-weight:700;letter-spacing:.6px;margin-bottom:8px;text-align:center;transition:all .3s}
            .product-grid .price span{color:#999;font-size:13px;font-weight:400;text-decoration:line-through;margin-left:3px;display:inline-block}
            .product-grid .add-to-cart{color:#000;font-size:13px;font-weight:600}
            @media only screen and (max-width:990px){.product-grid{margin-bottom:30px}
            }
            .search-sec{
                padding: 2rem;
            }
            .search-slt{
                display: block;
                width: 100%;
                font-size: 0.875rem;
                line-height: 1.5;
                color: #55595c;
                background-color: #fff;
                background-image: none;
                border: 1px solid #ccc;
                height: calc(3rem + 2px) !important;
                border-radius:0;
            }
            .wrn-btn{
                width: 100%;
                font-size: 16px;
                font-weight: 400;
                text-transform: capitalize;
                height: calc(3rem + 2px) !important;
                border-radius:0;
            }
            @media (min-width: 992px){
                .search-sec{
                    position: relative;
                    top: 0px;
                    background: rgba(26, 70, 104, 0.51);
                }
            }

            @media (max-width: 992px){
                .search-sec{
                    background: #1A4668;
                }
            }
        </style>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <form name="view" action="../MainController" method="POST">
            <input type="hidden" name="action" value=""/>
            <div class="container">
                <h2>${sessionScope.carDetail.carName}  </h2>
                <p>All cars available for renting.</p>            
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>License Plate</th>
                            <th>Price per Day</th>
                            <th>Color</th>
                            <th>Car Location</th>
                            <th>Rent</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionScope.carDetail.list}" var="dto" varStatus="counter">
                            <tr>
                                <td>${dto.licensePlate}</td>
                                <td>$ ${dto.price}</td>
                                <td>${dto.color}</td>
                                <td>
                                    <c:if test="${empty dto.location}">
                                        Unknown
                                    </c:if>
                                        <c:if test="${not empty dto.location}">
                                        ${dto.location}
                                    </c:if>
                                    </td>
                                  
                                    <td>
                                        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#my${counter.count}">
                                        Rent
                                    </button>

                                    <!-- The Modal -->
                                    <div class="modal" id="my${counter.count}">
                                        <div class="modal-dialog">
                                            <div class="modal-content">

                                                <!-- Modal Header -->
                                                <div class="modal-header">
                                                    <h4 class="modal-title">${dto.licensePlate}</h4>
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                </div>

                                                <!-- Modal body -->
                                                <div class="modal-body">
                                                    <b>Choose date Rent and Return</b><br><br>
                                                    <div class="row">
                                                        <div class="col">
                                                            <input type="date" id="dateRent${dto.licensePlate}" min="0" class="form-control search-slt" placeholder="Date Rent" name="dateRent${dto.licensePlate}" value="${sessionScope.searchDTO.dateRent}">
                                                        </div>
                                                        <div class="col">
                                                            <input type="date" id="dateReturn${dto.licensePlate}" min="0" class="form-control search-slt" placeholder="Date Return" name="dateReturn${dto.licensePlate}" value="${sessionScope.searchDTO.dateReturn}">
                                                        </div>        
                                                    </div>
                                                </div>
                                                <!-- Modal footer -->
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-success" onclick="Renting('${dto.licensePlate}')" >Accept</button>
                                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
            </div>
                <input type="hidden" name="licensePlate" id="licensePlate" value="" />
        </form>
    </body>

    <script>
        function checkDate(str) {
            var dateRent = document.getElementById('dateRent'+str).value;
            var dateReturn = document.getElementById('dateReturn'+str).value;
            console.log(dateRent+" - "+dateReturn);
            if (dateRent == '' || dateReturn == '')
                return 'Please fill date Rent and Return please!';
            dateRent = dateRent.replaceAll('-', '');
            dateReturn = dateReturn.replaceAll('-', '');
            console.log(dateRent+" - "+dateReturn);
            if (parseInt(dateRent) >= parseInt(dateReturn))
                return 'Date rent cannot greater than date return';
            var d = new Date();
            var month = d.getMonth()+1;
            if(month<10) month='0'+month;
            var date =  d.getDate();
            if(date<10) date='0'+date;
            var now = parseInt(d.getFullYear() + '' + month + '' +date);          
            if (parseInt(dateRent) < now)
                return 'date Rent cannot less than  today';
            if (parseInt(dateReturn) < now)
                return 'date Return cannot less than today';
            return '';
        }
        function Renting(str) {
            var mes = checkDate(str);
            if (mes != '') {
                alert(mes);
                return;
            }   
            document.getElementById('licensePlate').value = str;
            document.forms['view'].action.value = 'Renting';
            document.forms['view'].submit();
        
        }
        function Home() {
            document.forms['home'].action.value = 'Init';
            document.forms['home'].submit();
        }
    </script>
</html>
