-- liquibase formatted sql
-- changeset uploadimage:update_db_uploadimage-1.0.1-2.0.0.sql
-- preconditions onFail:MARK_RAN onError:WARN
UPDATE core_admin_right SET icon_url = 'ti ti-crop' WHERE id_right = 'UPLOADIMAGE_MANAGEMENT';
