INSERT INTO `form_field` (`id`,`name`,`type`,`optional`) VALUES (1, 'production', 'string', 0);
INSERT INTO `form_field` (`id`,`name`,`type`,`optional`) VALUES (2, 'sandbox', 'string', 0);
INSERT INTO `form_field` (`id`,`name`,`type`,`optional`) VALUES (3, 'subscribe-token', 'string', 1);

INSERT INTO `permission` (`id`, `name`) VALUES (1, 'MANAGE_ROLES_AND_PERMISSIONS');
INSERT INTO `permission` (`id`, `name`) VALUES (2, 'PAY');

INSERT INTO `role` (`id`,`name`) VALUES (1, 'ADMIN');
INSERT INTO `role` (`id`,`name`) VALUES (2, 'PAYING');

INSERT INTO `role_permissions` (`role_id`, `permission_id`) VALUES (2, 2);

INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (1, 'paypal_magazine1', '$2y$12$zmgwEBI0HhJHAcbva32tCOsJRNB7kVMewGg6CwS.vYIFxX8PApCDm', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (2, 'paypal_magazine2', '$2y$12$DEV4WuFLnIw3juFkR98iDuy5GKbSRxx1h6RHEx0T.yQPPwRVdysUa', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (3, 'paypal_magazine3', '$2y$12$9UhlqNzOzK.vncSx02l8t.zdc5j/NeTvMsuXadH1.dzeT4jpek0SC', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (4, 'paypal_magazine10', '$2y$12$.HyxMtNYzy/G6/PjzMQXmuPN/uS.RCdeOpc47/iXzXUJwwExTQlfC', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (5, 'paypal_magazine11', '$2y$12$9kTROjjpICzeGeIHqw6Tke6KohDU6.9gGxhH3pjVj7Cgq/ZUXUUwa', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (6, 'paypal_magazine12', '$2y$12$DXDW.hVGmKS0gKAK5pS.P.BejD6f7M7Jcudaj84Q14oB7o0NhnJ7i', 0);

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (3, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (4, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (5, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (6, 2);

INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (1, 'production', 'production_1');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (2, 'sandbox', 'sandbox_1');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (3, 'subscribe-token', 'subscribe-token_1');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (4, 'production', 'production_2');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (5, 'sandbox', 'sandbox_2');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (6, 'production', 'production_3');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (7, 'sandbox', 'sandbox_3');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (8, 'production', 'production_4');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (9, 'sandbox', 'sandbox_4');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (10, 'subscribe-token', 'subscribe-token_4');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (11, 'production', 'production_5');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (12, 'sandbox', 'sandbox_5');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (13, 'subscribe-token', 'subscribe-token_5');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (14, 'production', 'production_6');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (15, 'sandbox', 'sandbox_6');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (16, 'subscribe-token', 'subscribe-token_6');

INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (1, 1);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (1, 2);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (1, 3);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (2, 4);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (2, 5);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (3, 6);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (3, 7);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (4, 8);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (4, 9);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (4, 10);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (5, 11);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (5, 12);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (5, 13);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (6, 14);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (6, 15);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (6, 16);