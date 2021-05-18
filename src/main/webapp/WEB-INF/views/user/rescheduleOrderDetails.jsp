<div class="modal-box">
       <div class="modal adv-search-modal fade sidebar-align" id="adv-search-modal" tabindex="-1"
                                    role="dialog" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content mt-10">
                                        <div id="rescheduleDetailsLoaderDiv"></div>
                                            <div class="modal-header">
                                                <h5 class="modal-title">Reschedule Order</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                               
                                           <div class="modal-body">
                                            <div class="profile_images_img item-img" id="serviceImage"></div>
                                            <ul>    
                                               <li><div id="resecheduleOrderDiv"></div></li>
                                               </ul>
                                            </div>
                                            <div class="modal-footer">
                                               <button type="button" class="footer-btn text-light gradient-pastel-green" id="rescheduleServiceOrder"><i class="fa fa-save mg-l-10"></i>&nbsp;Save</button>
                                               <button type="button" class="footer-btn bg-gradient-gplus" data-dismiss="modal"><i class="fas fa-times mg-l-10"></i>&nbsp;Close</button>
                                            </div>
                                        </div>
                                    </div>
       </div>
</div>

<script>
function rescheduleOrder(orderUUID){
	 var appUrl ='${appUrl}';
	   $("#rescheduleDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/getRescheduleDetails?${_csrf.parameterName}=${_csrf.token}&orderUUID="+orderUUID,
			     success :function(response) {
			    	 
			 		if(response.object.timeSlotList != '' && response.object.timeSlotList.length > 0){
					    var result = "";
					    
					    result = result+'<div class="row">';
						
					    result = result+'<div class="col-lg-4 col-sm-6 col-12">';
					    result = result+'<h5>Order Date</h5>';
					    for (var i=0; i<response.object.serviceDates.length; i++){
		        	    	var orderDate = response.object.serviceDates[i];
		        	    	
		        	    	result = result +"<ul class='table-light mt-2'>";
		        	    	result = result +'<li style="font-size: 14px; font-weight: 600"><input type="radio"  value="'+orderDate.passableDate+'"  name="orderDate'+orderUUID+'">&nbsp;&nbsp;'+orderDate.serviceDate+'</li>';
		        	    	result = result +"</ul>";
		        	    	
		        	    }
					    result = result+'</div>';
					    
					    result = result+'<div class="col-lg-8 col-sm-6 col-12">'; 
					    result = result+'<h5>Time Slots</h5>';
					    for (var j=0; j<response.object.timeSlotList.length; j++){
		        	    	var slots = response.object.timeSlotList[j];
		        	    	
		        	    	result = result +"<ul class='table-light mt-2'>";
		        	    	result = result +'<li style="font-size: 14px; font-weight: 600"><input type="radio"  value="'+slots.timeSlot+'"  name="timeslot'+orderUUID+'">&nbsp;&nbsp;'+slots.timeSlot+'</li>';
		        	    	result = result +"</ul>";
		        	    	
		        	    }
					    result = result+'</div>';
					    result = result+'<p class="mt-2" id="orderErrorMessage'+orderUUID+'" style="display:none">&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-exclamation-triangle"></i>&nbsp;&nbsp;&nbsp;Please provide order date and time slot</p>';
					    result = result+'</div>';
					
					    var rescheduleOrderElement = document.getElementById('rescheduleServiceOrder');
						 rescheduleOrderElement.onclick = function() {
   			    		 rescheduleServiceOrder(orderUUID)
   			    	 	}
		        	  
		        	  $("#resecheduleOrderDiv").empty();  
		   	    	  $("#resecheduleOrderDiv").append(result);
				 }
			    	 
			    	 $("#rescheduleDetailsLoaderDiv").removeAttr("style");
					},
		});
}
</script>

<script>
function rescheduleServiceOrder(orderUUID){
	  var serviceOrderDate = $("input[name='orderDate"+orderUUID+"']:checked").val();
	  var serviceTimeSlot = $("input[name='timeslot"+orderUUID+"']:checked").val();
	  
	  if(typeof(serviceOrderDate) == 'undefined' || typeof(serviceTimeSlot) == 'undefined'){
		  $("#orderErrorMessage"+orderUUID).css({ display: "block" });
	  }else{
		  $("#orderErrorMessage"+orderUUID).css({ display: "none" });
		   var appUrl ='${appUrl}';
		   $("#rescheduleDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
			var formData = new FormData();
			formData.append("serviceOrderDate", serviceOrderDate);
			formData.append("serviceTimeSlot", serviceTimeSlot);
			formData.append("orderUUID", orderUUID);
		   
		    $.ajax({
			data: formData,
	   	    contentType: false,
	   	    processData: false,
				  type: "POST",
				    url: appUrl+"/rescheduleOrder",
				     success :function(result) {
				    	 
				    	   if (result.response === "AWKWARD") {
			        		   $("#rescheduleDetailsLoaderDiv").removeAttr("style");
			    		        location.href = "/errorPage";
			    		   }else if (result.response === "SUCCESS") {     
						 	     location.replace(appUrl+"/myOrders");
		                       }
				    	
						 
						},
			}); 
	  }
	  
}
</script>
       
        