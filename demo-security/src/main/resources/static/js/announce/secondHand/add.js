function addSecondHand(){
	console.log(add.price);
	$("#title").val(add.title);
	$("#price").val(add.price);
	$("#description").val(CKEDITOR.instances['description'].getData());
	
}

var add = new Vue({
	el : '#add',
	data : {
		price : '',
		title: '',
		description:''
	}
});


/*
$("#file").change(function(){
	console.log(this.files);
	
	for(var i = 0;i<this.files.length;i++){
		console.log(i);
		let imgFile = this.files[i];
		let fr = new FileReader();
		fr.onload = function() {
			$("img").attr("src", fr.result);
	    };
	    
	    fr.readAsDataURL(imgFile);
	}
	
});
*/

//Dropzone.autoDiscover = false;
//var myDropzone = new Dropzone("#my-awesome-dropzone", { url: "/secondHand/add"});
Dropzone.options.myAwesomeDropzone = {
		  paramName: "file", // The name that will be used to transfer the file
		  maxFiles:5,
		  maxFilesize: 2, // MB
		  acceptedFiles: ".jpeg,.jpg,.png,.gif",
		  addRemoveLinks:true,
		  autoProcessQueue: false,
		  uploadMultiple: true,
		  dictDefaultMessage:$("#uploadFileMsg").val(),
		  dictRemoveFile:$("#removeFile").val(),
		  parallelUploads: 5,
		  
		  init: function() {
		        var submitButton = document.querySelector("#addBtn");
		        myDropzone = this;
		       
		        submitButton.addEventListener("click", function() {
		        	addSecondHand();
		            myDropzone.processQueue(); 
		        });
		        myDropzone.on("success", function(file) {
		        	document.location.href="/secondHand";
		        });
		    }
		};
