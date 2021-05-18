<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="ui-modal-box">
  <div class="modal-box">
       <div class="modal right-slide-modal fade sidebar-align" id="right-slide-modal" tabindex="-1"
                                    role="dialog" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                        <div id="userOrderDetailsLoaderDiv"></div>
                                            <div class="modal-header">
                                                <h5 class="modal-title">Order Details</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
										    <div class="row">
										    <div class="col-md-12">
										     	<div id="orderDetailsDiv"></div>
										     </div>
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
       function getUserOrderDetails(orderItems,orderRates,totalAmount,orderUUIDs,currencyType,orderDateStatus,orderItemsCanceled,orderItemsReschedule,orderStatus,userArrivedStatus){
    	   
    	   var appUrl ='${appUrl}';
    	   
    	   var items = orderItems.split(",");
    	   var rates = orderRates.split(",");
    	   var orderUUID = orderUUIDs.split(",");
    	   var userOrderStatus = orderStatus.split(",");
    	   var arrivedStatus = userArrivedStatus.split(",");
    	   
    	   var result = "";
    	   
    	   result = result +'<table class="table pricetable">';
    	   result = result +'<thead>';
    	   result = result +'<tr><th>Ordered Item</th> <th style="padding-left: 85px;">Item Price</th> <th>Actions</th></tr>';
    	   result = result +'</thead>';
    	   result = result +'<tbody>';
    	   
    	   for(var i=0 ; i<items.length; i++){
    		   result = result +'<tr>';
    		   result = result +'<td>'+items[i]+'</td>';
    		   result = result +'<td>';
    		   result = result +'<div class="text-right pricetdwidth">'+currencyType+' '+rates[i]+'</div>';
    		   result = result +'</td>';
    		   result = result +'<td>';
    		   result = result +'<ul class="list-unstyled list-inline actionbtns">';
    		   if(orderDateStatus != 'Expired'){
    			  
    			   if(userOrderStatus[i] != 'Canceled'){
    				   if(orderItemsCanceled[i] == 'Y'){
    					   if(userArrivedStatus[i] == 'N'){
    						   result = result +'<li><a href="#" data-toggle="modal" data-target="#standard-modal"onclick="cancelOrder(\''+orderUUID[i]+'\')"><i class="fa fa-times text-danger"></i>';
    					   }else{
    						   result = result +'<li class="text-success">Arrived</li>';
    					   }
        				   
        			   }
        			   
        			   if(orderItemsReschedule[i] == 'Y'){
        				   if(userArrivedStatus[i] == 'N'){
        					   result = result +'&nbsp;&nbsp;<a href="#" data-toggle="modal" data-target="#adv-search-modal" onclick="rescheduleOrder(\''+orderUUID[i]+'\')"><i class="fas fa-redo"></i></a>'; 
        				   }
        				   
        			   }
        			   
        			   if(orderItemsCanceled[i] == 'N' && orderItemsReschedule[i] == 'N'){
        				   if(userArrivedStatus[i] == 'Y'){
        					   result = result +'<li class="text-success">Arrived</li>';
        				   }else{
        					   result = result +'<li class="text-info">Not Arrived</li>';
        				   }
        			   }
    			   }else{
    				   result = result +'<li class="text-danger">'+userOrderStatus[i]+'</li>';
    			   }
    			   
    			   
    			   
    		   }else{
    			   //result = result +'<li class="text-danger">Order Date Expired</li>';
    			   if(userArrivedStatus[i] == 'Y'){
					   result = result +'<li class="text-success">Arrived</li>';
				   }else{
					   result = result +'<li class="text-info">Not Arrived</li>';
				   }
    		   }
    		   
    		   result = result +'</ul>';
    		   result = result +'</td>';
    		   result = result +'</tr>';
    	   };
    	   
    	   result = result +'</tbody>';
    	   result = result +'<tfoot>';
    	   result = result +'<tr>';
    	   result = result +'<th>Total Amount</th>';
    	   result = result +'<th colspan="2">';
    	   result = result +'<div class="text-right pricetdwidth">'+currencyType+' '+totalAmount+'</div>';
    	   result = result +'</th>';
    	   result = result +'</tr>';
    	   result = result +'</tfoot>';
    	   result = result +'</table>';
    	   
    	   $("#orderDetailsDiv").empty();
    	   $("#orderDetailsDiv").append(result);
    	   
    	   
       }
       </script>
       
