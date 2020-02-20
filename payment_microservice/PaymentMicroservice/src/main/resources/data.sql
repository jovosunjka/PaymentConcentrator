-- password==username

INSERT INTO `permission` (`id`, `name`) VALUES (1, 'MANAGE_ROLES_AND_PERMISSIONS');
INSERT INTO `permission` (`id`, `name`) VALUES (2, 'PAY');

INSERT INTO `role` (`id`,`name`) VALUES (1, 'ADMIN');
INSERT INTO `role` (`id`,`name`) VALUES (2, 'PAYING');

INSERT INTO `role_permissions` (`role_id`, `permission_id`) VALUES (1, 1);
INSERT INTO `role_permissions` (`role_id`, `permission_id`) VALUES (2, 2);

INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (1, 'je7WxcL7FhCnouG0Iyiodw==','n6rHo+1ts4hieM55l36KsQ==', '$2y$12$TxHOkrOHJBW39ZuH0hxE0e8Jfk53XEQI66R9KXrmaIgZn.SdRKYei');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (2, 'DySpQJelow/4CFkwKTBUdg==','fHRBA10JcytXCvvn6jxxLg==', '$2y$12$aO9J/cE8XdZAQ3VWPu5nneHeRPIklTo53pHCnheYsVz7gjA1fAUIa');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (3, 'VKkv5WoZGDqCxKPzMxU96A==','E2qtRYx+zGFHvGWFwvI/MQ==', '$2y$12$FMTUbM2zAOXIdRaJKGC8we/pr7qAVz2XEG1l/RKMvIrfTJ8aYCf3S');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (4, 'aRfvKrifz+q8uAs4bZZyDA==','qG7Tj11goK87BtrQi7LImw==', '$2y$12$V7WT28jfRij5c7tMaKqir.2gIytNBhP0cgehYDzHs1A5ct1tT3Yvy');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (5, 'SSd0ubn9opNUeCh18Txl8g==','PFGQx2Y5hmLvtOt92DY07g==', '$2y$12$sVnSKgibZD2JbgaSIPdCWO2FUPiV8wasotmvFCAbSJakW327ZP77S');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (6, '/nf+DP0YzmhpzhFPO0CBQA==','ex1gebq4X0zFqfylPBaCvQ==', '$2y$12$vKbakU5ZxjA3oikG1p4m9ufRh/Tkmgppn7RnkJ7S4zBpVEeNypLBO');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (7, 'zhWXFYsagHEx51dcpJfMQg==','+I3JgCBgwe7wjg+ioj1G9w==', '$2y$12$jHTppI44NNwzLFv3JOgcmuqo8wxCkHq9aW/YKK3.T96McX1LPp5SC');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (8, 'HiXC3xPsmYmhTAgIRFoBwg==','0d3QGtSFrv+RMngPg9u20Q==', '$2y$12$RS5k3aTzBmmAFU1Ct20T0eShFNah7V2orakxbLZarbTQVx/1/vUim');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (9, 'deSr9ykVBlN+Qt3hbktagg==','lWEYxHiHP0svWSbNY/ZD7w==', '$2y$12$zKna0dLdcfFKVlxxIXYHQuefuqZpEWuOUxLk9eJn5Dzva3gfANNQO');
INSERT INTO `user` (`id`,`name`,`username`,`password`) VALUES (10, 'u+HkrzBMRhUCindvRdqgqg==','l8RoeVYY7xiLdARF2h7+3w==', '$2y$12$ofIF6vC/IVAJqnR9lalGUe5EfpWlhk2Wq6VmPGXmb44T3GEuzrHRa');

INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (1, 'paypal', 'tjbtyevqIbvsWkrbSYJsUjmwNZO1F3SRL8Y7YXnAayc=', 'izylmT4ANxOrngEk4dawYw==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (2, 'paypal', 'xHIg4deR6ckJz77w9088NzmwNZO1F3SRL8Y7YXnAayc=', 'wR/seQInMXkHs1nWCccYzQ==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (3, 'paypal', '	S/NpAjAq40HlzWFs0/hDnDmwNZO1F3SRL8Y7YXnAayc=', 'Wns5t7pgISAxIFZx88oGCQ==	', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (4, 'bitcoin', '7msvXjxJbsoJ5Py+zrOtd1b3TQEXoIpmE+F+ymUo5ks=', 'z1c3TUGTUluxglNh7d862w==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (5, 'bitcoin', '7msvXjxJbsoJ5Py+zrOtd/Y6z1mgHnw3nDvgqhk4sNY=', '5ZPpEB8fAlR0YAOmnNwL8Q==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (6, 'bitcoin', '7msvXjxJbsoJ5Py+zrOtd3j9sJ369IKQqYUoCky8Rew=', 'LoMlnU9pT1DOfKC7p3862A==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (7, 'card-payment', 'rzYeunfXpKlhJXVYxgVqTfRVPO+kDUhJyfMzeC7w/qE=', 'QFfehIB/UT+Jr+XHh5JiEQ==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (8, 'card-payment', 'rzYeunfXpKlhJXVYxgVqTQYEaJV8CE1GhgsFBuyoLAU=', '0EjcXfoi0Fe/6DtOxBhNdQ==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (9, 'card-payment', 'rzYeunfXpKlhJXVYxgVqTeyjccFG/F4o/NtISxzf2kg=', 'jr+wI7ejhOF+yv9QnqX5yQ==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (10, 'paypal', 'tjbtyevqIbvsWkrbSYJsUqkyTXVjCm09PM1evUrWq1E=', 'hGVvGZuEoI1ZEBl6IK7Wsg==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (11, 'paypal', 'tjbtyevqIbvsWkrbSYJsUvgp2KKowKGhUhNsf4/4ICQ=', '9S0CmrA3j/gNvTEqbORB3g==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (12, 'paypal', 'tjbtyevqIbvsWkrbSYJsUjvC/OF0j7i8jTf5QafO0VY=', 'gX9/noF9MfAGntp/klP1AQ==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (13, 'bitcoin', '7msvXjxJbsoJ5Py+zrOtd6+1ob1ENDMs4bnLTIg8NIE=', 'DNHWTTrPS00FbHTW4QJQtg==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (14, 'bitcoin', '	7msvXjxJbsoJ5Py+zrOtd1VlCx2p59NpRVg6BMytXdI=', 'kCKSHfSoJbBfk7DQxMpF8A==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (15, 'bitcoin', '7msvXjxJbsoJ5Py+zrOtd84ZXmoRkr9aSxZdyZ1CDik=', 'mUGbVKNuHmcXupsnxMmTHw==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (16, 'card-payment', 'rzYeunfXpKlhJXVYxgVqTdhrD0V/+vmfAK9zgqQNUMU=', 'JCSw89M/8A/5BraZRbuHRg==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (17, 'card-payment', 'rzYeunfXpKlhJXVYxgVqTW/UEp4FXFgB1Fn0dT62bN0=', '4Sbpfdy+vyJgx4jxmPBuWQ==', NULL, 0);
INSERT INTO `payment_account` (`id`, `type`, `username`, `password`, `access_token`, `currency`) VALUES (18, 'card-payment', 'rzYeunfXpKlhJXVYxgVqTTjLU0esuWhWTu+js6+DW2o=', 'EP0d6nz65mB9HHY5omIUgQ==', NULL, 0);

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