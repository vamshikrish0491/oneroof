<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.26.0/moment.min.js"></script>

<style>
.timeslot{
	display:none;
}
.selectedSlot{
	background: #be9c52 !important;
	color: #fff !important;
}
</style>

<div class="card height-auto">
      <div class="card-body">
          <div class="single-info-details">
                 <div class="vendor_images_img item-img"  id="vendorProfileImage" class="mb-3"></div>
                 <div class="item-content">
                     <div class="header-inline item-header">
                         <h3 class="text-dark-medium font-medium"><span id="vendorName"></span><br/>
                         <span class="view-subtitle" style="font-size: 15px;">
                         <i class="fa fa-map-marker" aria-hidden="true"></i>&nbsp;
                                        <span id="vendorLocation"></span></span><br/>
                                        <span id="directions"></span>
                                        </h3>
                     </div>
                     <p style="color: black;" id="vendorDescription"></p>
                 </div>
             </div>
     </div>
</div>   

  <div class="card height-auto">   
     <div class="card ui-tab-card">
     <div id="serviceslistLoadingDiv"></div>
       <div class="card-body">
	       <div class="icon-tab">
	       		<div id="servicesListDiv"></div>
	       </div>
       </div>
                 
      <div class="ui-btn-wrap">   
       <div class="card-body">
          <div id="categoriesListDiv"></div>
        </div> 
       </div> 
      
       
       <div class="card-body">
       		<div class="service_wrap">
          	<div id="categoryServicesListDiv"></div>
          </div>
       </div> 
       
       <div class="modal-body">
       <div class="modal-box">
       <div class="modal pop-up-modal fade" id="pop-up-modal" tabindex="-1"
                                    role="dialog" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content mt-10">
                                        <div id="vendorDetailsLoaderDiv"></div>
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="dataTitle"></h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                               
                                           <div class="modal-body">
                                            <ul>    
                                               <li><span id="termsAndConditions"></span></li>
                                               </ul>
                                            </div>
                                            <div class="modal-footer">
                                               <button type="button" class="footer-btn bg-gradient-gplus" data-dismiss="modal"><i class="fa fa-times mg-l-10"></i>&nbsp;Close</button>
                                            </div>
                                        </div>
                                    </div>
       </div>
       </div>
       <jsp:include page="buyService.jsp" />
       </div>
    </div>
 </div>
 
 
 <script type="text/javascript">
 
		window.onload = function () {
			getVendorServicesInfo();
		 };
		 
		 var currencyCode = '';
		 
		 function getVendorServicesInfo(){
			   var vendorUUID = '${vendorUUID}';
	    	   var appUrl ='${appUrl}';
	    	   $("#serviceslistLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	    	   $.ajax({
	    			  type: "GET",
	    			    url: appUrl+"/ws/getServicesList?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID,
	    			     success :function(response) {
	    			    	 var serviceUUID = '';
	    			    	 var isEntryRatioEnabled = '';
	    			    	 
	    			    	 $("#vendorProfileImage").html('<img src="'+response.object.profileImage+'" onerror="predefineVendorProfileImage(this);" data-id= "vendorProfileImage" >');
	    			    	 $("#vendorName").html(response.object.vendorName);
	    					 $("#vendorLocation").html(response.object.location);
	    					 if(response.object.latitude != '' && response.object.longitude != ''){
	    						var cordinates =  response.object.latitude+","+response.object.longitude;
	    						var directions = '<a class="view-subtitle" style="font-size: 15px; font-weight: normal;" id="vendorDirections" href="https://www.google.com/maps/place/'+cordinates+'" target="_blank">Get Directions</a>';
	    						$("#directions").append(directions);
	    					 }
	    					 $("#vendorDescription").html(response.object.description);
	    					 
	    					 currencyCode = getCurrency(response.object.currency);
	    					 
		 					if(response.object.servicesList != '' && response.object.servicesList.length > 0){
	    						 
	    						 var result = "";
	    						 
	    						 result = result +'<ul class="nav nav-tabs vendor-cart" role="tablist">';
	    						  for (var i=0; i<response.object.servicesList.length; i++)
	    			   				{ 
	    			        		  var service = response.object.servicesList[i];
	    			        		  var active = '';
	    			        		  if(i == 0){
	    			        			  active = 'active';
	    			        			  serviceUUID = service.serviceUUID;
	    			        			  isEntryRatioEnabled = service.isEntryRatioEnabled;
	    			        		  }
	    			        		  
	    			        		  if(service.serviceName != 'Events'){
	    			        			  result = result +'<li class="nav-item">';
		    			        		  result = result +'<a  style="margin: 5px 15px 5px 15px !important;" class="nav-link border-pastel-gold '+active+'" data-value="'+service.serviceDisplayName+'" data-toggle="tab" href="#service"  onclick="getServiceCategories(\''+service.serviceUUID+'\',\''+service.isEntryRatioEnabled+'\')" role="tab" aria-selected="true" ><img src="'+service.serviceImage+'"></img></a><span><strong> <div align="center" >'+service.serviceName+'</strong></span>';
		    			        		  result = result +'</li>';
	    			        		  }
	    			        		  
	    			   				} 
	                               result = result +'</ul>';
	    			        	  
	    			        	  $("#servicesListDiv").empty();  
		    		   	    	  $("#servicesListDiv").append(result);
	    						 
	    					 }
		 					getServiceCategories(serviceUUID,isEntryRatioEnabled);
	    					 $("#serviceslistLoadingDiv").removeAttr("style");
	    					},
	    		});
		 }
</script>


