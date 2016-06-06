var piro84 = piro84 || {};

piro84.truck = (function (){

		var fields={
			name: "truck"
		}			 

		return $.subclass(piro84.vehicle,{
				superHonk: function(){
					console.log(this.getName() + " SUPER HONK!");
				}
			},
			{
				options:fields
			})
})();