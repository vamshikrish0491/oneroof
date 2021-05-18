<div class="tab-pane fade show active" id="orders" role="tabpanel">
		<div class="modal-body">
		    <div class="table-responsive">
		    
		    <ul class="float-right list-unstyled list-inline" style="margin-bottom: 5px;">
	  			<li id="ordersListSearch" style="position: relative;"><input type="text" class="repSearchBar form-control"  id='ordersDataTableSearch'/><span class="repSearchIcon" id="searchDiv"></span></li>
       		 </ul>
       		 
       		 <div class="service_wrap_table datatable_filter positionRelative">
			    <div class="service_tabs_inn_table dataFilterNew pdtop">
			    	<div id="ordersList" class="datatable_filter"></div>
				</div>	  
             </div>
		    
            </div>
		</div>
</div>

<jsp:include page="orderDetails.jsp" />


<script>
 function getOrdersList(bottleServiceUUID){
	  
	 $("#ordersLoaderDiv").attr('style','position:absolute; width:100%; height:350%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/loader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	  var result = "";

	 	result = result + '<table id="ordersListTable" class="display mt10" cellspacing="0" width="100%" >';
		result = result + '<thead>';
		result = result + '<tr>';
		result = result +'	<th><i class="fas fa-user mr-2"></i>User Name</th>';
	  	result = result +'  <th><i class="fas fa-info-circle mr-2"></i>Order Code</th>';
	  	result = result +'  <th><i class="fas fa-calendar mr-2"></i>Service Date</th>';
	  	result = result +'  <th><i class="fas fa-clock mr-2"></i>Time Slot</th>';
		result = result +'  <th><i class="fas fa-money-bill mr-2"></i>Amount</th>';
	  	result = result +'  <th><i class="fas fa-user-secret mr-2"></i>Vendor Status</th>';
		result = result +'  <th><i class="fas fa-cogs mr-2"></i>Actions</th>';
		result = result + '</tr>';
		result = result + '</thead>	';
	    result = result +"</table>";
	    $("#ordersList").empty();
	    $("#ordersList").append(result);
	    $("#ordersListSearch").show();
	    $("#ordersDataTableSearch").val('');
	    getServiceOrders(bottleServiceUUID);
	   
 }
 </script>
 
 <script>
 function getServiceOrders(bottleServiceUUID){
	 var appUrl = '${appUrl}';
	 var vendorUUID = '${Wayuparty_vendorUUID}';
	 var searchValue = $("#ordersDataTableSearch").val();
	 searchValue = searchValue ? searchValue : "";
	 var url = appUrl+"/getOrdersList?serviceUUID="+bottleServiceUUID+"&vendorUUID="+vendorUUID+"&searchValue="+searchValue;
	 ordersPagination(url,bottleServiceUUID); 
	 $("#ordersLoaderDiv").removeAttr("style");
 }
 </script>
 
 <script>
 function ordersPagination(url,bottleServiceUUID){
	 var colIndex = 0;
	    $('#ordersListTable').DataTable( { 
	      "processing": true,
	      "serverSide": true,
	      "searching": false,
	      "ajax": $.fn.dataTable.pipeline( {
	    	  url: url,
	          method: 'GET',
	          pages: 2
	        }),
	        
	        "columns": [
	            { "data": "userName"},
	            { "data": "orderCode"},
	            { "data": "serviceOrderDate"},
	            { "data": "timeSlot"},
	            { "data": "orderAmount"},
	            { "data": "vendorOrderStatus"},
	            { "data": "orderActions"}
	        ],
	        "columnDefs": [ {
		    	  	"targets": colIndex++,
		    	  	"data": "userName",
		    	  	"sortable": false,
		    	  	"render": function(data, type, opt, meta) {
		    	  		 var result = "";
		    	  	   	 result = result +"<td>";
	                	 result = result +"<div class='width150px'>";
	                	 result = result +"<div class='tbldata-dotdot-big pull-left' title='"+opt.userName+"'>"+opt.userName;
	                	 result = result +"</div>"
	                	 result = result +"</td>";
			             return result;
		    	  	}
		      },{
		        	  "targets": colIndex++,
		              "data": "orderCode",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	  var result = "";
		             	  result = result +"<td><div class='tblminwidth-90'><span>"+opt.orderCode+"</span></div></td>";
			              return result;
		              }
			     },{
			    	  "targets": colIndex++,
		              "data": "serviceOrderDate",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	  var result = "";
		             	  result = result +"<td><div class='tblminwidth-90'><span>"+opt.serviceOrderDate+"</span></div></td>";
			              return result;
		              }
			      },{
		        	  "targets": colIndex++,
		              "data": "timeSlot",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	  var result = "";
		             	  result = result +"<td><div class='tblminwidth-90'><span>"+opt.timeSlot+"</span></div></td>";
			              return result;
		              }
			     },{
		        	  "targets": colIndex++,
		              "data": "orderAmount",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	  var result = "";
		            	  var currency = getCurrency(opt.currency);
		             	  result = result +"<td><div class='tblminwidth-50'><span>"+currency+" "+opt.orderAmount+"</span></div></td>";
			              return result;
		              }
			     },{
		        	  "targets": colIndex++,
		              "data": "vendorOrderStatus",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	  var result = "";
	                	  result = result +"<td><div class='tblminwidth-90'><span>"+opt.vendorOrderStatus+"</span></div></td>";
	                	  return result;
		              }
			     },{
			    	  "targets": colIndex++,
		              "data": "orderActions",
		              "sortable": false,
		              "render": function ( data, type, opt, meta ) {
		            	    var result = "";
		            	    
		            	    if(opt.vendorOrderStatus == 'Pending'){
		            	    	    result = result +'<td>';
				            	    result = result +'<div class="tblminwidth-90">';
				            	    result = result +'<ul class="list-unstyled list-inline  clearfix">';
				            	    result = result +'<li style="float: left; margin-left: 15px;"><a href="javascript:void(0)" data-toggle="modal" data-target="#right-slide-modal" onclick="getOrderDetails(\''+opt.orderUUID+'\')"><i class="fas fa-eye cursorPointer text-info" title="Details"></i></a></li>';
				            	    result = result +'<li style="float: left; margin-left: 15px;"><a href="javascript:void(0)"><i class="fas fa-user-plus cursorPointer text-success" title="Accept" onclick="acceptOrder(\''+opt.orderUUID+'\',\''+bottleServiceUUID+'\')"></i></a></li>';
				            	    result = result +'<li style="float: left; margin-left: 15px;"><a href="javascript:void(0)"><i class="fas fa-user-times cursorPointer text-danger" title="Cancel" onclick="cancelOrder(\''+opt.orderUUID+'\',\''+bottleServiceUUID+'\')"></i></a></li>';
				            	    result = result +'</ul>';
				            	    result = result +'</div>';
				            	    result = result +'</td>'; 
		            	    }else{
		            	    	    result = result +'<td>';
				            	    result = result +'<div class="tblminwidth-90">';
				            	    result = result +'<ul class="list-unstyled list-inline  clearfix">';
				            	    result = result +'<li style="float: left; margin-left: 10px;"><a href="javascript:void(0)" data-toggle="modal" data-target="#right-slide-modal" onclick="getOrderDetails(\''+opt.orderUUID+'\')"><i class="fas fa-eye cursorPointer text-info" title="Details"></i></a></li>';
				            	   
				            	    if(opt.vendorOrderStatus == 'Approved' && opt.isUserArrived == ''){
					            	    result = result +'<li style="float: left; margin-left: 10px;"><a href="javascript:void(0)" onclick="userArrivedStatus(\''+opt.orderUUID+'\',\''+bottleServiceUUID+'\',\'Y\')"><img  src="/resources/img/accept_icon.png" class="placeOrderImg cursorPointer" title="Arrived"></img></a></li>';
					            	    result = result +'<li style="float: left; margin-left: 10px;"><a href="javascript:void(0)" onclick="userArrivedStatus(\''+opt.orderUUID+'\',\''+bottleServiceUUID+'\',\'N\')"><img  src="/resources/img/reject_icon.png" class="placeOrderImg cursorPointer" title="Not Arrived"></img></a></li>';
				            	    }else if(opt.vendorOrderStatus == 'Approved' && opt.isUserArrived == 'Y'){
				            	    	result = result +'<li style="float: left; margin-left: 10px;">|&nbsp;<span class="text-success">Arrived</span></li>';
				            	    }else if(opt.vendorOrderStatus == 'Approved' && opt.isUserArrived == 'N'){
				            	    	result = result +'<li style="float: left; margin-left: 10px;">|&nbsp;<span class="text-info">Not Arrived</span></li>';
				            	    }
				            	    result = result +'</ul>';
				            	    result = result +'</div>';
				            	    result = result +'</td>'; 
		            	    }
		            	   
		            	    return result;
		              }
			     }],
	        
		    	"scrollCollapse": false,
		    	"lengthMenu": [[20, 30, 40, 50], [20, 30, 40, 50]],
		    	"bLengthChange": true,
		    	"bDestroy": true,
		    	"ordering": true,
		    	"oLanguage": {
		     		"sSearch": "",
		     		 "sZeroRecords": "", "sEmptyTable": "", 
		     		 "sProcessing": "<img src='/resources/img/loader.gif' style='width: 75px; height: 75px;'/>"
		     }
	    });
	    
	 var searchOrderElement = document.getElementById("searchDiv");
	 searchOrderElement.onclick = function() {
		 getServiceOrders(bottleServiceUUID);
   	 }
	    
	   $("#ordersListTable").wrap("<div style='overflow-x: auto; overflow-y: hidden;'></div>"); 
	    $("input[type=search]").hide();
	    $("#ordersDataTableSearch").keyup(function(e) {
	    	   if(e.which === 13) {
	   		    $('#ordersDataTableSearch').parent().find('p.jquery_form_error_message').remove();
	   	    	var searchData = $('#ordersDataTableSearch').val();
	   	    	
	   	 	if(searchData.endsWith("-") || searchData.endsWith("\\") || searchData.endsWith("//") || searchData.endsWith("%")){
		    		$("#ordersDataTableSearch").after("<p class='jquery_form_error_message'><span><font color='red'>Enter valid Search Data</font></span>");
		    	}else{
		    		getServiceOrders(bottleServiceUUID);
		    	}
	   	    	$('#ordersDataTableSearch').val(searchData);
	    	   }
	    	}); 
 }
 </script>
 
 <script>
 function userArrivedStatus(orderUUID,bottleServiceUUID,arrivedStatus){
	 
	 $("#ordersLoaderDiv").attr('style','position:absolute; width:100%; height:350%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/loader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	 
     var appUrl = "${appUrl}";
     var formData = new FormData();
	 formData.append("orderUUID", orderUUID);
	 formData.append("arrivedStatus", arrivedStatus);
      $.ajax({
			 type : "POST",
			 data: formData,
  	         processData: false,
  	         contentType:false,
      	    	 url: appUrl+"/saveUserArrivedStatus?${_csrf.parameterName}=${_csrf.token}", 
			        success : function(result) {
			        	
			        	 if (result && result.response === "INVALID_DATA") {
			        		 $("#ordersLoaderDiv").removeAttr("style");
			    		   }
			        	else if (result.response === "AWKWARD") {
			        		   $("#ordersLoaderDiv").removeAttr("style");
			    		        location.href = "/errorPage";
			    		   }  else if (result.response === "SUCCESS") { 
			    			    $("#ordersLoaderDiv").removeAttr("style");
						 	    getOrdersList(bottleServiceUUID)
		                   }
			        	
			        		
			},
	});
 }
 </script>
 
 
 <script>
 function acceptOrder(orderUUID,bottleServiceUUID){
	 $("#ordersLoaderDiv").attr('style','position:absolute; width:100%; height:350%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/loader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	 
     var appUrl = "${appUrl}";
     var formData = new FormData();
	 formData.append("orderUUID", orderUUID);
      $.ajax({
			 type : "POST",
			 data: formData,
  	         processData: false,
  	         contentType:false,
      	    	 url: appUrl+"/acceptOrder?${_csrf.parameterName}=${_csrf.token}", 
			        success : function(result) {
			        	
			        	 if (result && result.response === "INVALID_DATA") {
			        		 $("#ordersLoaderDiv").removeAttr("style");
			    		   }
			        	else if (result.response === "AWKWARD") {
			        		   $("#ordersLoaderDiv").removeAttr("style");
			    		        location.href = "/errorPage";
			    		   }  else if (result.response === "SUCCESS") { 
			    			    $("#ordersLoaderDiv").removeAttr("style");
						 	    getOrdersList(bottleServiceUUID)
		                   }
			        	
			        		
			},
	});
 }
 </script>
 
 
  <script>
 function cancelOrder(orderUUID,bottleServiceUUID){
	 $("#ordersLoaderDiv").attr('style','position:absolute; width:100%; height:350%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/loader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	 
	 var appUrl = "${appUrl}";
     var formData = new FormData();
	 formData.append("orderUUID", orderUUID);
      $.ajax({
			 type : "POST",
			 data: formData,
  	         processData: false,
  	         contentType:false,
      	    	 url: appUrl+"/cancelUserOrder?${_csrf.parameterName}=${_csrf.token}", 
			        success : function(result) {
			        	
			        	 if (result && result.response === "INVALID_DATA") {
			        		 $("#ordersLoaderDiv").removeAttr("style");
			    		   }
			        	else if (result.response === "AWKWARD") {
			        		   $("#ordersLoaderDiv").removeAttr("style");
			    		        location.href = "/errorPage";
			    		   }  else if (result.response === "SUCCESS") { 
			    			    $("#ordersLoaderDiv").removeAttr("style");
						 	    getOrdersList(bottleServiceUUID)
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
 
