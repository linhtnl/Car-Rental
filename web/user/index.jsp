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
                            <img class="pic-1" height="150"  width="350" src="../images/${dto.img}">                               
                            <ul class="social">                         
                                <li><a href="../CarRentalSvl?carID=${dto.carID}" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
                                <li>
                                    <a data-tip="Quick View" data-toggle="modal" data-target="#myModal${counter.count}"><i class="fa fa-search"></i></a>
                                </li>
                            </ul>

                        </div>                              
                        <div class="product-content">
                            <h3 class="title"><a href="">${dto.carName}</a></h3>

                        </div>
                    </div>
                </div>

                <!-- Modal -->
                <div id="myModal${counter.count}" class="modal fade" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">${dto.carName}</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">                  
                                <b>Year: </b> ${dto.year} <br> 
                                <b>Category: </b> 
                                <c:forEach items="${sessionScope.listCategory}" var="cate">
                                    <c:if test="${cate.id eq dto.categoryID}">
                                        ${cate.name}
                                    </c:if>
                                </c:forEach>
                                <br>
                                <b>Quantity: </b> ${dto.size} <br> 
                                <b>No of seats: </b> ${dto.noOfSeats} <br> 
                                <b>Fuel: </b> ${dto.fuel} <br> 


                            </div>
                            <div class="modal-footer">
                                <a type="button" href="../CarRentalSvl?carID=${dto.carID}" class="btn btn-primary"><font color="white">Rent</font></a>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </div>

                    </div>
                </div>
            </c:forEach>
        </div>

    </div>


    <%@include file="footer.jsp" %>
</form>

