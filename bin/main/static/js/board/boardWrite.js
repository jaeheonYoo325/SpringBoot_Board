/**
 * 
 */

var controller = $.extend(new $.CommonObj(), {
	eventInit:function() {
		
	},
	onCreate:function() {
		ClassicEditor.create(
		          document.querySelector("#content"), {
		          ckfinder: {
		        	  uploadUrl: 'https://localhost:8443/board/boardImageFileUpload.do'
		          }
		        }
		 );
		
		 /* 
			$("#fileSearchBtn").on('click', '.browse', function() {
				var file = $(this).parent().parent().parent().find('.file');
				file.trigger('click');
			}); 
		*/
		$("#fileSearchBtn").click(function() {
			var file = $(this).parent().parent().parent().find('.file');
			file.trigger('click');
		});
		
		$("#createBtn").click(function() {
			if ( $("#recruitDt").val() == "") {
				
				$("#checkMessage").html("채용마감을 입력해주세요.");
				$("#messageModal").modal("show");
				return false;
			}
			if ( $("#title").val() == "") {
				
				$("#checkMessage").html("글 제목을 입력해주세요.");
				$("#messageModal").modal("show");
				return false;
			}
			/* if ( $("#content").val() == "" ){
				$("#checkMessage").html("글 내용을 입력해주세요.");
				$("#messageModal").modal("show");
				return false;
			} */
			
			/* if ( $(".ck ck-content ck-editor__editable ck-rounded-corners ck-blurred ck-editor__editable_inline").find().val() == "") {
				alert("글 내용을 입력하세요.");
				$("#content").focus();
				return false;
			} */
			
			controller.autoClosingAlert("#successMessage", 2000);
			$("#boardWriteForm").attr({
				method:"post", 
		     	action:"/board/boardWrite.do",
		     	enctype:"multipart/form-data"
			}).submit();
		});
		
	}, autoClosingAlert : function(selector, delay) {
		var alert = $(selector).alert();
		alert.show();
		window.setTimeout(function() { alert.hide() }, delay);		
	}	
});

$(document).ready(function() {
	controller.init();
});