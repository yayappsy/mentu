/*!
 * jQuery lightweight plugin boilerplate
 * Original author: @ajpiano
 * Further changes, comments: @addyosmani
 * Licensed under the MIT license
 */

// the semi-colon before the function invocation is a safety
// net against concatenated scripts and/or other plugins
// that are not closed properly.
;
(function($, window, document, undefined) {

	// undefined is used here as the undefined global
	// variable in ECMAScript 3 and is mutable (i.e. it can
	// be changed by someone else). undefined isn't really
	// being passed in so we can ensure that its value is
	// truly undefined. In ES5, undefined can no longer be
	// modified.

	// window and document are passed through as local
	// variables rather than as globals, because this (slightly)
	// quickens the resolution process and can be more
	// efficiently minified (especially when both are
	// regularly referenced in your plugin).

	// Create the defaults once
	var pluginName = "skuCheckbox", defaults = {
		propertyName : "id",
        // The class that will be applied to selected rows.
        selectedRowClass: 'warning',
        // The selector used to find the checkboxes on the table. You may
		// customize this in
        // order to match your table layout if it differs from the assumed one.
        checkboxSelector: 'input[type="checkbox"]',
		/** 已选择的checkbox */
		selectedNodes : [],
		/** 需要选择的checkbox的id前缀 */
		selectPrefix : "",
		/** checkbox与前缀之间的分隔符 */
		selectSeparator : "_"
	};

    // A callback that is used to determine wether a checkbox is selected or
	// not.
    var isChecked = function($checkbox) {
        return $checkbox.is(':checked');
    };
	// The actual plugin constructor
	function Plugin(element, options) {
		this.element = element;

		// jQuery has an extend method that merges the
		// contents of two or more objects, storing the
		// result in the first object. The first object
		// is generally empty because we don't want to alter
		// the default options for future instances of the plugin
		this.options = $.extend({}, defaults, options);

		this._defaults = defaults;
		this._name = pluginName;

		this.init();
	}

	Plugin.prototype = {

		init : function() {
			// Place initialization logic here
			// You already have access to the DOM element and
			// the options via the instance, e.g. this.element
			// and this.options
			// you can add more functions like the one below and
			// call them like so: this.yourOtherFunction(this.element,
			// this.options).
			var selectedPrefix = this.element.attr("id");
			this._selectedNodes = this.selectedNodes = this.options.selectedNodes;
			$.each(this.selectedNodes, function(i, node) {
				$("#"+selectedPrefix + "_" + node.id).iCheck("check");
			});
		},

		getCheckedNodes : function(checked) {
			checked = checked || true;
			var r = [];
			this.element.find(this.options.checkboxSelector).each(function(){
				if(isChecked($(this)) == checked){
					r.push({
						id:$(this).data("id"),
						name:$(this).data("name"),
						value:$(this).data("value"),
					})
				}
			});
			return r;
		},
	};

	// A really lightweight plugin wrapper around the constructor,
	// preventing against multiple instantiations
	$.fn[pluginName] = {
		init : function(obj, options) {
			return new Plugin(obj, options);
		}
	};

})(jQuery, window, document);