function changeTab(id) {
	$('#tabs').children().children().removeClass("active");
	$('div[name=content]').css("display", "none");

	$("#" + id).css("display", "block");
	$("#" + id + "_tab").addClass("active");

	// 通过函数名来调用函数
	var strFun = id + "Find"; // Name of the function to be called
	var fn = window[strFun];
	fn();

}

function findById() {
	$.get("/user/findById", {
		id : $("#id").val()
	}, function(data) {
		detail.datas=data
	});

}

function toSave() {
	detail.toSave = true;
	detail.editable = true;
}

function save() {

}

var detail = new Vue({
	el : '#detail',
	data : {
		datas : '',
		editable : false,
		toSave : false
	}
});

findById();



jQuery(function($){

    // Create variables (in this scope) to hold the API and image size
    var jcrop_api,
        boundx,
        boundy,

        // Grab some information about the preview pane
        $preview = $('#preview-pane'),
        $pcnt = $('#preview-pane .preview-container'),
        $pimg = $('#preview-pane .preview-container img'),

        xsize = $pcnt.width(),
        ysize = $pcnt.height();
    
    console.log('init',[xsize,ysize]);
    $('#target').Jcrop({
      onChange: updatePreview,
      onSelect: updatePreview,
      aspectRatio: xsize / ysize
    },function(){
      // Use the API to get the real image size
      var bounds = this.getBounds();
      boundx = bounds[0];
      boundy = bounds[1];
      // Store the API in the jcrop_api variable
      jcrop_api = this;

      // Move the preview into the jcrop container for css positioning
      $preview.appendTo(jcrop_api.ui.holder);
    });

    function updatePreview(c)
    {
      if (parseInt(c.w) > 0)
      {
        var rx = xsize / c.w;
        var ry = ysize / c.h;

        $pimg.css({
          width: Math.round(rx * boundx) + 'px',
          height: Math.round(ry * boundy) + 'px',
          marginLeft: '-' + Math.round(rx * c.x) + 'px',
          marginTop: '-' + Math.round(ry * c.y) + 'px'
        });
      }
    };

  });
