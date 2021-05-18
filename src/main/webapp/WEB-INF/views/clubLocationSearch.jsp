<style>
.modal-content {
  background-image: url(/resources/img/cities/bg-img.jpg);
}
</style>
<div class="ui-modal-box">
  <div class="modal-box">
       <div class="modal large-modal fade sidebar-align" id="large-modal" tabindex="-1"
                                    role="dialog" aria-hidden="true">
                                    <div class="modal-dialog modal-lg" role="document">
                                        <div class="modal-content">
                                        <div id="locationDetailsLoaderDiv"></div>
                                            <div class="modal-header">
                                                <h5 class="modal-title">POPULAR CITIES</h5>
                                            </div>
                                           
											  <div class="modal-body">
											  <div class="werp">
											      <div class="forms clearfix">
											        <div class="row">
											          <div class="s-field clearfix">
											            <div class="s-f-left">
											              <img src="/resources/img/cities/search-icon.png" alt="srarch" />
											            </div>
											            <div class="s-f-right">
											               <input name="clubs_txtSearch" type="text"  id="clubs_txtSearch" class="clubs_txtSearch"  placeholder="Search">
											            </div>
											          </div>
											        </div>
											        <h3 class="s-res">POPULAR CITIES</h3>
											
											        <div class="row all-cities">
											          <div id="popularCitiesDiv"></div>
											        </div>
											      </div>
											    </div>
                                            
                                            </div>
                                            <div class="modal-footer">
                                               <button type="button" class="footer-btn bg-gradient-gplus" data-dismiss="modal" id="closeDataModal"><i class="fa fa-times mg-l-10"></i>&nbsp;Close</button>
                                            </div>
                                        </div>
                                    </div>
       </div>
       </div>
       </div>
       
       
  <style>
  .clubs_txtSearch, #clubs_txtSearch {
    z-index: 9999;
   }
   
   .pac-container{
    z-index: 99999;
   }
  </style>     
       
  <script>
      function getPopularCities(){
       $("#clubs_txtSearch").val('');
   	   var appUrl ='${appUrl}';
   	   $("#locationDetailsLoaderDiv").attr('style','position:absolute; width:100%; height:180%; background-color:rgba(255,255,255,0.8); top:50px; left:0px; z-index:100;background-image:url("/resources/img/preloader.gif"); background-position:center; background-repeat:no-repeat; background-size:75px;');
   	   $.ajax({
   			  type: "GET",
   			    url: appUrl+"/ws/getPopularCities?${_csrf.parameterName}=${_csrf.token}",
   			     success :function(resultData) {
   			    	 $("#locationDetailsLoaderDiv").removeAttr("style");
   			    	 var result = "";
   			    	 result = result +'<div class="btn-city-search" role="toolbar" aria-label="Toolbar with button groups">';
					 for (var i=0; i<resultData.data.length; i++)
		   				{ 
						  var opt = resultData.data[i];
		        		  result = result +'<div class="btn-group" role="group">';
		        		  result = result +'<a class="btn btn-outline-primary btn-sm" onclick="getRestaurantsList(\''+opt.latitude+'\',\''+opt.longitude+'\'); closeDataModal();"> <img src="'+opt.cityImage+'" alt="srarch" />'+opt.cityName+'</a>';
		        		  result = result +'</div>';
		   				} 
                       result = result +'<div>';
                       $("#popularCitiesDiv").empty();  
 		   	    	   $("#popularCitiesDiv").append(result);
   			    	 
   					},
   		});
   	   
     	googleApiLocation('clubs_txtSearch','country'); 
   	 	
      }
      
    
      
      function closeDataModal(){
    	  document.getElementById("closeDataModal").click();
    	  $("#clubs_txtSearch").val('');
      }
 </script>
       