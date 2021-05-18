<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="preloader"></div>

<div id="wrapper" class="wrapper bg-ash">
	<jsp:include page="../wayupartyMasterHeader.jsp" />
	<!-- Page Area Start Here -->
	<div class="dashboard-page-one">
		<jsp:include page="../wayupartyMasterSideNav.jsp" />
		<div class="dashboard-content-one">
			<div class="breadcrumbs-area">
				<h3>Add Special Package</h3>
				<ul>
					<li><a href="${Wayuparty_appUrl}/dashboard">Home</a></li>

					<li>Add Special Package</li>
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
				<div id="vendorDetailsLoadingDiv"></div>
				<div class="card-body">
					<div class="heading-layout1">
						<div class="item-title">
							<h3>Special Package Details</h3>
						</div>
					</div>

					<form class="new-added-form" id="add_special_package_form"
						name="add_special_package_form">
						<div class="row">

							<div class="col-xl-4 col-lg-6 col-12 form-group"
								id="serviceTypeDiv">
								<label>Service Type<span class="text-danger">&nbsp;*</span></label>
								<select class="select2" name="serviceType" id="serviceType"
									field-name="Service Type">
									<option value="">Please Select</option>
									<option value="deals">Deals</option>
									<option value="events">Events</option>
								</select>
							</div>

							<div class="col-xl-4 col-lg-6 col-12 form-group">
								<label>Start Date<span class="text-danger">&nbsp;*</span></label>
								<input type="text" placeholder="dd/mm/yyyy"
									class="form-control start-date-datepicker"
									data-position='bottom right' name="startDate" id="startDate"
									data-validation="required" field-name="Start Date"> <i
									class="far fa-calendar-alt"></i>
							</div>

							<div class="col-xl-4 col-lg-6 col-12 form-group">
								<label>End Date<span class="text-danger">&nbsp;*</span></label>
								<input type="text" placeholder="dd/mm/yyyy"
									class="form-control end-date-datepicker"
									data-position='bottom right' name="endDate" id="endDate"
									data-validation="required" field-name="End Date"> <i
									class="far fa-calendar-alt"></i>
							</div>

							<!-- <div class="col-xl-4 col-lg-6 col-12 form-group">
								<label>Upload Web Banner</label> <input type="file"
									class="form-control-file" id="uploadImageBtn"
									field-name="Upload Image" data-validation-optional="true"
									data-validation="validateImg"> <input type="text"
									class="form-control" readonly id="uploadImageFile"
									style="display: none;" name="docFile">
							</div>

							<div class="col-xl-4 col-lg-6 col-12 form-group">
								<label>Upload Mobile Banner</label> <input type="file"
									class="form-control-file" id="uploadMobileBtn"
									field-name="Upload Image" data-validation-optional="true"
									data-validation="validateImg"> <input type="text"
									class="form-control" readonly id="uploadMobileFile"
									style="display: none;" name="docFile">
							</div> -->


							<div class="col-xl-4 col-lg-6 col-12 form-group"
								id="displayImageDiv">
								<label>Upload Web Banner<span class="text-danger">&nbsp;*</span></label>
								<input type="file" class="form-control-file"
									id="uploadDisplayImageBtn" field-name="Upload Image"
									data-validation="required validateImg"
									field-name="Display Image"> <input type="text"
									class="form-control" readonly id="uploadDisplayImageFile"
									style="display: none;" name="docFile">
							</div>

							<div class="col-xl-4 col-lg-6 col-12 form-group"
								id="savedDisplayImg" style="display: none">
								<label>Upload Web Banner</label>
								<div class="events-image" id="uploadImageBtn">
									<div id="savedImage"></div>
								</div>
								<div class="clearUpload clearUpload-profile-position"
									title="Delete Uploaded Photo" onclick="deleteDisplayImage();"></div>
							</div>

							<div class="col-xl-4 col-lg-6 col-12 form-group"
								id="bannerImageDiv">
								<label>Upload Mobile Banner<span class="text-danger">&nbsp;*</span></label>
								<input type="file" class="form-control-file"
									id="uploadBannerImageBtn" field-name="Upload Image"
									data-validation="required validateImg"
									field-name="Banner Image"> <input type="text"
									class="form-control" readonly id="uploadBannerImageFile"
									style="display: none;" name="docFile">
							</div>

							<div class="col-xl-4 col-lg-6 col-12 form-group"
								id="savedBannerImg" style="display: none">
								<label>Upload Mobile Banner</label>
								<div class="events-image" id="bannerImageBtn">
									<div id="savedBannerImage"></div>
								</div>
								<div class="clearUpload clearUpload-profile-position"
									title="Delete Uploaded Photo" onclick="deleteBannerImage();"></div>
							</div>


						</div>
						<br />


						<div class="row">
							<div class="col-12 ui-btn-wrap">
								<ul>
									<li><button type="button"
											class="btn-fill-lg font-normal text-light gradient-pastel-green"
											id="save_button" onclick="saveSpecialPackage()">
											<i class="fas fa-save mg-l-10"></i>&nbsp;Save
										</button></li>
									<li><button type="button"
											class="btn-fill-lg font-normal text-light btn-gradient-yellow"
											onclick="javascript:window.location.href = '${Wayuparty_appUrl}/vendor'">
											<i class="fas fa-undo mg-l-10"></i>&nbsp;Reset
										</button></li>
									<li><button type="button"
											class="btn-fill-lg font-normal text-light bg-gradient-gplus"
											onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'">
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
	<jsp:include page="../wayupartyMasterFooter.jsp" />
