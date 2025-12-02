var paramaters${fieldName} = { width: ${cropperOption.width}, height: ${cropperOption.height} };

$(function () {
  'use strict';
   var console = window.console || { log: function () {} },
      $alert = $('.docs-alert'),
      $message = $alert.find('.message'),
      showMessage = function (message, type) {
        $message.text(message);
        if (type) {
          $message.addClass(type);
        }
        $alert.fadeIn();
        setTimeout(function () {
          $alert.fadeOut();
        }, 3000);
      };

  // Demo
  // -------------------------------------------------------------------------
	
  (function () {
    var $image = $('.img-container${fieldName} > img'),
    $dataX = $('#dataX${fieldName}'),
    $dataY = $('#dataY${fieldName}'),
    $dataHeight = $('#dataHeight${fieldName}'),
    $dataWidth = $('#dataWidth${fieldName}'),
    $dataRotate = $('#dataRotate${fieldName}'),
    options = {
      data: {
        x: ${cropperOption.x},
        y: ${cropperOption.y},
        width: ${cropperOption.width},
        height: ${cropperOption.height}
      },
      strict: ${cropperOption.strict?c},
      responsive: ${cropperOption.responsive?c},
      checkImageOrigin: ${cropperOption.checkImageOrigin?c},
      modal: ${cropperOption.modal?c},
      guides: ${cropperOption.guides?c},
      highlight: ${cropperOption.highlight?c},
      background: ${cropperOption.background?c},
      autoCrop: ${cropperOption.autoCrop?c},
      autoCropArea: false,
      dragCrop: ${cropperOption.dragCrop?c},
      movable: ${cropperOption.movable?c},
      rotatable: ${cropperOption.rotatable?c},
      zoomable: ${cropperOption.zoomable?c},
      touchDragZoom: ${cropperOption.touchDragZoom?c},
      mouseWheelZoom: ${cropperOption.mouseWheelZoom?c},
      cropBoxMovable: ${cropperOption.cropBoxMovable?c},
      cropBoxResizable: ${cropperOption.cropBoxResizable?c},
      doubleClickToggle: ${cropperOption.doubleClickToggle?c},
      aspectRatio: ${cropperOption.ratio},
      preview: '.img-preview',
      crop: function (data) {
        $dataX.val(Math.round(data.x));
        $dataY.val(Math.round(data.y));
        $dataHeight.val(Math.round(data.height));
        $dataWidth.val(Math.round(data.width));
        $dataRotate.val(Math.round(data.rotate));
      }
    };

    $image.on({
      'build.cropper': function (e) { },
      'built.cropper': function (e) {  },
      'dragstart.cropper': function (e) { },
      'dragmove.cropper': function (e) {
       
      },
      'dragend.cropper': function (e) {
       
      },
      'zoomin.cropper': function (e) {
     
      },
      'zoomout.cropper': function (e) {
  
      },
      'change.cropper': function (e) {
     
      }
    }).cropper(options);


  // Methods
  $(document.body).on('click', '[data-method]', function (){
      var data = $(this).data(),
          $target,
          result;
      if (!$image.data('cropper')) {
        return;
      }

      if (data.method) {
        data = $.extend({}, data); // Clone a new one

        if (typeof data.target !== 'undefined') {
          $target = $(data.target);

          if (typeof data.option === 'undefined') {
            try {
              data.option = JSON.parse($target.val());
            } catch (e) {
              
            }
          }
        }
        result = $image.cropper(data.method, data.option);
        if(data.method === 'deleteImage'){
              var fieldName= data.option; 
              $('#imagesrc'+fieldName).val( );
              $('#canvasImage'+fieldName).html('');
              $('#deleteButton'+fieldName).hide();
        }
          
      if (data.method === 'getCroppedCanvas') {
          $('#imagesrc${fieldName}').val(result.toDataURL());
          $('#canvasImage${fieldName}').html(result);
          $('#deleteButton${fieldName}').show();
        }
        
        if ($.isPlainObject(result) && $target) {
          try {
            $target.val(JSON.stringify(result));
          } catch (e) {
          
          }
        }
      }
  }).on('keydown', function (e) {
      if (!$image.data('cropper')) {
        return;
      }
      switch (e.which) {
        case 37:
          e.preventDefault();
          $image.cropper('move', -1, 0);
          break;
        case 38:
          e.preventDefault();
          $image.cropper('move', 0, -1);
          break;
        case 39:
          e.preventDefault();
          $image.cropper('move', 1, 0);
          break;
        case 40:
          e.preventDefault();
          $image.cropper('move', 0, 1);
          break;
      }
    });

    // Zoom
    var $zoom = $("zoom_in${fieldName}");
        
    // Import image
    var $inputImage = $('#inputImage${fieldName}'),
        URL = window.URL || window.webkitURL,
        blobURL;

        if (URL) {
          $inputImage.change(function () {
      
            $('#label${fieldName}').hide();
            $('#buttonOption${fieldName}').show();
      
            var files = this.files, file;

            if (!$image.data('cropper')) {
              return;
            }
      
            if (files && files.length) {
              file = files[0];
              if (/^image\/\w+$/.test(file.type)) {
                blobURL = URL.createObjectURL(file);
                $image.one('built.cropper', function () {
                  URL.revokeObjectURL(blobURL); // Revoke when load complete
                }).cropper('reset').cropper('load', blobURL);
                $inputImage.val('');
              } else {
                showMessage('Please choose an image file.');
              }
            }
          });	
        } else {
          $inputImage.parent().remove();
        }
        
        // Options
        $('.docs-options :checkbox').on('change', function () {
          var $this = $(this);
          if (!$image.data('cropper')) {
            return;
          }
          options[$this.val()] = $this.prop('checked');
          $image.cropper('destroy').cropper(options);
        });

        // Tooltips
        $('[data-toggle="tooltip"]').tooltip();

      //hide button
      if(!options.rotatable){
        $('#rotate_right').hide();
        $('#rotate_left').hide();
      };

      if(!options.zoomable){
        $('#zoom_in').hide();
        $('#zoom_out').hide();
      };
      
      if(!options.movable){
        $('#move').hide();
      };
    }());
});

