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
	                    <h3>Event Settings</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
	                        <li>Event Settings</li>
	                    </ul>
	             </div>
                
                <div class="card height-auto">
                    <div class="card-body">
                        <div class="heading-layout1">
                            <div class="item-title">
                                <h3>Event Ticket Type</h3>
                            </div>
                           <div class="dropdown">
                                <a class="dropdown-toggle" href="#" role="button" 
                                data-toggle="dropdown" aria-expanded="false">...</a>
        
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#small-modal" onclick="clearForm();"><i class="fas fa-plus text-dark-pastel-green"></i>Add</a>
                                    <a class="dropdown-item" href="${Wayuparty_appUrl}/eventSettings"><i class="fas fa-redo-alt text-orange-peel"></i>Refresh</a>
                                </div>
                            </div> 
                        </div>
                        
                         <div class="table-responsive">
                            	<table class="table display text-nowrap" id="ticketTableList" >
	                                <thead>
	                           			  <tr>
		                                    <th>Ticket Name</th>
		                                    <th>Rating</th>
	                                        <th>Action</th>
	                                     </tr>
                                     </thead>
                                     <tbody>
	                                     
	                                     <c:if test="${ticketsListSize > 0}">
		                                     <c:forEach var="ticket" items="${ticketsList}">
		                                  	 <tr>
		                                       <td>${ticket.ticketName}</td>
		                                       <td>
					                                <c:set var="ticketRatingValue" value="${0}" scope="request"/>     
					                                <c:forEach begin="0" end="5" varStatus="loop">
					                                <c:if test="${ticketRatingValue+1 <= ticket.ticketRating}">
			    										<li class="fa fa-star cursorPointer textStarFilling"></li>
			    									</c:if>
			    									<c:if test="${ticketRatingValue > ticket.ticketRating}">
			    										<li class="fa fa-star cursorPointer text-muted"></li>
			    									</c:if>
			    										<c:set var="ticketRatingValue" value="${ticketRatingValue + 1}" scope="request"/>
													</c:forEach>
												</td> 	
												
		                                       <td><a href="#" class="anchorGreen" data-toggle="modal" data-target="#small-modal" onclick="getTicketEditForm('${ticket.ticketUUID}');"><i class="fa fa-edit"></i>&nbsp;&nbsp;Edit</a></td>
		                                   	 </tr>
			                                </c:forEach>
		                                </c:if>
		                                
		                                <c:if test="${ticketsListSize == 0}">
		                                 <tr>
	                                       <td colspan="4" class="noRecords"><img src="/resources/img/no-records.png" alt=""/></td>
	                                   	 </tr>
		                                </c:if>
	                                     
	                                </tbody>
                                </table>
                            	</div> 
                           
                      </div>
                   </div>
                   <jsp:include page="eventTickets.jsp" />
            </div>
       </div>
       <jsp:include page="../wayupartyMasterFooter.jsp" />
 </div>
 
 <script>
  window.onload = function () {
		getTicketListPagination();	
	 };
	 
  function getTicketListPagination(){
	  $('#ticketTableList').DataTable( { 
			"scrollCollapse": false,
			"bLengthChange": true,
			"lengthMenu": [[20, 30, 40, -1], [20, 30, 40, "All"]],
			"bDestroy": true,
			"ordering": false,
			"oLanguage": {
		 		"sSearch": "",
		 		"sSearchPlaceholder":"Search Menu",
		 		 "sZeroRecords": "", "sEmptyTable": ""
		 	}
		});
  }	 
  </script> 