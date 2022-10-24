-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 15, 2021 at 02:38 AM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shop_database_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `home_images_slider`
--

CREATE TABLE `home_images_slider` (
  `pictureID` bigint(20) NOT NULL,
  `pictureName` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `home_images_slider`
--

INSERT INTO `home_images_slider` (`pictureID`, `pictureName`) VALUES
(1, 'https://shopdatabase.000webhostapp.com/forest-1950402_1920.jpg'),
(2, 'https://shopdatabase.000webhostapp.com/mountains-1587287_1920.jpg'),
(3, 'https://shopdatabase.000webhostapp.com/nature-3082832_1920.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `items_table`
--

CREATE TABLE `items_table` (
  `itemID` bigint(20) NOT NULL,
  `itemName` longtext NOT NULL,
  `itemImage` longtext NOT NULL,
  `itemPrice` longtext NOT NULL,
  `itemCategory` longtext NOT NULL,
  `itemAmount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `items_table`
--

INSERT INTO `items_table` (`itemID`, `itemName`, `itemImage`, `itemPrice`, `itemCategory`, `itemAmount`) VALUES
(1, 'Procesor AMD AM4 Ryzen 9 5900X 3.7GHz', 'https://shopdatabase.000webhostapp.com/procesor_amd_am4_ryzen_9_5900x_3-7ghz-1610094906-457.png', '$550.90', 'Processor', 53),
(2, 'Memorija DDR4 16GB(2x8GB) 3600MHz', 'https://shopdatabase.000webhostapp.com/memorija_ddr4_4gb_2666mhz_patriot_signature_psd44g266681b-_bulk-1618385327-780.png', '$22.45', 'Memory', 21),
(3, 'Matična ploča MSI H410M-A PRO HDMI/DVI/M.2', 'https://shopdatabase.000webhostapp.com/maticna_ploca_msi_h410m-a_pro-1618048610-864.png', '$125.99', 'Motherboard', 7),
(4, 'Procesor AMD AM4 Ryzen 9 5900X 3.7GHz', 'https://shopdatabase.000webhostapp.com/procesor_amd_am4_ryzen_9_5900x_3-7ghz-1610094906-457.png', '$550.90', 'Processor', 53),
(5, 'Memorija DDR4 16GB(2x8GB) 3600MHz', 'https://shopdatabase.000webhostapp.com/memorija_ddr4_4gb_2666mhz_patriot_signature_psd44g266681b-_bulk-1618385327-780.png', '$22.45', 'Memory', 21),
(6, 'Matična ploča MSI H410M-A PRO HDMI/DVI/M.2', 'https://shopdatabase.000webhostapp.com/maticna_ploca_msi_h410m-a_pro-1618048610-864.png', '$125.99', 'Motherboard', 7);

-- --------------------------------------------------------

--
-- Table structure for table `users_table`
--

CREATE TABLE `users_table` (
  `userID` bigint(20) NOT NULL,
  `userName` longtext NOT NULL,
  `userSurname` longtext NOT NULL,
  `userUsername` longtext NOT NULL,
  `userPassword` longtext NOT NULL,
  `userAddress` longtext NOT NULL,
  `userPhone` longtext NOT NULL,
  `userCredit` longtext NOT NULL,
  `userRole` longtext NOT NULL,
  `userProfilePicture` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users_table`
--

INSERT INTO `users_table` (`userID`, `userName`, `userSurname`, `userUsername`, `userPassword`, `userAddress`, `userPhone`, `userCredit`, `userRole`, `userProfilePicture`) VALUES
(1, 'Petar', 'Milosevic', 'petar123', 'petar123', 'Nema', 'Nema', '56.78', 'admin', 'https://shopdatabase.000webhostapp.com/MyPictureLogoLinkRelShortCut_ICON_WebSite.png'),
(2, 'Petar', 'Petrovic', 'petar234', 'petar234', 'Nema', '4423462211', '00.00', 'admin', 'https://shopdatabase.000webhostapp.com/AccountIcon.png'),
(3, 'Jovan', 'Markovic', 'jovan123', 'jovan123', '/', '/', '25.00', 'user', 'https://shopdatabase.000webhostapp.com/MyPictureLogoLinkRelShortCut_ICON_WebSite.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `home_images_slider`
--
ALTER TABLE `home_images_slider`
  ADD PRIMARY KEY (`pictureID`);

--
-- Indexes for table `items_table`
--
ALTER TABLE `items_table`
  ADD PRIMARY KEY (`itemID`);

--
-- Indexes for table `users_table`
--
ALTER TABLE `users_table`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `home_images_slider`
--
ALTER TABLE `home_images_slider`
  MODIFY `pictureID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `items_table`
--
ALTER TABLE `items_table`
  MODIFY `itemID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users_table`
--
ALTER TABLE `users_table`
  MODIFY `userID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
