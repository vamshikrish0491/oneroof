<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tab-pane fade" id="settings" role="tabpanel">
<div id="vendorSettingsDetailsLoaderDiv"></div>


				<div class="vertical-tab">
                            <ul class="nav nav-tabs flex-column" role="tablist">
                               <c:forEach var="service" items="${servicesList}"> 
                                <li class="nav-item">
                                    <a class="nav-link  ${service.serviceId == 1 ? 'active' : ''}" data-toggle="tab" style="cursor:pointer;" role="tab" aria-selected="true" onclick="loadVendorSettings('${service.serviceUUID}');"><img src="${service.serviceImage}"></img>&nbsp;&nbsp;&nbsp;${service.serviceDisplayName}</a>
                                </li>
                                </c:forEach>
                            </ul>
                            <div class="tab-content">
                            <form class="new-added-form" id="vendor_settings_form" name="vendor_settings_form" >
								
								<div id="entryRatioDiv">
								<div class="row">	
									<div class="col-xl-3 col-lg-6 col-12">
									<label>Men Entry Ratio</label>
										<div class="btnAdd">
									    	<!-- <button type="button" class="btn btn-primary" onClick="menQuantityDec()"><i class="fa fa-minus"></i></button> -->
									        	<input type="number" readonly="readonly" step="1" min="0" value="2" placeholder="Value" class="form-control text-center" id="menRatioValue" name="menRatioValue" style="font-size: 13px;">
									        <!-- <button type="button" class="btn btn-primary" onclick="menQuantityInc()"><i class="fa fa-plus"></i></button> -->
									    </div>
									</div>
									
									<div class="col-xl-3 col-lg-6 col-12">
									<label>Women Entry Ratio</label>
										<div class="btnAdd">
									    	<!-- <button type="button" class="btn btn-primary" onClick="womenQuantityDec()"><i class="fa fa-minus"></i></button> -->
									        	<input type="number" readonly="readonly" step="1" min="0" value="1" placeholder="Value" class="form-control text-center" id="womenRatioValue" name="womenRatioValue" style="font-size: 13px;">
									        <!-- <button type="button" class="btn btn-primary" onclick="womenQuantityInc()"><i class="fa fa-plus"></i></button> -->
									    </div>
									</div>
									
									<div class="col-xl-3 col-lg-6 col-12">
										<div class="toggle-button">
										     <label class="switch-ratio"><input type="checkbox" id="entryRatioCheckbox" name="isEntryRatioEnabled" value="N" onclick="getEntryRatio()" />
										     <span class="slider round"></span>
										     </label>
									     </div>
									</div>
									</div>
								</div>
								
								<div class="row mt-5">	
									<div class="col-xl-12 col-lg-12 col-12 form-group" id="cancelOrderTypeDiv">
									 <label>Cancel Order</label>
									  <div class="formInline">
									  	<div class="form-group">
									  		<input type="text"  id="cancelOrderValue" name="cancelOderValue" placeholder="Value" class="form-control" data-validation-optional="true" data-validation="validate_space validate_int" field-name="Cancel Order Value">
									    </div>
									    <div class="form-group">
									    <label class="checkbox-inline">
									      <input type="radio" name="cancelOrderType" id="cancelOrderMinutes" value="Minutes" onclick="checkCancelOrderTypeRadio()">&nbsp;Minutes
									    </label>
									    </div>
									    <div class="form-group">
									    <label class="checkbox-inline">
									      <input type="radio" name="cancelOrderType" id="cancelOrderHours" value="Hours" onclick="checkCancelOrderTypeRadio()">&nbsp;Hours
									    </label>
									    </div>
									    <div class="form-group">
									    <label class="checkbox-inline">
									      <input type="radio" name="cancelOrderType" id="cancelOrderDays" value="Days" onclick="checkCancelOrderTypeRadio()">&nbsp;Days
									    </label>
									    </div>
									     <div class="toggle-button">
									     <label class="switch"><input type="checkbox" id="cancelOrderCheckbox" name="isCancelOrderEnabled" value="N"  onclick="getCancelOrderType()" />
									     <span class="slider round"></span>
									     </label>
									     </div>
									  </div>
									   <input type="hidden" id="cancelOrder" name="cancelOrder" data-validation-optional="true" field-name="Cancel Order Type">
									</div>
									
									
									<div class="col-xl-12 col-lg-12 col-12 form-group" id="rescheduleOrderTypeDiv">
									 <label>Reschedule Order</label>
									  <div class="formInline">
									  	<div class="form-group">
									  		<input type="text"  id="rescheduleOrderValue" name="rescheduleOrderValue" placeholder="Value" class="form-control" data-validation-optional="true" data-validation="validate_space validate_int" field-name="Reschedule Order Value">
									    </div>
									    <div class="form-group">
									    <label class="checkbox-inline">
									      <input type="radio" name="rescheduleOrderType" id="rescheduleOrderMinutes" value="Minutes" onclick="checkRescheduleOrderTypeRadio()">&nbsp;Minutes
									    </label>
									    </div>
									    <div class="form-group">
									    <label class="checkbox-inline">
									      <input type="radio" name="rescheduleOrderType" value="Hours" id="rescheduleOrderHours" onclick="checkRescheduleOrderTypeRadio()">&nbsp;Hours
									    </label>
									    </div>
									    <div class="form-group">
									    <label class="checkbox-inline">
									      <input type="radio" name="rescheduleOrderType" value="Days" id="rescheduleOrderDays" onclick="checkRescheduleOrderTypeRadio()">&nbsp;Days
									    </label>
									    </div>
									     <div class="toggle-button">
									     <label class="switch"><input type="checkbox" id="rescheduleOrderCheckbox" name="isRescheduleOrderEnabled" value="N"  onclick="getRescheduleOrderType()" />
									     <span class="slider round"></span>
									     </label>
									     </div>
									  </div>
									   <input type="hidden" id="rescheduleOrder" name="rescheduleOrder" data-validation-optional="true" field-name="Reschedule Order Type">
									</div>
									
									
									<div class="col-xl-12 col-lg-12 col-12 form-group" id="priorOrderTypeDiv">
									 <label>Order Prior Booking</label>
									  <div class="formInline">
									  	<div class="form-group">
									  		<input type="text"  id="priorOrderValue" name="priorOrderValue" placeholder="Value" class="form-control" data-validation-optional="true" data-validation="validate_space validate_int" field-name="Prior Order Value">
									    </div>
									    <div class="form-group">
									    <label class="checkbox-inline">
									      <input type="radio" name="priorOrderType" id="priorOrderMinutes" value="Minutes" onclick="checkPriorOrderTypeRadio()">&nbsp;Minutes
									    </label>
									    </div>
									    <div class="form-group">
									    <label class="checkbox-inline">
									      <input type="radio" name="priorOrderType" id="priorOrderHours" value="Hours" onclick="checkPriorOrderTypeRadio()">&nbsp;Hours
									    </label>
									    </div>
									    <div class="form-group">
									    <label class="checkbox-inline">
									      <input type="radio" name="priorOrderType" id="priorOrderDays" value="Days" onclick="checkPriorOrderTypeRadio()">&nbsp;Days
									    </label>
									    </div>
									     <div class="toggle-button">
									     <label class="switch"><input type="checkbox" id="priorOrderCheckbox" name="ispPriorOrderEnabled" value="N"  onclick="getPriorOrderType()" />
									     <span class="slider round"></span>
									     </label>
									     </div>
									  </div>
									   <input type="hidden" id="priorOrder" name="priorOrder" data-validation-optional="true" field-name="Prior Order Type">
									</div>
									
									
									
									<div class="col-xl-12 col-lg-12 col-12 form-group" id="priorOrderTypeDiv">
									 <label>Orders Approval</label>
										<div class="formInline">
										<div class="form-group">
									    <label class="checkbox-inline">
									      <input type="radio" name="ordersApprovalTypeFilter" id="approvalYes" value="Yes" onclick="changeOrderApprovalType()">&nbsp;Required
									    </label>
									    </div>
									    <div class="form-group">
									    <label class="checkbox-inline">
									      <input type="radio" name="ordersApprovalTypeFilter" id="approvalNo" value="No" onclick="changeOrderApprovalType()">&nbsp;Not Required
									    </label>
									    </div>
		              					<input type="hidden" id="orderApproval" name="orderApproval" value="No" data-validation="required" field-name="User Orders Approval">
		              					</div>
									</div>
									
									</div>
									
									<div class="row">	
							   			<div class="col-12 ui-btn-wrap">
									               <ul>
									               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="vendorSettingsBtn"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
									               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="loadVendorSettings('${bottleServiceUUID}');"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
									               <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
									               </ul>
									   </div>
								   </div>
							</form>
                            </div>
                </div>

