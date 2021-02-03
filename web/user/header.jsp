<%-- 
    Document   : footer
    Created on : Jan 25, 2021, 7:36:32 AM
    Author     : ASUS
--%>


   
        <form name="home" action="../MainController" method="POST">
            <input type="hidden" name="action" value=""/>   
            <!--header-->
            <nav class="navbar navbar-expand-lg navbar-dark">

                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav">
                        <li class="nav-item active">
                            <button type="button" onclick="Home()" class="nav-link btn btn-link">Home</button>
                            <!--<a href="home.jsp" class="nav-link">Home</a>-->
                        </li>
                    </ul>
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a href="../Logout" class="nav-link " style="float: right">Logout</a>
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
        </form>
  
   
</html>
