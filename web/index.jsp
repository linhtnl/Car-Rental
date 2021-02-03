<%-- 
    Document   : index
    Created on : Jan 21, 2021, 10:51:25 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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

            /*search box css start here*/
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
        <script>

        </script>
        <form name="index" action="MainController" method="Post">
            <input type="hidden" name="action" value=""/>
            <nav class="navbar navbar-expand-lg navbar-dark">

                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav">
                        <li class="nav-item active">
                            <button type="button" class="btn btn-link nav-link" onClick="Home()" >Home</button>
                            <!--<a href="index.jsp" class="nav-link">Home</a>-->
                        </li>
                    </ul>
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a href="login.jsp" class="nav-link " style="float: right">Login</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <!--SEARCH BAR -->
            <section class="search-sec">
                <div class="container">

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="row">
                                <input type="hidden" value="${sessionScope.searchDTO.categoryId}" id="categoryId"/>
                                <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                                    <input type="text" class="form-control search-slt" name="carName" value="${sessionScope.searchDTO.nameCar}" placeholder="Enter Name Car">
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-12 p-0">
                                    <select class="form-control search-slt" id="exampleFormControlSelect1" name="category" id="category">
                                        <option value="%%" id="none">Select Category</option>
                                        <c:forEach items="${sessionScope.listCategory}" var="dto">
                                            <option value="${dto.id}" id="${dto.id}">${dto.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-12 p-0">
                                    <input type="number" name="carNum" min="0" value="${sessionScope.searchDTO.carNum}" class="form-control search-slt" placeholder="Amount Of Car">
                                </div>

                                <div class="col-lg-2 col-md-2 col-sm-12 p-0">
                                    <input type="date" name="dateRent" min="0" class="form-control search-slt" placeholder="Date Rent" value="${sessionScope.searchDTO.dateRent}">
                                </div>
                                <div class="col-lg-2 col-md-2 col-sm-12 p-0">
                                    <input type="date" name="dateReturn" min="0" class="form-control search-slt" placeholder="Date Return" value="${sessionScope.searchDTO.dateReturn}">
                                </div>

                                <div class="col-lg-1 col-md-1 col-sm-12 p-0">
                                    <button type="button" onclick="Search()" class="btn btn-danger wrn-btn">Search</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </section>
            <!--CAR LIST -->
            <div class="container" style="margin-top: 5%">
                <div class="row">
                    <c:forEach items="${sessionScope.listCar}" var="dto" varStatus="counter">

                        <div class="col-md-3 col-sm-6">
                            <div class="product-grid" style="margin-bottom: 5%">
                                <div class="product-image" >      
                                    <img class="pic-1" height="150"  width="350" src="images/marcus-p-oUBjd22gF6w-unsplash.jpg">
                                    <b>Color: </b> ${dto.color}<br>
                                    <b>Year: </b> ${dto.year} <br> 
                                    <b>Category: </b> 
                                    <c:forEach items="${sessionScope.listCategory}" var="cate">
                                        <c:if test="${cate.id eq dto.categoryId}">
                                            ${cate.name}
                                        </c:if>
                                    </c:forEach>
                                    <br>

                                    <b>Quantity: </b> ${dto.quantity}<br>  

                                    <ul class="social">

                                        <!--<li><a data-tip="Quick View" ><i class="fa fa-search"></i></a></li>-->                         
                                        <li><a href="" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
                                        <li>
                                            <!-- Button trigger modal -->
                                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                                                Launch demo modal
                                            </button>




                                            <!-- Modal -->
                                            <!--                                            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                                            <div class="modal-dialog" role="document">
                                                                                                <div class="modal-content">
                                                                                                    <div class="modal-header">
                                                                                                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                                                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                                            <span aria-hidden="true">&times;</span>
                                                                                                        </button>
                                                                                                    </div>
                                                                                                    <div class="modal-body">
                                                                                                        ...
                                                                                                    </div>
                                                                                                    <div class="modal-footer">
                                                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                                                        <button type="button" class="btn btn-primary">Save changes</button>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>-->
                                        </li>
                                    </ul>

                                </div>
                                <ul class="rating">
                                    <input type="hidden" name="rate" value="${dto.rateAvg}"/>
                                    <c:set var="remain" value="${10-dto.rateAvg}"/>
                                    <c:forEach begin="1" end="${dto.rateAvg}">
                                        <li class="fa fa-star"></li>
                                        </c:forEach>
                                        <c:forEach begin="1" end="${remain}">
                                        <li class="fa fa-star disable"></li>
                                        </c:forEach>
                                </ul>
                                <div class="product-content">
                                    <h3 class="title"><a href="">${dto.name}</a></h3>
                                    <div class="price">$${dto.price}

                                    </div>

                                </div>
                            </div>
                        </div>

                        <!-- Modal -->
                        <div id="myModal" class="modal fade" role="dialog">
                            <div class="modal-dialog">

                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Modal Header</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>Some text in the modal.</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </c:forEach>
                </div>

            </div>
            <div class="container" style="margin-top: 5%;margin-bottom: 10%">
                <div class="row">

                    <div class="col-xl-4"></div>
                    <div class="col-xl-4" style="margin-left: 160px">
                        <ul class="pagination">
                            <!--
                            - Nếu total page <=3 thì chỉ hiện ra đúng số trang total page 
                            --- ở selected page thì active
                            - Nếu total page lớn hơn 3
                            --- Xem selected number(SN) là mấy
                            ------Nếu SN <= 2 thì hiện từ 1-3
                            ------Nếu SN => total page -2 thì hiện từ total đén total page -2
                            ------Còn lại hiện từ SN-1 đén SN+1
                            -->

                            <c:choose>

                                <c:when test="${sessionScope.totalPage <=3 }">
                                    <c:forEach begin="1" end="${sessionScope.totalPage}" var="i">
                                        <li class="page-item" id="page${i}"><a class="page-link"  onclick="pageNum('${i}')">${i}</a></li>
                                        </c:forEach>
                                    </c:when>
                                    <c:when test="${sessionScope.totalPage >3 }">

                                    <c:choose>
                                        <c:when test="${sessionScope.pageNum<=2}">

                                            <c:forEach begin="1" end="3" var="i">
                                                <li class="page-item" id="page${i}"><a class="page-link"  onclick="pageNum('${i}')">${i}</a></li>
                                                </c:forEach>
                                            </c:when>
                                            <c:when test="${sessionScope.pageNum>=sessionScope.totalPage-1}">

                                            <c:forEach begin="${sessionScope.totalPage-2}" end="${sessionScope.totalPage}" var="i">
                                                <li class="page-item" id="page${i}" ><a class="page-link" onclick="pageNum('${i}')">${i}</a></li>
                                                </c:forEach>
                                            </c:when> 

                                        <c:when test="${sessionScope.pageNum>2 && sessionScope.pageNum<sessionScope.totalPage-1}">

                                            <c:forEach begin="${sessionScope.pageNum-1}" end="${sessionScope.pageNum+1}" var="i">
                                                <li class="page-item" id="page${i}"><a class="page-link"  onclick="pageNum('${i}')">${i}</a></li>
                                                </c:forEach>
                                            </c:when>
                                        </c:choose>
                                    </c:when>
                                </c:choose>


                        </ul>

                    </div>
                    <div class="col-xl-4"></div>

                </div>

            </div>
            <input type="hidden" name="pageNum" id="pageNum" value="${sessionScope.pageNum}"/>
        </form>

    </body>
    <script>
        function getCategory(str) {
            alert(str);
        }
        function Home() {
            //alert(1);
            document.forms['index'].action.value = 'Init';
            document.forms['index'].submit();
        }
        function pageNum(num) {
            document.forms['index'].pageNum.value = num;
            document.forms['index'].action.value = 'Paging';
            document.forms['index'].submit();
        }
        function Search() {
            document.forms['index'].action.value = 'Search';
            document.forms['index'].submit();
        }
        $(window).on("load", function() {
            var pageNumber = 'page' + document.getElementById("pageNum").value;
            console.log(pageNumber)
            document.getElementById(pageNumber).classList.add('active');
            var categoryId = document.getElementById('categoryId').value;
            if (categoryId == '')
                categoryId = 'none';
            document.getElementById(categoryId).selected = true;

        });
    </script>
</html>