<script>
function getCurrency(currency){
	var currencyType;
	switch (currency) {
	  case "USD":
		currencyType = "&#36";
	    break;
	  case "EUR":
		  currencyType = "&#128";
	    break;
	  case "CRC":
		  currencyType = "&#8353";
	    break;
	  case "GBP":
		  currencyType = "&#163";
	    break;
	  case "ILS":
		  currencyType = "&#8362";
	    break;
	  case "INR":
		  currencyType = "&#x20B9";
	    break;
	  case  "JPY":
		  currencyType = "&#165";
	    break;
	  case  "KRW":
		  currencyType = "&#8361";
		break;
	  case  "NGN":
		  currencyType = "&#8358";
		break;
	  case  "PHP":
		  currencyType = "&#8369";
	    break;
	  case  "PLN":
		  currencyType = "PLN";
	    break;
	  case  "PYG":
		  currencyType = "&#8370";
	    break;
	  case  "THB":
		  currencyType = "&#3647";
	    break;
	  case  "UAH":
		  currencyType = "&#8372";
	    break;
	  case  "VND":
		  currencyType = "&#8363";
	    break;
	  case  "":
		  currencyType = "&#164";
	    break;
	}
	
	return currencyType
}
</script>


<script>
function getServiceCategories(serviceUUID,isEntryRatioEnabled){
	
	   var appUrl ='${appUrl}';
	   $("#serviceslistLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/ws/getServiceCategoriesList?${_csrf.parameterName}=${_csrf.token}&serviceUUID="+serviceUUID,
			     success :function(resultData) {
					 
			    	 if(resultData.data.length > 0){
						 
						 var result = "";
						 var categoryUUID = "";
						 
						 result = result +'<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">';
						 for (var i=0; i<resultData.data.length; i++)
			   				{ 
							  var opt = resultData.data[i];
							  var selected = '';
			        		  result = result +'<div class="btn-group" role="group" style="padding: 2px;">';
			        		  result = result +'<a class="btn btn-outline-primary btn-sm" id="category'+opt.categoryUUID+'" onclick="getCategoryServices(\''+opt.categoryUUID+'\',\''+isEntryRatioEnabled+'\')">'+opt.categoryName+'</a>';
			        		  result = result +'</div>';
			   				} 
                           result = result +'<div>';
			        	  
			        	  $("#categoriesListDiv").empty();  
			        	  $("#categoryServicesListDiv").empty(); 
    		   	    	  $("#categoriesListDiv").append(result);
						 
					 }
					 $("#serviceslistLoadingDiv").removeAttr("style");
					},
		});
}
</script>

<style>
/* .datepicker-inline .datepicker {
width: 250px !important;
} */

.service_wrap_name span{
cursor: pointer;
}
</style>

