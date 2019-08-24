/**
 * 
 */

var controller = $.extend(new $.CommonObj(), {
	eventInit:function() {
		
	},
	onCreate:function() {
		$("#email").keyup(function() {
			controller.emailCheck();
		});
		
		$("#password").keyup(function() {
			controller.passwordRegCheck();
			
		});
		
		$("#passwordConfirm").keyup(function() {
			controller.passwordConfirmCheck();
		});
		
		$("#name").keyup(function() {
			controller.nameCheck();
		});
		
		$("#registBtn").click(function() {
			
			if ( controller.validationCheck() ) {
				return;
			}
			else {
				controller.autoClosingAlert("#successMessage", 2000);
				 $("#registForm").attr({
						method:"post", 					     				     	
				     	action:"/member/memberRegist.do"
				 }).submit();
			}	
			
		});
		
	}, emailCheck : function() {
		var email = $("#email").val();
		var reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

		$.post("/member/emailDuplicate.do"
				,  {									 
					"email": $("#email").val()
				}
				, function(response) {
					
					if ( reg.test(email) ) {
						$("#emailMessage").hide();
						if ( response.cnt > 0 ) {					 
							$("#emailMessage").text("이미 존재하는 이메일입니다.").show();
							controller.errorState("#email");
							emailCheckFlag = true;
						}
						else {
							$("#emailMessage").text("사용가능한 이메일입니다.").show();
							controller.successState("#email");
							emailCheckFlag = false;
						}
					}
					else {
						$("#emailMessage").text("올바른 이메일 형식이 아닙니다. 다시 입력해 주세요.").show();
						controller.errorState("#email");
						emailCheckflag = true;
					}
			})
			return false;
		
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
		
	}, nameCheck : function() {
		var name = $("#name").val();
		
		if ( name != null) {
			$("#nameMessage").hide();
			controller.successState("#name");
		} 
		else {
			$("#nameMessage").text("이름을 입력해주세요.").show();
			controller.errorState("#name");
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
		
	}, autoClosingAlert : function(selector, delay) {
		var alert = $(selector).alert();
		alert.show();
		window.setTimeout(function() { alert.hide() }, delay);
		
	}, emptyCheck : function() {
		emailEmpty = false;
		passwordEmpty = false;
		passwordConfirmEmpty = false;
		nameEmpty = false;
		
		if ( $("#email").val() == "" ) {
			emailEmpty = true;
		}
		if ( $("#password").val() == "" ) {
			passwordEmpty = true;
		}
		if ( $("#passwordConfirm").val() == "" ) {
			passwordConfirmEmpty = true;
		}
		if ( $("#name").val() == "" ) {
			nameEmpty = true;
		}
		
	}, validationCheck : function() {
		
		controller.emptyCheck();
		
		if ( emailEmpty || passwordEmpty || passwordConfirmEmpty || nameEmpty ) {
			
			if ( emailEmpty ) {
				$("#emailMessage").text("이메일을 입력해주세요.").show();
				controller.errorState("#email");
			}	
			if ( passwordEmpty ) {
				$("#passwordMessage").text("비밀번호를 입력해주세요.").show();
				controller.errorState("#password");
				
			}
			if ( passwordConfirmEmpty ) {
				$("#passwordConfirmMessage").text("비밀번호 확인을 입력해주세요.").show();
				controller.errorState("#passwordConfirm");
				
			}
			if ( nameEmpty ) {
				$("#nameMessage").text("이름을 입력해주세요.").show();
				controller.errorState("#name");
				
			}
			return true;
		}
		else {				
				if ( emailCheckFlag == true) {
					return true;
				}
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