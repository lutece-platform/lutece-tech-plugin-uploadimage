<#if fieldName?has_content>
/*
 * Cropping widget of one upload field, rendered by UploadimageJsProvider on top of Cropper.js 1.x.
 * The cropped image is written to the hidden imagesrc<fieldName> input and announced by the
 * uploadimage:cropped event; the Cropper instance stays reachable as the image's `cropper` property.
 */
( function () {
	'use strict';

	var FIELD = '${fieldName?js_string}';
	var ROOT_ID = 'content_upload_image_' + FIELD;
	var blobUrl = null;
	var alertTimer = null;
	var RESPONSIVE = ${cropperOption.responsive?c};

	var options = {
		data: { x: ${cropperOption.x?c}, y: ${cropperOption.y?c}, width: ${cropperOption.width?c}, height: ${cropperOption.height?c} },
		viewMode: ${cropperOption.strict?then( 1, 0 )},
		dragMode: '${cropperOption.dragCrop?then( 'crop', 'none' )}',
		responsive: false,
		checkCrossOrigin: ${cropperOption.checkImageOrigin?c},
		modal: ${cropperOption.modal?c},
		guides: ${cropperOption.guides?c},
		highlight: ${cropperOption.highlight?c},
		background: ${cropperOption.background?c},
		autoCrop: ${cropperOption.autoCrop?c},
		movable: ${cropperOption.movable?c},
		rotatable: ${cropperOption.rotatable?c},
		zoomable: ${cropperOption.zoomable?c},
		zoomOnTouch: ${cropperOption.touchDragZoom?c},
		zoomOnWheel: ${cropperOption.mouseWheelZoom?c},
		cropBoxMovable: ${cropperOption.cropBoxMovable?c},
		cropBoxResizable: ${cropperOption.cropBoxResizable?c},
		toggleDragModeOnDblclick: ${cropperOption.doubleClickToggle?c}
	};

	function root() {
		return document.getElementById( ROOT_ID );
	}

	function find( selector ) {
		var container = root();

		return container ? container.querySelector( selector ) : null;
	}

	function image() {
		return find( '.img-container' + FIELD + ' > img' );
	}

	/** The Cropper instance of the widget, once built. */
	function cropper() {
		var img = image();

		return img && img.cropper ? img.cropper : null;
	}

	function setValue( selector, value ) {
		var field = find( selector );

		if ( field ) {
			field.value = value;
		}
	}

	/** Mirrors the crop box into the read-out fields of the widget. */
	function writeCropData( event ) {
		var data = event.detail;

		if ( !data ) {
			return;
		}
		setValue( '#dataX' + FIELD, Math.round( data.x ) );
		setValue( '#dataY' + FIELD, Math.round( data.y ) );
		setValue( '#dataWidth' + FIELD, Math.round( data.width ) );
		setValue( '#dataHeight' + FIELD, Math.round( data.height ) );
		setValue( '#dataRotate' + FIELD, Math.round( data.rotate ) );
	}

	function revokeBlobUrl() {
		if ( blobUrl ) {
			window.URL.revokeObjectURL( blobUrl );
			blobUrl = null;
		}
	}

	/** Transient message in the widget's alert strip. */
	function showMessage( message, type ) {
		var strip = find( '.docs-alert' );
		var text = find( '.docs-alert .message' );

		if ( !strip || !text ) {
			return;
		}
		text.textContent = message;
		if ( type ) {
			text.classList.add( type );
		}
		strip.style.display = 'block';
		window.clearTimeout( alertTimer );
		alertTimer = window.setTimeout( function () {
			strip.style.display = 'none';
		}, 3000 );
	}

	function showPreview( node ) {
		var holder = find( '#canvasImage' + FIELD );
		var button = find( '#deleteButton' + FIELD );

		if ( holder ) {
			holder.replaceChildren( node );
		}
		if ( button ) {
			button.classList.remove( 'd-none' );
		}
	}

	function clearPreview() {
		var holder = find( '#canvasImage' + FIELD );
		var button = find( '#deleteButton' + FIELD );

		setValue( '#imagesrc' + FIELD, '' );
		if ( holder ) {
			holder.replaceChildren();
		}
		if ( button ) {
			button.classList.add( 'd-none' );
		}
	}

	/**
	 * Publishes the cropped canvas: the hidden field carries it back with the host form and the
	 * uploadimage:cropped event lets a host page upload it itself. A page that owns the upload
	 * keeps its own getCroppedCanva entry point (the blog editor does).
	 */
	function addCroppedImage() {
		var instance = cropper();
		var width = find( '#dataWidth' + FIELD );
		var height = find( '#dataHeight' + FIELD );
		var size = {};
		var canvas;

		if ( !instance ) {
			return;
		}
		if ( typeof window.getCroppedCanva === 'function' ) {
			window.getCroppedCanva( FIELD );
			return;
		}
		if ( width && width.value ) {
			size.width = Number( width.value );
		}
		if ( height && height.value ) {
			size.height = Number( height.value );
		}
		canvas = instance.getCroppedCanvas( size );
		if ( !canvas ) {
			return;
		}
		setValue( '#imagesrc' + FIELD, canvas.toDataURL() );
		showPreview( canvas );
		document.dispatchEvent( new CustomEvent( 'uploadimage:cropped', {
			detail: { fieldName: FIELD, canvas: canvas }
		} ) );
	}

	/** Runs the Cropper method named by the clicked control, with its JSON or numeric argument. */
	function runCropperMethod( button ) {
		var instance = cropper();
		var method = button.getAttribute( 'data-cropper-method' );
		var raw = button.getAttribute( 'data-cropper-option' );
		var option;

		if ( !instance || typeof instance[ method ] !== 'function' ) {
			return;
		}
		if ( raw !== null && raw !== '' ) {
			try {
				option = JSON.parse( raw );
			} catch ( e ) {
				option = raw;
			}
		}
		instance[ method ]( option );
	}

	function onClick( event ) {
		var method = event.target.closest( '[data-cropper-method]' );
		var action = event.target.closest( '[data-cropimage-action]' );

		if ( method ) {
			runCropperMethod( method );
			return;
		}
		if ( !action ) {
			return;
		}
		if ( action.getAttribute( 'data-cropimage-action' ) === 'add' ) {
			addCroppedImage();
		} else {
			clearPreview();
		}
	}

	/** Arrow keys move the image, only while the focus is on the widget and not in one of its fields. */
	function onKeydown( event ) {
		var instance = cropper();
		var steps = { ArrowLeft: [ -1, 0 ], ArrowUp: [ 0, -1 ], ArrowRight: [ 1, 0 ], ArrowDown: [ 0, 1 ] };
		var step = steps[ event.key ];

		if ( !step || !instance || event.target.closest( 'input, textarea, select, [contenteditable]' ) ) {
			return;
		}
		event.preventDefault();
		instance.move( step[ 0 ], step[ 1 ] );
	}

	/** Replaces the cropped image with the file the user picked, without uploading it. */
	function onFileChange( event ) {
		var instance = cropper();
		var file = event.target.files && event.target.files.length ? event.target.files[ 0 ] : null;
		var label = find( '#label' + FIELD );
		var toolbar = find( '#buttonOption' + FIELD );

		if ( !file || !instance ) {
			return;
		}
		if ( !/^image\/\w+$/.test( file.type ) ) {
			showMessage( '#i18n{uploadimage.model.message.notAnImage}', 'warning' );
			return;
		}
		if ( label ) {
			label.classList.add( 'd-none' );
		}
		if ( toolbar ) {
			toolbar.classList.remove( 'd-none' );
		}
		revokeBlobUrl();
		blobUrl = window.URL.createObjectURL( file );
		instance.replace( blobUrl );
		event.target.value = '';
	}

	/** Hides the controls the stored options disable. */
	function hideDisabledControls() {
		var hidden = [];

		if ( !options.rotatable ) {
			hidden.push( '#rotate_left_' + FIELD, '#rotate_right_' + FIELD );
		}
		if ( !options.zoomable ) {
			hidden.push( '#zoom_in_' + FIELD, '#zoom_out_' + FIELD );
		}
		hidden.forEach( function ( selector ) {
			var button = find( selector );

			if ( button ) {
				button.classList.add( 'd-none' );
			}
		} );
	}

	/** The site theme does not initialise Bootstrap tooltips, the admin theme does. */
	function initTooltips() {
		var container = root();

		if ( !container || !window.bootstrap || !window.bootstrap.Tooltip ) {
			return;
		}
		container.querySelectorAll( '[data-bs-toggle="tooltip"]' ).forEach( function ( element ) {
			window.bootstrap.Tooltip.getOrCreateInstance( element );
		} );
	}

	/**
	 * Runs the callback once the element has a width: Cropper sizes itself on its wrapper, so a
	 * widget built while hidden (a modal not shown yet) would stay at zero size.
	 */
	function whenVisible( element, callback ) {
		var observer;

		if ( element.offsetWidth > 0 || typeof window.ResizeObserver !== 'function' ) {
			callback();
			return;
		}
		observer = new window.ResizeObserver( function () {
			if ( element.offsetWidth > 0 ) {
				observer.disconnect();
				callback();
			}
		} );
		observer.observe( element );
	}

	/**
	 * Follows the size of the wrapper instead of the window: Cropper's own window listener rescales the
	 * crop box by the new wrapper width, which is zero while the widget is hidden, and the box never recovers.
	 */
	function followWrapperSize( wrapper ) {
		var observer;

		if ( !RESPONSIVE || typeof window.ResizeObserver !== 'function' ) {
			return;
		}
		observer = new window.ResizeObserver( function () {
			var instance = cropper();

			if ( instance && instance.ready && wrapper.offsetWidth > 0 && wrapper.offsetHeight > 0 ) {
				instance.resize();
			}
		} );
		observer.observe( wrapper );
	}

	function init() {
		var container = root();
		var img = image();
		var input = find( '#inputImage' + FIELD );

		if ( !container || !img ) {
			return;
		}
		if ( typeof window.Cropper !== 'function' ) {
			console.error( 'uploadimage: Cropper.js is not loaded, call @addRequiredJsUploadImages before @cropimage' );
			return;
		}
		img.addEventListener( 'ready', revokeBlobUrl );
		img.addEventListener( 'crop', writeCropData );
		whenVisible( img.parentNode, function () {
			new window.Cropper( img, options );
			followWrapperSize( img.parentNode );
		} );
		container.addEventListener( 'click', onClick );
		container.addEventListener( 'keydown', onKeydown );
		if ( input && window.URL ) {
			input.addEventListener( 'change', onFileChange );
		} else if ( input ) {
			input.remove();
		}
		hideDisabledControls();
		initTooltips();
	}

	if ( document.readyState === 'complete' ) {
		init();
	} else {
		window.addEventListener( 'load', init );
	}
} )();
</#if>
