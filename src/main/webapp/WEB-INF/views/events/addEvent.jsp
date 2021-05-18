<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="preloader"></div>

<style>
.cardEvent-design-inner-values p { 
font-size: 14px !important;
color: #FF0000 !important; 
}
</style>


<div id="wrapper" class="wrapper bg-ash">
  <jsp:include page="../wayupartyMasterHeader.jsp" />
        <!-- Page Area Start Here -->
        <div class="dashboard-page-one">
           <jsp:include page="../wayupartyMasterSideNav.jsp" />
	          <div class="dashboard-content-one">
	            <div class="breadcrumbs-area">
	                    <h3>Add Event</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
                        
	                        <li>Add Event</li>
	                    </ul>
	             </div>
	             
			        <div class="alert icon-alart bg-light-green2" id="successMsgDiv" role="alert" style="display: none;">
		                                   <i class="far fa-hand-point-right bg-light-green3"></i>
		                                  <span id="successMsg" style="color: white;"></span> 
		            </div>
		            
		            <div class="alert icon-alart bg-pink2" role="alert" id="invalidMsgDiv" style="display: none;">
		                                   <i class="fas fa-times bg-pink3"></i>
		                                  <span id="invalidMsg" style="color: white;"></span> 
		            </div>
            
            
		            <div class="card height-auto">
		             <div id="ticketDetailsLoadingDiv"></div>
		                   <div class="card-body">
		                        <div class="heading-layout1">
		                            <div class="item-title">
		                                <h3>Event Details</h3>
		                            </div>
		                        </div>    
		                            
                        <form class="new-added-form" id="add_events_form" name="add_events_form" >
                   	 		<div class="row">	
                    	 		<div class="col-xl-5 col-12">
		                        	<div class="card">
		                            <div class="card-body">
		                             	<div class="heading-layout1 mg-b-17">
		                                    <div class="item-title">
		                                        <h6>Event Info</h6>
		                                    </div>
	                                    </div>
	                                    <div class="notice-box-wrap m-height-150">
                                			
                                			<div class="row mt-2">		
				                        	 		
			                    	 		  <div class="col-xl-12 col-lg-6 col-12 form-group">
			                                    <label>Event Name<span class="text-danger">&nbsp;*</span></label>
			                                    <input type="text" class="form-control"  name="eventName" id="eventName" data-validation="required validate_Space validate_length length1-40"  field-name="Event Name">
			                                </div>
			                                
			                                 <div class="col-xl-12 col-lg-6 col-12 form-group">
			                                    <label>Event Type<span class="text-danger">&nbsp;*</span></label>
			                                    <input type="text" class="form-control"  name="eventType" id="eventType" data-validation="required validate_Space validate_length length1-100"  field-name="Event Type">
			                                </div>
			                                
								              <div class="col-xl-12 col-lg-6 col-12 form-group">
			                                    <label>Event Location</label>
			                                    <input type="text" class="form-control"  name="eventLocation" id="eventLocation" data-validation="required" data-validation="validate_Space validate_length length1-40"  field-name="Event Location">
			                                </div>
			             
			             					  <div class="col-xl-12 col-lg-6 col-12 form-group">
			                                    <label>Event Host<span class="text-danger">&nbsp;*</span></label>
			                                    <input type="text" class="form-control"  name="eventHost" id="eventHost"  data-validation="required validate_Space validate_length length1-100"  field-name="Event Host">
			                                </div>
			                                
			                                 <div class="col-xl-12 col-lg-6 col-12 form-group">
			                                    <label>Music Type<span class="text-danger">&nbsp;*</span></label>
			                                     <select class="select2" class="form-control" multiple name="musicType" id="musicType"  data-validation="required" field-name="Music Type">
                                       
                                    			</select>
			                                
			                                </div>
			                               
								              <div class="col-xl-12 col-lg-6 col-12 form-group">
								                <label>Event Date<span class="text-danger">&nbsp;*</span></label>
								                <input type="text" placeholder="dd/mm/yyyy" class="form-control current-day-datepicker"
								                                             data-position='bottom right' name="eventDate" id="eventDate" readonly="readonly" data-validation="required" field-name="Event Date">
								                                         <i class="far fa-calendar-alt"></i>
								               </div>
								            
											<div class="col-xl-12 col-lg-6 col-12 form-group">
											 		   <label class="text-muted">Start Time / End Time<span class="text-danger">&nbsp;*</span></label>
											 		   <div id="timeScheduleList"></div>
										    </div>
			                                
			                                <div class="col-xl-12 col-lg-6 col-12 form-group">
			                                    <label>Language<span class="text-danger">&nbsp;*</span></label>
			                                    <input type="text" class="form-control"  name="eventLanguage" id="eventLanguage"  data-validation="required validate_Space validate_length length1-100"  field-name="Event Language">
			                                </div>
			                                
			                                <div class="col-xl-12 col-lg-6 col-12 form-group">
			                                    <label>Address<span class="text-danger">&nbsp;*</span></label>
			                                    <textarea class="textarea form-control" name="eventAddress" id="eventAddress" cols="10" rows="5" 
			                                    data-validation="required validate_space" field-name="Address" ></textarea>
			                                </div>
			                                
									      <div class="col-xl-12 col-lg-6 col-12 form-group" id="displayImageDiv">
		                                    <label>Display Image<span class="text-danger">&nbsp;*</span></label>
		                                    <input type="file" class="form-control-file" id="uploadDisplayImageBtn" field-name="Upload Image"  data-validation="required validateImg" field-name="Display Image">
		                                    <input type="text" class="form-control" readonly
												id="uploadDisplayImageFile" style="display: none;" name="docFile" >
		                                 </div>
		                                 
		                                  <div class="col-xl-12 col-lg-6 col-12 form-group" id="savedDisplayImg" style="display: none">
						                      <label>Display Image</label>
						                      <div class="events-image" id="uploadImageBtn">
													<div id="savedImage"></div>			
											  </div>
											<div class="clearUpload clearUpload-profile-position" title="Delete Uploaded Photo" onclick="deleteDisplayImage();"></div>
						                  </div>
		                                 
		                                 <div class="col-xl-12 col-lg-6 col-12 form-group" id="bannerImageDiv">
		                                    <label>Banner Image<span class="text-danger">&nbsp;*</span></label>
		                                    <input type="file" class="form-control-file" id="uploadBannerImageBtn" field-name="Upload Image" data-validation="required validateImg" field-name="Banner Image">
		                                    <input type="text" class="form-control" readonly
												id="uploadBannerImageFile" style="display: none;" name="docFile" >
		                                 </div>
		                                 
		                                 <div class="col-xl-12 col-lg-6 col-12 form-group" id="savedBannerImg" style="display: none">
						                      <label>Banner Image</label>
						                      <div class="events-image" id="bannerImageBtn">
													<div id="savedBannerImage"></div>			
											  </div>
											<div class="clearUpload clearUpload-profile-position" title="Delete Uploaded Photo" onclick="deleteBannerImage();"></div>
						                  </div>
                                
	                                    </div>
	                                  </div>  
	                                    
		                            </div>
		                            </div>
                            	</div>
                            	
                            	<div class="col-xl-7 col-12">
                        			<div class="card dashboard-card-eleven">
                            			<div class="card-body">
		                                	<div class="heading-layout1">
		                                    	<div class="item-title">
		                                        	<h6>Event Tickets
		                                        	<i class="fas fa-plus text-dark-pastel-green" style="cursor:pointer;" onclick="addTicket();" title="Add Ticket"></i></h6>
		                                    	</div>
		                                    </div>
		                                    
		                                    <!-- <div id="eventsTicketsListDiv"></div> -->
		                                    
		                                    <div class="row">
											            <div class="panel panel-default clearfix">
															<div class="panel-body">
																<input type="hidden" id="ticketItemCounter" value="1">
																<div class="cardEvent-design">
																	<h4 class="bg-event-primary">Ticket</h4>
																	<div class="clearfix"></div>
																	<div class="cardEvent-design-inner">
																	<div class="cardEvent-design-inner-values">
																		<ul class="row">
																		<li class="col-xl-6 col-lg-4 col-12 mb-2">
																			<strong><label>Ticket Name<span class="text-danger">&nbsp;*</span></label></strong><br/><input type="text" name="ticketItemsList[0].ticketName" id="ticketName" data-validation="required validate_Space validate_AlphaNumber validate_length length1-40" style="width: 200px;" field-name="Ticket Name" />
																		</li>
																		<li class="col-xl-6 col-lg-4 col-12 mb-2">
																			<strong><label>Total Tickets<span class="text-danger">&nbsp;*</span></label></strong><br/><input type="text" name="ticketItemsList[0].totalTickets" id="totalTickets" data-validation="required validate_Space validate_int" field-name="Ticket Name" />
																		</li>
											 								<li class="col-xl-12 col-lg-6 col-12 mb-2"><strong><label>Stag<span class="text-danger">&nbsp;*</span></label></strong><br/>
											 								<input type="text" name="ticketItemsList[0].stagTicketCost" id="stagTicketCost" placeholder="Ticket Cost" data-validation="required validate_int" field-name="Amount" />
											 								<input type="text" name="ticketItemsList[0].stagMaxBooking" id="stagMaxBooking" placeholder="Max Booking Allowed" data-validation="required validate_int" field-name="Quantity"/>
											 								</li>
											 								
											 								<li class="col-xl-12 col-lg-6 col-12 mb-2"><strong><label>Couple<span class="text-danger">&nbsp;*</span></label></strong><br/>
											 								<input type="text" name="ticketItemsList[0].coupleTicketCost" id="coupleTicketCost" placeholder="Ticket Cost"  data-validation="required validate_int" field-name="Amount" />
											 								<input type="text" name="ticketItemsList[0].coupleMaxBooking" id="coupleMaxBooking" placeholder="Max Booking Allowed" data-validation="required validate_int" field-name="Quantity"/>
											 								</li>
											 								
											 								<li class="col-xl-12 col-lg-6 col-12 mb-2"><strong><label>Single Lady<span class="text-danger">&nbsp;*</span></label></strong><br/>
											 								<input type="text" name="ticketItemsList[0].singleLadyTicketCost" id="singleLadyTicketCost" placeholder="Ticket Cost"  data-validation="required validate_int" field-name="Amount" />
											 								<input type="text" name="ticketItemsList[0].singleLadyMaxBooking" id="singleLadyMaxBooking" placeholder="Max Booking Allowed" data-validation="required validate_int" field-name="Quantity"/>
											 								</li>
										    			        		</ul>
																	</div>
																	</div>
																</div>
																
																<div id="ticketItemContainer"></div>
																
																</div>
													    </div>
					
		                                    
		                                    </div>
		                                    	
                                    	</div>
                                    </div>
                               </div>
			                                
                                
                                </div><br/>
		                                
		                          <div class="row">	
									   <div class="col-12 ui-btn-wrap">
									               <ul>
									               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="compareTimeSlots()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
									               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/addEvent'"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
									               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/events'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
									               </ul>
									   </div>
		                          </div>
		                           </form>
		                   	</div>
		           	</div>
                </div>
          </div>
           <jsp:include page="../wayupartyMasterFooter.jsp" />
           <script src="/resources/js/bootstrap-timepicker.js"></script> 
    </div>
    
