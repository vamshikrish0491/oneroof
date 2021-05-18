<div id="wrapper" class="wrapper bg-ash">
<div class="dashboard-page-one">
<div id="eventInfoLoadingDiv"></div>
 <div class="container">
	<header class="eventBanner">
		<span id="bannerImage" alt="eventbanner" class="img-responsive"></span>
	</header>
  <div class="row">
	<div class="col-xs-6 col-sm-12 col-md-10 col-lg-10">
		<div class="clearfix webevent">
			<h2 id="eventNameDiv"></h2>
			<p id="eventTypeDiv"></p>
	   </div>
		<div class="clearfix text-muted webevent-dates row">
		
		<div class="col-xs-12 col-sm-8 col-md-8 col-lg-6">
		<p class="text-black">
			<i class="fas fa-headphones"></i>
			&nbsp;<span id="eventHostDiv"></span>
		</p>
		<p class="text-black">
			<i class="far fa-calendar"></i>
			&nbsp;<span id="eventDateDiv"></span>
		</p>
		<p class="text-black">
			<i class="fas fa-map-marked-alt"></i>
			&nbsp;<span id="eventAddressDiv"></span>
		</p>
		
		<p class="text-black">
		<i class="far fa-money-bill-alt"></i>
			&nbsp;<span id="eventMinCostDiv"></span>
		</p>	
		</div>
		
		<div class="col-xs-12 col-sm-8 col-md-8 col-lg-6">
		<p class="text-black">
			<i class="fas fa-music"></i>
			&nbsp;<span id="musicTypeDiv"></span>
		</p>
		<p class="text-black">
			<i class="fa fa-hourglass-half"></i>
			<span id="durationDiv"></span>
		</p>
		<p class="text-black">
			<i class="fas fa-language"></i>
			&nbsp;<span id="languageDiv"></span>
		</p>
		
		<p class="text-black">
		<i class="fas fa-user"></i>
			&nbsp;<span id="ageDiv"></span>
		</p>	
		</div>
		
		</div>
		
		<div class="clearfix text-muted webevent-moreinfo">
			<div id="timeSlotsListDiv"></div>
			
				  <div class="formInline mt-5">
					  <div class="form-group">
	                            
					    <label class="radiobtnstyles">
					      <input type="radio" name="categoryTypeFilter" checked="checked" value="Stag" onclick="getTicketDetails('stag')">&nbsp;Stag
					      <span class="checkmark"></span>
					    </label>
					    </div>
					    
					    <div class="radiobtnstyles">
					    <label class="checkbox-inline">
					      <input type="radio" name="categoryTypeFilter" value="Couple" onclick="getTicketDetails('couple')">&nbsp;Couple
					      <span class="checkmark"></span>
					    </label>
					    </div>
					    
					    <div class="radiobtnstyles">
					    <label class="checkbox-inline">
					      <input type="radio" name="categoryTypeFilter" value="Single Lady" onclick="getTicketDetails('singleLady')">&nbsp;Single Lady
					       <span class="checkmark"></span>
					    </label>
					    </div>
		    	</div>
		    	
		    	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mt-5">
		    	    <div id="ticketsLoadingDiv"></div>
		    		<div id="eventTicketsDiv"></div>
		    	</div>
									    
		</div>
    </div>
	</div>