<script>
function getCategoryServices(categoryUUID,isEntryRatioEnabled){
	 $( '.btn-toolbar .btn-group' ).find( 'a' ).removeClass('selected');
	 $("#category"+categoryUUID).addClass( 'btn btn-outline-primary btn-sm selected' );
	 var vendorUUID = '${vendorUUID}';
	 var appUrl = '${appUrl}';
	 $("#serviceslistLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/ws/getCategoryServicesList?${_csrf.parameterName}=${_csrf.token}&categoryUUID="+categoryUUID+"&vendorUUID="+vendorUUID,
			     success :function(resultData) {
					 
			    	 var result = "";
			    	 var serviceStartDates = [];
			    	 var serviceEndDates = [];
			    	 var serviceUUIDs = [];
			    	 if(resultData.data.length > 0){
       
						 for (var i=0; i<resultData.data.length; i++)
			   				{ 
							  var opt = resultData.data[i];
							  
							  var servicePrice = '';
							  var quantityButtons = '';
							  if(opt.serviceName == 'Venue' || opt.serviceName == 'Cuisine' || opt.serviceName == 'Events'){
								  quantityButtons = 'disabled';
							  }
							  if(opt.minimumOrder != 0){
								  servicePrice = opt.minimumOrder
							  }else{
								  servicePrice = opt.offerPrice
							  }
							  
							  result = result+'<div class="service_wrap_inn" data-id="4">';
							  result = result+'<div class="service_wrap_top">';
							  result = result+'<img src="/resources/img/logo.png" width="180" height="130">';
							  result = result+'<div class="service_wrap_top_text">Price<br><span>'+currencyCode+' '+servicePrice+'</span></div>';
							  result = result+'</div>';
							  result = result+'<div class="service_wrap_name">';
							  result = result+opt.subCategory;
							  result = result+'<div class="row">';
							  
							  if(opt.minimumOrder != 0){
								  result = result+'<span class="col-xl-6 col-lg-6 col-12">Minimum Order :&nbsp;'+currencyCode+' '+opt.minimumOrder+'</span>';
								  
								  if(opt.discountType != ''){
									  if(opt.discountType == 'amount'){
										  result = result+'<span class="col-xl-6 col-lg-6 col-12">Discount :&nbsp;'+currencyCode+' '+opt.discountValue+'</span>';
									  }else{
										  result = result+'<span class="col-xl-6 col-lg-6 col-12">Discount :&nbsp;'+opt.discountValue+' %</span>';
									  }
									 
								  }
								 
							  }else{
								  result = result+'<span class="col-xl-6 col-lg-6 col-12">Actual Price :&nbsp;'+currencyCode+' '+opt.actualPrice+' per slot</span>';
								  result = result+'<span class="col-xl-6 col-lg-6 col-12">Offer Price :&nbsp;'+currencyCode+' '+opt.offerPrice+' per slot</span>';
							  }
							 
							//   result = result+'<span class="col-xl-6 col-lg-6 col-12">Start Date : '+opt.startDate+'</span>';
							//   result = result+'<span class="col-xl-6 col-lg-6 col-12">End Date : '+opt.endDate+'</span>';
							//   result = result+'<span class="col-xl-6 col-lg-6 col-12">Qty : <button type="button" '+quantityButtons+'  onclick="quantityDec(\''+opt.masterServiceUUID+'\')">-</button><input type="number" readonly step="1" min="1" value="1" id="number'+opt.masterServiceUUID+'" style="max-width: 40px;"><button type="button"  '+quantityButtons+' onclick="quantityInc(\''+opt.masterServiceUUID+'\')">+</button></span>';
							//   result = result+'<span class="col-xl-6 col-lg-6 col-12">Allowed : '+opt.allowed+' per quantity</span>';
							  
							//   if(opt.serviceName == 'Surprise'){
									 
							// 	  result = result+'<div class="col-xl-6 col-lg-2 col-6 mt-2">';
							// 	  result = result+'<select class="form-control" id="surpriseFor">';
							// 	  result = result+'<option value="">Select Suprise For</option>';
							// 	  for (var j=0; j<opt.surpriseForList.length; j++){
					        // 	    	var surpriseFor = opt.surpriseForList[j];
					        // 	    	 result = result+'<option value="'+surpriseFor.surpriseUUID+'" class="text-black-dp">'+surpriseFor.surpriseName+'</option>';
					        // 	    }
							// 	  result = result+'</select>';
							// 	  result = result+'</div>';
								  
							// 	  result = result+'<div class="col-xl-6 col-lg-2 col-6 mt-2">';
							// 	  result = result+'<select class="form-control" id="surpriseOccation">';
							// 	  result = result+'<option value="">Select Suprise Occasion</option>';
							// 	  for (var j=0; j<opt.surpriseOccationList.length; j++){
					        // 	    	var surpriseFor = opt.surpriseOccationList[j];
					        // 	    	 result = result+'<option value="'+surpriseFor.surpriseUUID+'" class="text-black-dp">'+surpriseFor.surpriseName+'</option>';
					        // 	    }
							// 	  result = result+'</select>';
							// 	  result = result+'</div>';
								  
							// 	  result = result+'<div class="col-xl-12 col-lg-2 col-6 mt-2">';
							// 	  result = result+'<textarea class="textarea form-control" id="occationInstructions" placeholder="Occasion Instructions" cols="3" rows="3"></textarea>';
							// 	  result = result+'</div>';
                              
							//   }
							 
							  result = result+'<span class="col-xl-6 col-lg-6 col-12 text-danger mt-2" data-toggle="modal" data-target="#pop-up-modal" onclick="getTermsAndConditions(\''+opt.termsAndConditions+'\')">Terms & Conditions</span>';
							  result = result+'<span class="col-xl-6 col-lg-6 col-12 text-danger mt-2" data-toggle="modal" data-target="#pop-up-modal" onclick="getServiceOffer(\''+opt.serviceOffer+'\')">Service Offer</span>';
							  result = result+'</div>';
							  result = result+'<div class="service_wrap_user_img">';
							  result = result+'<img src="'+opt.serviceImage+'" onerror="predefineVendorServiceImage(this);" >';
							  result = result+'</div>';
							  
							//   if(isEntryRatioEnabled == 'Y'){
							// 	  result = result+'<div class="row">';
							// 	  result = result+'<span class="col-xl-6 col-lg-6 col-12">Men : <button type="button"  onclick="menQuantityDec(\''+opt.masterServiceUUID+'\')">-</button><input type="number" readonly step="1" min="0" value="0" id="menRatio'+opt.masterServiceUUID+'" style="max-width: 40px;"><button type="button" onclick="menQuantityInc(\''+opt.masterServiceUUID+'\')">+</button></span>';
							// 	  result = result+'<span class="col-xl-6 col-lg-6 col-12">Women : <button type="button"  onclick="womenQuantityDec(\''+opt.masterServiceUUID+'\')">-</button><input type="number" readonly step="1" min="0" value="0" id="womenRatio'+opt.masterServiceUUID+'" style="max-width: 40px;"><button type="button"  onclick="womenQuantityInc(\''+opt.masterServiceUUID+'\')">+</button></span>';
							// 	  result = result+'</div>';
							//   }
							  
							  result = result+'</div>';
							  
							  result = result+'<div class="service_wrap_content">';

								result = result+'<div class="row" style="padding: 10px 15px 15px 50px;">';

								// result = result+'<div class="form-group">';
								// result = result+'<label>From Date</label>';
								// result = result+'<input type="text" placeholder="dd/mm/yyyy" class="form-control start-date-datepicker" data-position="bottom right" name="startDate" id="startDate" data-validation="required" field-name="Start Date">';
								// result = result+'<i class="far fa-calendar-alt"></i>';
								// result = result+'</div>';







								result = result+'<div class="row">';
									result = result+'<div class="col-xl-4 col-lg-6 col-12 form-group">';
										result = result+'<label>Start Date<span class="text-danger">&nbsp;*</span></label>';
										result = result+'<input type="text" placeholder="dd/mm/yyyy" class="form-control start-date-datepicker" data-position="bottom right" name="startDate" id="startDate" data-validation="required" field-name="Start Date"> ';
										result = result+'<i class="far fa-calendar-alt"></i>';
										result = result+'</div>';

										result = result+'<div class="col-xl-4 col-lg-6 col-12 form-group">';
											result = result+'	<label>End Date<span class="text-danger">&nbsp;*</span></label>';
											result = result+'<input type="text" placeholder="dd/mm/yyyy" class="form-control end-date-datepicker" data-position="bottom right" name="endDate" id="endDate" data-validation="required" field-name="End Date">';
											result = result+'	<i class="far fa-calendar-alt"></i>';
											result = result+'</div>';
											result = result+'</div>';




								
								// result = result+'<div class="form-group" style="padding-left: 15px;">';
								// result = result+'<label>To Date</label>';
								// result = result+'<input type="text" placeholder="dd/mm/yyyy" class="form-control end-date-datepicker" data-position="bottom right" name="endDate" id="endDate" data-validation="required" field-name="End Date">';
								// result = result+'<i class="far fa-calendar-alt"></i>';
								// result = result+'</div>';
								
								 result = result+'</div>';



								result = result+'<div class="modal-footer">';
								result = result+'<input type="submit" id="selectedFirstSlot" value="First Half" onclick="selectedFirstSlot()" class="btn btn-success" style="background: transparent;color: #be9c52;font-weight: 600;text-transform: uppercase;font-size: 14px;padding: 5px 10px;border-radius: 10px;border: 2px solid #be9c52 !important;box-shadow: 0px 0px 5px rgb(0 0 0 / 0%) !important;">';
								result = result+'<input type="button"  id="selectedSecondSlot" value="Second Half" onclick="selectedSecondSlot()" class="btn btn-danger"style="background: transparent;color: #be9c52;font-weight: 600;text-transform: uppercase;font-size: 14px;padding: 5px 10px;border-radius: 10px;border: 2px solid #be9c52 !important;box-shadow: 0px 0px 5px rgb(0 0 0 / 0%) !important;">';
								result = result+' </div>';


							  result = result+'<div class="row">';
							//   result = result+'<div class="col-lg-8 col-sm-6 col-12">';
							//   result = result+'<div class="event-calendar-box mb-5">';
							//   result = result+'<div class="item-content table-responsive">';
							  
							//   var timeSlotString = '';
							//   for (var j=0; j<opt.timeSlotList.length; j++){
							//   		var timeSlots = opt.timeSlotList[j];
							//  		timeSlotString = timeSlotString + timeSlots.startTime+' to '+timeSlots.endTime+'/n';
							//   }
							//   result = result+'<div id="serviceCalendar'+opt.masterServiceUUID+'" onclick="test(\''+opt.masterServiceUUID+'\', \''+timeSlotString+'\')"></div>';

							 
							//   result = result+'</div>';
							//   result = result+'</div>';
							//   result = result+'</div>';

							  

							 
							  result = result+'<div class="col-lg-4 col-sm-6 col-12">';
							  result = result+'<div class="heading-layout1 mg-b-17">';
							  result = result+'<div class="item-title">';
							  result = result+'<div id="eventTitleDiv"></div>';

							//   result = result+'<h5>Time Slots</h5>';

							  result = result+'<div class="timeslot-box-wrap">';
							  
							//   var count = 0;
							  
							//   for (var j=0; j<opt.timeSlotList.length; j++){
				        	//     	var slots = opt.timeSlotList[j];
							// 		var today = new Date(); 
				        	//     	result = result +"<ul class='table-light mt-2'>";

							// 			let timeSlotDisplayValue = "First Half";   
										
				        	//    			if(getDefaultTimeSlotValidation(opt.masterServiceUUID, slots.startTime+' to '+slots.endTime)){
				        	//    				count++;
							// 				 if(slots.startTime == '12:00 AM'){
							// 					timeSlotDisplayValue = "First Half";   
							// 				 }else{
							// 					timeSlotDisplayValue = "Second Half";   
							// 				 }  
							// 				result = result +'<li class="timeslot_'+opt.masterServiceUUID+'_'+j+'" style="font-size: 15px; font-weight: 600; display:block;"><input type="radio"  value="'+slots.startTime+' to '+slots.endTime+'" onclick="getTimeSlots(\''+opt.masterServiceUUID+'\');" name="timeslot'+opt.masterServiceUUID+'">&nbsp;'+timeSlotDisplayValue+'</li>';
								  		     
							// 	    	}else{
							// 				result = result +'<li class="timeslot_'+opt.masterServiceUUID+'_'+j+'" style="font-size: 15px; font-weight: 600; display:none;"><input type="radio"  value="'+slots.startTime+' to '+slots.endTime+'" onclick="getTimeSlots(\''+opt.masterServiceUUID+'\');" name="timeslot'+opt.masterServiceUUID+'">&nbsp;'+timeSlotDisplayValue+'</li>';
								    		 
							// 	    	}
								    	
				        	//     	result = result +"</ul>";
				        	    	
				        	//     }
				        	    
				        	    // if(count == 0){
				        	    	
				        	    // 	result = result+'<p id="errorMessage'+opt.masterServiceUUID+'" style="display:block"><i class="fa fa-exclamation-triangle"></i>&nbsp;&nbsp;No time slots for the day.</p>';
				        	    
				        	    // }
							    
							  result = result+'<input type="hidden" id="bookedTimeSlot'+opt.masterServiceUUID+'" />';
							  result = result+'</div>';
							  result = result+'</div>';
							  result = result+'</div>';
							  result = result+'<p id="errorMessage'+opt.masterServiceUUID+'" style="display:none"><i class="fa fa-exclamation-triangle"></i>&nbsp;&nbsp;Please select order date and time slot.</p>';
							  result = result+'<p id="surpriseErrorMessage'+opt.masterServiceUUID+'" style="display:none"><i class="fa fa-exclamation-triangle"></i>&nbsp;&nbsp;Please select order date, time slot and surprise details.</p>';
							  result = result+'<p id="entryErrorMessage'+opt.masterServiceUUID+'" style="display:none"><i class="fa fa-exclamation-triangle"></i>&nbsp;&nbsp;Invalid men and women entry ratio.</p>';
							  result = result+'<p id="personsErrorMessage'+opt.masterServiceUUID+'" style="display:none"><i class="fa fa-exclamation-triangle"></i>&nbsp;&nbsp;Invalid entry count</p>';
							  result = result+'<p id="stagsErrorMessage'+opt.masterServiceUUID+'" style="display:none"><i class="fa fa-exclamation-triangle"></i>&nbsp;&nbsp;Invalid entry count</p>';
							  result = result+'</div>';
							  result = result+'</div>';
							  
							  
							  result = result+'<div class="clear"></div>';
							  result = result+'</div>';
							 
							  result = result+'<div class="service_wrap_bot">';
 							  if(opt.serviceName == 'Packages'){
 								 result = result+'<a data-button="customize" data-id="4" href="javascript:void(0)" id="customizeService'+opt.masterServiceUUID+'" data-toggle="modal" style="display:none" data-target="#right-slide-modal" onclick="getServiceDetails(\''+opt.masterServiceUUID+'\',\''+categoryUUID+'\',\''+isEntryRatioEnabled+'\')">Customize</a>';
 								result = result+'<a data-button="customize" data-id="4" href="javascript:void(0)" onclick="getPackagesServiceInfo(\''+opt.masterServiceUUID+'\',\''+isEntryRatioEnabled+'\',\''+opt.allowed+'\')">Customize</a>';
 							  }else{
								  result = result+'<a data-button="buyService" data-id="4" href="javascript:void(0)" id="buyService'+opt.masterServiceUUID+'" data-toggle="modal" data-target="#right-slide-modal" style="display:none"  onclick="getServiceDetails(\''+opt.masterServiceUUID+'\',\''+categoryUUID+'\',\''+isEntryRatioEnabled+'\')">Buy Now</a>';
								  result = result+'<a data-button="buyService" data-id="4" href="javascript:void(0)" onclick="getServiceInfo(\''+opt.masterServiceUUID+'\',\''+opt.serviceName+'\',\''+isEntryRatioEnabled+'\',\''+opt.allowed+'\')">Buy Now</a>';
							  }
							 
 							  
							  result = result+'<div class="clear"></div>';
							  result = result+'</div>';
							  result = result+'</div>';
							  
							  $("#categoryServicesListDiv").empty(); 
			   	    	      $("#categoryServicesListDiv").append(result);
							  
			   	    	   serviceStartDates.push(opt.serviceStartDate);
			   	    	   serviceEndDates.push(opt.serviceEndDate);
			   	    	   serviceUUIDs.push(opt.masterServiceUUID);
							  
			   				} 
						 
					 }else{
						 
						 var serviceType = '';
						   $('.nav-item a').each(function(){
							   if($(this).hasClass('active'))
								   serviceType = $(this).attr('data-value');
							});
						   
						 if(serviceType == 'Book a bottle'){
							 result = result+'<div class="noRecords-dashboard-portlets"><img src="/resources/img/services/no_bottles_found.jpg" alt=""/></td>';
						 }else if(serviceType == 'Venue'){
							 result = result+'<div class="noRecords-dashboard-portlets"><img src="/resources/img/services/no_table_found.jpg" alt=""/></td>';
						 }else if(serviceType == 'Entry'){
							 result = result+'<div class="noRecords-dashboard-portlets"><img src="/resources/img/services/no_entry_found.jpg" alt=""/></td>';
						 }else if(serviceType == 'Surprise'){
							 result = result+'<div class="noRecords-dashboard-portlets"><img src="/resources/img/services/no_surprise_found.jpg" alt=""/></td>';
						 }else if(serviceType == 'Deals and Offers'){
							 result = result+'<div class="noRecords-dashboard-portlets"><img src="/resources/img/services/no_deals_found.jpg" alt=""/></td>';
						 }else if(serviceType == 'Packages'){
							 result = result+'<div class="noRecords-dashboard-portlets"><img src="/resources/img/services/no_packages_found.jpg" alt=""/></td>';
						 }
						
						 $("#categoryServicesListDiv").empty(); 
		   	    	     $("#categoryServicesListDiv").append(result);
					 }
			    	 
			    	//  if(resultData.data.length > 0){
			    	// 	 for (var i=0; i<resultData.data.length; i++){
			    	// 		 getServiceDates(serviceStartDates[i],serviceEndDates[i],serviceUUIDs[i]);
				    // 	 }
			    	//  }
			    	 
	   	    	    //   function getServiceDates(serviceStartDate, serviceEndDate,serviceUUID){
	   	    	    // 	var startDateParts = serviceStartDate.split('-');
	   	    	    // 	var minimumdate = new Date(startDateParts[0], startDateParts[1] - 1, startDateParts[2]);
	   	    	    // 	var endDateParts = serviceEndDate.split('-');
	   	    	    // 	var maximumdate = new Date(endDateParts[0], endDateParts[1] - 1, endDateParts[2]);
	   	    	    //     var dateToday = new Date();
	   	    	    //     if(minimumdate < dateToday){
	   	    	    //     	minimumdate = dateToday;
	   	    	    //     }
		   	    	//     $('#serviceCalendar1'+serviceUUID).datepicker({
		   	    	//       language: {
		   	    	//         days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
		   	    	//         daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
		   	    	//         daysMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
		   	    	//         months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		   	    	//         monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
		   	    	//         today: 'Today',
		   	    	//         clear: 'Clear',
		   	    	//         dateFormat: 'dd/mm/yyyy',
		   	    	//         firstDay: 0,
		   	    	//         height: '50px',
		   	    	//         width: '50px'
		   	    	//       },
		   	    	//       minDate: minimumdate,
		   	    	//       maxDate: maximumdate
		   	    	//     });
	   	    	    //   }
	   	    	      
  var dateToday = new Date();
    $('.start-date-datepicker').datepicker({
      language: {
        days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
        daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
        daysMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
        months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
        today: 'Today',
        clear: 'Clear',
        dateFormat: 'dd/mm/yyyy',
        firstDay: 0,
      },
      minDate: dateToday,
      onSelect : function(selected) {
      	getEndDateDatePicker();
  	 }
    });
    
    function getEndDateDatePicker(){
    	 var endDatepicker = $('#endDate').datepicker().data('datepicker');
    	 endDatepicker.clear();
    	 var startDate = $('#startDate').val();
    	 var dateParts = startDate.split('/');
    	 var minimumdate = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
        $('.end-date-datepicker').datepicker({
            language: {
              days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
              daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
              daysMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
              months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
              monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
              today: 'Today',
              clear: 'Clear',
              dateFormat: 'dd/mm/yyyy',
              firstDay: 0,
            },
            minDate: minimumdate,
            onSelect : function(selected) {
          	 }
            
        });
    }
    
    function getSavedEndDateDatePicker(){
     	 var startDate = $('#startDate').val();
     	 var dateParts = startDate.split('/');
     	 var minimumdate = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
         $('.end-date-datepicker').datepicker({
             language: {
               days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
               daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
               daysMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
               months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
               monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
               today: 'Today',
               clear: 'Clear',
               dateFormat: 'dd/mm/yyyy',
               firstDay: 0,
             },
             minDate: minimumdate,
             onSelect : function(selected) {
           	 }
             
         });
     }
     
    
					 $("#serviceslistLoadingDiv").removeAttr("style");
					},
		});
	 
	 
	 
}
</script>


