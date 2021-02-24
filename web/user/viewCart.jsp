<%-- 
    Document   : viewCart
    Created on : Feb 6, 2021, 1:43:50 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        <script>
            $(document).ready(function() {
                if (document.getElementById('flag').value == '1') {
                    // Show the Modal on load
                    //console.log("1");
                    $("#myModal").modal("show");
                }

                // Hide the Modal
                $("#myBtn").click(function() {
                    $("#myModal").modal("hide");
                });
            });

        </script>
    </head>
    <body>
        <%@include file="navbar.jsp" %><br>
        <form name="view" action="../CartManagementSvl" method="POST">
            <input type="hidden" name="action" value=""/>
            <div class="container">
                <h2>Your Cart  </h2>
                <p>All cars you have added.</p>     
                <c:if test="${empty sessionScope.CART}">
                    <b style="color:red;font-size: 25px">Sorry! you did not choose any car.</b>
                </c:if>
                <c:if test="${not empty sessionScope.CART}">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>License Plate</th>
                                <th>Price per Day</th>
                                <th>Color</th>
                                <th>Detail</th>
                                <th>Total Day</th>
                                <th>Sum</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${sessionScope.CART}" var="dto" varStatus="counter">
                                <tr>
                                    <td>${dto.licensePlate}<br>(${dto.name})
                                        <c:if test="${dto.available == false }">
                                            <br> <i style="color:red">This car is not available</i>
                                        </c:if>
                                        <c:if test="${dto.available || empty dto.available }">

                                        </c:if>
                                    </td>
                                    <td id="price${counter.count}">$${dto.price}</td>
                                    <td>${dto.color}</td>
                                    <td>
                                        <c:if test="${empty dto.pickup}"><c:set var="pick" value="Unknow"/></c:if>
                                        <c:if test="${not empty dto.pickup}"><c:set var="pick" value="${dto.pickup}"/></c:if>
                                        <c:if test="${empty dto.returnLocation}"><c:set var="returnL" value="Unknow"/></c:if>
                                        <c:if test="${not empty dto.returnLocation}"><c:set var="returnL" value="${dto.returnLocation}"/></c:if>
                                        <h6 id="DateRent${counter.count}">Date Rent: ${dto.dateRent}</h6><br>
                                        <h6 id="DateReturn${counter.count}">Date Return: ${dto.dateReturn}</h6><br>
                                        <h6>Pickup at <b>${pick}</b></h6><br>
                                        <h6>Return at <b>${returnL}</b></6>
                                    </td>
                                    <td>
                                        <div id="totalDay${counter.count}">hi</div>
                                    </td>
                                    <td>
                                        <div class="sum" id="sum${counter.count}">hi</div>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="remove('${dto.licensePlate}')">
                                            Remove
                                        </button>
                                        </div>
                                    </td>
                                </tr>

                            </c:forEach>
                        </tbody>                    
                    </table>
                </c:if>
                <div class="container linhclass">
                    <div class="row">
                        <div class="col"><b>Voucher  </b><input type="text" name="voucher" value="${sessionScope.voucher.id}"/>
                            <c:if test="${empty sessionScope.voucher || sessionScope.voucher.available == true }">
                                <i style="color:red"></i>
                            </c:if>
                            <c:if test="${sessionScope.voucher.available != true }">
                                <i style="color:red">Sorry, this voucher not exist or expire!</i>
                            </c:if>

                        </div>

                        <div class="col" style="display: inline " ><b style="font-size: 20px">Total :</b> <input type="text" disabled="true" id="TotalMoney" style="font-size: 20px"></h6></div>

                    </div>
                    <br>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col"></div>
                        <div class="col"></div> <div class="col"></div>
                        <div class="col"></div> 
                        <div class="col"><button type="button" id="myBtn" onclick="checkQuantity()" class="btn btn-success" style="padding: 10%">Checkout </button></div>
                        <div class="LinhModal">
                            <!-- Modal -->
                            <div class="modal fade" id="myModal" role="dialog">
                                <div class="modal-dialog modal-lg">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="container"><br>
                                            <b><h4 class="modal-title">Check your cart</h4></b>
                                        </div>
                                        <div class="modal-body">
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>License Plate</th>
                                                        <th>Detail</th>
                                                        <th>Total Day</th>
                                                        <th>Sum</th>
                                                        <th>Note</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${sessionScope.CART}" var="dto" varStatus="counter">
                                                        <tr>
                                                            <td>${dto.licensePlate}<br>(${dto.name})</td>
                                                            <td>
                                                                <c:if test="${empty dto.pickup}"><c:set var="pick" value="Unknow"/></c:if>
                                                                <c:if test="${not empty dto.pickup}"><c:set var="pick" value="${dto.pickup}"/></c:if>
                                                                <c:if test="${empty dto.returnLocation}"><c:set var="returnL" value="Unknow"/></c:if>
                                                                <c:if test="${not empty dto.returnLocation}"><c:set var="returnL" value="${dto.returnLocation}"/></c:if>
                                                                <h6 id="DateRent${counter.count}">Date Rent: ${dto.dateRent}</h6><br>
                                                                <h6 id="DateReturn${counter.count}">Date Return: ${dto.dateReturn}</h6><br>
                                                                <h6>Pickup at <b>${pick}</b></h6><br>
                                                                <h6>Return at <b>${returnL}</b></6>
                                                            </td>
                                                            <td>
                                                                <div id="totalDayC${counter.count}">hi</div>
                                                            </td>
                                                            <td>
                                                                <div class="sumC" id="sumC${counter.count}">hi</div>
                                                            </td>
                                                            <td >
                                                                <input type="hidden" value="${dto.available}" class="carStatus"/>
                                                                <c:if test="${dto.available}">
                                                                    ----
                                                                </c:if>
                                                                <c:if test="${!dto.available}">
                                                                    <i style="color:red">Sorry, this car is not available.</i> 
                                                                </c:if>
                                                            </td>
                                                        </tr>

                                                    </c:forEach>

                                                </tbody>
                                            </table>

                                            <div class="container" style="margin-left: 60%">
                                                <h5 id="TotalMoneyC">Subtotal: $0 </h5><br>
                                                <h5 id='discount'>Discount: ${sessionScope.voucher.percentage}%</h5><br>
                                                <h5 id="finalCost">Total: $0</h5>
                                            </div><br>
                                            <div class="modal-footer">
                                                <button class="btn btn-secondary"  data-dismiss="modal">Close</button>
                                                <button type="button" class="btn btn-primary" onclick="Book()">Book</button>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <input type="hidden" name="subaction" value=""/>
            <input type="hidden" name="licensePlate" id="licensePlate" value="" />
            <input type="hidden" name="flag" value="${sessionScope.flag}" id="flag"/>
        </form>
    </body>
    <script>
        function remove(str) {
            var ans = confirm('Are you sure?');
            if (ans == true) {
                document.forms['view'].subaction.value = 'remove';
                document.forms['view'].licensePlate.value = str;
                document.forms['view'].submit();
            }
        }
        function checkQuantity() {
            document.forms['view'].subaction.value = 'checkQuantity';
            document.forms['view'].submit();
        }
        function Book() {
            var f = 0;
            var s = document.getElementsByClassName('carStatus').length;
            for (var i = 0; i < s; i++) {
                console.log(document.getElementsByClassName('carStatus')[i].value);
                if (document.getElementsByClassName('carStatus')[i].value == 'false')
                    f = 1;
            }
            console.log(f);
            if (f == 1) {
                alert('You must remove car not available!');
                return;
            }
            document.forms['view'].subaction.value = 'checkout'; //final step
            document.forms['view'].submit();
        }
        var totalMoney = 0;
        $(window).on("load", function() {
            var size = document.getElementsByClassName('sum').length;
            //Cất data empty khi không có dữ liệu
            if (size == 0) {
                document.querySelectorAll('.linhclass')[0].
                        style.visibility = 'hidden';
            } else {
                document.querySelectorAll('.linhclass')[0].
                        style.visibility = 'visible';
            }
            //Thành tiền
            for (var i = 1; i <= size; i++) {
                var dateRent = parseInt(document.getElementById('DateRent' + i).innerHTML.replace('Date Rent:', '').replaceAll('-', ''));
                var dateReturn = parseInt(document.getElementById('DateReturn' + i).innerHTML.replace('Date Return:', '').replaceAll('-', ''));
                var totalDate = dateReturn - dateRent;
                if (totalDate == 0)
                    totalDate = 1;
                document.getElementById('totalDay' + i).innerHTML = totalDate;
                document.getElementById('sum' + i).innerHTML = '$' + (totalDate * parseFloat(document.getElementById('price' + i).innerHTML.replace('$', ''))).toFixed(2);
                totalMoney += parseFloat(document.getElementById('sum' + i).innerHTML.replace('$', ''));
            }
            document.getElementById('TotalMoney').value = '$' + totalMoney.toFixed(2);
            //Checkout modal
            if (document.getElementById('flag').value == '1') {
                for (var i = 0; i < size; i++) {
                    var t = i + 1;
                    document.getElementById('totalDayC' + t).innerHTML = document.getElementById('totalDay' + t).innerHTML;
                    document.getElementById('sumC' + t).innerHTML = document.getElementById('sum' + t).innerHTML;
                    document.getElementById('TotalMoneyC').innerHTML = 'Subtotal: ' + document.getElementById('TotalMoney').value;
                    var per = document.getElementById('discount').innerHTML;
                    per = per.replace('Discount: ', '');
                    per = parseInt(per.replace('%', ''));
                    if (100 - per > 100 || 100 - per < 0) {
                        per = 0;
                    }
                    document.getElementById('finalCost').innerHTML = 'Total: $' + ((100 - per) * totalMoney / 100).toFixed(2);
                }
            }
        });

    </script>
</html>
