INSERT INTO `form_field` (`id`,`name`,`type`,`optional`) VALUES (1, 'token', 'string', 0);

INSERT INTO `permission` (`id`, `name`) VALUES (1, 'MANAGE_ROLES_AND_PERMISSIONS');
INSERT INTO `permission` (`id`, `name`) VALUES (2, 'PAY');

INSERT INTO `role` (`id`,`name`) VALUES (1, 'ADMIN');
INSERT INTO `role` (`id`,`name`) VALUES (2, 'PAYING');

INSERT INTO `role_permissions` (`role_id`, `permission_id`) VALUES (2, 2);

INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (1, 'bitcoin_magazine4', '$2y$12$M0SR2pkJbpMJCdRgrgdJaOLmcwp5BtHwMq7nEhFB/7hn/E.YCFKvS', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (2, 'bitcoin_magazine5', '$2y$12$K/QE3JElbhpc2/pVz2/NmukQdbZnnoTzfFMOcoKAJlxK8LClNxopS', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (3, 'bitcoin_magazine6', '$2y$12$AVGVNcivDJkkdoGY4n19iuE.N5Vs9VsiP6dPBOJguIsHFvOgrU022', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (4, 'bitcoin_magazine13', '$2y$12$4NjnB3mrsV1BrKnz4KB96OC3bfAjh/b2jFj42e/O9WNov1dekn3sm', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (5, 'bitcoin_magazine14', '$2y$12$oyrgFAWJUzrqJb.MpIYZr.5mg1U.h04j/HzYupAaTWuS4kpGTJbZm', 0);
INSERT INTO `user` (`id`,`username`,`password`,`currency`) VALUES (6, 'bitcoin_magazine15', '$2y$12$3hpyalozrjUn0eJgCD7hnu4fYAiFnkdeMZksFXUdlsBLLaBbl4RvC', 0);

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (3, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (4, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (5, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (6, 2);

INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (1, 'token', 'token_1');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (2, 'token', 'token_2');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (3, 'token', 'token_3');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (4, 'token', 'token_4');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (5, 'token', 'token_5');
INSERT INTO `property` (`id`,`identifier`,`value`) VALUES (6, 'token', 'token_6');

INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (1, 1);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (2, 2);
INSERT INTO `user_properties` (`user_id`, `property_id`) VALUES (3, 3);