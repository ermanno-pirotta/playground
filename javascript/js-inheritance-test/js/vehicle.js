var piro84 = piro84 || {};

piro84.vehicle = (function (){
	    var name="vehicle";

	    return{
	 		honk: function(){
	 			console.log(this.getName() + " HONK!");
	 		},  

	 		getName: function(){
	 			return "vehicle";
 			}
    	};
})();