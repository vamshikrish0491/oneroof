<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="tab-pane fade show" id="ratings" role="tabpanel">
	<div class="row">	
		<div class="modal-body">
			<div class="heading-layout1">
                <div class="dropdown">
                <sec:authorize access="hasRole('ROLE_USER')"> 
                    <a  href="#" data-toggle="modal" data-target="#right-slide-modal"><i class="fas fa-plus text-dark-pastel-green"></i>&nbsp;Add Rating</a>
                </sec:authorize>    
                 </div> 
            </div>
                        
	          <div class="row">
						    <div class="col-sm-12 col-md-12">
						    <div class="cart_widget">
								<div id="vendorsRatingsDiv"></div>
						    </div>
						  </div>
						  </div>
	    </div>
	</div>
	 <jsp:include page="vendorAddRating.jsp" />
</div>