<!-- <script>
function menQuantityInc(masterServiceUUID){
	var quantity = document.getElementById("menRatio"+masterServiceUUID);
	quantity.stepUp(1); 
}

function menQuantityDec(masterServiceUUID){
	var quantity = document.getElementById("menRatio"+masterServiceUUID);
	quantity.stepDown(1); 
}

function womenQuantityInc(masterServiceUUID){
	var quantity = document.getElementById("womenRatio"+masterServiceUUID);
	quantity.stepUp(1); 
}

function womenQuantityDec(masterServiceUUID){
	var quantity = document.getElementById("womenRatio"+masterServiceUUID);
	quantity.stepDown(1); 
}
</script> -->

<!-- <script>
function getTimeSlotValidation(serviceUUID){
	   var flag = true
	    var orderDate = getSelectedDateFromCalendar($("#serviceCalendar"+serviceUUID).val());
		var timeslot = $("input[name='timeslot"+serviceUUID+"']:checked").val();
		 var today = new Date(); 
	     var dd = today.getDate(); 
	     var mm = today.getMonth() + 1; 

	     var yyyy = today.getFullYear(); 
	     if (dd < 10) { 
	         dd = '0' + dd; 
	     } 
	     if (mm < 10) { 
	         mm = '0' + mm; 
	     } 
	     var currentDate = dd + '/' + mm + '/' + yyyy; 
	     var currentDateParts = currentDate.split('/');
	 	var currDate = new Date(currentDateParts[2], currentDateParts[1] - 1, currentDateParts[0]);
	 	var orderDateParts = orderDate.split('/');
	 	var selectedDate = new Date(orderDateParts[2], orderDateParts[1] - 1, orderDateParts[0]);
			var slotEndTime = 	timeslot.split('to');
			var currentTime = moment(today.toLocaleTimeString(), 'hh:mm A').format('HH:mm')
			var slotTime = moment(slotEndTime[1].trim(), 'hh:mm A').format('HH:mm')
				
			currentTime =  currentTime.split(':');
			slotTime =  slotTime.split(':');

			currentTimeSeconds = parseInt(currentTime[0] * 3600 + currentTime[1] * 60 + currentTime[0]);
			slotTimeSeconds = parseInt(slotTime[0] * 3600 + slotTime[1] * 60 + slotTime[0]);
			if(currDate.valueOf() == selectedDate.valueOf()){
				if(slotTimeSeconds < currentTimeSeconds){
					flag = false;
				}
			}
			
			return flag;
}
</script> -->
<script>