</div>


<script type="text/javascript">
		window.onload = function () {
			googleApiLocation('vendorLocation','country');
		 };
 </script>

<script>
   
   document.getElementById("uploadImageBtn").onchange = function () {
       $('#uploadImageFile').val($(this).val().replace(/C:\\fakepath\\/i, ''));
   };
   $(function () {
       $("#uploadImageBtn").change(function () {
           fileUpload("uploadImageBtn", "vendor_banner_images", "add_special_package_form",0,0);
           $(".clearUpload").show();
       });
   });
 </script>

<script>
   
   document.getElementById("uploadMobileBtn").onchange = function () {
       $('#uploadMobileFile').val($(this).val().replace(/C:\\fakepath\\/i, ''));
   };
   $(function () {
       $("#uploadMobileBtn").change(function () {
           fileUpload("uploadMobileBtn", "vendor_mobile_banner_images", "add_special_package_form",0,0);
           $(".clearUpload").show();
       });
   });
 </script>


<script>
 function saveSpecialPackage(){

	   if ($('#add_special_package_form').validate(false, validationSettings)){
		   $("#vendorDetailsLoadingDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
		   var vendorUUID = '${vendorUUID}';
           var formData = getWayupayFormData("add_special_package_form");
	        		
            $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveVendorSpecialPackage?${_csrf.parameterName}=${_csrf.token}&vendorUUID="+vendorUUID, 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#vendorDetailsLoadingDiv").removeAttr("style");
				    		     $("#invalidMsgDiv").removeAttr("style");
					    		 $("#invalidMsgDiv").css({ display: "block" });
						         $("#invalidMsg").html("Attention! You had missed some data.");
						         $("#invalidMsgDiv").fadeIn(500);
						 	     $('#invalidMsgDiv').delay(5000).fadeOut('slow'); 
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#vendorDetailsLoadingDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							         $("#successMsgDiv").removeAttr("style");
						    		 $("#successMsgDiv").css({ display: "block" });
							         $("#successMsg").html("Well done! You successfully added new vendor.");
							         $("#successMsgDiv").fadeIn(200);
							 	     $('#successMsgDiv').delay(2000).fadeOut('slow');
							 	     location.replace(appUrl+"/dashboard");
			                       }
				        	
				        		
				},
		});   
           return true;
	   }else{
			return false;
		  } 
 }
 
 $('body').on('blur', '#add_special_package_form', function() {
		$("#add_special_package_form").showHelpOnFocus();
		$("#add_special_package_form").validateOnBlur(false, validationSettings);  
   });
 </script>

<script>
function googleApiLocation(locationId) {
	initialize(locationId);
}
function initialize(locationId) {
    	autocomplete = new google.maps.places.Autocomplete((document.getElementById(locationId)), {
    		types: ['geocode']/* ,
	        componentRestrictions: {
	        	country: ["IN","US","AU","SG","GB","FR","TH"]
	        } */
      
        });
  google.maps.event.addListener(autocomplete, 'place_changed', function() {
  place = autocomplete.getPlace();
  var address = {};
  for (var i = 0; i < place.address_components.length; i++) {
    var addressType = place.address_components[i].types[0];
    switch (addressType) {
    case "route":
   	 address.name=place.address_components[i].long_name;
   	 break;
     case "locality":
       address.city = place.address_components[i].long_name;
       break;
     case "administrative_area_level_1":
       address.state = place.address_components[i].long_name;
       break;
     case "country":
       address.country = place.address_components[i].long_name;
       break;
     case "postal_code":
       address.zipcode = place.address_components[i].long_name;
        
    }
    
  }
  
 var latitude = place.geometry.location.lat();
 var longitude = place.geometry.location.lng();
  
  $("#country").val('');
  $("#state").val('');
  $("#city").val('');
  $("#pincode").val('');
  
  $("#country").val(address.country);
  $("#state").val(address.state);
  $("#city").val(address.city);
  $("#pincode").val(address.zipcode);
  $("#latitude").val(latitude);
  $("#longitude").val(longitude);
  });
}
</script>

