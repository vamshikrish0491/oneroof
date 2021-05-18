<div class="tab-pane fade" id="cuisine" role="tabpanel">
<div id="vendorCuisineDetailsLoaderDiv"></div>
		<section class="clearfix">
				     <div class="select-role clearfix section-action-btns">
				     
				     <div class="clearfix">
					 <div id="cuisineListDiv"></div>
				     </div>
				     <br/>
                           <div class="ui-btn-wrap">
					             <ul>
					               <li><button type="button" class="btn-fill-lg font-normal text-light gradient-pastel-green" id="save_button" onclick="saveProfileCuisine()"><i class="fas fa-save mg-l-10"></i>&nbsp;Save</button></li>
					               <li><button type="button" class="btn-fill-lg font-normal text-light btn-gradient-yellow" onclick="loadVendorCuisineDetails();"><i class="fas fa-undo mg-l-10"></i>&nbsp;Reset</button></li>
				                   <li><button type="button" class="btn-fill-lg font-normal text-light bg-gradient-gplus" onclick="javascript:window.location.href = '${Wayuparty_appUrl}/dashboard'"><i class="fas fa-times mg-l-10"></i>&nbsp;Cancel</button></li>
					             </ul>
					       </div>
                     
				        </div>
				 </section>
</div>

<div class="alert icon-alart bg-light-green2" id="successCuisineMsgDiv" role="alert" style="display: none;">
                                 <i class="far fa-hand-point-right bg-light-green3"></i>
                                <span id="successCuisineMsg" style="color: white;"></span> 
</div>
          
<div class="alert icon-alart bg-pink2" role="alert" id="invalidCuisineMsgDiv" style="display: none;">
                           <i class="fas fa-times bg-pink3"></i>
                          <span id="invalidCuisineSMsg" style="color: white;"></span> 
</div>


<script>
function saveProfileCuisine(){
	 var cuisineIds = [];
	 $("input[name='cuisineList[]']:checked").each( function (i) {
		 cuisineIds[i] = $(this).val();
	});
	 
	 
	 var appUrl ='${appUrl}';
	 var vendorUUID = '${vendorUUID}';
	 var formData = new FormData();
	 formData.append("categoryType", "Cuisine");
	 formData.append("vendorUUID", vendorUUID);
	 formData.append("cuisineIds", cuisineIds);
	 
		if(cuisineIds.length > 0){
			 $("#vendorsCuisineDetailsLoaderDiv").attr('style','position:absolute; width:150%; height:80%; background-color:rgba(255,255,255,0.8); top:0px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
			$.ajax({ 
			    data: formData,
		    	contentType: false,
		    	processData: false,
			    type: "POST",
		    	url: appUrl+"/saveVendorProfileCategories?${_csrf.parameterName}=${_csrf.token}",
		        success: function(result) {
		        	 if (result.response === "SUCCESS") {
		        		 $("#successCuisineMsgDiv").removeAttr("style");
			    		 $("#successCuisineMsgDiv").css({ display: "block" });
				         $("#successCuisineMsg").html("Well done! You successfully added cusine.");
				         $("#successCuisineMsgDiv").fadeIn(200);
				 	     $('#successCuisineMsgDiv').delay(2000).fadeOut('slow');
				 	     $("#vendorsCuisineDetailsLoaderDiv").removeAttr("style");
				 	     loadVendorCuisineDetails();
			        }else if (result.response === "AWKWARD") {
		        		   $("#vendorsCuisineDetailsLoaderDiv").removeAttr("style");
		    		        location.href = "/errorPage";
		    	     }else{
			        	 $("#invalidCuisineMsgDiv").removeAttr("style");
			    		 $("#invalidCuisineMsgDiv").css({ display: "block" });
				         $("#invalidCuisineMsg").html("Opps! Something went worng.");
				         $("#invalidCuisineMsgDiv").fadeIn(200);
				 	     $('#invalidCuisineMsgDiv').delay(2000).fadeOut('slow');
				 	     $("#vendorsCuisineDetailsLoaderDiv").removeAttr("style");
			        }
		        },
		 }); 
		}else{
			 $("#vendorsCuisineDetailsLoaderDiv").removeAttr("style");
			 $("#invalidCuisineMsgDiv").removeAttr("style");
  		     $("#invalidCuisineMsgDiv").css({ display: "block" });
	         $("#invalidCuisineMsg").html("Opps! Please select atleat one cusine.");
	         $("#invalidCuisineMsgDiv").fadeIn(200);
	 	     $('#invalidCuisineMsgDiv').delay(2000).fadeOut('slow');
		} 
	 
}
</script>

<script>
function loadVendorCuisineDetails(){
	
	
	 var appUrl ='${appUrl}';
	 var vendorUUID = "${vendorUUID}";
	 
	 var formData = new FormData();
	 formData.append("categoryType", "Cuisine");
	 formData.append("vendorUUID", vendorUUID);
	   
	 $("#vendorCuisineDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:80%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
	     $.ajax({
	    	    data: formData,
	    	    contentType: false,
	    	    processData: false,
		    	type: "POST",
		    	url: appUrl+"/getProfileCategories?${_csrf.parameterName}=${_csrf.token}", 
		        success: function(resultData) {
		   			
		        	 var result = "";
		        	
		        	  result = result +'<select id="cuisine-select" multiple="multiple">';
		        	  for (var i=0; i<resultData.data.length; i++)
		   				{ 
		        		  var opt = resultData.data[i];
		        		  var selected = ''
			        		  if(opt.isCategoryConfigured == 'Yes'){
			        			  selected = 'selected'
			        		  }
			        		  result = result +'<option value='+opt.categoryId+' data-section='+opt.categoryName+' '+selected+' >'+opt.categoryName+'</option>';
		   				}
		        	  
		        	  result = result +'result = result +"</select>';
		
                  
		        	 
		        	 $("#cuisineListDiv").empty();  
	   	    	     $("#cuisineListDiv").append(result);
	   	    	     
		        },
		    }); 
	     
		  setTimeout(function () {
			 $("#cuisine-select").treeMultiselect({ enableSelectAll: true},"Cuisine");
			 $("#vendorCuisineDetailsLoaderDiv").removeAttr("style");
		  }, 500); 
}
</script>
		 	 	