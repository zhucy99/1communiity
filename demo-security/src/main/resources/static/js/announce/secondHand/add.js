function addSecondHand() {
	console.log(add.price);
	$("#title").val(add.title);
	$("#price").val(add.price);
	$("#description").val(CKEDITOR.instances['description'].getData());

}

var add = new Vue(
		{
			el : '#add',
			data : {
				title : '',
				price : '',
				description : ''
			},
			computed : {
				isValided : function() {
					if (this.title == '' || this.price == ''
							|| CKEDITOR.instances['description'].getData() == '') {
						return false;
					} else {
						return true;
					}

				}
			}
		});

/*
 * $("#file").change(function(){ console.log(this.files);
 * 
 * for(var i = 0;i<this.files.length;i++){ console.log(i); let imgFile =
 * this.files[i]; let fr = new FileReader(); fr.onload = function() {
 * $("img").attr("src", fr.result); };
 * 
 * fr.readAsDataURL(imgFile); }
 * 
 * });
 */

// Dropzone.autoDiscover = false;
// var myDropzone = new Dropzone("#my-awesome-dropzone", { url:
// "/secondHand/add"});
Dropzone.autoDiscover = false;
$(".dropzone").dropzone({
	paramName : "file", // The name that will be used to transfer the file
	maxFiles : 5,
	maxFilesize : 2, // MB
	acceptedFiles : ".jpeg,.jpg,.png,.gif",
	addRemoveLinks : true,
	autoProcessQueue : false,
	uploadMultiple : true,
	dictDefaultMessage : $("#uploadFileMsg").val(),
	dictRemoveFile : $("#removeFile").val(),
	parallelUploads : 5,
	init : function() {
		var submitButton = document.querySelector("#addBtn");
		myDropzone = this;

		submitButton.addEventListener("click", function() {
			if(add.title==''){
				$("#title_").addClass("input-error");
			}
			if(add.price==''){
				$("#price_").addClass("input-error");
			}
			if(add.price==''){
				$("#description_").addClass("input-error");
			}
			
			if(add.isValided){
				addSecondHand();
				myDropzone.processQueue();
			}
			// add.beginned=true;
			// 
		});
		myDropzone.on("complete", function(file) {
			document.location.href = "/secondHand";
		});
	}
});

$(document).ready(function() {
	$('[data-toggle="popover"]').popover();
});
