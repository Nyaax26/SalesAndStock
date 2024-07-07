ADIMLAR

1-)Xampp'ı başlatıyoruz.
2-)LocalHost'u açıp yeni bir veritabanı oluşturuyoruz.
3-)"satisvestok" adında ve comboboxtan "utf8mb4_turkish_ci" seçiyoruz.
4-)Localhosttan satisvestok seçerek sql dosyamızı okutuyoruz.
5-)Projeyi başlatabiliriz ve dilediğiniz işlemleri yapabilirsiniz.

STEPS

1-) We start Xampp.
2-) We can convert LocalHost and create a new database.
3-) We select "satisvestok" and "utf8mb4_turkish_ci" from the opened box.
4-) We read our sql file by selecting satisvestok from localhost.
5-) You can start the project and perform the operations you want.


**-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 25 Haz 2024, 14:17:25
-- Sunucu sürümü: 10.4.32-MariaDB
-- PHP Sürümü: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `satisvestok`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `accounts`
--

CREATE TABLE `accounts` (
  `Id` int(11) NOT NULL,
  `YetkiId` int(11) DEFAULT NULL,
  `PersonelId` int(11) DEFAULT NULL,
  `Sifre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `accounts`
--

INSERT INTO `accounts` (`Id`, `YetkiId`, `PersonelId`, `Sifre`) VALUES
(8, 1, 10, '123'),
(10, 3, 12, '12'),
(11, 3, 13, '123'),
(12, 2, 11, '1234');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kategori`
--

CREATE TABLE `kategori` (
  `Id` int(11) NOT NULL,
  `Adi` varchar(255) DEFAULT NULL,
  `ParentId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `kategori`
--

INSERT INTO `kategori` (`Id`, `Adi`, `ParentId`) VALUES
(11, 'Elektronik Cihazlar', 0),
(12, 'Ev Eşyaları', 0),
(15, 'Elektronik Cihazlar', 0),
(16, 'Monitör', 0),
(21, 'Kitaplar', 0),
(24, 'Kasalar', 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `musteri`
--

CREATE TABLE `musteri` (
  `Id` int(11) NOT NULL,
  `AdiSoyadi` varchar(255) DEFAULT NULL,
  `Telefon` varchar(255) DEFAULT NULL,
  `Adres` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `musteri`
--

INSERT INTO `musteri` (`Id`, `AdiSoyadi`, `Telefon`, `Adres`) VALUES
(7, 'Ali Veli', '0543', 'Ankara Gazi Üniversitesi'),
(10, 'Ozan Tufan', '05788898', 'İngiltere Hull City'),
(11, 'Hüseyin', 'Taş', 'Eskişehir/Tepebaşı Osman Abi');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `personel`
--

CREATE TABLE `personel` (
  `Id` int(11) NOT NULL,
  `adiSoyadi` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `personel`
--

INSERT INTO `personel` (`Id`, `adiSoyadi`, `email`) VALUES
(10, 'Satış ve Stok', 'satisstok@gmail.com'),
(11, 'Yarı Yetkili', 'personel@gmail.com'),
(12, 'Yetkisiz Eleman', 'yetkisizeleman@gmail.com');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `satis`
--

CREATE TABLE `satis` (
  `Id` int(11) NOT NULL,
  `UrunId` int(11) DEFAULT NULL,
  `MusteriId` int(11) DEFAULT NULL,
  `Tarih` date DEFAULT NULL,
  `Adet` int(11) DEFAULT NULL,
  `PersonelId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `satis`
--

INSERT INTO `satis` (`Id`, `UrunId`, `MusteriId`, `Tarih`, `Adet`, `PersonelId`) VALUES
(1, 17, 10, '2024-06-12', 100, 10),
(2, 17, 11, '2024-06-12', 50, 10),
(3, 13, 7, '2024-06-12', 21, 10);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `sehirler`
--

CREATE TABLE `sehirler` (
  `Id` int(11) NOT NULL,
  `SehirId` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `sehirler`
--

INSERT INTO `sehirler` (`Id`, `SehirId`) VALUES
(3, 'Ankara'),
(5, 'Eskişehir');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `stok`
--

CREATE TABLE `stok` (
  `Id` int(11) NOT NULL,
  `UrunId` int(11) DEFAULT NULL,
  `PersonelId` int(11) DEFAULT NULL,
  `Tarih` date DEFAULT NULL,
  `Adet` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `stok`
--

INSERT INTO `stok` (`Id`, `UrunId`, `PersonelId`, `Tarih`, `Adet`) VALUES
(1, 12, 10, '2024-06-07', 5),
(2, 12, 10, '2024-06-01', 154),
(3, 13, 10, '2024-06-06', 14),
(4, 12, 10, '2022-08-05', 222),
(5, 12, 10, '2024-06-14', 122),
(6, 14, 10, '2024-06-14', 12),
(7, 12, 10, '2024-06-14', 123),
(8, 12, 10, '2024-06-09', -19),
(9, 12, 10, '2024-06-12', -25),
(10, 12, 10, '2024-06-04', -200),
(11, 15, 10, '2024-06-12', 200),
(12, 17, 10, '2024-06-12', 200),
(13, 17, 10, '2024-06-12', -200),
(14, 12, 10, '2024-06-12', -200),
(15, 12, 10, '2024-06-12', -260),
(16, 17, 10, '2024-06-12', -100),
(17, 17, 10, '2024-06-12', -50),
(18, 13, 10, '2024-06-12', -21);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `urunler`
--

CREATE TABLE `urunler` (
  `Id` int(11) NOT NULL,
  `Adi` varchar(255) DEFAULT NULL,
  `KategoriId` int(11) DEFAULT NULL,
  `Tarih` date DEFAULT NULL,
  `Fiyat` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `urunler`
--

INSERT INTO `urunler` (`Id`, `Adi`, `KategoriId`, `Tarih`, `Fiyat`) VALUES
(12, 'Telefon', 11, '2024-06-09', 366),
(13, 'Bilgisayar', 11, '2024-06-09', 15),
(14, 'Mouse', 11, '2024-06-02', 2),
(15, 'Klavye', 11, '2024-06-08', 1500),
(17, 'Buzdolabı', 13, '2024-12-12', 19999);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `yetkiler`
--

CREATE TABLE `yetkiler` (
  `Id` int(11) NOT NULL,
  `Adi` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `yetkiler`
--

INSERT INTO `yetkiler` (`Id`, `Adi`) VALUES
(9, 'Admin'),
(10, 'Personel'),
(11, 'Yetkisiz');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `musteri`
--
ALTER TABLE `musteri`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `personel`
--
ALTER TABLE `personel`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `satis`
--
ALTER TABLE `satis`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `sehirler`
--
ALTER TABLE `sehirler`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `stok`
--
ALTER TABLE `stok`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `urunler`
--
ALTER TABLE `urunler`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `yetkiler`
--
ALTER TABLE `yetkiler`
  ADD PRIMARY KEY (`Id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `accounts`
--
ALTER TABLE `accounts`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Tablo için AUTO_INCREMENT değeri `kategori`
--
ALTER TABLE `kategori`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Tablo için AUTO_INCREMENT değeri `musteri`
--
ALTER TABLE `musteri`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Tablo için AUTO_INCREMENT değeri `personel`
--
ALTER TABLE `personel`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Tablo için AUTO_INCREMENT değeri `satis`
--
ALTER TABLE `satis`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `sehirler`
--
ALTER TABLE `sehirler`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `stok`
--
ALTER TABLE `stok`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Tablo için AUTO_INCREMENT değeri `urunler`
--
ALTER TABLE `urunler`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- Tablo için AUTO_INCREMENT değeri `yetkiler`
--
ALTER TABLE `yetkiler`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
