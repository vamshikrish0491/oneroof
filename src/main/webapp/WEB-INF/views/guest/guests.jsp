<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="preloader"></div>

   <div id="wrapper" class="wrapper bg-ash">
    <jsp:include page="../wayupartyMasterHeader.jsp" />
        <!-- Page Area Start Here -->
        <div class="dashboard-page-one">
          <jsp:include page="../wayupartyMasterSideNav.jsp" />
            <div class="dashboard-content-one">
                <div class="breadcrumbs-area">
	                    <h3>Guests</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
                        
	                        <li>Guests</li>
	                    </ul>
	             </div>
	             
            <div class="card height-auto">    
                    <div class="card-body">
                    <div id="guestsLoaderDiv"></div>
                    <div class="heading-layout1">
                            <div class="item-title">
                                <h3>Guests List</h3>
                            </div>
                           <div class="dropdown">
                               <a class="text-black" href="${Wayuparty_appUrl}/addGuest"><i class="fas fa-plus text-dark-pastel-green"></i>&nbsp;Add Guest</a>
                            </div> 
                        </div>
					     <div class="tab-content">
		                           <jsp:include page="guestsList.jsp" />
	                     </div>
                  </div>
              </div>
            </div>
       </div>
       <jsp:include page="../wayupartyMasterFooter.jsp" />
 </div>
 
 <script type="text/javascript">
		window.onload = function () {
			getGuestsList();
		 };
 </script> 
 
 
 