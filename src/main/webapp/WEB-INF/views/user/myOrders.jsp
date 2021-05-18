<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="preloader"></div>

 <style>
   body { background-color: #f2f3f7; padding-top: 0px; box-sizing: border-box; }
.cart_widget { background-color: #fff; padding: 15px; border: 1px solid rgba(203,213,224,var(--border-opacity))!important; border-radius: 6px; min-height: 200px;
 font-size: 14px;
}
   </style>

   <div id="wrapper" class="wrapper bg-ash">
    <jsp:include page="../wayupartyMasterHeader.jsp" />
        <!-- Page Area Start Here -->
        <div class="dashboard-page-one">
          <jsp:include page="../wayupartyMasterSideNav.jsp" />
            <div class="dashboard-content-one">
                <div class="breadcrumbs-area">
	                    <h3>My Orders</h3>
	                    <ul>
	                        <li>
	                            <a href="${Wayuparty_appUrl}/dashboard">Home</a>
	                        </li>
                        
	                        <li>My Orders</li>
	                    </ul>
	             </div>
	             
            <div class="card height-auto">    
                <div class="card ui-tab-card">
                    <div class="card-body">
                    <div id="ordersLoaderDiv"></div>
                    
						 <div class="row">
						    <div class="col-sm-12 col-md-12">
						    <h4>Orders</h4>
						    <div class="cart_widget">
								<div id="ordersListDiv"></div>
						    </div>
						  </div>
						  </div>
                  </div>
                </div>
              </div>
              <jsp:include page="userOrderDetails.jsp" />
              <jsp:include page="rescheduleOrderDetails.jsp" />
              <jsp:include page="cancelOrder.jsp" />
              <jsp:include page="placeOrderRating.jsp" />
            </div>
       </div>
       <jsp:include page="../wayupartyMasterFooter.jsp" />
 </div>
 
 <script type="text/javascript">
		window.onload = function () {
			getOrdersList();
		 };
 </script>
 
 <script>
 function getOrdersList(){
	 var loginUserUUID = '${loginUserUUId}';
	 var appUrl ='${appUrl}';
	 var formData = new FormData();
	 formData.append("userUUID", loginUserUUID);
	 
	 $("#ordersLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	 $.ajax({
		    data: formData,
 	        contentType: false,
 	        processData: false,
	    	type: "POST",
	    	 url: appUrl+"/getUserOrdersList?${_csrf.parameterName}=${_csrf.token}", 
	        success: function(resultData) {
	   			
	        	 var result = "";
	        	 if(resultData.data !== null && resultData.data.length > 0){
		        	for (var i=0; i<resultData.data.length; i++)
	   				{ 
		        		 var opt = resultData.data[i];
		        		 var currencyType = getCurrency(opt.currency);
		        		 if(opt.isUserRated == 'N'){
		        			 result = result +'<a  href="#" data-toggle="modal" data-target="#rating-modal" onclick="placeOrderRating(\''+opt.placeOrderCode+'\',);"><i class="fas fa-plus text-dark-pastel-green"></i>&nbsp;Add Rating</a>';
		        		 }else{
		        			 result = result +'<ul>';
			        		 for(var j=0; j<5; j++){
			        			 if(j < opt.rating){
			        				 result = result +'<li class="fa fa-star textStarFilling"></li>';
			        			 }else{
			        				 result = result +'<li class="fa fa-star text-muted"></li>';
			        			 }
			        		 }
			        		 result = result +'</ul>'; 
		        		 }
		        		 
		        		 result = result +'<a href="#" data-toggle="modal" data-target="#right-slide-modal" onclick="getUserOrderDetails(\''+opt.orderItems+'\',\''+opt.orderRates+'\',\''+opt.totalAmount+'\',\''+opt.orderUUIDs+'\',\''+currencyType+'\',\''+opt.orderDateStatus+'\',\''+opt.orderItemsCanceled+'\',\''+opt.orderItemsReschedule+'\',\''+opt.orderStatus+'\',\''+opt.userArrivedStatus+'\')">';
		        		 result = result +'<div class="clearfix row cart_widget_inner">';
		        		 result = result +'<div class="col-sm-12 col-md-10">';
		        		 result = result +'<div class="row">';
		        		 result = result +'<div class="col-sm-12 col-md-8 cart_margn_top">';
		        		 result = result +'<p>'+opt.clubName+'</p>';
		        		 result = result +'<p class="small text-muted">'+opt.clubLocation+'</p>';
		        		 result = result +'<p>'+opt.orderItems+'</p>';
		        		 result = result +'<p>'+currencyType+' '+opt.totalAmount+'</p>';
		        		 result = result +'</div>';
		        		 
		        		 result = result +'<div class="col-sm-12 col-md-4">';
		        		 result = result +'<div class="cart_margn_top">';
		        		 result = result +'<p>Order date on '+opt.orderDate+'</p>';
		        		 
	        			 result = result +'<div class="qr_code_img qr_margn_top">';
						 result = result +'<img src="'+opt.qrCode+'" onerror="predefineQRImage(this);" class="img-responsive" />';
						 result = result +'</div>';
		        		
		        		 result = result +'</div>';
		        		 result = result +'</div>';
	                    
		        		 result = result +'</div>';
		        		 result = result +'</div>';
		        		 result = result +'</div>';
		        		 result = result +'</a>';
	   				}
		        	
		        	 $("#ordersListDiv").empty();  
		   	    	 $("#ordersListDiv").append(result);
		   	    	 $("#ordersLoaderDiv").removeAttr("style");
	        	 }else{
	        		 result = result+'<div class="noRecords-dashboard-portlets"><img src="/resources/img/no_order_icon.png" alt=""/></td>';
					 $("#ordersListDiv").empty();  
		   	    	 $("#ordersListDiv").append(result);
	        		 $("#ordersLoaderDiv").removeAttr("style");
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
 