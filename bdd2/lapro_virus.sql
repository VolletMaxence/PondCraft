-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: mysql-lapro.alwaysdata.net
-- Generation Time: Mar 25, 2021 at 09:10 PM
-- Server version: 10.5.8-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lapro_virus`
--

-- --------------------------------------------------------

--
-- Table structure for table `map`
--

CREATE TABLE `map` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `position` varchar(50) NOT NULL,
  `mapNord` int(11) DEFAULT NULL,
  `mapSud` int(11) DEFAULT NULL,
  `mapEst` int(11) DEFAULT NULL,
  `mapOuest` int(11) DEFAULT NULL,
  `idUserDecouverte` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `map`
--

INSERT INTO `map` (`id`, `nom`, `position`, `mapNord`, `mapSud`, `mapEst`, `mapOuest`, `idUserDecouverte`) VALUES
(0, 'Marrai Echenté', 'RCRAHAJZ727ZIAUAZHIUAYHZ1298YZ', 64, 65, 66, 72, 1),
(64, 'Le Chemin Poisseux', '08dd8b55e3fcafc3d1791c5a0fd24cc14fa834e6', 71, 99, 67, NULL, 0),
(65, 'La Montagne des nains', '57dd521d54e14f9ffe05395ba8e625316c417dfd', 0, NULL, NULL, NULL, 0),
(66, 'La Foret Luxuriant', '350fa4b17e0bce2df3572762cfd75f3447962567', NULL, NULL, 0, NULL, 0),
(67, 'La Foret Sombre', '780e67266503d9384b1437149dc344537c30620a', 70, 68, 64, 69, 0),
(68, 'La Foret Enchantée', '7abd0c3ab2370cd32a69745b56688280a858c2e4', 67, NULL, NULL, NULL, 0),
(69, 'Le Dongeon de la mort', '9fb4a15b98e874692ef637876041706dacc22923', NULL, NULL, NULL, 67, 0),
(70, 'La Plaine Poisseux', '973280b0bd6cbaa7b8d0e778a5ee1497b20928fe', NULL, 67, NULL, NULL, 0),
(71, 'La Plaine Enchantée', 'e05a10fba60a58e21e0780e7fa8dfee14b173510', 90, 100, 91, 98, 0),
(72, 'La Cambrousse des loups bien', '0825563d6a8f3bf13c1aafe9a4c7d981b82f2fef', 73, NULL, NULL, 0, 0),
(73, 'La Foret Du pauvre ga', 'bf0cfe57972373e19e7bcf9f92069afffd27aa8f', 74, 72, NULL, NULL, 0),
(74, 'La Cambrousse Du pauvre beaulen', '348682dda6e21dca1ea8bbb6ec51808a0f8fd331', NULL, 73, 75, NULL, 0),
(75, 'La Ville Sombre riri', '9eba44d2444bec34c1a83d2e952096b7b71bade0', 76, NULL, 74, NULL, 0),
(76, 'La Foret Luxuriant mafortcon', '31e275bdd610af44c34a621aad21c02ad49b60b6', 77, 75, NULL, NULL, 0),
(77, 'La Ville Pas belle ripenni', 'fb790ed6a5ea0a6af5402a4ad2d4a8ea35add68e', 78, 76, NULL, NULL, 0),
(78, 'La Campagne Poisseux ga', 'd4fa647ec266d4fea0e5f3df7ac9fdcfb06615fb', NULL, 77, 79, NULL, 0),
(79, 'La Foret des loups dent', '30521f148a57bcd9f126e025295985266f1c2886', NULL, NULL, 78, 80, 0),
(80, 'Le Dongeon des nains gaca', '85834b8015b170544d429fc9277c5fcf02fa0a51', NULL, NULL, 81, 79, 0),
(81, 'La Plaine de la mort labeaudent', '5996ea185ffeb9e012dc7082948602e01a814c4d', NULL, 82, 80, NULL, 0),
(82, 'La Ville Pas belle lenron', '42877980c9554241021f401176b7d3143e3bf9cd', 81, 83, NULL, NULL, 0),
(83, 'La Ville de la mort fort', 'bad0ff6b95a36a3d4be8505a475cf684d7af9283', 82, 84, 87, NULL, 0),
(84, 'Le Marai Du pauvre fort', 'f04d6844ca78841eabb9c299acd8a7cb7637e053', 83, 85, NULL, NULL, 0),
(85, 'La Campagne de la mort mondentpon', '7862eb2d19b2462fdffdd977aeb0f82f4839d1e8', 84, NULL, 86, NULL, 0),
(86, 'La Plaine des nains nifortfeu', 'f618a859ca49ff0ae6ea9ce4a06fdb36ffe1884b', NULL, NULL, 85, NULL, 0),
(87, 'La Cambrousse Du pauvre lenpon', '65e66e2485c92a77aa9409f0dd806f36b34e24ae', NULL, 88, 83, NULL, 0),
(88, 'La Campagne Poisseux monlenfort', '72207ded7f838791a3b7101a67e129679559378a', 87, 89, NULL, NULL, 0),
(89, 'La Plaine Luxuriant bienri', 'b9c62ac94ef2810ae31e5fa7472931adc3d70121', 88, NULL, NULL, NULL, 0),
(90, 'Le Chemin Pas belle roncon', '5d0b4917acb350b6ea734255a10f61ba29683a10', NULL, 71, NULL, NULL, 0),
(91, 'Le Village des nains feucon', '071c2ae2511d219376bf11f47e82ed313a25e239', 92, 103, 71, 102, 0),
(92, 'La Cambrousse Poisseux beaufeu', '605aeefc12be94b9630cb4c8519479033d617d97', 93, 91, NULL, NULL, 0),
(93, 'La Plaine Noir la', '0576115ab654b7b118ae2f1ed77174cc78e40a2c', 94, 92, 95, NULL, 0),
(94, 'La Plaine des loups laron', '03d97c9521d36a4ffe1ae7a897cea9146fd5efc6', NULL, 93, NULL, NULL, 0),
(95, 'Le Marai Sombre rini', 'd7ac300fb8d02d3c4268d0a2f8c447dd0b4398d0', NULL, 107, 93, 100, 0),
(96, 'La Plaine Noir conmafeu', '6b139f9cb2f33a23fa31065f007213ff12da4964', 95, NULL, NULL, 97, 0),
(97, 'La Cambrousse Enchantée ca', '01dd8de96be03dd7c30fc5fe8a20c1784ec2a4a7', NULL, NULL, NULL, 96, 0),
(98, 'Le Village des nains monnardracar', '4925f2ed69ca2a7daffcf8db003713c12733a0cf', NULL, NULL, 99, 71, 0),
(99, 'Le Marai des nains lonfeulaga', 'bf20c0ccbfa1914cbf4c8509208679fc6a9b1f1b', 64, 101, 98, 100, 0),
(100, 'Le Marai Poisseux len', '66fa5c47e3baba6e1e9839119e3498630d88a350', 71, NULL, 95, 99, 0),
(101, 'La Foret Du pauvre carlon', '99d41eeb2c830e91f9f05e8063af6db822be56e8', 99, NULL, NULL, NULL, 0),
(102, 'La Ville Pas belle pon', 'b9760b62709f9ea48c5f123c5cac7d9f58aa9257', NULL, NULL, NULL, 91, 0),
(103, 'Le Chemin de la mort pencon', 'cc2b5d9498dab72af68a64a8deae85a41a9755a1', 91, 104, NULL, NULL, 0),
(104, 'Le Village Pas belle ridentdentni', '2bfd88660e96309b8a859628209cb7a8da862f9f', 103, 105, NULL, NULL, 0),
(105, 'La Cambrousse des loups belmon', 'aa22e110c6ac1f5f0d6df4e0ad2a1fb7855c7666', 104, 106, 107, NULL, 0),
(106, 'Le Chemin Enchantée laponorri', 'e53574176e2a9eb846a98e692492e24de872b0f7', 105, NULL, NULL, NULL, 0),
(107, 'La Foret Sombre mapenmonnar', 'd5e53c5518f586fcd105a6eb55a6f5612d97584a', 95, NULL, 105, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `Personnage`
--

CREATE TABLE `Personnage` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `vie` int(50) NOT NULL,
  `degat` int(50) NOT NULL,
  `idMap` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Personnage`
--

INSERT INTO `Personnage` (`id`, `nom`, `vie`, `degat`, `idMap`) VALUES
(1, 'Vegeta', 100, 100, 90),
(2, 'Goku', 80, 120, 64);

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `id` int(11) NOT NULL,
  `login` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `mdp` varchar(20) NOT NULL,
  `idPersonnage` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`id`, `login`, `prenom`, `mdp`, `idPersonnage`) VALUES
(1, 'Rapidecho', 'Julien', 'Julien1234', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `map`
--
ALTER TABLE `map`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Position` (`position`),
  ADD KEY `mapNord` (`mapNord`),
  ADD KEY `mapSud` (`mapSud`),
  ADD KEY `mapEst` (`mapEst`),
  ADD KEY `mapOuest` (`mapOuest`),
  ADD KEY `idUserDecouverte` (`idUserDecouverte`);

--
-- Indexes for table `Personnage`
--
ALTER TABLE `Personnage`
  ADD PRIMARY KEY (`id`),
  ADD KEY `position` (`idMap`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD KEY `idPersonnage` (`idPersonnage`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `map`
--
ALTER TABLE `map`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;

--
-- AUTO_INCREMENT for table `Personnage`
--
ALTER TABLE `Personnage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
