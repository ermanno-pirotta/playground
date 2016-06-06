$(function(){
  
  	console.log("-------------------------");
  	console.log("tests using_jquery extend");

 	console.log("call base method");
	piro84.vehicle.honk();

	console.log("call inherited method in truck");
	piro84.truck.honk();

	console.log("call inherited method in bike");
	piro84.bike.honk();

	console.log("call method in child truck object");
	piro84.truck.superHonk();

	console.log("-------------------------");
});