function getTimeSlotValidation(serviceUUID){
		
		return true;

}
</script>

<script>
   function getServiceInfo(serviceUUID,service,isEntryRatioEnabled,allowed){
	   if(getTimeSlotValidation(serviceUUID)){
		   var orderDate = getSelectedDateFromCalendar($("#serviceCalendar"+serviceUUID).val());
		   var timeslot = $("input[name='timeslot"+serviceUUID+"']:checked").val();
		   $("#number"+serviceUUID).val(getDaysDiff($("#startDate").val(), $("#endDate").val()))
		   var quantity = $("#number"+serviceUUID).val();
		   var totalAllowed = allowed*quantity;
		   if(service == 'Surprise'){
				var surpriseFor = $("#surpriseFor").val();
		  		var surpriseOccation = $("#surpriseOccation").val();
		  		var surpriseInstructions = $("#occationInstructions").val();
			   if(orderDate != '' && orderDate != 'undefined' && timeslot !=  '' && typeof(timeslot) != 'undefined'
					   && surpriseFor != '' && surpriseFor != 'undefined' && surpriseOccation != '' && surpriseOccation != 'undefined'
						   && surpriseInstructions != '' && surpriseInstructions != 'undefined'){
				   $("#surpriseErrorMessage"+serviceUUID).css({ display: "none" });
				   if(isEntryRatioEnabled == 'Y'){
					   var menRatio = $("#menRatio"+serviceUUID).val();
					   var womenRatio = $("#womenRatio"+serviceUUID).val();
					   
					   if(Number(womenRatio) >= 1){
						   $("#stagsErrorMessage"+serviceUUID).css({ display: "none" });
						   var personEntryCount = Number(menRatio) + Number(womenRatio)
						   if(personEntryCount == 0 || (Number(personEntryCount) > Number(totalAllowed))){
							   $("#personsErrorMessage"+serviceUUID).css({ display: "block" });
						   }else{
							   $("#personsErrorMessage"+serviceUUID).css({ display: "none" });
							   var enabledRatio = Number(menRatio) - Number(womenRatio);
							   if((parseInt(womenRatio) >= parseInt(enabledRatio)) || Number(menRatio) == 1){
								   $("#entryErrorMessage"+serviceUUID).css({ display: "none" });
								   $("#buyService"+serviceUUID).click();
							   }else{
								   $("#entryErrorMessage"+serviceUUID).css({ display: "block" });
							   }
						   }
					   }else{
						   $("#stagsErrorMessage"+serviceUUID).css({ display: "block" });
					   }
					   
				
					   
				   }else{
					   $("#buyService"+serviceUUID).click();
				   }
				   
			   }else{
				   $("#surpriseErrorMessage"+serviceUUID).css({ display: "block" });
			   }
		   }else{
			   var fromDate = $("#startDate").val();
			   var toDate = $("#endDate").val();
			  
			   var orderDate = $("#startDate").val();
			   $("#serviceCalendar"+serviceUUID).val(fromDate);

			   if(fromDate != '' && fromDate != 'undefined' && toDate != '' && toDate != 'undefined'){
				   $("#errorMessage"+serviceUUID).css({ display: "none" });
				   if(isEntryRatioEnabled == 'Y'){
					   var menRatio = $("#menRatio"+serviceUUID).val();
					   var womenRatio = $("#womenRatio"+serviceUUID).val();
			   		   
					   if(Number(womenRatio) >= 1){
						   $("#stagsErrorMessage"+serviceUUID).css({ display: "none" });
						   var personEntryCount = Number(menRatio) + Number(womenRatio)
						   if(personEntryCount == 0 || (Number(personEntryCount) > Number(totalAllowed))){
							   $("#personsErrorMessage"+serviceUUID).css({ display: "block" });
						   }else{
							   $("#personsErrorMessage"+serviceUUID).css({ display: "none" });
							   var enabledRatio = Number(menRatio) - Number(womenRatio);
							   if((parseInt(womenRatio) >= parseInt(enabledRatio)) || Number(menRatio) == 1){
								   $("#entryErrorMessage"+serviceUUID).css({ display: "none" });
								   $("#buyService"+serviceUUID).click();
							   }else{
								   $("#entryErrorMessage"+serviceUUID).css({ display: "block" });
							   }
						   }
					   }else{
						   $("#stagsErrorMessage"+serviceUUID).css({ display: "block" });
					   }
					   
				   }else{
					   $("#buyService"+serviceUUID).click();
				   }
			   }else{
				   $("#errorMessage"+serviceUUID).css({ display: "block" });
			   }
		   }
	   }else{
		   alert("Invalid time slot. Choose another time slot");
	   }
	  
   }

   function getPackagesServiceInfo(serviceUUID,isEntryRatioEnabled,allowed){
	   if(getTimeSlotValidation(serviceUUID)){
		   var orderDate = getSelectedDateFromCalendar($("#serviceCalendar"+serviceUUID).val());
		   var timeslot = $("input[name='timeslot"+serviceUUID+"']:checked").val();
		   $("#number"+serviceUUID).val(getDaysDiff($("#startDate").val(), $("#endDate").val()))
		   var quantity = $("#number"+serviceUUID).val();
		   var totalAllowed = allowed*quantity;
		   
		   if(orderDate != '' && orderDate != 'undefined' && timeslot !=  '' && typeof(timeslot) != 'undefined'){
			   $("#errorMessage"+serviceUUID).css({ display: "none" });
			   if(isEntryRatioEnabled == 'Y'){
				   var menRatio = $("#menRatio"+serviceUUID).val();
				   var womenRatio = $("#womenRatio"+serviceUUID).val();
		   		   
				   if(Number(womenRatio) >= 1){
					   $("#stagsErrorMessage"+serviceUUID).css({ display: "none" });
					   var personEntryCount = Number(menRatio) + Number(womenRatio);
					   if(personEntryCount == 0 || (Number(personEntryCount) > Number(totalAllowed))){
						   $("#personsErrorMessage"+serviceUUID).css({ display: "block" });
					   }else{
						   $("#personsErrorMessage"+serviceUUID).css({ display: "none" });
						   var enabledRatio = Number(menRatio) - Number(womenRatio);
						   if((parseInt(womenRatio) >= parseInt(enabledRatio)) || Number(menRatio) == 1){
							   $("#entryErrorMessage"+serviceUUID).css({ display: "none" });
							   $("#customizeService"+serviceUUID).click();
						   }else{
							   $("#entryErrorMessage"+serviceUUID).css({ display: "block" });
						   }
					   }
				   }else{
					   $("#stagsErrorMessage"+serviceUUID).css({ display: "block" });
				   }
			
			   }else{
				   $("#customizeService"+serviceUUID).click();
			   }
		   }else{
			   $("#errorMessage"+serviceUUID).css({ display: "block" });
		   }
	   }else{
		   alert("Invalid time slot. Choose another time slot");
	   }
	   
	   
   }
   </script>


