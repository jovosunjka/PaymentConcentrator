INSERT INTO `form_field` (`id`,`name`,`type`,`optional`) VALUES (1, 'MERCAHNT_ID', 'string', 0);
INSERT INTO `form_field` (`id`,`name`,`type`,`optional`) VALUES (2, 'MERCHANT_PASSWORD', 'string', 0);

INSERT INTO `permission` (`id`, `name`) VALUES (1, 'MANAGE_ROLES_AND_PERMISSIONS');
INSERT INTO `permission` (`id`, `name`) VALUES (2, 'PAY');

INSERT INTO `role` (`id`,`name`) VALUES (1, 'ADMIN');
INSERT INTO `role` (`id`,`name`) VALUES (2, 'PAYING');

INSERT INTO `role_permissions` (`role_id`, `permission_id`) VALUES (2, 2);

INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (1, 'card-payment_magazine7', '$2y$12$kqvKSqHvVHXJIcH1/7dfD.0sOGhNACm2ksZ89n0MQKlL.s5jZKRe6', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (2, 'card-payment_magazine8', '$2y$12$ZRc7fUSCPEu6W9fqC7eF7.VV3fZWK8xlclw1OBvMDgWqH4ITM6t..', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (3, 'card-payment_magazine9', '$2y$12$.R300MGcKLpnzABx8TnZaO/lmmzoxzE6j8X6a/YzAewkDHHpuVrtO', 0);

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (3, 2);

INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (1, 'MERCAHNT_ID', 'MERCAHNT_ID_1');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (2, 'MERCHANT_PASSWORD', 'MERCHANT_PASSWORD_1');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (3, 'MERCAHNT_ID', 'MERCAHNT_ID_2');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (4, 'MERCHANT_PASSWORD', 'MERCHANT_PASSWORD_2');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (5, 'MERCAHNT_ID', 'MERCAHNT_ID_3');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (6, 'MERCHANT_PASSWORD', 'MERCHANT_PASSWORD_3');

INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (1, 1);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (2, 2);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (3, 3);