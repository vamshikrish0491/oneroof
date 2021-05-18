<link rel="stylesheet" href="/resources/css/ratings-style.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
 <div class="modal-box">
       <div class="modal right-slide-modal fade sidebar-align" id="right-slide-modal" tabindex="-1"
                                    role="dialog" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content mt-5">
                                        <div id="vendorRatingsLoaderDiv"></div>
                                            <div class="modal-header">
                                                <h5 class="modal-title">Rating</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                               
											   <div class="ratingsContainer">
											      <div class="star-widget">
											        <input type="radio" name="rate" id="rate-5">
											        <label for="rate-5" class="fas fa-star" onclick="getStarRating('5');"></label>
											        <input type="radio" name="rate" id="rate-4">
											        <label for="rate-4" class="fas fa-star" onclick="getStarRating('4');"></label>
											        <input type="radio" name="rate" id="rate-3">
											        <label for="rate-3" class="fas fa-star" onclick="getStarRating('3');"></label>
											        <input type="radio" name="rate" id="rate-2">
											        <label for="rate-2" class="fas fa-star" onclick="getStarRating('2');"></label>
											        <input type="radio" name="rate" id="rate-1">
											        <label for="rate-1" class="fas fa-star" onclick="getStarRating('1');"></label>
											         <form class="new-added-form" id="add_vendor_rating" name="add_vendor_rating" >
											          <header></header>
											          <input type="hidden" name="rating" id="starRating"/>
											          <div class="textarea">
											            <textarea cols="30"  placeholder="Describe your experience.." id="ratingDescription" name="ratingDescription" field-name="Description" data-validation="required"></textarea>
											          </div>
											          <div class="btn">
											            <button type="button" onclick="saveVendorRating()">Post</button>
											          </div>
											        </form>
											      </div>
											    </div>
   
                                            <div class="modal-footer">
                                               <button type="button" class="footer-btn bg-gradient-gplus" data-dismiss="modal"><i class="fas fa-times mg-l-10"></i>&nbsp;Close</button>
                                            </div>
                                        </div>
                                    </div>
       </div>
       </div>
 
 
 <script>
 function getStarRating(starValue){
	 $('#starRating').val(starValue);
 }
 </script>
  
  
<script>
 function saveVendorRating(){
	   if ($('#add_vendor_rating').validate(false, validationSettings)){
		   $("#vendorRatingsLoaderDiv").attr('style','position:absolute; width:100%; height:100%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
           var appUrl = "${appUrl}";
           var formData = getWayupayFormData("add_vendor_rating");
           var vendorUUID = '${vendorUUID}';
           var userUUID = '${Wayuparty_loginUserUUId}';
           formData.append("vendorUUID", vendorUUID);
           formData.append("userUUID", userUUID);
           
             $.ajax({
				 type : "POST",
				 data: formData,
	    	     processData: false,
	    	     contentType:false,
	        	    	 url: appUrl+"/saveVendorRatings?${_csrf.parameterName}=${_csrf.token}", 
				        success : function(result) {
				        	
				        	 if (result && result.response === "INVALID_DATA") {
				        		 $("#vendorRatingsLoaderDiv").removeAttr("style");
				    		   }
				        	else if (result.response === "AWKWARD") {
				        		   $("#vendorRatingsLoaderDiv").removeAttr("style");
				    		        location.href = "/errorPage";
				    		   }  else if (result.response === "SUCCESS") {     
							 	     location.replace(appUrl+"/vendorInfo?vendorUUID="+vendorUUID);
			                       }
				        	
				        		
				},
		});  
           return true;
	   }else{
			return false;
		  } 
 }
 
 $('body').on('blur', '#add_vendor_rating', function() {
		$("#add_vendor_rating").showHelpOnFocus();
		$("#add_vendor_rating").validateOnBlur(false, validationSettings);  
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
   
  