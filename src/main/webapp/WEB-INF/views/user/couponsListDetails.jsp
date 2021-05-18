<div class="modal-box">
	<div class="modal right-slide-modal fade sidebar-align"
		id="coupons-modal" tabindex="-1" role="dialog" aria-hidden="true">

		<div class="modal-dialog" role="document">
			<div class="modal-content mt-5">
				<div class="modal-header" style="background: darkgrey;">
					<h5 class="modal-title" style="margin-left: 41%;">Coupons</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div id="couponsListDiv"></div>

			</div>
		</div>

	</div>
</div>

<script type="text/javascript">

		window.onload = function () {
			getCouponsList();
		 };
		 
			function getCouponsList(){
				 var appUrl ='${appUrl}';
		    	   $("#couponsListLoadingDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
		    	   $.ajax({
		    			  type: "GET",
		    			    url: appUrl+"/ws/getCouponList",
		    			     success :function(response) {
		    			     
		    			     	var result = "";
		
								var couponsList = response.data;
		    			  
		    					for(var i=0;i< couponsList.length;i++){
			
									var opt = couponsList[i];
									
									result = result +'<div style="margin: 0px 25px 25px 25px;">';
									result = result +'<div style="border-style: solid; border-width: thin; border-color: #a9a9a9; border-radius: 10px;">';
									result = result +'<div class="row" style="margin: 0px;">';
									result = result +'<div class="col-sm-12 col-md-8">';
									result = result +'<span style="color: #0373d4e0;font-weight: 600;">'+opt.couponName+'</span><br>';
									result = result +'<span class="text-muted">Minimum Order: '+opt.minimumOrder+'</span><br>';
									result = result +'<span class="text-muted">Expired Date: '+opt.expireDate+'</span><br>';
									result = result +'<span class="text-muted">Usage Limit:  '+opt.usageLimit+'</span>';
									result = result +'</div>';
									result = result +'<div class="col-sm-12 col-md-4">';
									result = result +'<p style="color: #0373d4e0;font-weight: 600;float: right;">'+opt.displayOffer+'</p>';
									result = result +'</div>';
									result = result +'</div>';
															
									result = result +'<div class="row" style="margin: 0px;">';
									result = result +'<div class="col-sm-12" style="text-align: center;padding-bottom: 15px;">';
									result = result +'Coupon Code:- <span style="color: #ff7200;font-weight: 600;">'+opt.couponCode+'</span><br>';
									result = result +'</div>';
									result = result +'</div>';
									result = result +'</div>';
														
									result = result +'</div>';
								
								}
										
								$("#couponsListLoadingDiv").removeAttr("style");
												
								$("#couponsListDiv").empty();  
								$("#couponsListDiv").append(result);
		    			     },
		    		}); 
			}
			
		 
   </script>
