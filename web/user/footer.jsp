<%-- 
    Document   : footer
    Created on : Feb 3, 2021, 11:33:43 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
