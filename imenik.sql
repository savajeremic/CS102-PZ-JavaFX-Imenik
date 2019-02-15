-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 01, 2016 at 09:01 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `imenik`
--

-- --------------------------------------------------------

--
-- Table structure for table `kontakti`
--

CREATE TABLE `kontakti` (
  `id` int(11) NOT NULL,
  `ime` varchar(22) NOT NULL,
  `prezime` varchar(22) NOT NULL,
  `broj` varchar(255) NOT NULL,
  `userId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kontakti`
--

INSERT INTO `kontakti` (`id`, `ime`, `prezime`, `broj`, `userId`) VALUES
(7, 'Katarina', 'Novakovic', '064-3614214', 2),
(8, 'Marko', 'Markovic', '069-1134522', 2),
(9, 'Igor', 'Jeremic', '064-1802418', 2),
(10, 'Petar', 'Petrovic', '061-2342345', 2),
(12, 'Ognjen', 'Ristic', '061-2209544', 3),
(13, 'Marko', 'Nikolic', '061-9573374', 3),
(14, 'Jovan', 'Kostic', '066-2345456', 3),
(15, 'Nikola', 'Rakocevic', '064-3398534', 3);

-- --------------------------------------------------------

--
-- Table structure for table `korisnici`
--

CREATE TABLE `korisnici` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnici`
--

INSERT INTO `korisnici` (`id`, `username`, `password`, `admin`) VALUES
(1, 'admin', 'admin', 1),
(2, 'sava', '123', 0),
(3, 'jack', '123123', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kontakti`
--
ALTER TABLE `kontakti`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `korisnici`
--
ALTER TABLE `korisnici`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kontakti`
--
ALTER TABLE `kontakti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `korisnici`
--
ALTER TABLE `korisnici`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
