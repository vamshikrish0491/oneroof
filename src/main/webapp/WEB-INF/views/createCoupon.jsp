<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="preloader"></div>

<div id="wrapper" class="wrapper bg-ash">
	<jsp:include page="wayupartyMasterHeader.jsp" />
	<!-- Page Area Start Here -->
	<div class="dashboard-page-one">
		<jsp:include page="wayupartyMasterSideNav.jsp" />
		<div class="dashboard-content-one">
			<div class="breadcrumbs-area">
				<h3>Add Coupon</h3>
				<ul>
					<li><a href="${Wayuparty_appUrl}/users">Users</a></li>

					<li>Add Coupon</li>
				</ul>
			</div>

			<div class="alert icon-alart bg-light-green2" id="successMsgDiv"
				role="alert" style="display: none;">
				<i class="far fa-hand-point-right bg-light-green3"></i> <span
					id="successMsg" style="color: white;"></span>
			</div>

			<div class="alert icon-alart bg-pink2" role="alert"
				id="invalidMsgDiv" style="display: none;">
				<i class="fas fa-times bg-pink3"></i> <span id="invalidMsg"
					style="color: white;"></span>
			</div>


			<div class="card height-auto">
				<div id="couponLoadingDiv"></div>
				<div class="card-body">
					<div class="heading-layout1">
						<div class="item-title">
							<h3>Coupon Details</h3>
						</div>
					</div>

					<form class="new-added-form" id="add_coupon_form"
						name="add_coupon_form">
						<div class="row">
							<div class="col-xl-4 col-lg-6 col-12 form-group">
								<label>Coupon Name<span class="text-danger">&nbsp;*</span></label>
								<input type="text" class="form-control" name="couponName" placeholder="Enter coupon name" data-validation="required"
									id="couponName" field-name="Coupon Name">
							</div>
							
							<div class="col-xl-4 col-lg-6 col-12 form-group">
								<label>Coupon Code<span class="text-danger">&nbsp;*</span></label>
								<input type="text" class="form-control" name="couponCode" placeholder="Enter coupon code" data-validation="required"
									id="couponCode" field-name="Coupon Code">
							</div>
							<div class="col-xl-4 col-lg-6 col-12 form-group">
								<label>Display Offer<span class="text-danger">&nbsp;*</span></label>
								<input type="text" class="form-control" name="displayOffer" placeholder="Enter display offer" data-validation="required"
									id="displayOffer" field-name="Display Offer">
							</div>
							<div class="col-xl-4 col-lg-6 col-12 form-group">
								<label>Minimum Order<span class="text-danger">&nbsp;*</span></label>
								<input type="text" class="form-control" name="minimumOrder" placeholder="Enter minimim order" data-validation="required"
									id="minimumOrder" field-name="Minimum Order">
							</div>
							<div class="col-xl-4 col-lg-6 col-12 form-group">
								<label>Coupon Value<span class="text-danger">&nbsp;*</span></label>
								<input type="text" class="form-control" name="couponValue" placeholder="Enter coupon value" data-validation="required"
									id="couponValue" field-name="Coupon Value">
							</div>
							<div class="col-xl-4 col-lg-6 col-12 form-group">
								<label>Discount Type<span class="text-danger">&nbsp;*</span></label>
								<select class="select2" name="discountType" id="discountType" field-name="discountType" data-validation="required">
									<option value="">Please Select</option>
									<option value="PERCENTAGE">Percentage</option>
									<option value="RUPEES">Rupees</option>
								</select>
							</div>
							<div class="col-xl-4 col-lg-6 col-12 form-group">
								<label>Expire Date<span class="text-danger">&nbsp;*</span></label>
								<input type="text" placeholder="dd/mm/yyyy" data-validation="required"
									class="form-control start-date-datepicker"
									data-position='bottom right' name="expireDate" id="expireDate"
									field-name="Expire Date"> <i
									class="far fa-calendar-alt"></i>
							</div>
							<div class="col-xl-4 col-lg-6 col-12 form-group">
								<label>Coupon Applicable<span class="text-danger"></span></label>
								<select class="select2" name="currency" id="currency" field-name="Currency">
									<option value="">Please Select</option>
									<option value="All">All</option>
									<option value="Users">Users</option>
								</select>
							</div>
							<div class="col-xl-4 col-lg-6 col-12 form-group">
								<label>User UUID<span class="text-danger"></span></label>
								<input type="text" class="form-control" name="userUUID" placeholder="Enter User UUID"
									id="userUUID"
									field-name="User UUID">
							</div>
							
							<div class="col-xl-4 col-lg-6 col-12 form-group">
								<label>Usage Limit<span class="text-danger"></span></label>
								<input type="text" class="form-control" name="usageLimit" placeholder="Enter usage limit"
									id="usageLimit"
									field-name="Usage Limit">
							</div>
							

						</div>