<div id="divStartTimeTd" style="display: none; float: left;">
<div class="input-group bootstrap-timepicker mt-2">
				      <input type="text" class="form-control" readonly="readonly" id="REPLACEROWIDstartTime" name="timeSchedulerInfo[REPLACEROWID].startTime"  data-validation="required validate_space" field-name="Start Time" >
				      <span class="input-group-addon">&nbsp;<i class="fa fa-clock" style="top: 18px;"></i></span>&nbsp;&nbsp;&nbsp;
</div>
</div>


<div id="divEndTimeTd" style="display: none; float: left;">
<div class="input-group bootstrap-timepicker  mt-2">
				      <input type="text" class="form-control" readonly="readonly" id="REPLACEROWIDendTime" name="timeSchedulerInfo[REPLACEROWID].endTime"  data-validation="required validate_space" field-name="End Time" >
				      <span class="input-group-addon">&nbsp;<i class="fa fa-clock" style="top: 18px;"></i></span>&nbsp;&nbsp;&nbsp;
</div>
</div>

<div id="divRemoveTimeTd" style="display: none; float: left;">
<br/>
   <a href="javascript:void(0);">&nbsp;<span class="fa fa-times text-danger" style="top: 120px; right: 60px;" onclick="removeTimeSchedularRow(REPLACEROWID);"></span></a> 
