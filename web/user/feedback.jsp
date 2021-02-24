<%-- 
    Document   : demo
    Created on : Feb 21, 2021, 6:29:42 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <style>

            /****** Style Star Rating Widget *****/
            #rating{border:none;float:left;}
            #rating>input{display:none;}/*ẩn input radio - vì chúng ta đã có label là GUI*/
            #rating>label:before{margin-right: 5px;font-size:1.25em;font-family:FontAwesome;display:inline-block;content:"\f005";}/*1 ngôi sao*/
            #rating>label{color:#ddd;float:right;}/*float:right để lật ngược các ngôi sao lại đúng theo thứ tự trong thực tế*/
            /*thêm màu cho sao đã chọn và các ngôi sao phía trước*/
            #rating>input:checked~label,
            #rating:not(:checked)>label:hover, 
            #rating:not(:checked)>label:hover~label{color:#FFD700;}
            /* Hover vào các sao phía trước ngôi sao đã chọn*/
            #rating>input:checked+label:hover,
            #rating>input:checked~label:hover,
            #rating>label:hover~input:checked~label,
            #rating>input:checked~label:hover~label{color:#FFED85;}

        </style>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <form name="feedback" action="../FeedbackSvl" method="POST">
            <div class="container"><br>
                <h3>InvoiceID ${sessionScope.invoiceFB.id}</h3>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Car</th>
                            <th>License Plate</th>
                            <th>Rating</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="total" value="${0}"/>
                        <c:forEach items="${sessionScope.invoiceFB.list}" var="o">
                            <c:set var="total" value="${total + o.price}" />
                            <tr>
                                <td>${o.name}</td>
                                <td id="license${dto.id}">${o.licensePlate}</td>
                                <td>

                                    <div id="rating" >            
                                        <input type="radio" id="star10${o.licensePlate}" name="rating${o.licensePlate}" value="10" onchange="rate(this.value, '${o.licensePlate}')" />
                                        <label class = "full" for="star10${o.licensePlate}" ></label>
                                        <input type="radio" id="star9${o.licensePlate}" name="rating${o.licensePlate}" value="9" onchange="rate(this.value, '${o.licensePlate}')"/>
                                        <label class = "full" for="star9${o.licensePlate}" ></label>
                                        <input type="radio" id="star8${o.licensePlate}" name="rating${o.licensePlate}" value="8" onchange="rate(this.value, '${o.licensePlate}')" />
                                        <label class = "full" for="star8${o.licensePlate}" ></label>
                                        <input type="radio" id="star7${o.licensePlate}" name="rating${o.licensePlate}" value="7" onchange="rate(this.value, '${o.licensePlate}')"  />
                                        <label class = "full" for="star7${o.licensePlate}" ></label>
                                        <input type="radio" id="star6${o.licensePlate}" name="rating${o.licensePlate}" value="6" onchange="rate(this.value, '${o.licensePlate}')" />
                                        <label class = "full" for="star6${o.licensePlate}" ></label>
                                        <input type="radio" id="star5${o.licensePlate}" name="rating${o.licensePlate}" value="5" onchange="rate(this.value, '${o.licensePlate}')" />
                                        <label class = "full" for="star5${o.licensePlate}" ></label>
                                        <input type="radio" id="star4${o.licensePlate}" name="rating${o.licensePlate}" value="4" onchange="rate(this.value, '${o.licensePlate}')" />
                                        <label class = "full" for="star4${o.licensePlate}"></label>
                                        <input type="radio" id="star3${o.licensePlate}" name="rating${o.licensePlate}" value="3" onchange="rate(this.value, '${o.licensePlate}')" />
                                        <label class = "full" for="star3${o.licensePlate}" ></label>
                                        <input type="radio" id="star2${o.licensePlate}" name="rating${o.licensePlate}" value="2" onchange="rate(this.value, '${o.licensePlate}')" />
                                        <label class = "full" for="star2${o.licensePlate}" ></label>
                                        <input type="radio" id="star1${o.licensePlate}" name="rating${o.licensePlate}" value="1" onchange="rate(this.value, '${o.licensePlate}')" />
                                        <label class = "full" for="star1${o.licensePlate}" ></label> 

                                    </div>

                                    <span id="rate-num${o.licensePlate}" style="margin-left: 10px;color: coral">0 Star</span>
                                    <input type="hidden" id="rating${o.licensePlate}" name="rating${o.licensePlate}" value="0"/>
                                </td>

                            </tr>      
                        </c:forEach>
                    </tbody>
                </table>    
                <div style="margin-left: 60%">
                    <a class="btn btn-secondary" style="margin-right: 3%" href="javascript:Invoice()" >Back to Invoices</a >
                    <button class="btn btn-primary" type="submit">Submit </button>
                </div>
            </div>
        </form>
    </body>
    <script>
     
        function rate(star, id) {
            document.getElementById('rating' + id).value = star;
            document.getElementById('rate-num' + id).innerHTML = star + ' Stars';
        }
        function SubmitFeedback(id) {
            document.forms['view'].id.value = id;
            document.forms['view'].subaction.value = 'feedback';
            document.forms['view'].submit();
        }

    </script>
</html>
