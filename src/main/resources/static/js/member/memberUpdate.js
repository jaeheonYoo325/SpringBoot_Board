/**
 * 
 */

var controller = $.extend(new $.CommonObj(), {
	eventInit:function() {
		
	},
	onCreate:function() {
		$("#password").keyup(function() {
			controller.passwordRegCheck();			
		});
		
		$("#passwordConfirm").keyup(function() {
			controller.passwordConfirmCheck();
		});
		
		$("#updateBtn").click(function() {
			
			var password = $("#password").val();
			var passwordConfirm = $("#passwordConfirm").val();
			
			if ( password != passwordConfirm) {
				alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
				return false;
			}
			
			if ( controller.validationCheck() ) {
				return;
			}
			else {
				controller.autoClosingAlert("#successMessage", 2000);
				$("#updateForm").attr({
					method:"post", 
			     	action:"/member/memberUpdate.do"					
				}).submit();
			} 
		});
		
	}, passwordRegCheck : function() {
		var password = $("#password").val();
		var reg =  /^(?=.*[a-zA-z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}/;
		
		if (reg.test(password)) {
			$("#passwordMessage").hide();
			$("#passwordMessage").text("사용가능한 비밀번호입니다.").show();
			controller.successState("#password");
			passwordRegCheckFlag = false;
		} 
		else {
			$("#passwordMessage").text("대/소문자, 특수문자 8글자 이상 입력하세요.").show();
			controller.errorState("#password");
			passwordRegCheckFlag = true;
		}
		
	}, passwordConfirmCheck : function() {
		var password = $("#password").val();
		var passwordConfirm = $("#passwordConfirm").val();
						
		if ( password == passwordConfirm ) {
			$("#passwordConfirmMessage").hide();
			$("#passwordConfirmMessage").text("비밀번호가 일치합니다.").show();
			controller.successState("#passwordConfirm");
		}
		else {
			$("#passwordConfirmMessage").text("비밀번호와 일치하지 않습니다. 다시 입력해 주세요.").show();
			controller.errorState("#passwordConfirm");
		}
		
	}, successState : function(sel) {
		$(sel).closest(".form-group")
		  .removeClass("has-error")
		  .addClass("has-success")
		  .find(".form-control-feedback")
		  .removeClass("glyphicon-remove")
		  .addClass("glyphicon-ok")
		  .show();
		
	}, errorState : function(sel) {
		$(sel).closest(".form-group")
		  .removeClass("has-success")
		  .addClass("has-error")
		  .find(".form-control-feedback")
		  .removeClass("glyphicon-ok")
		  .addClass("glyphicon-remove")
		  .show();
		
	}, emptyCheck : function() {
		passwordEmpty = false;
		passwordConfirmEmpty = false;
		
		if ( $("#password").val() == "" ) {
			passwordEmpty = true;
		}
		if ( $("#passwordConfirm").val() == "" ) {
			passwordConfirmEmpty = true;
		}
		
	}, autoClosingAlert : function(selector, delay) {
		var alert = $(selector).alert();
		alert.show();
		window.setTimeout(function() { alert.hide() }, delay);
		
	}, validationCheck : function() {
		controller.emptyCheck();
		 
		if ( passwordEmpty || passwordConfirmEmpty ) {
			 
			if ( passwordEmpty ) {
				$("#passwordMessage").text("비밀번호를 입력해주세요.").show();
				controller.errorState("#password");
					
			}
			if ( passwordConfirmEmpty ) {
				$("#passwordConfirmMessage").text("비밀번호 확인을 입력해주세요.").show();
				controller.errorState("#passwordConfirm");							
			}
			return true;
		 }
		 else {
			if ( passwordRegCheckFlag == true ) {
					return true;
			}
			return false;
		 }
	}	
});

$(document).ready(function() {
	controller.init();
});