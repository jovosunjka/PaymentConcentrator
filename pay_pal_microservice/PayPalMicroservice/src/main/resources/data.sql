INSERT INTO `form_field` (`id`,`name`,`type`,`optional`) VALUES (1, 'production', 'string', 0);
INSERT INTO `form_field` (`id`,`name`,`type`,`optional`) VALUES (2, 'sandbox', 'string', 0);
INSERT INTO `form_field` (`id`,`name`,`type`,`optional`) VALUES (3, 'subscribe-token', 'string', 1);

INSERT INTO `permission` (`id`, `name`) VALUES (1, 'MANAGE_ROLES_AND_PERMISSIONS');
INSERT INTO `permission` (`id`, `name`) VALUES (2, 'PAY');

INSERT INTO `role` (`id`,`name`) VALUES (1, 'ADMIN');
INSERT INTO `role` (`id`,`name`) VALUES (2, 'PAYING');

INSERT INTO `role_permissions` (`role_id`, `permission_id`) VALUES (2, 2);

INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (1, 'paypal_magazine1', '$2a$10$EEI1h1vIVUrZyFZdugP7Qu1CrZdhXPP13QsWYHWRG5YyFppZpY9RG', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (2, 'paypal_magazine2', '$2a$10$gzt85WpdOwm6oPImL6pkXeEPkPS9Bqb5QofCn3xkSDgpy4Yu/TNOe', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (3, 'paypal_magazine3', '$2a$10$kgnr39eKGg2ptpSi8pggv.zwEwGQ93CZMy6c7/ErYTCvKSXWZS4/6', 0);

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (3, 2);

INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (1, 'production', 'production_1');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (2, 'sandbox', 'sandbox_1');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (3, 'subscribe-token', 'subscribe-token_1');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (4, 'production', 'production_2');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (5, 'sandbox', 'sandbox_2');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (6, 'production', 'production_3');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (7, 'sandbox', 'sandbox_3');

INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (1, 1);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (1, 2);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (1, 3);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (2, 4);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (2, 5);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (3, 6);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (3, 7);