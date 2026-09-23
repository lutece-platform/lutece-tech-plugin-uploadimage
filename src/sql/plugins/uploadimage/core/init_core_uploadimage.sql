-- liquibase formatted sql
-- changeset uploadimage:init_core_uploadimage.sql
-- preconditions onFail:MARK_RAN onError:WARN

--
-- Data for table core_admin_right
--
DELETE FROM core_admin_right WHERE id_right = 'UPLOADIMAGE_MANAGEMENT';
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('UPLOADIMAGE_MANAGEMENT','uploadimage.adminFeature.ManageUploadimage.name',1,'jsp/admin/plugins/uploadimage/ManageUploadimage.jsp','uploadimage.adminFeature.ManageUploadimage.description',0,'uploadimage',NULL,'ti ti-crop',NULL,4);


--
-- Data for table core_user_right
--
DELETE FROM core_user_right WHERE id_right = 'UPLOADIMAGE_MANAGEMENT';
INSERT INTO core_user_right (id_right,id_user) VALUES ('UPLOADIMAGE_MANAGEMENT',1);