</div>
                           
       </div>
 </div>
 
 <div class="alert icon-alart bg-light-green2" id="successMsgDiv" role="alert" style="display: none;">
                                   <i class="far fa-hand-point-right bg-light-green3"></i>
                                  <span id="successMsg" style="color: white;"></span> 
            </div>
            
            <div class="alert icon-alart bg-pink2" role="alert" id="invalidMsgDiv" style="display: none;">
                                   <i class="fas fa-times bg-pink3"></i>
                                  <span id="invalidMsg" style="color: white;"></span> 
            </div>
 
 <button id="rzp-button1" style="display: none">Pay</button>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
 
  
 <script type="text/javascript">
		window.onload = function () {
			getEventsDetails();
			getEventsTimSlots();
			getTicketDetails('stag');
		 };
 </script>	
 
 <script>
       function getEventsDetails(){
    	   var appUrl = '${appUrl}';
    	   var eventUUID = '${eventUUID}';
    	   $("#eventInfoLoadingDiv").attr('style','position:absolute; width:100%; height:300%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
    	   $.ajax({
    			  type: "GET",
    			    url: appUrl+"/getEventDetails?${_csrf.parameterName}=${_csrf.token}&eventUUID="+eventUUID,
    			     success :function(response) {
    			    	    
    			    	 $("#bannerImage").html('<img src="'+response.object.eventImage+'">');
    			    	 $("#eventNameDiv").html(response.object.eventName);
    			    	 $("#eventTypeDiv").html(response.object.eventType);
    			    	 $("#eventHostDiv").html(response.object.eventHost);
    			    	 $("#eventDateDiv").html(response.object.eventDate);
    			    	 $("#eventAddressDiv").html(response.object.address);
    			    	 $("#eventMinCostDiv").html(response.object.minimumStartingAmount);
    			    	 $("#musicTypeDiv").html(response.object.musicType);
    			    	 $("#durationDiv").html(response.object.duration);
    			    	 $("#languageDiv").html(response.object.language);
    			    	 $("#ageDiv").html(response.object.age);
    			    	 
    			    	 $("#eventInfoLoadingDiv").removeAttr("style");
    			    	 
    					},
    		});
       }
 </script>
 
 
  <script>
       function getEventsTimSlots(){
    	   var appUrl = '${appUrl}';
    	   var eventUUID = '${eventUUID}';
    	   $("#eventInfoLoadingDiv").attr('style','position:absolute; width:100%; height:300%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
    	   $.ajax({
    			  type: "GET",
    			    url: appUrl+"/ws/getEventTimeSlots?${_csrf.parameterName}=${_csrf.token}&eventUUID="+eventUUID,
    			     success :function(response) {
    			    	    
    			    	 	 var result = "";
    			    	 	 result = result +'<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">';
		    			      for (var i=0; i<response.object.timeSlots.length; i++)
		  		   			  {  
		    			    		  var opt = response.object.timeSlots[i];
		    			    		  result = result +'<div class="btn-group" role="group" style="padding: 2px;">';
					        		  result = result +'<a class="btn btn-outline-primary btn-sm" id="timeSlot'+i+'"  onclick="getBookingTimeSlot(\''+opt.timeSlot+'\',\''+i+'\')" >'+opt.timeSlot+'</a>';
					        		  result = result +'</div>';
					        		 
		  		   			   }
		    			      result = result +'<div>';
		    			      result = result +'<input type="hidden" id="bookedTimeSlot" />';
		    			      result = result+'<p id="timeSlotErrorMessage" style="display:none" class="text-danger"><i class="fa fa-exclamation-triangle"></i>&nbsp;&nbsp;Choose time slot</p>';
				        	
				        	  $("#timeSlotsListDiv").empty();  
	    		   	    	  $("#timeSlotsListDiv").append(result);
	    		   	    	  $("#eventInfoLoadingDiv").removeAttr("style");
    					},
    		});
       }
       
     function getBookingTimeSlot(timeSlot, selectedTime){
    	 $( '.btn-toolbar .btn-group' ).find( 'a' ).removeClass('selected');
    	 $("#timeSlot"+selectedTime).addClass( 'btn btn-outline-primary btn-sm selected' );
    	 $("#bookedTimeSlot").val(timeSlot);
     }  
 </script>
 
 <script>
function getTicketDetails(categoryType){
	   var appUrl = '${appUrl}';
	   var eventUUID = '${eventUUID}';
	   $("#eventInfoLoadingDiv").attr('style','position:absolute; width:150%; height:300%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/ws/getEventTickets?${_csrf.parameterName}=${_csrf.token}&eventUUID="+eventUUID+"&categoryType="+categoryType,
			     success :function(response) {
			    	    
			    	 	var result = "";
			        	if(response.data.length > 0){
			        		
			        		var ticketCategory = '';
			        		var ticketNote = '';
			        		if(categoryType == 'stag'){
			        			ticketNote = 'Allowed one male per ticket';
			        		}else if(categoryType == 'couple'){
			        			ticketNote = 'Allowed one male and one female per ticket'
			        		}else{
			        			ticketNote = 'Allowed one female per ticket'
			        		}
			        		
			        		result = result +'<div class="panel panel-default clearfix">';
			        		result = result +'<div class="panel-body">';
			        		for (var i=0; i<response.data.length; i++)
			   				{ 
			        			var opt = response.data[i];
			        			var currencyCode = getCurrency(opt.currency);
			        			result = result +'<div class="cardEvent-design">';
			        			result = result +'<h4 class="bg-event-primary">'+opt.ticketType+'</h4>';
			        			result = result +'<div class="clearfix"></div>';
			        			result = result +'<div class="cardEvent-design-inner">';
			        			result = result +'<div class="cardEvent-design-inner-values">';
			        			result = result +'<div>';
			        			result = result +'<p>Cost : <span>'+currencyCode+'  '+opt.ticketAmount+'</span></p>';
			        			result = result +'</div>';
			        			result = result +'<div>';
			        			result = result +'<p>Max booking : <span>'+opt.maxBookingAllowed+'</span></p>';
			        			result = result +'</div>';
			        			result = result +'<div>';
			        			result = result +'<div class="btnAdd">';
			        			result = result +'<button type="button" class="btn btn-primary" onclick="minusValue(\''+i+'\')"><i class="fa fa-minus"></i></button>';
			        			result = result +'<input type="number" id="ticket'+i+'" placeholder="0" readonly  step="1" min="0" max="'+opt.maxBookingAllowed+'" class="form-control text-center" style="font-size: 13px; color:black;">';
			        			result = result +'<button type="button" class="btn btn-primary"><i class="fa fa-plus" onclick="addValue(\''+i+'\')"></i></button>';
			        			result = result +'</div>';
			        			result = result+'<p id="eventEntryErrorMessage'+i+'" style="display:none" class="text-danger">&nbsp;&nbsp;Invalid entry count</p>';
			        			result = result +'</div>';
			        			result = result +'<div>';
			        			result = result +'<button type="button" class="btn btn-primary eventTicketBookBtn" onclick="confirmTicket(\''+opt.ticketType+'\',\''+opt.ticketAmount+'\',\''+opt.currency+'\',\''+i+'\');">Confirm Ticket</button>';
			        			result = result +'</div>';
			        			result = result +'</div>';
			        			result = result +'<div class="cleafix"></div>';
			        			result = result +'<p class="text-muted">Note: '+ticketNote+'</p>';
			        			result = result +'</div>';
			        			result = result +'</div>';
			        			
			   				}
			        		
			        		result = result +'</div>';
			        		result = result +'</div>';
			        	}
			          $("#eventTicketsDiv").empty();  
  		   	    	  $("#eventTicketsDiv").append(result);
  		   	    	  $("#eventInfoLoadingDiv").removeAttr("style");
  		   	    	  
					},
		});
 }
 
 function addValue(incrementValue){
	 var quantity = document.getElementById("ticket"+incrementValue);
	 quantity.stepUp(1); 
	 
 }
 
 function minusValue(incrementValue){
	 var quantity = document.getElementById("ticket"+incrementValue);
	 quantity.stepDown(1); 
 }
 
 </script>
 
 
 
 <script>
 function confirmTicket(ticketType, ticketAmount, currency, incrementValue){
	 
	 var timeslot = $("#bookedTimeSlot").val();
	 var quantity = $("#ticket"+incrementValue).val();
	 
	 if(quantity > 0 && timeslot != ''){
		 $("#eventEntryErrorMessage"+incrementValue).css({ display: "none" });
		 $("#timeSlotErrorMessage").css({ display: "none" });
		 
		    var appUrl ='${appUrl}';
			var cartAmount = ticketAmount * quantity;
			var formData = new FormData();
			formData.append("currency", currency);
			formData.append("cartAmount", cartAmount);
			
			 $("#eventInfoLoadingDiv").attr('style','position:absolute; width:100%; height:300%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
			   $.ajax({
				    data: formData,
			   	    contentType: false,
			   	    processData: false,
						type: "POST",
					    url: appUrl+"/orders",
					     success :function(result) {
					    	 
					    	   if (result.response === "AWKWARD") {
				        		   $("#eventInfoLoadingDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }else if (result.response === "SUCCESS") {   
				    			   
				    			   $("#eventInfoLoadingDiv").removeAttr("style");
				    			   
				    			   var options = {
				    					    "key": '${razorpayKeyId}', 
				    					    "name": "Wayuparty",
				    					    "order_id": result.object.orderId, 
				    					    "handler": function (response){
				    					        placeEvent(response.razorpay_payment_id,response.razorpay_order_id,response.razorpay_signature,ticketType,ticketAmount,currency,quantity);
				    					    },
				    					    "prefill": {
				    					        "name": '${Wayuparty_loginUserName}',
				    					        "email": '${Wayuparty_loginUserEmail}',
				    					        "contact": '${Wayuparty_loginUserMobile}'
				    					    },
				    					    "notes": {
				    					        "address": "Wayuparty"
				    					    },
				    					    "theme": {
				    					        "color": "#cfb376"
				    					    }
				    					};
				    					var rzp = new Razorpay(options);
				    					rzp.on('payment.failed', function (response){
				    					     
				    						
				    						
				    					});
				    					
				    					rzp.open();
							        
			                       }
							},
				});
		 
		 
	 }else{
		 if(quantity == 0){
			 $("#eventEntryErrorMessage"+incrementValue).css({ display: "block" });
		 }
		 
		 if(timeslot == ''){
			 $("#timeSlotErrorMessage").css({ display: "block" });
		 }
		 
	 }
	 
	 
 }
 </script>
 
 <script>
 function placeEvent(paymentId, orderId, signature, ticketType, ticketAmount, currency, quantity){
	 $("#ticketsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	  var categoryTypeRadio = $('input[name=categoryTypeFilter]');
	  var categoryType = categoryTypeRadio.filter(':checked').val();
	  var eventUUID = '${eventUUID}';
	  var vendorUUID = '${vendorUUID}'
	  var userUUID = '${Wayuparty_loginUserUUId}';
	  var timeslot = $("#bookedTimeSlot").val();
	  var formData = new FormData();
	  formData.append("eventUUID", eventUUID);
	  formData.append("userUUID", userUUID);
	  formData.append("vendorUUID", vendorUUID);
	  formData.append("categoryType", categoryType);
	  formData.append("ticketType", ticketType);
	  formData.append("ticketAmount", ticketAmount);
	  formData.append("currency", currency);
	  formData.append("quantity", quantity);
	  formData.append("paymentId", paymentId);
	  formData.append("orderId", orderId);
	  formData.append("signature", signature);
	  formData.append("timeslot", timeslot);
	  
	   $.ajax({
		   data: formData,
   	    contentType: false,
   	    processData: false,
			  type: "POST",
			    url: appUrl+"/saveEventOrder",
			     success :function(result) {
			    	 
			    	   if (result.response === "AWKWARD") {
			    		   $("#ticketsLoadingDiv").removeAttr("style");
		    		        location.href = "/errorPage";
		    		   }else if (result.response === "SUCCESS") {    
		    			    $("#ticketsLoadingDiv").removeAttr("style");
					         $("#successMsgDiv").removeAttr("style");
				    		 $("#successMsgDiv").css({ display: "block" });
					         $("#successMsg").html("Well done! You successfully placed Order.");
					         $("#successMsgDiv").fadeIn(200);
					 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
					 	     location.replace(appUrl+"/clubEvents?vendorUUID="+vendorUUID);
	                    }else if (result.response === "INVALID_DATA") {     
	                    	    $("#ticketsLoadingDiv").removeAttr("style");
						         $("#invalidMsgDiv").removeAttr("style");
					    		 $("#invalidMsgDiv").css({ display: "block" });
						         $("#invalidMsg").html("Some data is missing.");
						         $("#invalidMsgDiv").fadeIn(200);
						 	     $('#invalidMsgDiv').delay(2000).fadeOut('slow');
		                       }
			    	
					 
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


     