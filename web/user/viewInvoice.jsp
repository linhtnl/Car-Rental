<%-- 
Document   : viewInvoice
Created on : Feb 6, 2021, 7:34:48 PM
Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Invoice</title>

        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />    
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>


    </head>
    <body>

        <%@include file="navbar.jsp" %>
        <form name="view" action="../InvoiceManagementSvl" method="POST">
            <input type="hidden" name="action" value=""/>
            <div class="container">
                <br>

                <div class="row">

                    <div class="col">
                        <input type="text" name="invoiceName"  class="form-control search-slt"  value="${sessionScope.searchInfo.name}" placeholder="InvoiceId">
                    </div>
                    <div class="col">
                        <input type="date" name="dateRent" min="0" class="form-control search-slt" id="DateRent" value="${sessionScope.searchInfo.dateRent}">
                    </div>
                    <div class="col">
                        <input type="date" name="dateReturn" min="0" class="form-control search-slt" id="DateReturn" value="${sessionScope.searchInfo.dateReturn}">

                    </div>
                    <div class="col"><button type="button" onclick="Search()" class="btn btn-secondary wrn-btn">Search</button>

                        <button type="button" onclick="GetAll()" class="btn btn-primary wrn-btn">Get All</button>
                    </div>
                </div>
                <br>
                <h2>Your Invoice  </h2>

                <c:if test="${empty sessionScope.listInvoice}">
                    <b style="color:red;font-size: 25px">Nothing in here. </b>
                </c:if>
                <c:if test="${not empty sessionScope.listInvoice}">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Invoice ID</th>
                                <th>Trading Date</th>
                                <th>More</th>
                                <th>Action</th>
                                <th>Feedback</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.listInvoice}" var="dto" varStatus="counter">
                                <tr>
                                    <td>${dto.id}</td>
                                    <td>${dto.dateSubmit}</td>
                                    <td>
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal${counter.count}">
                                            Detail
                                        </button>

                                        <!-- Modal -->
                                        <div class="modal fade bd-example-modal-lg" id="exampleModal${counter.count}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" >
                                            <div class="modal-dialog modal-lg" role="document" style="max-width:1200px">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">Invoice ${dto.id}</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="container">
                                                            <table class="table table-striped">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Car</th>
                                                                        <th>License Plate</th>
                                                                        <th>Price</th>
                                                                        <th>Date Rent</th>
                                                                        <th>Date Return</th>
                                                                      
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:set var="total" value="${0}"/>
                                                                    <c:forEach items="${dto.list}" var="o">
                                                                        <c:set var="total" value="${total + o.price}" />
                                                                        <tr>
                                                                            <td>${o.name}</td>
                                                                            <td>${o.licensePlate}</td>
                                                                            <td>${o.price}</td>
                                                                            <td>${o.dateRent}</td>
                                                                            <td>${o.dateReturn}</td>                                        
                                                                           
                                                                        </tr>      
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                            <div class="float-right">
                                                                <b> Discount: </b>${dto.discount}%   <br>
                                                                <b>Total: </b>$
                                                                <fmt:formatNumber type = "number" 
                                                                                  maxFractionDigits = "2" value = "${total*(100-dto.discount)/100}" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                        <button type="button" class="btn btn-danger" onclick="Delete('${dto.id}')">Delete</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="Delete('${dto.id}')">
                                            Delete
                                        </button>

                                    </td>
                                    <td>
                                        <c:if test="${!dto.isFeedback}">
                                            <a href="../FeedbackSvl?invoiceID=${dto.id}"  class="btn btn-secondary" >Send Feedback</a>
                                            <!-- Button trigger modal -->

                                        </c:if>

                                    </td>

                                </tr>


                            </c:forEach>
                        </tbody>                    
                    </table>
                </c:if>
            </div>
        </div>
        <input type="hidden" name="subaction" value=""/>
        <input type="hidden" name="id" value="" />
    </form>
</body>
<script>


    function Delete(str) {
        if (confirm('Are you sure ?') == true) {
            document.forms['view'].id.value = str;
            document.forms['view'].subaction.value = 'delete';
            document.forms['view'].submit();
        }
    }
    function checkDate(str) {
        var year = str.split('-')[0];
        if (year.length > 4) {
            return false;
        }
        return true;
    }
    function checkDate2nd(dR, dRe) {
        dR = parseInt(dR);
        dRe = parseInt(dRe);
        if (dRe < dR)
            return false;
        return true;
    }
    function Search() {
        var dR = document.getElementById('DateRent').value;
        var dRe = document.getElementById('DateReturn').value;
        if (checkDate(dR) == true && checkDate(dRe) == true) {
            document.forms['view'].subaction.value = 'search';
            document.forms['view'].submit();
        } else if (!checkDate2nd()) {
            alert('Date Return cannot less than Date Rent');

        } else {
            alert('Year is not available');
        }
    }
    function GetAll() {
        document.forms['view'].subaction.value = 'GetAll';
        document.forms['view'].submit();
    }

</script>
</html>