</div> 
    
    
     <script type="text/javascript">
		window.onload = function () {
			getVendorTicketsList();
			getMusicGenre();
			var eventUUID = '${eventUUID}';
			if(eventUUID != null && eventUUID != '' && eventUUID != 'undefined'){
				getEventDetails(eventUUID);
				$('.bootstrap-timepicker > input').timepicker();
			}else{
				getTimeSchedular();
				$('.bootstrap-timepicker > input').timepicker();
			}
			
		 };
    </script>
    
    
    <script>
	   function getEventDetails(eventUUID){
	    	
	    	   var appUrl ='${appUrl}';
	    	   $("#ticketDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	    	   $.ajax({
	    			  type: "GET",
	    			    url: appUrl+"/getSavedEventDetails?${_csrf.parameterName}=${_csrf.token}&eventUUID="+eventUUID,
	    			     success :function(response) {
	    			    	    
	    			    	 
	    			    	 $("#eventName").val(response.object.eventName);
	    			    	 $("#eventType").val(response.object.eventType);
	    			    	 $("#eventLocation").val(response.object.eventLocation);
	    			    	 $("#eventHost").val(response.object.eventHost);
	    			    	 $("#eventDate").val(response.object.eventDate);
	    			    	 $("#eventLanguage").val(response.object.eventLanguage);
	    			    	 getMusicGenre(response.object.musicType);
	    			    	 
	    			     if(response.object.displayImage != ''){
	    			    		 document.getElementById("displayImageDiv").style.display="none";
	    			    		 document.getElementById("savedDisplayImg").style.display="block";
	    			    		 $("#savedImage").html('<img src="'+response.object.displayImage+'" data-id= "vendorProfileImage" >');
	    			    		 $("#uploadDisplayImageBtn").removeAttr('data-validation');
	    			    		 $("#uploadDisplayImageBtn").attr('data-validation','validateImg');
	    			    		 $("#uploadDisplayImageBtn").attr('data-validation-optional','true');
	    			    	 }else{
	    			    		 document.getElementById("savedDisplayImg").style.display="none";
	    			    		 document.getElementById("displayImageDiv").style.display="block";
	    			    	 } 
	    			     
	    			     
	    			     if(response.object.bannerImage != ''){
    			    		 document.getElementById("bannerImageDiv").style.display="none";
    			    		 document.getElementById("savedBannerImg").style.display="block";
    			    		 $("#savedBannerImage").html('<img src="'+response.object.bannerImage+'" data-id= "vendorProfileImage" >');
    			    		 $("#uploadBannerImageBtn").removeAttr('data-validation');
    			    		 $("#uploadBannerImageBtn").attr('data-validation','validateImg');
    			    		 $("#uploadBannerImageBtn").attr('data-validation-optional','true');
    			    	 }else{
    			    		 document.getElementById("savedBannerImg").style.display="none";
    			    		 document.getElementById("bannerImageDiv").style.display="block";
    			    	 } 
	    			    	 
	    			    	 
	    			    	 $("textarea[name=eventAddress]").val(response.object.eventAddress);
	    			    	 
	    			    	 
 							if(response.object.ticketItemsList.length > 0){
 								 $("#ticketItemCounter").val(response.object.ticketItemsList.length);
	    			    		 for (var j=0; j<response.object.ticketItemsList.length; j++){
		    			    		 var opt = response.object.ticketItemsList[j];
		    			    		 if(j == 0){
		    			    			 $("#ticketName").val(opt.ticketName);
		    			    			 $("#totalTickets").val(opt.totalTickets);
		    			    			 $("#stagTicketCost").val(opt.stagTicketCost);
		    			    			 $("#stagMaxBooking").val(opt.stagMaxBooking);
		    			    			 $("#coupleTicketCost").val(opt.coupleTicketCost);
		    			    			 $("#coupleMaxBooking").val(opt.coupleMaxBooking);
		    			    			 $("#singleLadyTicketCost").val(opt.singleLadyTicketCost);
		    			    			 $("#singleLadyMaxBooking").val(opt.singleLadyMaxBooking);
		    			    		 }else{
		    			    			 
		    			    			 var result = "";
		    			    	    	    
		    			    			    result = result+'<div class="cardEvent-design" id="ticket'+j+'" data-id="ticket'+j+'">';
		    			    			    result = result+'<h4 class="bg-event-primary">Ticket</h4>';
		    			    			    result = result+'<div class="clearfix"></div>';
		    			    			    result = result+'<div class="cardEvent-design-inner">';
		    			    			    result = result+'<div class="cardEvent-design-inner-values">';
		    			    			    result = result+'<span class="deleteBtn">';
		    			    			    result = result+'<i class="fa fa-times" style="cursor:pointer;" onclick="removeTicketItem(this,'+j+')"></i>';
		    			    			    result = result+'</span>';
		    			    			    result = result+'<ul class="row">';
		    			    			    result = result+'<li class="col-xl-6 col-lg-4 col-12 mb-2">';
		    			    			    result = result+'<strong><label>Ticket Name<span class="text-danger">&nbsp;*</span></label></strong><br/><input type="text" name="ticketItemsList['+j+'].ticketName" value="'+opt.ticketName+'" data-validation="required validate_Space validate_AlphaNumber validate_length length1-40" style="width: 200px;" field-name="Ticket Name" />';
		    			    			    result = result+'</li>';
		    			    			    result = result+'<li class="col-xl-6 col-lg-4 col-12 mb-2">';
		    			    			    result = result+'<strong><label>Total Tickets<span class="text-danger">&nbsp;*</span></label></strong><br/><input type="text" name="ticketItemsList['+j+'].totalTickets" value="'+opt.totalTickets+'" data-validation="required validate_Space validate_int" field-name="Ticket Name" />';
		    			    			    result = result+'</li>';
		    			    			    result = result+'<li class="col-xl-12 col-lg-6 col-12 mb-2"><strong><label>Stag<span class="text-danger">&nbsp;*</span></label></strong><br/>';
		    			    			    result = result+'<input type="text" name="ticketItemsList['+j+'].stagTicketCost" value="'+opt.stagTicketCost+'" placeholder="Ticket Cost" data-validation="required validate_int" field-name="Amount" />';
		    			    			    result = result+'&nbsp;<input type="text" name="ticketItemsList['+j+'].stagMaxBooking" value="'+opt.stagMaxBooking+'" placeholder="Max Booking Allowed" data-validation="required validate_int" field-name="Quantity"/>';
		    			    			    result = result+'</li>';
		    			    							
		    			    			    result = result+'<li class="col-xl-12 col-lg-6 col-12 mb-2"><strong><label>Couple<span class="text-danger">&nbsp;*</span></label></strong><br/>';
		    			    			    result = result+'<input type="text" name="ticketItemsList['+j+'].coupleTicketCost" value="'+opt.coupleTicketCost+'" placeholder="Ticket Cost"  data-validation="required validate_int" field-name="Amount" />';
		    			    			    result = result+'&nbsp;<input type="text" name="ticketItemsList['+j+'].coupleMaxBooking" value="'+opt.coupleMaxBooking+'" placeholder="Max Booking Allowed" data-validation="required validate_int" field-name="Quantity"/>';
		    			    			    result = result+'</li>';
		    			    							
		    			    			    result = result+'<li class="col-xl-12 col-lg-6 col-12 mb-2"><strong><label>Single Lady<span class="text-danger">&nbsp;*</span></label></strong><br/>';
		    			    			    result = result+'<input type="text" name="ticketItemsList['+j+'].singleLadyTicketCost" value="'+opt.singleLadyTicketCost+'" placeholder="Ticket Cost"  data-validation="required validate_int" field-name="Amount" />';
		    			    			    result = result+'&nbsp;<input type="text" name="ticketItemsList['+j+'].singleLadyMaxBooking" value="'+opt.singleLadyMaxBooking+'" placeholder="Max Booking Allowed" data-validation="required validate_int" field-name="Quantity"/>';
		    			    			    result = result+'</li>';
		    			    			    result = result+'</ul>';
		    			    			    result = result+'</div>';
		    			    			    result = result+'</div>';
		    			    			    result = result+'</div>';
		    			    			    

		    			    				$('<div>').append(result).appendTo($('#ticketItemContainer'));
		    			    		 }
		    			    	 }
	    			    	 }
	    			    	 
	    			    	 
	    			    	 var result = "";
	    			    	 var rowCount = parseInt(response.object.timeSchedulerInfo.length);
	    			    	 
	    			    	 result = result +"<table class='timeMultipleAddrow' cellpadding='0' cellspacing='0'>";
			        		 result = result +"<input type='hidden' id='savedTimeRowCount' value='"+response.object.timeSchedulerInfo.length+"' />";
	    			    	 for (var i=0; i<response.object.timeSchedulerInfo.length; i++){
	    			    		 var counter = i;
				    			 var opt = response.object.timeSchedulerInfo[i];
				    			 
				    			 if(i == 0){
					    			 result = result +"<input type='hidden' name='timeAttcahmentMaxRowNumber'  id='timeAttcahmentMaxRowNumber' value='"+rowCount+"' />";
					        	     result = result +"<input type='hidden' id='addedTimeRowCount' value='"+rowCount+"' />";
					        	 	 result = result +"<table class='timeMultipleAddrow'  id='timeAttachmentTable' cellpadding='0' cellspacing='0'>";
				    			 }
				    			 
				    			 result = result +"<tr id='divTimeRow"+counter+"'>";
								 	
				    			 result = result +"<td><div class='input-group bootstrap-timepicker mt-2'><input type='text' readonly='readonly' class='form-control' value='"+opt.startTime+"' data-validation='required' field-name='Start Time' name='timeSchedulerInfo["+counter+"].startTime' id='"+counter+"startTime'><span class='input-group-addon'>&nbsp;<i class='fa fa-clock' style='top: 18px;'></i></span></div></td>";
		    				 	 result = result +"<td><div class='input-group bootstrap-timepicker mt-2'><input type='text' readonly='readonly' class='form-control' value='"+opt.endTime+"' data-validation='required' field-name='End Time' name='timeSchedulerInfo["+counter+"].endTime' id='"+counter+"endTime'><span class='input-group-addon'>&nbsp;<i class='fa fa-clock' style='top: 18px;'></i></span></div></td>";
				        	 	 
				        	 	 result = result +"<td>"
				        	 	 
				        	 	 if(i == 0){
				        	 		result = result +"<td><a href='javascript:void(0);'>&nbsp;<span class='fa fa-plus text-success' style='top:50px!important; left: 405px;' id='addDocRow' onclick='addTimeSchedularRow()'></span></a></td>";
				        	 	 }else{
				        	 		 result = result +"<td><a href='javascript:void(0);'>&nbsp;<span class='fa fa-times text-danger'  style='top:105px!important; left: 430px;' onclick='deleteTimeRow("+counter+")'></span></a></td>";
				        	 	 }
							 	
							 	 result = result +"</tr>";
	    			    	 }
	    			    	 
	    			    	 result = result +"</table>"; 
	    			    	 
	    				 	$("#timeScheduleList").empty();
	    				   	$("#timeScheduleList").append(result);
	    			    	$("#ticketDetailsLoadingDiv").removeAttr("style");
	    			    	
	    			    	$('.bootstrap-timepicker > input').timepicker();
	    			    	
	    					},
	    		});
	    }
	   
	   function deleteDisplayImage(){
			document.getElementById("displayImageDiv").style.display="block";
			document.getElementById("savedDisplayImg").style.display="none";
			$("#uploadDisplayImageBtn").removeAttr('data-validation-optional');
   		    $("#uploadDisplayImageBtn").attr('data-validation','required validateImg');
		}
	   
	   
	   function deleteBannerImage(){
			document.getElementById("bannerImageDiv").style.display="block";
			document.getElementById("savedBannerImg").style.display="none";
			$("#uploadBannerImageBtn").removeAttr('data-validation-optional');
  		    $("#uploadBannerImageBtn").attr('data-validation','required validateImg');
		}
	   
	   function deleteTimeRow(rowId){
			 document.getElementById("divTimeRow"+rowId).remove();
			 var checkMaxTsRowNum = $("#timeAttcahmentMaxRowNumber").val();
			 $("#timeAttcahmentMaxRowNumber").val(parseInt(checkMaxTsRowNum) - 1)
		}
    </script>
    
    <script>
    function getTimeSchedular(){
    	var result = "";
    	
    	 result = result +"<input type='hidden' name='timeAttcahmentMaxRowNumber'  id='timeAttcahmentMaxRowNumber' value='1' />";
	     result = result +"<input type='hidden' id='addedTimeRowCount' value='1' />";
	 	 result = result +"<table class='timeMultipleAddrow'  id='timeAttachmentTable' cellpadding='0' cellspacing='0'>";
	 	 result = result +"<tr id='divTimeRow0'>";
	 	 result = result +"<td><div class='input-group bootstrap-timepicker'><input type='text' readonly='readonly' class='form-control' data-validation='required' field-name='Start Time' name='timeSchedulerInfo[0].startTime' id='0startTime'><span class='input-group-addon'>&nbsp;<i class='fa fa-clock' style='top: 18px;'></i></span></div></td>";
	 	 result = result +"<td><div class='input-group bootstrap-timepicker'><input type='text' readonly='readonly' class='form-control' data-validation='required' field-name='End Time' name='timeSchedulerInfo[0].endTime' id='0endTime'><span class='input-group-addon'>&nbsp;<i class='fa fa-clock' style='top: 18px;'></i></span></div></td>";
	 	 result = result +"<td><a href='javascript:void(0);'>&nbsp;<span class='fa fa-plus text-success' style='top:50px!important; left: 405px;' id='addDocRow' onclick='addTimeSchedularRow()'></span></a></td>";
	 	 result = result +"</tr>";
	 	 result = result +"</table>";
	 	 
	 	$("#timeScheduleList").empty();
	   	$("#timeScheduleList").append(result);
    }
    </script>
    
    
    <script>
