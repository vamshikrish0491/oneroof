<div class="modal-box">
       <div class="modal adv-search-modal fade sidebar-align" id="standard-modal" tabindex="-1"
                                    role="dialog" aria-hidden="true">
                                          <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                        <div id="cancelOrderDetailsLoaderDiv"></div>
                                            <div class="modal-header">
                                                <h5 class="modal-title">Cancel Order</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                               Are you sure to cancel order?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="footer-btn bg-dark-low"
                                                    data-dismiss="modal">Close</button>
                                                <button type="button" class="footer-btn bg-linkedin" id="cancelOrder">Save Changes</button>
                                            </div>
                                        </div>
                                    </div>
       </div>
</div>

<script>
function cancelOrder(orderUUID){
	var cancelOrder = document.getElementById('cancelOrder');
	cancelOrder.onclick = function() {
		cancelBookingOrder(orderUUID);
    }
}
</script>

 <script>
 function cancelBookingOrder(orderUUID){
	 $("#cancelOrderDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:350%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/loader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	 
	 var appUrl = "${appUrl}";
     var formData = new FormData();
	 formData.append("orderUUID", orderUUID);
      $.ajax({
			 type : "POST",
			 data: formData,
  	         processData: false,
  	         contentType:false,
      	    	 url: appUrl+"/cancelOrder?${_csrf.parameterName}=${_csrf.token}", 
			        success : function(result) {
			        	
			        	 if (result && result.response === "INVALID_DATA") {
			        		 $("#userOrderDetailsLoaderDiv").removeAttr("style");
			    		   }
			        	else if (result.response === "AWKWARD") {
			        		   $("#userOrderDetailsLoaderDiv").removeAttr("style");
			    		        location.href = "/errorPage";
			    		   }  else if (result.response === "ORDER_CANCELED") { 
			    			    $("#userOrderDetailsLoaderDiv").removeAttr("style");
			    			    location.replace(appUrl+"/myOrders");
		                   }
			        	
			        		
			},
	}); 
 }
 </script>