<script>
function clearVendorLocation(){
	  $("#vendorLocation").val('');
	  $("#country").val('');
	  $("#state").val('');
	  $("#city").val('');
	  $("#pincode").val('');
}
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
      	getEndDateDatePicker();
  	 }
    });
    
    function getEndDateDatePicker(){
    	 var endDatepicker = $('#endDate').datepicker().data('datepicker');
    	 endDatepicker.clear();
    	 var startDate = $('#startDate').val();
    	 var dateParts = startDate.split('/');
    	 var minimumdate = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
        $('.end-date-datepicker').datepicker({
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
            minDate: minimumdate,
            onSelect : function(selected) {
              validateVendorService();
          	 }
            
        });
    }
    
    function getSavedEndDateDatePicker(){
     	 var startDate = $('#startDate').val();
     	 var dateParts = startDate.split('/');
     	 var minimumdate = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
         $('.end-date-datepicker').datepicker({
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
             minDate: minimumdate,
             onSelect : function(selected) {
           	  validateVendorService();
           	 }
             
         });
     }
     
     
     
       function deleteDisplayImage(){
			document.getElementById("displayImageDiv").style.display="block";
			document.getElementById("savedDisplayImg").style.display="none";
			$("#uploadDisplayImageBtn").removeAttr('data-validation-optional');
   		    $("#uploadDisplayImageBtn").attr('data-validation','required validateImg');
		}
	   
	   
	   function deleteBannerImage(){
			document.getElementById("bannerImageDiv").style.display="block";
			document.getElementById("savedBannerImg").style.display="none";
			$("#uploadBannerImageBtn").removeAttr('data-validation-optional');
  		    $("#uploadBannerImageBtn").attr('data-validation','required validateImg');
		}
    
    </script>

<script>
    var _URL = window.URL;
    $("#uploadDisplayImageBtn").change(function (e) {
        var file, img;
        if ((file = this.files[0])) {
            img = new Image();
            img.onload = function () {
            	var width = this.width;
            	var height = this.height;
            	var displayImageSize = parseInt(height) - parseInt(width);
            	if(displayImageSize <= 410){
            		$('input#uploadDisplayImageFile').removeAttr( "class record-exist record-exist-errormsg");
					$('#displayImageDiv').find('p.jquery_form_error_message').remove();
					
					   document.getElementById("uploadDisplayImageBtn").onchange = function () {
					       $('#uploadDisplayImageFile').val($(this).val().replace(/C:\\fakepath\\/i, ''));
					   };
					   $(function () {
					    	   var vendorId = '${vendorId}';
					           fileUpload("uploadDisplayImageBtn", "vendor_banner_images", "add_special_package_form",vendorId,0);
					           $(".clearUpload").show();
					   });
            	}else{
					    $('#displayImageDiv').find('p.jquery_form_error_message').remove(); 
			    		$('input#uploadDisplayImageFile').attr('class','error form-control');
			    		$("input#uploadDisplayImageFile").after("<p class='jquery_form_error_message'>Size Limt Width 870 & Height 1280</p>");
						$('input#uploadDisplayImageFile').attr('record-exist','yes');
						$('input#uploadDisplayImageFile').attr('record-exist-errorMsg',' Size Limt Width 870 & Height 1280'); 
            	}
                
            };
            img.src = _URL.createObjectURL(file);
        }
    });
    
    
    $("#uploadBannerImageBtn").change(function (e) {
        var file, img;
        if ((file = this.files[0])) {
            img = new Image();
            img.onload = function () {
            	if(this.width >= 375 && this.height >= 210){
            		$('input#uploadBannerImageFile').removeAttr( "class record-exist record-exist-errormsg");
					$('#bannerImageDiv').find('p.jquery_form_error_message').remove();
					
					   document.getElementById("uploadBannerImageBtn").onchange = function () {
					       $('#uploadBannerImageFile').val($(this).val().replace(/C:\\fakepath\\/i, ''));
					   };
					   $(function () {
					    	   var vendorId = '${vendorId}';
					           fileUpload("uploadBannerImageBtn", "vendor_banner_images", "add_special_package_form",vendorId,1);
					           $(".clearUpload").show();
					   });
            	}else{
            		    $('#bannerImageDiv').find('p.jquery_form_error_message').remove(); 
			    		$('input#uploadBannerImageFile').attr('class','error form-control');
			    		$("input#uploadBannerImageFile").after("<p class='jquery_form_error_message'>Size Limt Width 375 & Height > 210</p>");
						$('input#uploadBannerImageFile').attr('record-exist','yes');
						$('input#uploadBannerImageFile').attr('record-exist-errorMsg',' Size Limt Width 375 & Height > 210');
            	}
                
            };
            img.src = _URL.createObjectURL(file);
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
