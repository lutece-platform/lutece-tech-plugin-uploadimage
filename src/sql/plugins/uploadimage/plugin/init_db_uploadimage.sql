-- liquibase formatted sql
-- changeset uploadimage:init_db_uploadimage.sql
-- preconditions onFail:MARK_RAN onError:WARN
INSERT INTO uploadimage_options ( id_options, strict, responsive, checkImageOrigin, modal, guides, highlight, background, autoCrop, dragCrop, movable, rotatable, zoomable, touchDragZoom, mouseWheelZoom, cropBoxMovable, cropBoxResizable, doubleClickToggle, width, height, x, y, ratio, fieldName ) VALUES ( 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 952, 576, 172, 128, '16/9' , 'fieldName' );