<br/>  
						<div class="row">
							<div class="col-12 ui-btn-wrap">
								<ul>
									<li><button type="button"
											class="btn-fill-lg font-normal text-light gradient-pastel-green"
											id="save_button" onclick="saveCoupon()">
											<i class="fas fa-save mg-l-10"></i>&nbsp;Save
										</button></li>
									<li><button type="button"
											class="btn-fill-lg font-normal text-light btn-gradient-yellow"
											onclick="javascript:window.location.href = '${Wayuparty_appUrl}/createCoupon'">
											<i class="fas fa-undo mg-l-10"></i>&nbsp;Reset
										</button></li>
									<li><button type="button"
											class="btn-fill-lg font-normal text-light bg-gradient-gplus"
											onclick="javascript:window.location.href = 'users'">
											<i class="fas fa-times mg-l-10"></i>&nbsp;Cancel
										</button></li>
								</ul>
							</div>

						</div>

					</form>

				</div>
			</div>
		</div>
	</div>
	<jsp:include page="wayupartyMasterFooter.jsp" />
</div>

<script>
 function saveCoupon(){

	   if ($('#add_coupon_form').validate(false, validationSettings)){
		   $("#vendorDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var formData = getWayupayFormData("add_coupon_form");
	        		
            $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveCoupon", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#couponLoadingDiv").removeAttr("style");
				    		     $("#invalidMsgDiv").removeAttr("style");
					    		 $("#invalidMsgDiv").css({ display: "block" });
						         $("#invalidMsg").html("Attention! You had missed some data.");
						         $("#invalidMsgDiv").fadeIn(500);
						 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#couponLoadingDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							         $("#successMsgDiv").removeAttr("style");
						    		 $("#successMsgDiv").css({ display: "block" });
							         $("#successMsg").html("Well done! You successfully added new coupon.");
							         $("#successMsgDiv").fadeIn(200);
							 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
							 	     location.replace(appUrl+"/users");
			                       }
				        	
				        		
				},
		});   
           return true;
	   }else{
			return false;
		  } 
 }
 
 $('body').on('blur', '#add_coupon_form', function() {
		$("#add_coupon_form").showHelpOnFocus();
		$("#add_coupon_form").validateOnBlur(false, validationSettings);  
   });

</script>

<script>
  var dateToday = new Date();
    $('.start-date-datepicker').datepicker({
      language: {
        days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
        daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
        daysMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
        months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
        today: 'Today',
        clear: 'Clear',
        dateFormat: 'dd/mm/yyyy',
        firstDay: 0,
      },
      minDate: dateToday,
      onSelect : function(selected) {
  	 }
    });
    
    
    </script>
    
     <script type="text/javascript">
        var validationSettings = {
            errorMessagePosition : 'element',
            borderColorOnError : '',
			scrollToTopOnError : true,
			dateFormat : 'yyyy/mm/dd' 
            };
</script>
