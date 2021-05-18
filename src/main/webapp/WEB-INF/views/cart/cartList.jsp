<style>
	.mt-10px{
		margin-top:10px;
	}
	.mt-5px{
		margin-top:5px;
	}

   body { background-color: #f2f3f7; padding-top: 0px; box-sizing: border-box; }
.cart_widget { background-color: #fff; padding: 15px; border: 1px solid rgba(203,213,224,var(--border-opacity))!important; border-radius: 6px; min-height: 200px;
 font-size: 14px;
}
</style>
<div class="container">

 <div class="alert icon-alart bg-light-green2" id="successMsgDiv" role="alert" style="display: none;">
      <i class="far fa-hand-point-right bg-light-green3"></i>
     <span id="successMsg" style="color: white;"></span> 
 </div>
 
<div class="card height-auto">
<div class="card-body">
  <div class="row">
    
    <div class="col-sm-12 col-md-8">
    <div class="cart_widget">
      <h4>My Cart<span  id="mycartDiv"></span></h4>
      <div id="cartListDiv"></div>
    </div>
  </div>

    <div class="col-sm-12 col-md-4" id="priceDetailsDiv">
        <div class="cart_widget">
        <h4 class="text-muted text-uppercase">Price Details</h4>        
            <div class="clearfix cart_pricelist">
                    <ul class="list-unstyled">
                        <li>Price<span id="cartItemsDiv"></span><span class="pull-right" id="totalPrice"></span></li>
                        
                        <li><span id="couponsAppliedDiv"></span></li>
                       
                        <li><strong>Total Amount</strong> <span class="pull-right" id="totalAmount"><i class="fa fa-rupee text-muted"></i></span></li>
                    </ul>
                    <input type="hidden" id="cartAmount"></input>
                    <input type="hidden" id="cartCurrency"></input>
                    <input type="hidden" id="discountAmount"></input>
                    
            </div>
            <div class="row mt-10px"> 
            
	             <div class="col-xl-12 col-lg-12 col-12 form-group">
	 	            <input type="text" placeholder="Enter Promo/Coupon Code" class="form-control" name="coupon" id="coupon">
	 	             <p class="invalid" id="couponCodeMessage"></p>
	             </div>
	             
    	    </div>
    	    
    	    <div class="row mt-10px"> 
    	    
	    	     <div class="col-xl-6 col-lg-6 col-6 form-group mt-5px">
		    	      <button type="button" class="btn-fill-sm radius-30 text-light gradient-dodger-blue" data-toggle="modal" data-target="#coupons-modal" style="padding: 6px 12px;" onclick="getCouponsList('${couponList}')">&nbsp;Get Deal</button>
		    	 </div>
		    	 
		    	 <div class="col-xl-6 col-lg-6 col-6 form-group mt-5px">
		    	      <button type="button" class="btn-fill-sm radius-30 text-light gradient-orange-peel" style="padding: 8px 8px;font-size: 12px;" onclick="applyCoupon()">&nbsp;Apply Coupon</button>
		    	 </div>
	    	         
	    	</div>
    	    
    	</div>
    </div>
           <jsp:include page="../user/couponsListDetails.jsp" />

  <div class="clearfix col-sm-12 col-md-8" id="placeOrderDiv">
    <div class="modal-footer">
          <button type="button" class="btn-fill-lmd radius-30 text-light gradient-pastel-green" onclick="placeOrder();"><i class="fa fa-shopping-bag mg-l-10"></i>&nbsp;Place Order</button>
      </div>
  </div>

  </div>
</div>
</div>
</div>

<button id="rzp-button1" style="display: none">Pay</button>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
								            
<style>
.pull-right {
    float: right!important;
}
</style>
 
<script>

var inputBox = document.getElementById('coupon');

var length = 0;
inputBox.onkeyup = function(){
    $("#printchatbox").html(inputBox.value.length);
    length = inputBox.value.length;
}

var cartItems = [];
function getCartList(){
	
	 $("#cartCount").html('${cartCount}');
	   var appUrl ='${appUrl}';
	   $("#cartListLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "GET",
			    url: appUrl+"/getCartList?${_csrf.parameterName}=${_csrf.token}",
			     success :function(resultData) {
					 
			    	 var result = "";
			    	 if(resultData.data.length > 0){
						 
						 $("#cartItemsDiv").html('('+resultData.data.length+ ' Items)');
						 $("#mycartDiv").html('('+resultData.data.length+')');
						 
						 var totalAmount = '';
						 var currencyCode = '';
						 
						 for (var i=0; i<resultData.data.length; i++)
			   				{ 
							  var opt = resultData.data[i];
							  var orderAmount = '';
							  if(opt.orderAmount != 0){
								  orderAmount = opt.orderAmount;
							  }else{
								  orderAmount = opt.totalAmount;
							  }
							  cartItems.push(opt.cartUUID);
							  $("#cartCurrency").val(opt.currency);
							  currencyCode = getCurrency(opt.currency);
							  result = result +'<div class="clearfix row cart_widget_inner">';
							  result = result +'<div class="col-sm-12 col-md-2 text-center">';
							  result = result +'<div class="item_img cart_margn_top">';
							  result = result +'<img src="'+opt.serviceImage+'" class="img-responsive" />';
							  result = result +'</div>';
							  result = result +'</div>';
							  result = result +'<div class="col-sm-12 col-md-10">';
							  result = result +'<div class="row">';
							  result = result +'<div class="col-sm-12 col-md-8 cart_margn_top">';
							  result = result +'<p>'+opt.serviceName+'</p>';
							  result = result +'<p class="text-muted">Price : '+currencyCode+' '+orderAmount+'</p>';
							  result = result +'<p class="text-muted">Quantity : '+opt.quantity+'</p>';
							  result = result +'<p class="text-muted">Amount : '+currencyCode+' '+opt.totalAmount+'</p>';
							  result = result +'</div>';
							  result = result +'<div class="col-sm-12 col-md-4">';
							  result = result +'<div class="cart_margn_top">';
							  result = result +'<p>'+opt.serviceOrderDate+'</p>';
							  result = result +'<p>'+opt.timeSlot+'</p>';
							  result = result +'<p class="small text-danger">No Order Replacement</p>';
							  result = result +'<a href="javascript:void(0)" onclick="removeCartItem(\''+opt.cartUUID+'\')" class="text-uppercase btn">Remove</a>';
							  result = result +'</div>';
							  result = result +'</div>';
							  result = result +'</div>';
							  result = result +'</div>';
							  result = result +'</div>';
							  
							  totalAmount = Number(opt.totalAmount) + Number(totalAmount);
							
			   				} 
			        	  
			        	  $("#cartListDiv").empty();  
    		   	    	  $("#cartListDiv").append(result);
    		   	    	  
    		   	    	 $("#totalPrice").html(currencyCode+"&nbsp;"+totalAmount);
						 $("#totalAmount").html(currencyCode+"&nbsp;"+totalAmount);
						 $("#cartAmount").val(totalAmount);
						 
					 }else{
						 document.getElementById("priceDetailsDiv").style.display="none";
						 document.getElementById("placeOrderDiv").style.display="none";
						 result = result+'<div class="noRecords-dashboard-portlets"><img src="/resources/img/empty-cart-icon.png" alt=""/></td>';
						 $("#cartListDiv").empty();  
   		   	    	     $("#cartListDiv").append(result);
					 }
					 $("#cartListLoadingDiv").removeAttr("style");
					},
		});
	
}



