  <div class="modal-box">
       <div class="modal right-slide-modal fade sidebar-align" id="right-slide-modal" tabindex="-1"
                                    role="dialog" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content mt-5">
                                        <div id="vendorDetailsLoaderDiv"></div>
                                            <div class="modal-header">
                                                <h5 class="modal-title">Service Details</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                               
                                           <div class="modal-body">
                                            <div class="profile_images_img item-img" id="serviceImage"></div>
                                            <ul>    
                                               <li><strong>Service  : </strong><span id="serviceName"></span></li>
                                               <li><strong>Category  : </strong><span id="serviceCategory"></span></li>
                                               <li><strong>Service Name  : </strong><span id="subCategoryName"></span></li>
                                               <li><strong>Start Date  : </strong><span id="startDate"></span></li>
                                               <li><strong>End Date  : </strong><span id="endDate"></span></li>
                                               <li id="actualPriceDiv" style="display: none"><strong>Actual Price	: </strong><span id="actualPrice"></span></li>
                                               <li id="offerPriceDiv" style="display: none"><strong>Offer Price  : </strong><span id="offerPrice"></span></li>
                                               <li id="minimumOrderDiv" style="display: none"><strong>Minimum Order	: </strong><span id="minimumOrder"></span></li>
                                               <li id="discountValueDiv" style="display: none"><strong>Discount Value	: </strong><span id="discountValue"></span></li>
                                               <li><div id="serviceTimeSlotsDiv"></div></li>
                                               </ul>
                                            </div>
                                            <div class="modal-footer">
                                               <button type="button" class="footer-btn bg-gradient-gplus" data-dismiss="modal"><i class="fas fa-times mg-l-10"></i>&nbsp;Close</button>
                                            </div>
                                        </div>
                                    </div>
       </div>
       </div>
        
  <script>
       function getVendorServiceDetails(serviceUUID){
    	   
    	   var appUrl ='${appUrl}';
    	   $("#vendorServicesLoaderDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
    	   $.ajax({
    			  type: "GET",
    			    url: appUrl+"/getVendorServiceDetails?${_csrf.parameterName}=${_csrf.token}&serviceUUID="+serviceUUID,
    			     success :function(response) {
    			    	    
    			    	 $("#serviceImage").html('<img src="'+response.object.serviceImage+'" onerror="predefineVendorServiceImage(this);" data-id= "vendorProfileImage" >');
    			    	 $("#serviceName").html(response.object.service);
    			    	 $("#serviceCategory").html(response.object.category);
    			    	 $("#subCategoryName").html(response.object.subCategory);
    			    	 $("#startDate").html(response.object.serviceStartDate);
    			    	 $("#endDate").html(response.object.serviceEndDate);
    			    	 
    			    	 var currencyType = getCurrency(response.object.currency)
    			    	 
    			    	 if(response.object.actualPrice != 0 && response.object.offerPrice != 0){
    			    		 document.getElementById('actualPriceDiv').style.display='block'
    			    	     document.getElementById('offerPriceDiv').style.display='block'
    			    	     
    			    	     $("#actualPrice").html(currencyType+" "+response.object.actualPrice);
        			    	 $("#offerPrice").html(currencyType+" "+response.object.offerPrice);
    			    		 
    			    	     document.getElementById('minimumOrderDiv').style.display='none'
    			    		 document.getElementById('discountValueDiv').style.display='none'
    			    	 }else{
    			    		 
       			    	    	 document.getElementById('minimumOrderDiv').style.display='block'
       			    	    	 
       			    	    	 if(response.object.discountValue != ''){
       			    	    		document.getElementById('discountValueDiv').style.display='block'
       			    	    	 }
        			    	     
        			    	     $("#minimumOrder").html(currencyType+" "+response.object.minimumOrder);
       			    	    	 if(response.object.discountType == 'amount'){
       			    	    		 $("#discountValue").html(currencyType+" "+response.object.discountValue);
       			    	    	 }else if(response.object.discountType == 'percentage'){
       			    	    		 $("#discountValue").html(response.object.discountValue+" %");
       			    	    	 }
            			    	
        			    		 
            			    	 document.getElementById('actualPriceDiv').style.display='none'
            			    	 document.getElementById('offerPriceDiv').style.display='none'
    			    	 }
    			    	
    			    	 
    			    	 
    			 		if(response.object.timeSlotList != '' && response.object.timeSlotList.length > 0){
						    var result = "";
						    result = result +"<div class='container'>";      
				        	result = result +"<table border='1' cellpadding='4' style='border-collapse: collapse;' class='table table-bordered table-custom table-hover mt-5'>";
				        	result = result +"<thead class='thead-dark'>";
				        	result = result +"<tr>";
				        	result = result +"<th>Start Time</th>";
				        	result = result +"<th>End Time</th>";
				        	result = result +"</tr>";
				        	result = result +"</thead>";
				        	result = result +"<tbody>";
				        	  for (var i=0; i<response.object.timeSlotList.length; i++){
				        	    	var opt = response.object.timeSlotList[i];
				        	    	
				        	    	result = result +"<tr class='table-light'>";
				        	    	result = result +"<td class='width80'>"+opt.startTime+"</td>";
				        	    	result = result +"<td class='width80'>"+opt.endTime+"</td>";
				        	    	result = result +"</tr>";
				        	    	
				        	    }

			       			result = result +"</tbody>";
			       			result = result +"</table>";
			       			result = result +"</div>";
			        	  
			        	  $("#serviceTimeSlotsDiv").empty();  
    		   	    	  $("#serviceTimeSlotsDiv").append(result);
					 }
    			    	 
    			    	 $("#vendorServicesLoaderDiv").removeAttr("style");
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
       