<script>
function getTermsAndConditions(termsAndConditions){
	$("#termsAndConditions").empty();
	$("#termsAndConditions").html(termsAndConditions);
	$("#dataTitle").html("Terms & Conditions");
	
}

function getServiceOffer(serviceOffer){
	$("#termsAndConditions").empty();
	$("#termsAndConditions").html(serviceOffer);
	$("#dataTitle").html("Service Offer");
}

function quantityInc(maserServiceUUID){
	var quantity = document.getElementById("number"+maserServiceUUID);
	quantity.stepUp(1); 
}

function quantityDec(maserServiceUUID){
	var quantity = document.getElementById("number"+maserServiceUUID);
	quantity.stepDown(1); 
}
</script>

<script>
// function getDefaultTimeSlotValidation(serviceUUID, timeSlot){
// 	   	var flag = true
// 	   	var orderDate = $("#serviceCalendar"+serviceUUID).val();
	   	
// 		var timeslot = timeSlot;
// 		var today = new Date(); 
	    
// 	     var dd = today.getDate(); 
// 	     var mm = today.getMonth() + 1; 

// 	     var yyyy = today.getFullYear(); 
// 	     if (dd < 10) { 
// 	         dd = '0' + dd; 
// 	     } 
// 	     if (mm < 10) { 
// 	         mm = '0' + mm; 
// 	     } 
// 	     var currentDate = dd + '/' + mm + '/' + yyyy; 
	     
