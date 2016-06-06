$(function(){
  
  	console.log("-------------------------");
  	console.log("tests using_jquery extend");

 	console.log("call base method");
	piro84.vehicle.honk();

	console.log("call inherited method");
	piro84.truck.honk();

	console.log("call method in child object");
	piro84.truck.superHonk();
	console.log("-------------------------");
});