function zoomIn(fieldName) {
    var element = ".img-container" + fieldName;
    $(element+" > img").cropper("zoom",0.1);
}

function zoomOut(fieldName) {
    var elem = ".img-container" + fieldName;
    $(elem+" > img").cropper("zoom",-0.1);
}

function move(fieldName) {
    var elem = ".img-container" + fieldName;
    $(elem+" > img").cropper("setDragMode",'move');
}
function rotate(fieldName, deg) {
    var elem = ".img-container" + fieldName;
    $(elem+" > img").cropper("rotate",deg);
}

function cropBox(fieldName) {
    var elem = ".img-container" + fieldName;
    $(elem+" > img").cropper("crop");
}

function clearBox(fieldName) {
    var elem = ".img-container" + fieldName;
    $(elem+" > img").cropper("clear");
}

function resetBox(fieldName) {
    var elem = ".img-container" + fieldName;
    $(elem+" > img").cropper("reset");
}

function getCroppedCanva( fieldName ){
	const CurrentIdBlog = $('#id').val();
	const CurrentdHeight = $('#dataHeight${fieldName}').val();
	const CurrentdWidth = $('#dataWidth${fieldName}').val();
	const $element= $('.img-container'+fieldName+' > img');
	const CurrentfileType = $('#fileType option:checked').val();
	result = $element.cropper('getCroppedCanvas', { width: CurrentdWidth, height: CurrentdHeight });
	doAddContent( fieldName, result.toDataURL( 'image/jpeg',1.0 ), CurrentfileType, CurrentIdBlog );
};


function getCroppedCanva2(fieldName){
	var $element= $('.img-container'+fieldName+' > img');
	result = $element.cropper('getCroppedCanvas', paramaters${fieldName});
	$('#imagesrc'+fieldName).val(result.toDataURL());
	// $('#canvasImage'+fieldName).html(result);	
	$('#content-list').append(result);
	$('#deleteButton'+fieldName).show();
};

function getCanvasWithParam(fieldName, param){
	var $element= $('.img-container'+fieldName+' > img');
	result = $element.cropper('getCroppedCanvas', param);
	const resultData = result.toDataURL();
	$('#imagesrc'+fieldName).val( resultData );
	$('#canvasImage'+fieldName).html(result);
	$('#deleteButton'+fieldName).show();	
};

function deleteImage(fieldName){ 
  $('#imagesrc'+fieldName).val( );
  $('#canvasImage'+fieldName).html('');
  $('#deleteButton'+fieldName).hide();	
};