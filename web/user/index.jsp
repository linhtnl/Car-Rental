<%-- 
    Document   : index
    Created on : Jan 23, 2021, 5:20:20 PM
    Author     : ASUS
--%>



<form name="index" action="../MainController" method="POST">
    <input type="hidden" name="action" value=""/>
    <!--CAR LIST -->
    <div class="container" style="margin-top: 5%">
        <div class="row">
            <c:forEach items="${sessionScope.listCar}" var="dto" varStatus="counter">

                        <div class="col-md-3 col-sm-6">
                            <div class="product-grid" style="margin-bottom: 5%">
                                <div class="product-image" >      
                                    <img class="pic-1" height="150"  width="350" src="../images/marcus-p-oUBjd22gF6w-unsplash.jpg">
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
            </c:forEach>
        </div>

    </div>


    <div class="container" style="margin-top: 5%;margin-bottom: 10%">
        <div class="row">

            <div class="col-xl-4"></div>
            <div class="col-xl-4" style="margin-left: 160px">
                <ul class="pagination">
                    <!--
                    - N?u total page <=3 thì ch? hi?n ra ?úng s? trang total page 
                    --- ? selected page thì active
                    - N?u total page l?n h?n 3
                    --- Xem selected number(SN) là m?y
                    ------N?u SN <= 2 thì hi?n t? 1-3
                    ------N?u SN => total page -2 thì hi?n t? total ?én total page -2
                    ------Còn l?i hi?n t? SN-1 ?én SN+1
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
            <input type="hidden" name="pageNum" id="pageNum" value="${sessionScope.pageNum}"/>
        </div>
    </div>
</form>