function placeOrder(){
	 var appUrl ='${appUrl}';
	var currency = $("#cartCurrency").val();
	var cartAmount = $("#cartAmount").val();
	
	var formData = new FormData();
	formData.append("currency", currency);
	formData.append("cartAmount", cartAmount);
	
	 $("#cartListLoadingDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
		    data: formData,
	   	    contentType: false,
	   	    processData: false,
				type: "POST",
			    url: appUrl+"/orders",
			     success :function(result) {
			    	 
			    	   if (result.response === "AWKWARD") {
		        		   $("#cartListLoadingDiv").removeAttr("style");
		    		        location.href = "/errorPage";
		    		   }else if (result.response === "SUCCESS") {   
		    			   
		    			   $("#cartListLoadingDiv").removeAttr("style");
		    			   
		    			   var options = {
		    					    "key": '${razorpayKeyId}', 
		    					    "name": "Wayuparty",
		    					   // "image": "/resources/img/logo.png",
		    					    "order_id": result.object.orderId, 
		    					    "handler": function (response){
		    					        placeCartOrder(response.razorpay_payment_id,response.razorpay_order_id,response.razorpay_signature);
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
		    					        
		    						/* alert(response.error.code);
		    					        alert(response.error.description);
		    					        alert(response.error.source);
		    					        alert(response.error.step);
		    					        alert(response.error.reason);
		    					        alert(response.error.metadata.order_id);
		    					        alert(response.error.metadata.payment_id); */
		    					});
		    					
		    					rzp.open();
					        
	                       }
					},
		});
}



