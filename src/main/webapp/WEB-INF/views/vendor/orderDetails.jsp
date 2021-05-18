<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="ui-modal-box">
  <div class="modal-box">
       <div class="modal right-slide-modal fade sidebar-align" id="right-slide-modal" tabindex="-1"
                                    role="dialog" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                        <div id="orderDetailsLoaderDiv"></div>
                                            <div class="modal-header">
                                                <h5 class="modal-title">Order Details</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                               
                                           <div class="modal-body service-info">
                                            <ul class="row"> 
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Service  : </strong><span id="serviceName"></span></li>  
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>User Name  : </strong><span id="userName"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Order Code  : </strong><span id="orderCode"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Order Date  : </strong><span id="orderDate"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Service Date  : </strong><span id="serviceDate"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Time Slot  : </strong><span id="timeSlot"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Quantity  : </strong><span id="quantity"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Order Amount	: </strong><span id="orderAmount"></span></li>
                                               <li class="col-xl-6 col-lg-6 col-12"><strong>Allowed	: </strong><span id="allowed"></span></li>
                                               <li class="col-xl-8 col-lg-6 col-12"><strong>Order Status  : </strong><span id="vendorStatus"></span></li>
                                               <li class="col-xl-8 col-lg-6 col-12"><span id="surpriseDetails"></span></li>
                                               <li class="col-xl-8 col-lg-6 col-12"><span id="packageDetails"></span></li>
                                               </ul>
                                            </div>
                                            <div class="modal-footer">
                                               <button type="button" class="footer-btn bg-gradient-gplus" data-dismiss="modal"><i class="fas fa-times mg-l-10"></i>&nbsp;Close</button>
                                            </div>
                                        </div>
                                    </div>
       </div>
       </div>
       </div>
       
       <script>
       function getOrderDetails(orderUUID){
    	   
    	   var appUrl ='${appUrl}';
    	   $("#orderDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
    	   $.ajax({
    			  type: "GET",
    			    url: appUrl+"/getOrderDetails?${_csrf.parameterName}=${_csrf.token}&orderUUID="+orderUUID,
    			     success :function(response) {
    			    	    
    			    	 $("#orderDetailsLoaderDiv").removeAttr("style");
    			    	 
    			    	 $("#serviceImage").html('<img src="'+response.object.serviceImage+'" onerror="predefineVendorProfileImage(this);" data-id= "vendorProfileImage" >');
    			    	 $("#serviceName").html(response.object.serviceName);
    			    	 $("#userName").html(response.object.userName);
    			    	 $("#orderCode").html(response.object.orderCode);
    			    	 $("#orderDate").html(response.object.placeOrderDate);
    			    	 $("#serviceDate").html(response.object.serviceOrderDate);
    			    	 $("#timeSlot").html(response.object.timeSlot);
    			    	 $("#quantity").html(response.object.quantity);
    			    	 var currency = getCurrency(response.object.currency)
    			    	 $("#orderAmount").html(currency+" "+response.object.totalOrderAmount);
    			    	 var totalAllowed =  Number(response.object.allowed) * Number(response.object.quantity);
    			    	 $("#allowed").html(totalAllowed);
    			    	 $("#vendorStatus").html(response.object.vendorOrderStatus);
    			    	 
    			    	 if(response.object.surpriseList.length > 0){
    			    		 var result = '';
			        		 for (var j=0; j< response.object.surpriseList.length; j++)
		  	        	    	{
			        			 var surprise = response.object.surpriseList[j];
			        			 result = result +'<span><strong>'+surprise.surpriseType+' :</strong> '+surprise.surpriseName+'</span></br>';
		  	        	    	}
			        		 result = result +'<span><strong>Instructions : </strong>'+response.object.surpriseInstructions+'</span>';
			        		 $("#surpriseDetails").html(result);
		        		 }else{
		        			 $("#surpriseDetails").html('');
		        		 }
    			    	 
    			    	 if(response.object.packageItems.length > 0){
    			    		 var result = '';
    			    		 result = result +'<div class="mt-5"><h5><strong>Package MenuItems</strong></h5>';
			        		 for (var k=0; k< response.object.packageItems.length; k++)
		  	        	    	{
			        			 var menu = response.object.packageItems[k];
			        			 result = result +'<span><strong>'+menu.menuOfferingItem+' :</strong> '+menu.menuItems+'</span><br/>';
		  	        	    	}
			        		 result = result +'</div>';
			        		 $("#packageDetails").html(result);
		        		 }else{
		        			 $("#packageDetails").html('');
		        		 }
    			    	
    			    	 
    					},
    		});
       }
       </script>
       