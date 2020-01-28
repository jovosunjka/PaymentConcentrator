INSERT INTO `form_field` (`id`,`name`,`type`,`optional`) VALUES (1, 'token', 'string', 0);

INSERT INTO `permission` (`id`, `name`) VALUES (1, 'MANAGE_ROLES_AND_PERMISSIONS');
INSERT INTO `permission` (`id`, `name`) VALUES (2, 'PAY');

INSERT INTO `role` (`id`,`name`) VALUES (1, 'ADMIN');
INSERT INTO `role` (`id`,`name`) VALUES (2, 'PAYING');

INSERT INTO `role_permissions` (`role_id`, `permission_id`) VALUES (2, 2);

INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (1, 'bitcoin_magazine4', '$2y$12$og/8zXMr0kQG3i9236TKq.dZpakmh/rAKVWbYLB6a7Gjl4E6/.USK', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (2, 'bitcoin_magazine5', '$2y$12$ukTRt4rxqZiMgbftHiIkUus5henjVRO025XXK8A888Xx3uugG4pQu', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (3, 'bitcoin_magazine6', '$2y$12$AwwOXgvo05cQanJbZ.nK/.mtjz1LX8WifLRzD.Pl9fDBdXmBSZYOy', 0);

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (3, 2);

INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (1, 'token', 'production_1');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (2, 'token', 'sandbox_2');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (3, 'token', 'token_3');

INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (1, 1);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (2, 2);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (3, 3);