var piro84 = piro84 || {};

piro84.vehicle = (function (){
		var options={
			name:"vehicle"
		}	
		
	    return{
	 		honk: function(){
	 			console.log(this.getName() + " HONK!");
	 		},  

	 		getName: function(){
	 			return this.options.name;
 			},
 			options: options
    	};
})();