</div>

 <script>
   function changeOrderApprovalType(){
	   var ordersTypeFilterRadio = $('input[name=ordersApprovalTypeFilter]');
	   var ordersApprovalTypeFilter = ordersTypeFilterRadio.filter(':checked').val();
	   $('#orderApproval').val(ordersApprovalTypeFilter);
   }
   </script>

<script>
function menQuantityInc(){
	var quantity = document.getElementById("menRatioValue");
	quantity.stepUp(1); 
}

function menQuantityDec(){
	var quantity = document.getElementById("menRatioValue");
	quantity.stepDown(1); 
}

function womenQuantityInc(){
	var quantity = document.getElementById("womenRatioValue");
	quantity.stepUp(1); 
}

function womenQuantityDec(){
	var quantity = document.getElementById("womenRatioValue");
	quantity.stepDown(1); 
}
</script>


<script>
function loadVendorSettings(serviceUUID){
	var vendorUUID = '${vendorUUID}';
	var vendorSettingsElement = document.getElementById('vendorSettingsBtn');
	vendorSettingsElement.onclick = function() {
		saveVendorSettings(serviceUUID)
	 }
	
	if(serviceUUID == 'Hr3Fol7r'){
		 $("#entryRatioDiv").css({ display: "none" });
	}else{
		$("#entryRatioDiv").css({ display: "block" });
	}
	
	 var appUrl ='${appUrl}';
	   $("#vendorSettingsDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   
	   var formData = new FormData();
	   formData.append("vendorUUID", vendorUUID);
	   formData.append("serviceUUID", serviceUUID);
       
		$.ajax({
			 type : "POST",
			 data: formData,
   	         processData: false,
   	         contentType:false,
			    url: appUrl+"/getVendorSettingsDetails?${_csrf.parameterName}=${_csrf.token}",
			     success :function(response) {
			    	 
			    	 if(response.object != null){
			    		 $("#menRatioValue").val(response.object.menRatioValue);
				    	 $("#womenRatioValue").val(response.object.womenRatioValue); 
				    	 
				    	 if(response.object.orderApproval != ''){
				    		 if(response.object.orderApproval == 'Yes'){
				    			 document.getElementById("approvalYes").checked = true;
				    		 }else{
				    			 document.getElementById("approvalNo").checked = true;
				    		 }
				    		 $('#orderApproval').val(response.object.orderApproval);
				    	 }else{
				    		 document.getElementById("approvalYes").checked = false;
					    	 document.getElementById("approvalNo").checked = false;
					    	 $('#orderApproval').val("No");
				    	 }
				    	 
				    	 
				    	 if(response.object.isEntryRatioEnabled == 'Y'){
				    		 document.getElementById("entryRatioCheckbox").checked = true;
				    		 $("#entryRatioCheckbox").val("Y");
				    	 }else{
				    		 document.getElementById("entryRatioCheckbox").checked = false;
				    		 $("#entryRatioCheckbox").val("N");
				    	 }
				    	 
				    	 $("#cancelOrderValue").val(response.object.cancelOderValue); 
				    	 if(response.object.cancelOrder != ''){
    			    		 if(response.object.cancelOrder == 'Minutes'){
    			    			 document.getElementById("cancelOrderMinutes").checked = true
    			    		 }else if(response.object.cancelOrder == 'Hours'){
    			    			 document.getElementById("cancelOrderHours").checked = true
    			    		 }else{
    			    			 document.getElementById("cancelOrderDays").checked = true
    			    		 }
    			    		 $('#cancelOrder').val(response.object.cancelOrder);
    			    	 }else{
    			    		 document.getElementById("cancelOrderMinutes").checked = false
    				    	 document.getElementById("cancelOrderHours").checked = false
    				    	 document.getElementById("cancelOrderDays").checked = false
    				    	 $('#cancelOrder').val('');
    			    	 }
				    	 
				    	 if(response.object.isCancelOrderEnabled == 'Y'){
				    		 document.getElementById("cancelOrderCheckbox").checked = true;
				    		 getCancelOrderType();
				    	 }else{
				    		 document.getElementById("cancelOrderCheckbox").checked = false;
				    		 getCancelOrderType();
				    	 }
				    	 
				    	 
				    	 $("#rescheduleOrderValue").val(response.object.rescheduleOrderValue); 
				    	 if(response.object.rescheduleOrder != ''){
    			    		 if(response.object.rescheduleOrder == 'Minutes'){
    			    			 document.getElementById("rescheduleOrderMinutes").checked = true
    			    		 }else if(response.object.rescheduleOrder == 'Hours'){
    			    			 document.getElementById("rescheduleOrderHours").checked = true
    			    		 }else{
    			    			 document.getElementById("rescheduleOrderDays").checked = true
    			    		 }
    			    		 $('#rescheduleOrder').val(response.object.rescheduleOrder);
    			    	 }else{
    				    	 document.getElementById("rescheduleOrderMinutes").checked = false
    				    	 document.getElementById("rescheduleOrderHours").checked = false
    				    	 document.getElementById("rescheduleOrderDays").checked = false
    				    	 $('#rescheduleOrder').val('');
    			    	 }
				    	 
				    	 if(response.object.isRescheduleOrderEnabled == 'Y'){
				    		 document.getElementById("rescheduleOrderCheckbox").checked = true;
				    		 getRescheduleOrderType();
				    	 }else{
				    		 document.getElementById("rescheduleOrderCheckbox").checked = false;
				    		 getRescheduleOrderType();
				    	 }
				    	 
				    	 
				    	 
				    	 $("#priorOrderValue").val(response.object.priorOrderValue); 
				    	 if(response.object.priorOrder != ''){
    			    		 if(response.object.priorOrder == 'Minutes'){
    			    			 document.getElementById("priorOrderMinutes").checked = true
    			    		 }else if(response.object.priorOrder == 'Hours'){
    			    			 document.getElementById("priorOrderHours").checked = true
    			    		 }else{
    			    			 document.getElementById("priorOrderDays").checked = true
    			    		 }
    			    		 $('#priorOrder').val(response.object.priorOrder);
    			    	 }else{
    			    		 document.getElementById("priorOrderMinutes").checked = false
    				    	 document.getElementById("priorOrderHours").checked = false
    				    	 document.getElementById("priorOrderDays").checked = false
    				    	 $('#priorOrder').val('');
    			    	 }
				    	 
				    	 if(response.object.ispPriorOrderEnabled == 'Y'){
				    		 document.getElementById("priorOrderCheckbox").checked = true;
				    		 getPriorOrderType();
				    	 }else{
				    		 document.getElementById("priorOrderCheckbox").checked = false;
				    		 getPriorOrderType();
				    	 }
				    	 
			    	 }else{
			    	
				    	 document.getElementById("approvalYes").checked = false;
				    	 document.getElementById("approvalNo").checked = false;
				    	 $('#orderApproval').val("No");
			    		 
				    	 document.getElementById("entryRatioCheckbox").checked = false;
				    	 $("#entryRatioCheckbox").val("N");
				    	 
				    	 document.getElementById("cancelOrderMinutes").checked = false
				    	 document.getElementById("cancelOrderHours").checked = false
				    	 document.getElementById("cancelOrderDays").checked = false
				    	 $('#cancelOrder').val('');
				    	 $('#cancelOrderValue').val('');
				    	 document.getElementById("cancelOrderCheckbox").checked = false;
			    		 getCancelOrderType();
			    		 
				    	 document.getElementById("rescheduleOrderMinutes").checked = false
				    	 document.getElementById("rescheduleOrderHours").checked = false
				    	 document.getElementById("rescheduleOrderDays").checked = false
				    	 
				    	 $('#rescheduleOrder').val('');
				    	 $('#rescheduleOrderValue').val('');
				    	 document.getElementById("rescheduleOrderCheckbox").checked = false;
				    	 getRescheduleOrderType();
				    	 
				    	 
				    	 document.getElementById("priorOrderMinutes").checked = false
				    	 document.getElementById("priorOrderHours").checked = false
				    	 document.getElementById("priorOrderDays").checked = false
				    	 $('#priorOrder').val('');
				    	 $('#priorOrderValue').val('');
				    	 document.getElementById("priorOrderCheckbox").checked = false;
				    	 getPriorOrderType();
			    	 }
			    	 
			    	 
			    	 $("#vendorSettingsDetailsLoaderDiv").removeAttr("style");
					},
		});
}
</script>

<script>

function getEntryRatio(){
	var checked = document.getElementById("entryRatioCheckbox").checked
	if(checked == true){
		$("#entryRatioCheckbox").val("Y");
	}else{
		$("#entryRatioCheckbox").val("N");
	}
}

function getCancelOrderType(){
	var checked = document.getElementById("cancelOrderCheckbox").checked;
	if(checked == true){
		$("#cancelOrder").removeAttr('data-validation-optional');
		$("#cancelOrder").attr('data-validation','required');
		
		$("#cancelOrderValue").removeAttr('data-validation-optional');
		$("#cancelOrderValue").attr('data-validation','required validate_space validate_int');
		
		$("#cancelOrderCheckbox").val("Y");
		
		
	}else{
		$("#cancelOrder").removeAttr('data-validation');
		$("#cancelOrder").attr('data-validation-optional','true');
		
		$("#cancelOrderValue").removeAttr('data-validation');
		$("#cancelOrderValue").attr('data-validation','validate_space validate_int');
		$("#cancelOrderValue").attr('data-validation-optional','true');
		$("#cancelOrderTypeDiv").find('p.jquery_form_error_message').remove();
		
		$("#cancelOrderCheckbox").val("N");
	}
	
}

function checkCancelOrderTypeRadio(){
	    var cancelOrderTypeRadio = $('input[name=cancelOrderType]');
	    var cancelOrderType = cancelOrderTypeRadio.filter(':checked').val();
	    $('#cancelOrder').val(cancelOrderType);
	    $("#cancelOrderTypeDiv").find('p.jquery_form_error_message').remove();
}

function getRescheduleOrderType(){
	var checked = document.getElementById("rescheduleOrderCheckbox").checked
	if(checked == true){
		$("#rescheduleOrder").removeAttr('data-validation-optional');
		$("#rescheduleOrder").attr('data-validation','required');
		
		$("#rescheduleOrderValue").removeAttr('data-validation-optional');
		$("#rescheduleOrderValue").attr('data-validation','required validate_space validate_int');
		$("#rescheduleOrderCheckbox").val("Y");
		
	}else{
		$("#rescheduleOrder").removeAttr('data-validation');
		$("#rescheduleOrder").attr('data-validation-optional','true');
		
		$("#rescheduleOrderValue").removeAttr('data-validation');
		$("#rescheduleOrderValue").attr('data-validation','validate_space validate_int');
		$("#rescheduleOrderValue").attr('data-validation-optional','true');
		$("#rescheduleOrderTypeDiv").find('p.jquery_form_error_message').remove();
		$("#rescheduleOrderCheckbox").val("N");
	}
	
}

function checkRescheduleOrderTypeRadio(){
	var rescheduleOrderTypeRadio = $('input[name=rescheduleOrderType]');
    var rescheduleOrderType = rescheduleOrderTypeRadio.filter(':checked').val();
    $('#rescheduleOrder').val(rescheduleOrderType);
    $("#rescheduleOrderTypeDiv").find('p.jquery_form_error_message').remove();
	
}


function getPriorOrderType(){
	var checked = document.getElementById("priorOrderCheckbox").checked
	if(checked == true){
		$("#priorOrder").removeAttr('data-validation-optional');
		$("#priorOrder").attr('data-validation','required');
		
		$("#priorOrderValue").removeAttr('data-validation-optional');
		$("#priorOrderValue").attr('data-validation','required validate_space validate_int');
		$("#priorOrderCheckbox").val("Y");
		
		
	}else{
		$("#priorOrder").removeAttr('data-validation');
		$("#priorOrder").attr('data-validation-optional','true');
		
		$("#priorOrderValue").removeAttr('data-validation');
		$("#priorOrderValue").attr('data-validation','validate_space validate_int');
		$("#priorOrderValue").attr('data-validation-optional','true');
		$("#priorOrderTypeDiv").find('p.jquery_form_error_message').remove();
		$("#priorOrderCheckbox").val("N");
	}
	
}

function checkPriorOrderTypeRadio(){
	var priorOrderTypeRadio = $('input[name=priorOrderType]');
    var priorOrderType = priorOrderTypeRadio.filter(':checked').val();
    $('#priorOrder').val(priorOrderType);
    $("#priorOrderTypeDiv").find('p.jquery_form_error_message').remove();
	
}

</script>


<script>
 function saveVendorSettings(serviceUUID){
	   if ($('#vendor_settings_form').validate(false, validationSettings)){
		   $("#vendorSettingsDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var vendorUUID = "${vendorUUID}";
           var formData = getWayupayFormData("vendor_settings_form");
			formData.append("vendorUUID", vendorUUID);
			formData.append("serviceUUID", serviceUUID);
            $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveVendorSettingsDetails?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#vendorSettingsDetailsLoaderDiv").removeAttr("style");
				    		     $("#invalidMsgDiv").removeAttr("style");
					    		 $("#invalidMsgDiv").css({ display: "block" });
						         $("#invalidMsg").html("Attention! You had missed some data.");
						         $("#invalidMsgDiv").fadeIn(500);
						 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#vendorSettingsDetailsLoaderDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {
				    			   $("#vendorSettingsDetailsLoaderDiv").removeAttr("style");
							         $("#successMsgDiv").removeAttr("style");
						    		 $("#successMsgDiv").css({ display: "block" });
							         $("#successMsg").html("Well done! You successfully updated vendor details.");
							         $("#successMsgDiv").fadeIn(200);
							 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
							 	     loadVendorSettings(serviceUUID);
			                   }
				        	
				        		
				},
		});   
           return true;
	   }else{
			return false;
		  } 
 }

</script>
