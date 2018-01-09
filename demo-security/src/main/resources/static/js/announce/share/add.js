
$("#file").change(function() {
	console.log(this.files);

	for (var i = 0; i < this.files.length; i++) {
		console.log(i);
		let imgFile = this.files[i];
		let fr = new FileReader();
		fr.onload = function() {
			$("img").attr("src", fr.result);
		};

		fr.readAsDataURL(imgFile);
	}

});
