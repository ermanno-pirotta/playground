/*
* Utility method for extending classes.
* Usage:
	$.subclass(yournamespace.yourclass,{your overrides - additional methods})
*/

$.extend({
	subclass: function(baseClass, overridesAndNewMethods){
		return $.extend(Object.create(baseClass), overridesAndNewMethods);
	}
});