function placeCartOrder(paymentId, orderId, signature){
	 var appUrl ='${appUrl}';
	 $("#cartListLoadingDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
		var userUUID = '${Wayuparty_loginUserUUId}';
		var formData = new FormData();
		formData.append("cartItems", cartItems);
		formData.append("userUUID", userUUID);
		formData.append("paymentId", paymentId);
		formData.append("orderId", orderId);
		formData.append("signature", signature);
	   
	   $.ajax({
		   data: formData,
   	    contentType: false,
   	    processData: false,
			  type: "POST",
			    url: appUrl+"/placeOrder",
			     success :function(result) {
			    	 
			    	   if (result.response === "AWKWARD") {
		        		   $("#cartListLoadingDiv").removeAttr("style");
		    		        location.href = "/errorPage";
		    		   }else if (result.response === "SUCCESS") {     
					         $("#successMsgDiv").removeAttr("style");
				    		 $("#successMsgDiv").css({ display: "block" });
					         $("#successMsg").html("Well done! You successfully placed Order.");
					         $("#successMsgDiv").fadeIn(200);
					 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
					 	     location.replace(appUrl+"/cart");
	                       }
			    	
					 
					},
		});
} 


function removeCartItem(cartUUID){
	
	 var appUrl ='${appUrl}';
	   $("#cartListLoadingDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:10px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	   $.ajax({
			  type: "POST",
			    url: appUrl+"/removeCartItem?cartUUID="+cartUUID,
			     success :function(result) {
			    	 
			    	   if (result.response === "AWKWARD") {
		        		   $("#cartListLoadingDiv").removeAttr("style");
		    		        location.href = "/errorPage";
		    		   }else if (result.response === "SUCCESS") {     
					         $("#successMsgDiv").removeAttr("style");
				    		 $("#successMsgDiv").css({ display: "block" });
					         $("#successMsg").html("Well done! You successfully removed items from cart.");
					         $("#successMsgDiv").fadeIn(200);
					 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
					 	     location.replace(appUrl+"/cart");
					 	     $("#cartListLoadingDiv").removeAttr("style");
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
<script>
function applyCoupon(){
	
	var oldDiscountAmount = $("#discountAmount").val();
	var totalAmount = $("#cartAmount").val();
	
	if(Number(oldDiscountAmount) > 0){
		totalAmount = Number(totalAmount) + Number(oldDiscountAmount);
	}
	
	var couponCode = document.getElementById("coupon").value;

	if(isCouponAvailable(couponCode)){
	
		var discountAmount = getCouponDiscountAmount(couponCode,totalAmount);
		
		var totalAmount = totalAmount - discountAmount;
		$("#totalAmount").html(totalAmount);
    	$("#cartAmount").val(totalAmount);
		$("#discountAmount").val(discountAmount);
		
		var result = '';
		result = result + 'Discount: <strong>'+couponCode+'</strong>';
		result = result + '<span class="pull-right">'+discountAmount+'</span>';
		
		$("#couponCodeMessage").empty();  
		document.getElementById("coupon").value = '';
		$("#couponsAppliedDiv").empty();  
    	$("#couponsAppliedDiv").append(result);
		
	}else{
	
		$("#couponCodeMessage").empty();  
   		$("#couponCodeMessage").html('Coupon Code is invalid.');
   			
	}
	
}

</script>

<script>


	
var couponsList;

function getCouponList(){
		var appUrl ='${appUrl}';
		$("#couponsListLoadingDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
		$.ajax({
			type: "GET",
			url: appUrl+"/ws/getCouponList",
			success :function(response) {
			
					couponsList = response.data;
			},
		}); 
}
	
	
		
function isCouponAvailable(couponCode){
	var returnValue = false;
	
	if(couponCode == ''){
		
		return returnValue;
			
	}else{

		for(var i =0; i < couponsList.length;i++){
			
			if(couponsList[i].couponCode == couponCode){
				
				returnValue = true;
				
			}
		}
	}
		
	return returnValue;
}

function getCouponDiscountAmount(couponCode,totalAmount){

	var discountAmount = 0;

	for(var i =0; i < couponsList.length; i++){
				
			if(couponsList[i].couponCode == couponCode){
				
				if(couponsList[i].discountType == "PERCENTAGE"){
				
					discountAmount = calculatePercentageDiscount(totalAmount, couponsList[i].couponValue);
					
				}else if(couponsList[i].discountType == "RUPEES"){
				
					discountAmount = calculateRupeesDiscount(totalAmount, couponsList[i].couponValue);
				
				}
				
			}
	}
	
	return discountAmount;

}

function calculatePercentageDiscount(totalAmount, discountValue){

	var numVal1 = Number(totalAmount);
	var numVal2 = Number(discountValue) / 100;
	var totalValue = numVal1 - (numVal1 * numVal2);
    var discountAmount = totalAmount - totalValue.toFixed(2);
	return discountAmount;
	
}

function calculateRupeesDiscount(totalAmount, discountValue){
	
	var discountAmount = discountValue;
	
	if(discountValue > totalAmount){
	
		discountAmount = totalAmount;
	
	}
            
	return discountAmount;
	
}

</script>