function addTimeSchedularRow(){  
			var checkMaxTsRowNum = $("#timeAttcahmentMaxRowNumber").val();
			var timeRowCount = $("#addedTimeRowCount").val();
			var tbl = document.getElementById("timeAttachmentTable");
			var maxRowNum = parseInt($("input[name = 'timeAttcahmentMaxRowNumber']").val());
			$("input[name = 'timeAttcahmentMaxRowNumber']").val(maxRowNum + 1);
			$("input[id = 'addedTimeRowCount']").val(parseInt(timeRowCount) + parseInt(1));
			var row = tbl.insertRow(maxRowNum);
			row.setAttribute("id", "divTimeRow" + timeRowCount);
			
			var cell0 = row.insertCell(0);
			var divDocTypeTdStr = document.getElementById("divStartTimeTd").innerHTML;
			divDocTypeTdStr = divDocTypeTdStr.replace(/REPLACEROWID/g, (timeRowCount));
			cell0.innerHTML = divDocTypeTdStr;
			
			var cell1 = row.insertCell(1);
			var divDocTypeTdStr = document.getElementById("divEndTimeTd").innerHTML;
			divDocTypeTdStr = divDocTypeTdStr.replace(/REPLACEROWID/g, (timeRowCount));
			cell1.innerHTML = divDocTypeTdStr;
			
			var cell2 = row.insertCell(2);
			var divDocRemoveTdStr = document.getElementById("divRemoveTimeTd").innerHTML;
			divDocRemoveTdStr = divDocRemoveTdStr.replace(/REPLACEROWID/g, (timeRowCount));
			cell2.innerHTML = divDocRemoveTdStr;
			
			$('.bootstrap-timepicker > input').timepicker();
}	