// 	    if(orderDate == undefined){
// 	   		orderDate = currentDate;
// 	   	 }
	   	 
// 	     var currentDateParts = currentDate.split('/');
// 	 	var currDate = new Date(currentDateParts[2], currentDateParts[1] - 1, currentDateParts[0]);
// 	 	var orderDateParts = orderDate.split('/');
// 	 	var selectedDate = new Date(orderDateParts[2], orderDateParts[1] - 1, orderDateParts[0]);
// 			var slotEndTime = 	timeslot.split('to');
// 			var currentTime = moment(today.toLocaleTimeString(), 'hh:mm A').format('HH:mm')
// 			var slotTime = moment(slotEndTime[1].trim(), 'hh:mm A').format('HH:mm')
				
// 			currentTime =  currentTime.split(':');
// 			slotTime =  slotTime.split(':');

// 			currentTimeSeconds = parseInt(currentTime[0] * 3600 + currentTime[1] * 60 + currentTime[0]);
// 			slotTimeSeconds = parseInt(slotTime[0] * 3600 + slotTime[1] * 60 + slotTime[0]);
// 			if(currDate.valueOf() == selectedDate.valueOf()){
// 				if(slotTimeSeconds < currentTimeSeconds){
// 					flag = false;
// 				}
// 			}
			
