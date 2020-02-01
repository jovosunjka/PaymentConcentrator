INSERT INTO `form_field` (`id`,`name`,`type`,`optional`) VALUES (1, 'MERCAHNT_ID', 'string', 0);
INSERT INTO `form_field` (`id`,`name`,`type`,`optional`) VALUES (2, 'MERCHANT_PASSWORD', 'string', 0);

INSERT INTO `permission` (`id`, `name`) VALUES (1, 'MANAGE_ROLES_AND_PERMISSIONS');
INSERT INTO `permission` (`id`, `name`) VALUES (2, 'PAY');

INSERT INTO `role` (`id`,`name`) VALUES (1, 'ADMIN');
INSERT INTO `role` (`id`,`name`) VALUES (2, 'PAYING');

INSERT INTO `role_permissions` (`role_id`, `permission_id`) VALUES (2, 2);

INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (1, 'card-payment_magazine7', '$2y$12$eR.JJR4SotlD3ihcbHdOaet4RwLml.0bbRCZKzcgSORocnNerAlVu', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (2, 'card-payment_magazine8', '$2y$12$/wY873E5qzSLk5v2NIL8t.tKcsnSyk4wu.04ECrbS6OIhj/gcMJ9q', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (3, 'card-payment_magazine9', '$2y$12$QW/r9HWhAprHDCu.tdfECOIqKv7Y8i4cL.eNF5sbz2I28eWTzvZXO', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (4, 'card-payment_magazine16', '$2y$12$c0M8OCbg/jlRTA5DimsKTecCF9aIutv4916ogRp/uqrqSu6r1BTeS', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (5, 'card-payment_magazine17', '$2y$12$gur7McSellarMiLe0o2IxusXlQ6mIW0IHYueNb0PPe80JzZagqZAa', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (6, 'card-payment_magazine18', '$2y$12$00MfxY7OQlRfrvmHJtCUnuJhEBflJS4kRBL7a986A3Y2RZXFQrAQO', 0);

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (3, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (4, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (5, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (6, 2);

INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (1, 'MERCAHNT_ID', 'magazine1_bank');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (2, 'MERCHANT_PASSWORD', 'magazine1_pass_bank');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (3, 'MERCAHNT_ID', 'magazine2_bank');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (4, 'MERCHANT_PASSWORD', 'magazine2_pass_bank');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (5, 'MERCAHNT_ID', 'magazine3_bank');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (6, 'MERCHANT_PASSWORD', 'magazine3_pass_bank');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (7, 'MERCAHNT_ID', 'magazine4_bank');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (8, 'MERCHANT_PASSWORD', 'magazine4_pass_bank');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (9, 'MERCAHNT_ID', 'magazine5_bank');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (10, 'MERCHANT_PASSWORD', 'magazine5_pass_bank');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (11, 'MERCAHNT_ID', 'magazine6_bank');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (12, 'MERCHANT_PASSWORD', 'magazine6_pass_bank');

INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (1, 1);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (1, 2);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (2, 3);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (2, 4);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (3, 5);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (3, 6);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (4, 7);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (4, 8);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (5, 9);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (5, 10);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (6, 11);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (6, 12);