</script>

<script type="text/javascript">
	function removeTimeSchedularRow(rowId) {
		
		 var addedTimeRowCount = document.getElementById("addedTimeRowCount").value;
		 $("input[id = 'addedTimeRowCount']").val(parseInt(addedTimeRowCount));
		 
		 var documentsRow = document.getElementById("addedTimeRowCount").value;
		 
		 document.getElementById("divTimeRow"+rowId).style.display = 'none';
		 document.getElementById("divTimeRow"+rowId).value= '';
		 var  rId = "divTimeRow"+rowId;
		 var r = document.getElementById("timeAttachmentTable").rows[rId];
         var count = 2*(r.cells.length);
		    for(i=0;i<=count;i++){
		        r.deleteCell(0);
		    } 
	}
</script>

<script>
function getMusicGenre(musicGenre){
    
    $("#ticketDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
  	 var appUrl = '${appUrl}';
  	 var vendorUUID = '${vendorUUID}';
  	 var formData = new FormData();
	 formData.append("categoryType", "Music Genre");
	 formData.append("vendorUUID", vendorUUID);
	 
    $.ajax({
	    data: formData,
	    contentType: false,
	    processData: false,
	    	type: "POST",
	    	url: appUrl+"/getProfileCategories?${_csrf.parameterName}=${_csrf.token}", 
	        success: function(resultData) {
	   			
	        	 var result = "";
	        	
	        	  for (var i=0; i<resultData.data.length; i++)
	   				{ 
	        		  var opt = resultData.data[i];
	        		  if(opt.isCategoryConfigured == 'Yes'){
	        			  var selected = ''
	        			  if(musicGenre != null && musicGenre != '' && musicGenre != 'undefined'){
	        				  var musicGenreList = musicGenre.split(',');
	        				  if(musicGenreList.includes(opt.categoryName)){
	        					  selected = 'selected';
	        				  }
	        			  }
	        			  
	        			  result = result + "<option value='" +opt.categoryName+ "' "+selected+">"+opt.categoryName+"</option>";
	        		  }
	   				}
	        	
	        	 $("#musicType").empty();  
	    	     $("#musicType").append(result);
	    	     $('#musicType').trigger("chosen:updated");
			     $("#ticketDetailsLoadingDiv").removeAttr("style");
	    	     
	        },
	    });
   }
</script>  


<script>
function addTicket(){
	var ticketItemCounter = $("#ticketItemCounter").val();
    	    var result = "";
    	    
		    result = result+'<div class="cardEvent-design" id="ticket'+ticketItemCounter+'" data-id="ticket'+ticketItemCounter+'">';
		    result = result+'<h4 class="bg-event-primary">Ticket</h4>';
		    result = result+'<div class="clearfix"></div>';
		    result = result+'<div class="cardEvent-design-inner">';
		    result = result+'<div class="cardEvent-design-inner-values">';
		    result = result+'<span class="deleteBtn">';
		    result = result+'<i class="fa fa-times" style="cursor:pointer;" onclick="removeTicketItem(this,'+ticketItemCounter+')"></i>';
		    result = result+'</span>';
		    result = result+'<ul class="row">';
		    result = result+'<li class="col-xl-6 col-lg-4 col-12 mb-2">';
		    result = result+'<strong><label>Ticket Name<span class="text-danger">&nbsp;*</span></label></strong><br/><input type="text" name="ticketItemsList['+ticketItemCounter+'].ticketName" data-validation="required validate_Space validate_AlphaNumber validate_length length1-40" style="width: 200px;" field-name="Ticket Name" />';
		    result = result+'</li>';
		    result = result+'<li class="col-xl-6 col-lg-4 col-12 mb-2">';
		    result = result+'<strong><label>Total Tickets<span class="text-danger">&nbsp;*</span></label></strong><br/><input type="text" name="ticketItemsList['+ticketItemCounter+'].totalTickets"  data-validation="required validate_Space validate_int" field-name="Ticket Name" />';
		    result = result+'</li>';
		    result = result+'<li class="col-xl-12 col-lg-6 col-12 mb-2"><strong><label>Stag<span class="text-danger">&nbsp;*</span></label></strong><br/>';
		    result = result+'<input type="text" name="ticketItemsList['+ticketItemCounter+'].stagTicketCost" placeholder="Ticket Cost" data-validation="required validate_int" field-name="Amount" />';
		    result = result+'&nbsp;<input type="text" name="ticketItemsList['+ticketItemCounter+'].stagMaxBooking" placeholder="Max Booking Allowed" data-validation="required validate_int" field-name="Quantity"/>';
		    result = result+'</li>';
						
		    result = result+'<li class="col-xl-12 col-lg-6 col-12 mb-2"><strong><label>Couple<span class="text-danger">&nbsp;*</span></label></strong><br/>';
		    result = result+'<input type="text" name="ticketItemsList['+ticketItemCounter+'].coupleTicketCost" placeholder="Ticket Cost"  data-validation="required validate_int" field-name="Amount" />';
		    result = result+'&nbsp;<input type="text" name="ticketItemsList['+ticketItemCounter+'].coupleMaxBooking" placeholder="Max Booking Allowed" data-validation="required validate_int" field-name="Quantity"/>';
		    result = result+'</li>';
						
		    result = result+'<li class="col-xl-12 col-lg-6 col-12 mb-2"><strong><label>Single Lady<span class="text-danger">&nbsp;*</span></label></strong><br/>';
		    result = result+'<input type="text" name="ticketItemsList['+ticketItemCounter+'].singleLadyTicketCost" placeholder="Ticket Cost"  data-validation="required validate_int" field-name="Amount" />';
		    result = result+'&nbsp;<input type="text" name="ticketItemsList['+ticketItemCounter+'].singleLadyMaxBooking" placeholder="Max Booking Allowed" data-validation="required validate_int" field-name="Quantity"/>';
		    result = result+'</li>';
		    result = result+'</ul>';
		    result = result+'</div>';
		    result = result+'</div>';
		    result = result+'</div>';
		    

			$('<div>').append(result).appendTo($('#ticketItemContainer'));

			ticketItemCounter++;
		    $("#ticketItemCounter").val(ticketItemCounter);
}

function removeTicketItem(div, id){
	$("#ticket"+id).remove();
}

</script>  
    
<script>
function getVendorTicketsList(){
	 
	var vendorUUID = "${vendorUUID}";
	var appUrl = "${appUrl}";
	  $("#ticketDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	 
		$.ajax({
	  		  type: "GET",
	  		    url: appUrl+"/getVendorEventTicketsList?vendorUUID="+vendorUUID,
	  		     success :function(resultData) {
	  		    	 var result = "";
	  		    	
	  		    	 if(resultData != null && resultData.data.length > 0){
	  		    		 for (var i=0; i< resultData.data.length; i++)
	  	        	    	{
	  		    			 
	  		    			var opt = resultData.data[i];
	  		    			result = result +'<ul class="accordion mt-3">';
	  		    			result = result +'<li>';
	  		    			result = result +'<div class="toggle menuToggle'+opt.ticketUUID+'" onclick="toogleMenu(\''+opt.ticketUUID+'\')">';
	  		    			result = result +'<div class="row">';
	  		    			result = result +'<div class="col-md-11">';
	  		    			result = result +'<h6>'+opt.ticketName+'</h6>';
	  		    			result = result +'<div class="clearfix"></div>';
	  		    			result = result +'</div>';
	  		    			result = result +'<div class="col-md-1">';
	  		    			result = result +'<div class="arrowic"></div>';
	  		    			result = result +'</div>';
	  		    			result = result +'</div>';
	  		    			result = result +'</div>';
	  		    			
	  		    			result = result +'<div class="menu-inner">';
	 								
	 								result = result +"<ul class='row'>";
	 								result = result +'<li class="col-xl-12 col-lg-6 col-12 mb-2"><strong><label>Stag<span class="text-danger">&nbsp;*</span></label></strong><br/>';
	 								result = result +'&nbsp;&nbsp;<input type="text" id="stagTicketAmount'+opt.ticketUUID+'" placeholder="Ticket Cost" data-validation="required validate_int" field-name="Amount" />';
	 								result = result +'&nbsp;<input type="text" id="stagTicketQuantity'+opt.ticketUUID+'" placeholder="Max Booking Allowed" data-validation="required validate_int" field-name="Quantity"/>';
	 								result = result +'</li>';
	 								
	 								result = result +'<li class="col-xl-12 col-lg-6 col-12 mb-2"><strong><label>Couple<span class="text-danger">&nbsp;*</span></label></strong><br/>';
	 								result = result +'&nbsp;&nbsp;<input type="text" id="coupleTicketAmount'+opt.ticketUUID+'" placeholder="Ticket Cost"  data-validation="required validate_int" field-name="Amount" />';
	 								result = result +'&nbsp;<input type="text" id="coupleTicketQuantity'+opt.ticketUUID+'" placeholder="Max Booking Allowed" data-validation="required validate_int" field-name="Quantity"/>';
	 								result = result +'</li>';
	 								
	 								result = result +'<li class="col-xl-12 col-lg-6 col-12 mb-2"><strong><label>Single Lady<span class="text-danger">&nbsp;*</span></label></strong><br/>';
	 								result = result +'&nbsp;&nbsp;<input type="text" id="singleLadyTicketAmount'+opt.ticketUUID+'" placeholder="Ticket Cost"  data-validation="required validate_int" field-name="Amount" />';
	 								result = result +'&nbsp;<input type="text" id="singleLadyTicketQuantity'+opt.ticketUUID+'" placeholder="Max Booking Allowed" data-validation="required validate_int" field-name="Quantity"/>';
	 								result = result +'</li>';
	    			        		result = result +"</ul>";
	    			        		
	    			        		
    			        	
	  		    			
	  		    			result = result +'</div>';
	  		    			result = result +'</li>';
	  		    			result = result +'</ul>';
					  		
	  	        	    	}
	  		    	 }else{
	  		    		result = result +'<div  class="no-records-found"></div>';
	  		    	 }
	  		    	 
	  		    	 $("#eventsTicketsListDiv").empty();
	  		    	 $("#eventsTicketsListDiv").empty();
	  		    	 $("#eventsTicketsListDiv").append(result);
	  		    	 $("#ticketDetailsLoadingDiv").removeAttr("style");
	  		    	 
	  		    	 
	  		     },
	  	});
}
</script>

<script>
        function toogleMenu(ticketUUID){
        	 var $this = $('.menuToggle'+ticketUUID);
	    	    if ($this.next().hasClass('show')) {
	    	        $this.next().removeClass('show');
	    	        $this.next().slideUp(350);
	    	        $this.find(".arrowic").removeClass("arrowicActive");
	    	    } else {
	    	        $this.parent().parent().find('li .menu-inner').removeClass('show');
	    	        $this.parent().parent().find('li .menu-inner').slideUp(350);
	    	        $this.next().toggleClass('show');
	    	        $this.next().slideToggle(350);
	    	        $this.find(".arrowic").addClass("arrowicActive");
	    	       
	    	    }
        	
		    	
        }
</script>

    
    <script>
    var _URL = window.URL;
    $("#uploadDisplayImageBtn").change(function (e) {
        var file, img;
        if ((file = this.files[0])) {
            img = new Image();
            img.onload = function () {
            	var width = this.width;
            	var height = this.height;
            	var displayImageSize = parseInt(height) - parseInt(width);
            	if(displayImageSize <= 410){
            		$('input#uploadDisplayImageFile').removeAttr( "class record-exist record-exist-errormsg");
					$('#displayImageDiv').find('p.jquery_form_error_message').remove();
					
					   document.getElementById("uploadDisplayImageBtn").onchange = function () {
					       $('#uploadDisplayImageFile').val($(this).val().replace(/C:\\fakepath\\/i, ''));
					   };
					   $(function () {
					    	   var vendorId = '${vendorId}';
					           fileUpload("uploadDisplayImageBtn", "vendor_events_images", "add_events_form",vendorId,0);
					           $(".clearUpload").show();
					   });
            	}else{
					    $('#displayImageDiv').find('p.jquery_form_error_message').remove(); 
			    		$('input#uploadDisplayImageFile').attr('class','error form-control');
			    		$("input#uploadDisplayImageFile").after("<p class='jquery_form_error_message'>Size Limt Width 870 & Height 1280</p>");
						$('input#uploadDisplayImageFile').attr('record-exist','yes');
						$('input#uploadDisplayImageFile').attr('record-exist-errorMsg',' Size Limt Width 870 & Height 1280'); 
            	}
                
            };
            img.src = _URL.createObjectURL(file);
        }
    });
    
    
    $("#uploadBannerImageBtn").change(function (e) {
        var file, img;
        if ((file = this.files[0])) {
            img = new Image();
            img.onload = function () {
            	if(this.width >= 375 && this.height >= 210){
            		$('input#uploadBannerImageFile').removeAttr( "class record-exist record-exist-errormsg");
					$('#bannerImageDiv').find('p.jquery_form_error_message').remove();
					
					   document.getElementById("uploadBannerImageBtn").onchange = function () {
					       $('#uploadBannerImageFile').val($(this).val().replace(/C:\\fakepath\\/i, ''));
					   };
					   $(function () {
					    	   var vendorId = '${vendorId}';
					           fileUpload("uploadBannerImageBtn", "vendor_events_images", "add_events_form",vendorId,1);
					           $(".clearUpload").show();
					   });
            	}else{
            		    $('#bannerImageDiv').find('p.jquery_form_error_message').remove(); 
			    		$('input#uploadBannerImageFile').attr('class','error form-control');
			    		$("input#uploadBannerImageFile").after("<p class='jquery_form_error_message'>Size Limt Width 375 & Height > 210</p>");
						$('input#uploadBannerImageFile').attr('record-exist','yes');
						$('input#uploadBannerImageFile').attr('record-exist-errorMsg',' Size Limt Width 375 & Height > 210');
            	}
                
            };
            img.src = _URL.createObjectURL(file);
        }
    });
    </script>
    
    
 <script>
 function compareTimeSlots() {
 	var checkMaxTsRowNum = $("#timeAttcahmentMaxRowNumber").val();
 	var startTimeArray = new Array();
	var endTimeArray = new Array();
 	for(var i=0; i<=checkMaxTsRowNum; i++){
 		var myEle = document.getElementById("divTimeRow"+i)
 		if(myEle != null){
 			if(document.getElementById(i+"startTime") != null && document.getElementById(i+"endTime") != null){ 
 			    var strStartTime = document.getElementById(i+"startTime").value;
 		        var strEndTime = document.getElementById(i+"endTime").value;

 		        var startTime = new Date().setHours(GetHours(strStartTime), GetMinutes(strStartTime), 0);
 		        var endTime = new Date(startTime)
 		        endTime = endTime.setHours(GetHours(strEndTime), GetMinutes(strEndTime), 0);
 		        
 		       if((startTimeArray.includes(strStartTime) || endTimeArray.includes(strStartTime)) && i > 0){
		        	$('#divTimeRow'+i).find('p.jquery_form_error_message').remove(); 
		        	
		        	$('input#'+i+'startTime').attr('class','error form-control');
		    		$('input#'+i+'startTime').attr('record-exist','yes');
		    		$('input#'+i+'startTime').attr('style','background-color: #f65151');
		    		$('input#'+i+'startTime').after("<p class='jquery_form_error_message'></p>");
		    		$('input#'+i+'startTime').attr('record-exist-errorMsg',''); 
		    		
		    		$('input#'+i+'endTime').attr('class','error form-control');
		    		$('input#'+i+'endTime').attr('record-exist','yes');
		    		$('input#'+i+'endTime').attr('style','background-color: #f65151');
		    		$('input#'+i+'endTime').after("<p class='jquery_form_error_message'></p>");
		    		$('input#'+i+'endTime').attr('record-exist-errorMsg',''); 
					$("#save_button" ).addClass('btn-fill-lg font-normal text-light gradient-pastel-green').val('Save');
		        }else if (startTime < endTime) {
 		            $('input#'+i+'startTime').removeAttr("class record-exist record-exist-errormsg");
 					$('input#'+i+'startTime').attr('class','valid form-control');
 					$('input#'+i+'startTime').attr('style','background-color:#f0f1f3');
 					
 					$('input#'+i+'endTime').removeAttr("class record-exist record-exist-errormsg");
  					$('input#'+i+'endTime').attr('class','valid form-control');
  					$('input#'+i+'endTime').attr('style','background-color:#f0f1f3');
  					
 					$('#divTimeRow'+i).find('p.jquery_form_error_message').remove();
 					$( "#save_button" ).removeAttr("style");
 					$( "#save_button" ).addClass('btn-fill-lg font-normal text-light gradient-pastel-green').val('Save');
 					startTimeArray.push(strStartTime);
	    		    endTimeArray.push(strEndTime);
 		        }else if(startTime === endTime){
 		        	$('#divTimeRow'+i).find('p.jquery_form_error_message').remove(); 
 		    		$('input#'+i+'endTime').attr('class','error form-control');
 		    		$('input#'+i+'endTime').attr('record-exist','yes');
 		    		$('input#'+i+'endTime').attr('style','background-color: #f65151');
 		    		$('input#'+i+'endTime').after("<p class='jquery_form_error_message'></p>");
 		    		$('input#'+i+'endTime').attr('record-exist-errorMsg',''); 
 					$("#save_button" ).addClass('btn-fill-lg font-normal text-light gradient-pastel-green').val('Save');
 		        }else{
 		        	$('#divTimeRow'+i).find('p.jquery_form_error_message').remove(); 
 		    		$('input#'+i+'startTime').attr('class','error form-control');
 		    		$('input#'+i+'startTime').attr('record-exist','yes');
 		    		$('input#'+i+'startTime').attr('style','background-color: #f65151');
 		    		$('input#'+i+'startTime').after("<p class='jquery_form_error_message'></p>");
 		    		$('input#'+i+'startTime').attr('record-exist-errorMsg',''); 
 					$("#save_button" ).addClass('btn-fill-lg font-normal text-light gradient-pastel-green').val('Save');
 		        }
 		        
 			}  
 		}
 	}
 	
 	saveVedorEvent();

 }
 function GetHours(d) {
     var h = parseInt(d.split(':')[0]);
     if (d.split(':')[1].split(' ')[1] == "PM") {
         h = h + 12;
     }
     return h;
 }
 function GetMinutes(d) {
     return parseInt(d.split(':')[1].split(' ')[0]);
 }
 
 </script>
 
 <script>
 function saveVedorEvent(){
	    if ($('#add_events_form').validate(false, validationSettings)){
		   $("#ticketDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
		     var appUrl = "${appUrl}";
			 var vendorUUID = "${vendorUUID}";
			 var eventUUID = "${eventUUID}";
		     var formData = getWayupayFormData("add_events_form");
		     formData.append("vendorUUID", vendorUUID);
		     formData.append("eventUUID", eventUUID);
		     
           $.ajax({
           	type: "POST",
           	data: formData,
               contentType : 'application/json; charset=utf-8',
               enctype: 'multipart/form-data',
               processData: false,
               contentType:false,
               dataType : 'json',
           	    	 url: appUrl+"/saveVendorEvents?${_csrf.parameterName}=${_csrf.token}", 
       		        success : function(result) {
       		        	
       		        	 if (result && result.response === "INVALID_DATA") {
       		        		 $("#menuLoadingDiv").removeAttr("style");
       		    		     $("#invalidMsgDiv").removeAttr("style");
       			    		 $("#invalidMsgDiv").css({ display: "block" });
       				         $("#invalidMsg").html("Attention! You had missed some data.");
       				         $("#invalidMsgDiv").fadeIn(500);
       				 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
       		    		   }
       		        	else if (result.response === "AWKWARD") {
       		        		   $("#menuLoadingDiv").removeAttr("style");
       		    		        location.href = "/errorPage";
       		    		   }  else if (result.response === "SUCCESS") {     
       					         $("#successMsgDiv").removeAttr("style");
       				    		 $("#successMsgDiv").css({ display: "block" });
       					         $("#successMsg").html("Well done! You successfully added new event.");
       					         $("#successMsgDiv").fadeIn(200);
       					 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
       					 	     location.replace(appUrl+"/events");
       	                       }
       		        	
       		        		
       		},
       }); 
           return true;
	   }else{
			return false;
		  } 
 }
 
 $('body').on('blur', '#add_events_form', function() {
		$("#add_events_form").showHelpOnFocus();
		$("#add_events_form").validateOnBlur(false, validationSettings);  
   });  
 </script>
 
 
 
 
  <script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script> 
 