// 			return flag;
// }

// // function test(serviceUUID,serviceTimeSlots){
// // 	    var orderDate = $("#serviceCalendar"+serviceUUID).val();
	   	
// // 	   	var timeSlots = serviceTimeSlots.split('/n');
	   	
// // 	     var today = new Date(); 
	    
// // 	     var dd = today.getDate(); 
// // 	     var mm = today.getMonth() + 1; 

// // 	     var yyyy = today.getFullYear(); 
// // 	     if (dd < 10) { 
// // 	         dd = '0' + dd; 
// // 	     } 
// // 	     if (mm < 10) { 
// // 	         mm = '0' + mm; 
// // 	     } 
// // 	     var currentDate = dd + '/' + mm + '/' + yyyy; 
	     
// // 	     if(orderDate == undefined){
// // 	   		orderDate = currentDate;
// // 	   	 }
	   	 
// // 	     var count = 0;
	     
// // 	     if(orderDate.valueOf() == currentDate.valueOf()){
	     
// // 	     	for (var z=0; z<timeSlots.length; z++){
// // 		    	var slots = timeSlots[z];
				
// // 				if(slots != ''){
// // 			    	if(getDefaultTimeSlotValidation(serviceUUID, slots)){
// // 			  		     $(".timeslot_"+serviceUUID+"_"+z).css({ display: "block" });
// // 						 count++;
// // 			    	}else{
// // 			    		 $(".timeslot_"+serviceUUID+"_"+z).css({ display: "none" });
// // 			    	}
// // 		    	}
		    	
// // 		    }

// // 	     } else{

// // 			for (var z=0; z<timeSlots.length; z++){
// // 		    	var slots = timeSlots[z];
// // 	  		    $(".timeslot_"+serviceUUID+"_"+z).css({ display: "block" });
// // 	  		    count++;
// // 		    }
		   
// // 	    }
	    
// // 	    if(count == 0){
// // 	     	$("#errorMessage"+serviceUUID).css({ display: "block" });
// // 	    }else{
// // 	     	$("#errorMessage"+serviceUUID).css({ display: "none" });
// // 	    }
	    
// // }   	
  
</script>

<script>

	function selectedFirstSlot(){

		if($("#selectedFirstSlot").hasClass('selectedSlot')){
			$("#selectedFirstSlot").removeClass('selectedSlot');
		}else{
			$("#selectedFirstSlot").addClass( 'selectedSlot' );
		}

	}

	function selectedSecondSlot(){

		if($("#selectedSecondSlot").hasClass('selectedSlot')){
			$("#selectedSecondSlot").removeClass('selectedSlot');
		}else{
			$("#selectedSecondSlot").addClass( 'selectedSlot' );
		}

	}

	function getDaysDiff(fromDate, toDate){
		   
		 var timeslotCount = 1;
    	 var fromDateParts = fromDate.split('/');
    	 var toDateParts = toDate.split('/');
		 var daysDiff = toDateParts[0] - fromDateParts[0] + 1;

		 if($("#selectedFirstSlot").hasClass('selectedSlot') && $("#selectedSecondSlot").hasClass('selectedSlot')){
			timeslotCount = 2;

		}else if($("#selectedFirstSlot").hasClass('selectedSlot')) {
			timeslotCount = 1;
		}else if($("#selectedSecondSlot").hasClass('selectedSlot')) {
			timeslotCount = 1;
		}
		
		var slotsCount = daysDiff * timeslotCount;

		return slotsCount;

	}
</script>