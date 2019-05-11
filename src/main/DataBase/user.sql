-- --------------------------------------------------------
-- 主機:                           127.0.0.1
-- 服務器版本:                        10.2.9-MariaDB - mariadb.org binary distribution
-- 服務器操作系統:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 導出  表 itrdb.users 結構
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `work_id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_work_id_unique` (`work_id`),
  UNIQUE KEY `users_email_unique` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 正在導出表  itrdb.users 的資料：~9 rows (大約)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `name`, `work_id`, `email`, `password`, `remember_token`, `created_at`, `updated_at`) VALUES
	(1, '林炳榮', '0610892', 'SYRDJOR8Wo@systex.com', '$2a$10$vkjItDq9M2D/9DcevYi5..FIG79Z7zpX5p3grJnq6GjvFpXkf1PZy', NULL, NULL, NULL),
	(2, '李長勇', '0690109', 'QPTfTv8DpA@systex.com', '$2a$10$QuPIb6bRwqBhxBprrONxPOFgHtTJWRkkpO9m53yBjl15p0.3rTXJu', NULL, NULL, NULL),
	(3, '林一平', '0700022', 'bT68BLOkJA@systex.com', '$2a$10$LYWCgLnBiVEMrjPPb/yoO.oRZVp2GsiwtKR70wKrDd/mcKMzWWg9q', NULL, NULL, NULL),
	(4, '劉堯民', '1100150', '5GcwXHEgoW@systex.com', '$2a$10$8lwlU/QYe9PHzJ9sqINooOG5eGJVrsNBiHEsmOqAL01F0WuBcKUZK', NULL, NULL, NULL),
	(5, '韓守忠', '1105028S', 'XvQnJEOFV4@systex.com', '$2a$10$5JtO.AV931pOtIWpSjxcfOKNnoV31LPKbGqDcMUgzU5UIWn6iaBrO', NULL, NULL, NULL),
	(6, '江相木', '0690186', 'uVTEeU59hc@systex.com', '$2a$10$.j4vQtnmaq16aFR5fxsscet36DguOKJ5iI0pmox6iA6CkqksYjnhy', NULL, NULL, NULL),
	(7, '王秀鴻', '0690187', 'OhItRhwi24@systex.com', '$2a$10$0K4AApBoZJ1/Q61ytcrb/eCf5ZM3OmmWEwyzD085/Qut6oB.fwjGG', NULL, NULL, NULL),
	(8, '黃湘君', '0694386', '0YSQFdL7xH@systex.com', '$2a$10$k.TbBOFGR5X7DkbXhVV/7eRdAMI93N/QYfZJ9Bz.YntR/TTIy7YxK', NULL, NULL, NULL),
	(9, '鄭道鴻', '0800015', 'cGTDWNbpE5@systex.com', '$2a$10$CSUFZTzNjBK90nYxURJGu.zOYT.EODdDKxxpGNoMHjrzEgVsjsZuC', NULL, NULL, NULL),
	(10, '江劍凡', '0690237', 'c7gdQ4b9e6@systex.com', '$2a$10$S2eaM3BbD7vcA3mvfjwwxOsPVQ4rnjJ/ASuBle8ksDHymcvizswZS', NULL, NULL, NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
