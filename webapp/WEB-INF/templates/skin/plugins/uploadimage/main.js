var paramaters${fieldName} = { width: ${option.width}, height: ${option.height} };
var imgSupprimer = false;

$(function () {

  'use strict';

    var console = window.console || {
                log: function () {
                }
            },
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

  (function () {
    var $image = $('.img-container${fieldName} > img'),
        $dataX = $('#dataX${fieldName}'),
        $dataY = $('#dataY${fieldName}'),
        $dataHeight = $('#dataHeight${fieldName}'),
        $dataWidth = $('#dataWidth${fieldName}'),
        $dataRotate = $('#dataRotate${fieldName}'),
        options = {
           data: {
             x: ${option.x},
             y: ${option.y},
             width: ${option.width},
             height: ${option.height}
           },

           strict: ${option.strict?c},
           responsive: ${option.responsive?c},
           checkImageOrigin: ${option.checkImageOrigin?c},

           modal: ${option.modal?c},
           guides: ${option.guides?c},
           highlight: ${option.highlight?c},
           background: ${option.background?c},

           autoCrop: ${option.autoCrop?c},
           autoCropArea: false,
           dragCrop: ${option.dragCrop?c},
           movable: ${option.movable?c},
           rotatable: ${option.rotatable?c},
           zoomable: ${option.zoomable?c},
           touchDragZoom: ${option.touchDragZoom?c},
           mouseWheelZoom: ${option.mouseWheelZoom?c},
           cropBoxMovable: ${option.cropBoxMovable?c},
           cropBoxResizable: ${option.cropBoxResizable?c},
           doubleClickToggle: ${option.doubleClickToggle?c},

          aspectRatio: ${option.ratio},
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
      'build.cropper': function (e) {

      },
      'built.cropper': function (e) {

      },
      'dragstart.cropper': function (e) {

      },
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

    // zoom
    var $zoom = $("zoom_in${fieldName}");

    // Import image
    var $inputImage = $('#inputImage${fieldName}'),
        URL = window.URL || window.webkitURL,
        blobURL;

      if (URL) {

          $inputImage.change(function () {
              $('#initUpload').hide();
              $('#buttonOption${fieldName}').show();
              $('#img_div').show();

              var files = this.files,
                  file;

              if (!$image.data('cropper')) {
                  return;
              }

              if (files && files.length) {
                  file = files[0];

                  $('#typeImage').val(file.type);


                  if (/^image\/\w+$/.test(file.type)) {
                      blobURL = URL.createObjectURL(file);

                      // resize contenaire photo
                      var img = new Image;
                      img.src = blobURL;

                      img.onload = function () {
                          var divHeight;
                          $('#img_div').height(function (index, height) {
                              if (img.height > ${option.maxHeight}) {
                                  divHeight = (${option.maxHeight});
                              } else if (img.height < 100) {
                                  divHeight = 100;
                              }
                              else {
                                  divHeight = img.height;
                              }

                              $image.one('built.cropper', function () {
                                  URL.revokeObjectURL(blobURL); // Revoke when load complete

                              }).cropper('reset').cropper('load', blobURL);

                              imgSupprimer = false;
                              return divHeight;
                          });
                      };

                      $inputImage.val('');
                      
                      $("#imgChange").val("true");
                      
                  } else {
                      showMessage('${errorMsg}');
                  }

              }
          });

      } else {
          $inputImage.parent().remove();
      }

      $('#content_upload_image').show();
      //show buttons
      if (options.rotatable) {
          $('#rotate_right').show();
          $('#rotate_left').show();
      }

      if (options.zoomable) {
          $('#zoom_in').show();
          $('#zoom_out').show();
      }

      if (options.movable) {
          $('#move').show();
      }

  }());

});

// Fonctions

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

function getCroppedCanva(fieldName){

    if(!imgSupprimer){
        var $element= $('.img-container'+fieldName+' > img');
        result = $element.cropper('getCroppedCanvas', paramaters${fieldName});
        console.log(result.toDataURL());
        $('#imagesrc'+fieldName).val(result.toDataURL());
        $('#canvasImage'+fieldName).html(result);
        $('#deleteButton'+fieldName).show();
    }
}

function deleteImage(fieldName) {
    $('#imagesrc' + fieldName).val();
    $('#canvasImage' + fieldName).html('');
    $('#deleteButton' + fieldName).hide();
}

function supprimerImage(fieldName) {

    imgSupprimer = true;

    // Reset des champs
    $('#imagesrc' + fieldName).val('');

    $('#buttonOption' + fieldName).hide();
    $('#deleteButton' + fieldName).hide();
    $('#img_div').hide();
    $('#initUpload').show()
}

