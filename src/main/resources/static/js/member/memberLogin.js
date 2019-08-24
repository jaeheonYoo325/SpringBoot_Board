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
			$("#passwordMessage").hide();				
		});
		
		$("#loginBtn").click(function() {
			
			if ( controller.validationCheck() ) {
				return;
			}
			else {
				
				var form = $("#loginForm");
				form.ajaxSubmit({
					url:"/member/memberAjax.do",
					data:form.serialize(),
					type:"POST",
					success: function(response) {
						 if( response.loginFail == false ) {
							 	alert("아이디 또는 비밀번호를 다시 확인하세요. 등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.");						
								return;
							} else {							
								controller.autoClosingAlert("#successMessage", 2000);
								location.href="https://localhost:8443/main/index.do";
							} 
					},
					error: function() {
						alert("통신 에러로 로그인이 실패하였습니다.");						
						return;
					}
				});
			}
		});
		
	}, emailCheck : function() {		
		var email = $("#email").val();
		var reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		
		if ( reg.test(email) ) {
			$("#emailMessage").hide();
			controller.successState("#email");
			return false;
		}
		else {
			$("#emailMessage").text("올바른 이메일 형식이 아닙니다. 다시 입력해 주세요.").show();
			controller.errorState("#email");
			return true;
		}
		
	}, successState : function(sel) {
		$(sel).closest(".form-group")
  	   	  .removeClass("has-error")
  	   	  .find(".form-control-feedback")
  	   	  .removeClass("glyphicon-ok")
  	   	  .removeClass("glyphicon-remove")
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
		
		if ( $("#email").val() == "" ) {
			emailEmpty = true;
		}
		if ( $("#password").val() == "" ) {
			passwordEmpty = true;
		}
		
	}, validationCheck : function() {
		controller.emptyCheck();
		
		if ( emailEmpty || passwordEmpty ) {
			if ( emailEmpty ) {
				$("#emailMessage").text("이메일을 입력해주세요.").show();
				controller.errorState("#email");
			}
			if ( passwordEmpty ) {
				$("#passwordMessage").text("비밀번호를 입력해주세요.").show();
				controller.errorState("#password");
			}
			return true;
		}
		else {
				if ( controller.emailCheck() == true ) {
					return true;
				}
				return false;
		}		
	}
});

$(document).ready(function() {
	controller.init();
});