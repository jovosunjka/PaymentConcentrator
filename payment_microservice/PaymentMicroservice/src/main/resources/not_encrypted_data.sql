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
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (5, 'Magazine4','magazine4', '$2y$12$sVnSKgibZD2JbgaSIPdCWO2FUPiV8wasotmvFCAbSJakW327ZP77S');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (6, 'Magazine5','magazine5', '$2y$12$vKbakU5ZxjA3oikG1p4m9ufRh/Tkmgppn7RnkJ7S4zBpVEeNypLBO');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (7, 'Magazine6','magazine6', '$2y$12$jHTppI44NNwzLFv3JOgcmuqo8wxCkHq9aW/YKK3.T96McX1LPp5SC');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (8, 'Magazine7','magazine7', '$2y$12$RS5k3aTzBmmAFU1Ct20T0eShFNah7V2orakxbLZarbTQVx/1/vUim');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (9, 'Magazine8','magazine8', '$2y$12$zKna0dLdcfFKVlxxIXYHQuefuqZpEWuOUxLk9eJn5Dzva3gfANNQO');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (10, 'Magazine9','magazine9', '$2y$12$ofIF6vC/IVAJqnR9lalGUe5EfpWlhk2Wq6VmPGXmb44T3GEuzrHRa');

INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (1, 'paypal', 'paypal_magazine1', 'g$b)x+3sJ4', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (2, 'paypal', 'paypal_magazine2', 'gmJ(v2=THP', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (3, 'paypal', 'paypal_magazine3', '@z]m5P(.HG', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (4, 'bitcoin', 'bitcoin_magazine4', '5]D4}Mw4s<', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (5, 'bitcoin', 'bitcoin_magazine5', 'Epr8^n}E?=', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (6, 'bitcoin', 'bitcoin_magazine6', '<Kg8bPZk%[', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (7, 'card-payment', 'card-payment_magazine7', '5NMf@NENv]', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (8, 'card-payment', 'card-payment_magazine8', '}}qHA37DsG', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (9, 'card-payment', 'card-payment_magazine9', '?S/3/phh[^', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (10, 'paypal', 'paypal_magazine10', 'A5^:bJwe:\', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (11, 'paypal', 'paypal_magazine11', 'Ag+hj2:@rQ', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (12, 'paypal', 'paypal_magazine12', '^ZHh9U<J,K', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (13, 'bitcoin', 'bitcoin_magazine13', '8aCL!X\xs7', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (14, 'bitcoin', 'bitcoin_magazine14', 'fGXWJ$,g7K', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (15, 'bitcoin', 'bitcoin_magazine15', 'ftDF_T6%Bk', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (16, 'card-payment', 'card-payment_magazine16', '~8Z6ycq9~s', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (17, 'card-payment', 'card-payment_magazine17', '_,M>d,3&/y', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (18, 'card-payment', 'card-payment_magazine18', 'r9PJEn''}q$', NULL, 0);

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (3, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (4, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (5, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (6, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (7, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (8, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (9, 2);
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES (10, 2);

INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (2, 1);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (2, 4);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (3, 2);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (3, 7);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (4, 5);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (4, 8);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (5, 6);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (5, 9);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (6, 3);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (6, 13);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (7, 10);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (7, 16);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (8, 14);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (8, 17);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (9, 12);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (9, 18);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (10, 11);
INSERT INTO `user_accounts` (`user_id`, `payment_account_id`) VALUES (10, 15);