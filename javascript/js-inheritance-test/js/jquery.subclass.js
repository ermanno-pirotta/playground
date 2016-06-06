/*
* Utility method for extending classes.
* Usage:
	$.subclass(yournamespace.yourclass,{your overrides - additional methods}, {options:{name:'test'}})
*/

$.extend({
	subclass: function(baseClass, overridesAndNewMethods, options){
		overridesAndNewMethods = overridesAndNewMethods || {};
		options = options || {};

		return $.extend(Object.create(baseClass), $.extend(overridesAndNewMethods, options));
	}
});