var piro84 = piro84 || {};

piro84.truck = (function (){
		var name = "truck";	 

		return $.subclass(piro84.vehicle,{
			superHonk: function(){
				console.log(this.getName() + " SUPER HONK!");
			},

			// "override the getNameFunction"
			getName: function(){
				return name;
			}		
		})
})();