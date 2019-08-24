/**
 * 
 */

jQuery.extend({
	CommonObj : function() {
		
		var that = this;
		var $form = null;
		var $body = null;
		
		this.onCreate = function() {
		};

		this.eventInit = function() {
		};
		
		this.init = function() {
			that.$body = $('body');
			this.eventInit();
			this.onCreate();
		};
	}
	
});

$(document).ready(function() {
	var CommonObj = new $.CommonObj();
});