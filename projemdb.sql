-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 24 Haz 2023, 16:03:18
-- Sunucu sürümü: 10.4.28-MariaDB
-- PHP Sürümü: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `projemdb`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `islemler`
--

CREATE TABLE `islemler` (
  `iID` int(11) NOT NULL,
  `user` varchar(50) NOT NULL,
  `islemAciklama` varchar(250) NOT NULL,
  `islemTutar` double NOT NULL,
  `islemTarih` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `islemler`
--

INSERT INTO `islemler` (`iID`, `user`, `islemAciklama`, `islemTutar`, `islemTarih`) VALUES
(1, 'admin', 'Kahvaltı Malzemeleri', 125, '2021-04-01'),
(2, 'admin', 'Kahve Malzemeleri', 100, '2021-04-04'),
(3, 'gokhan', 'Manav giderleri', 500, '2021-04-14'),
(4, 'gokhan', 'Kahve Giderleri', 17.5, '2021-04-06'),
(5, 'celil', 'Temizlik Malzemeleri', 210, '2021-05-01'),
(6, 'gokhan', 'Manav Giderleri', 87.4, '2021-03-10');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `koltuksecme`
--

CREATE TABLE `koltuksecme` (
  `koltukID` int(11) NOT NULL,
  `btn1` bit(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `koltuksecme`
--

INSERT INTO `koltuksecme` (`koltukID`, `btn1`) VALUES
(1, b'01'),
(2, b'00');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `login`
--

CREATE TABLE `login` (
  `kID` int(11) NOT NULL,
  `kul_ad` varchar(100) NOT NULL,
  `sifre` varchar(100) NOT NULL,
  `yetki` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `login`
--

INSERT INTO `login` (`kID`, `kul_ad`, `sifre`, `yetki`) VALUES
(1, 'fırat', '202cb962ac59075b964b07152d234b70', 1),
(2, 'ferhat', '202cb962ac59075b964b07152d234b70', 0),
(5, 'celil', '250cf8b51c773f3f8dc8b4be867a9a02', 0),
(14, 'gokhan', '202cb962ac59075b964b07152d234b70', 0),
(15, 'ahmet', '8d5e957f297893487bd98fa830fa6413', 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ucusekleme`
--

CREATE TABLE `ucusekleme` (
  `ucusID` int(11) NOT NULL,
  `Nereden` varchar(50) NOT NULL,
  `Nereye` varchar(50) NOT NULL,
  `kalkis_Tarih` date NOT NULL,
  `koltukID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `ucusekleme`
--

INSERT INTO `ucusekleme` (`ucusID`, `Nereden`, `Nereye`, `kalkis_Tarih`, `koltukID`) VALUES
(4, 'ankara', 'urfa', '2023-06-29', 1),
(5, 'aydın', 'hatay', '2023-07-03', 2),
(6, 'izmir', 'urfa', '2023-06-05', 3);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `islemler`
--
ALTER TABLE `islemler`
  ADD PRIMARY KEY (`iID`);

--
-- Tablo için indeksler `koltuksecme`
--
ALTER TABLE `koltuksecme`
  ADD PRIMARY KEY (`koltukID`);

--
-- Tablo için indeksler `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`kID`);

--
-- Tablo için indeksler `ucusekleme`
--
ALTER TABLE `ucusekleme`
  ADD PRIMARY KEY (`ucusID`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `islemler`
--
ALTER TABLE `islemler`
  MODIFY `iID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `koltuksecme`
--
ALTER TABLE `koltuksecme`
  MODIFY `koltukID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Tablo için AUTO_INCREMENT değeri `login`
--
ALTER TABLE `login`
  MODIFY `kID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Tablo için AUTO_INCREMENT değeri `ucusekleme`
--
ALTER TABLE `ucusekleme`
  MODIFY `ucusID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
