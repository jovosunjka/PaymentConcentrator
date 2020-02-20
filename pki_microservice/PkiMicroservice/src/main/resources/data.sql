INSERT INTO `user` (`id`,`username`,`password`) VALUES (1, 'admin', '$2a$10$SGCaOooXdD6TalaQqfiALeKlQQPYwmM/VvQdLWYL0r.WF0C0CQQGC');

INSERT INTO `role` (`id`,`name`) VALUES (1, 'SECURE_ADMINISTRATOR');
INSERT INTO `role` (`id`,`name`) VALUES (2, 'ADMINISTRATOR');

INSERT INTO `permission` (`id`,`name`) VALUES (1, 'READ_CERTIFICATE');
INSERT INTO `permission` (`id`,`name`) VALUES (2, 'GENERATE_CERTIFICATE');
INSERT INTO `permission` (`id`,`name`) VALUES (3, 'CERTIFICATE_SIGNING_REQUEST');
INSERT INTO `permission` (`id`,`name`) VALUES (4, 'REVOKE_CERTIFICATE');
INSERT INTO `permission` (`id`,`name`) VALUES (5, 'ADD_ROLE_TO_USER');
INSERT INTO `permission` (`id`,`name`) VALUES (6, 'ADD_PERMISSION_TO_ROLE');
INSERT INTO `permission` (`id`,`name`) VALUES (7, 'ADD_ROLE_AND_PERMISSION');
INSERT INTO `permission` (`id`,`name`) VALUES (8, 'READ_ROLES_AND_PERMISSIONS');
INSERT INTO `permission` (`id`,`name`) VALUES (9, 'CREATE_ADMINISTRATOR');
INSERT INTO `permission` (`id`,`name`) VALUES (10, 'WRITE_TRUST_STORE');
INSERT INTO `permission` (`id`,`name`) VALUES (11, 'READ_TRUST_STORE');

INSERT INTO `role_permissions` (`role_id`,`permission_id`) VALUES (1, 1);
INSERT INTO `role_permissions` (`role_id`,`permission_id`) VALUES (1, 2);
INSERT INTO `role_permissions` (`role_id`,`permission_id`) VALUES (1, 3);
INSERT INTO `role_permissions` (`role_id`,`permission_id`) VALUES (1, 5);
INSERT INTO `role_permissions` (`role_id`,`permission_id`) VALUES (1, 6);
INSERT INTO `role_permissions` (`role_id`,`permission_id`) VALUES (1, 7);
INSERT INTO `role_permissions` (`role_id`,`permission_id`) VALUES (2, 4);
INSERT INTO `role_permissions` (`role_id`,`permission_id`) VALUES (2, 8);
INSERT INTO `role_permissions` (`role_id`,`permission_id`) VALUES (2, 5);

INSERT INTO `user_roles` (`user_id`,`role_id`) VALUES (1, 1);
INSERT INTO `user_roles` (`user_id`,`role_id`) VALUES (1, 2);

INSERT INTO `ftp_application` (`id`,`organizational_unit_name`,`host`,`port`, `username`,`password`) VALUES (1, 'payment_concentrator_root_ca_main', NULL, NULL, NULL, NULL);
--INSERT INTO `ftp_application` (`id`,`organizational_unit_name`,`host`,`port`, `username`,`password`) VALUES (2, 'siem_center_1','localhost', 2121, 'siem_center_ftp', 'siem_center_ftp_pass');

--INSERT INTO `application_address` (`id`,`organizational_unit_name`,`url`) VALUES (1, 'siem_center_1','https://localhost:8081/science_center_1/certificate/send-request');