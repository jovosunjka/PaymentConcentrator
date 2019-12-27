-- password==username

INSERT INTO `permission` (`id`, `name`) VALUES (1, 'MANAGE_ROLES_AND_PERMISSIONS');
INSERT INTO `permission` (`id`, `name`) VALUES (2, 'PAY');

INSERT INTO `role` (`id`,`name`) VALUES (1, 'ADMIN');
INSERT INTO `role` (`id`,`name`) VALUES (2, 'PAYING');

INSERT INTO `role_permissions` (`role_id`, `permission_id`) VALUES (1, 1);
INSERT INTO `role_permissions` (`role_id`, `permission_id`) VALUES (2, 2);

INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (1, 'Admin','admin', '$2y$12$TxHOkrOHJBW39ZuH0hxE0e8Jfk53XEQI66R9KXrmaIgZn.SdRKYei');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (2, 'Magazine1','magazine1', '$2y$12$aO9J/cE8XdZAQ3VWPu5nneHeRPIklTo53pHCnheYsVz7gjA1fAUIa');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (3, 'Magazine2','magazine2', '$2y$12$FMTUbM2zAOXIdRaJKGC8we/pr7qAVz2XEG1l/RKMvIrfTJ8aYCf3S');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (4, 'Magazine3','magazine3', '$2y$12$V7WT28jfRij5c7tMaKqir.2gIytNBhP0cgehYDzHs1A5ct1tT3Yvy');


INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (2, 2);

