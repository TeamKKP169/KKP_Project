-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 18 Feb 2020 pada 07.20
-- Versi server: 10.3.15-MariaDB
-- Versi PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `app_book`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku`
--

CREATE TABLE `buku` (
  `id_buku` varchar(30) NOT NULL,
  `judul` varchar(30) NOT NULL,
  `pengarang` varchar(30) NOT NULL,
  `penerbit` varchar(30) NOT NULL,
  `kategori` varchar(30) NOT NULL,
  `isbn` text NOT NULL,
  `stok` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `buku`
--

INSERT INTO `buku` (`id_buku`, `judul`, `pengarang`, `penerbit`, `kategori`, `isbn`, `stok`) VALUES
('B0001', 'Bahasa Indonesia', 'Hikigaya', 'Gramedia', 'Pelajaran', '123 777 999', 81),
('B0002', 'Matematika', 'hikigaya', 'Gramedia', 'Pelajaran', '333 8888', 91),
('B0003', 'PC komplite', 'hikigaya haciman', 'Erlangga', 'Novel', '3444 666', 86);

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori_buku`
--

CREATE TABLE `kategori_buku` (
  `id` varchar(20) NOT NULL,
  `nama_kategori` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kategori_buku`
--

INSERT INTO `kategori_buku` (`id`, `nama_kategori`) VALUES
('A01', 'Komik'),
('A02', 'Novel'),
('A03', 'Pelajaran'),
('A04', 'Sastra');

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE `login` (
  `id` varchar(50) NOT NULL,
  `nama_depan` varchar(50) NOT NULL,
  `nama_belakang` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `hak` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`id`, `nama_depan`, `nama_belakang`, `password`, `hak`) VALUES
('admin', 'admin', 'admin', 'admin123', 'admin'),
('user', 'user', 'user', 'user123', 'user'),
('admin', 'admin', 'admin', 'admin123', 'admin'),
('user', 'user', 'user', 'user123', 'user');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengembalian`
--

CREATE TABLE `pengembalian` (
  `id_pinjam` varchar(30) NOT NULL,
  `npm` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `id_buku` varchar(30) NOT NULL,
  `judul` varchar(30) NOT NULL,
  `jumlah` int(30) NOT NULL,
  `tgl_pinjam` varchar(30) NOT NULL,
  `tgl_balik` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengunjung`
--

CREATE TABLE `pengunjung` (
  `id` varchar(50) NOT NULL,
  `nis` varchar(50) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `telpon` varchar(30) NOT NULL,
  `tanggal` varchar(200) NOT NULL,
  `jam` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pengunjung`
--

INSERT INTO `pengunjung` (`id`, `nis`, `nama`, `telpon`, `tanggal`, `jam`) VALUES
('P01', '201643500199', 'ali', '0878 7897 0878', '16-2-2020', '22:13:30');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pinjaman`
--

CREATE TABLE `pinjaman` (
  `id_pinjam` varchar(30) NOT NULL,
  `npm` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `id_buku` varchar(30) NOT NULL,
  `judul` varchar(30) NOT NULL,
  `jumlah` int(30) NOT NULL,
  `tgl_pinjam` varchar(30) NOT NULL,
  `tgl_balik` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `siswa`
--

CREATE TABLE `siswa` (
  `nis` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `no_hp` varchar(30) NOT NULL,
  `jenis_kelamin` varchar(40) NOT NULL,
  `kelas` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `siswa`
--

INSERT INTO `siswa` (`nis`, `nama`, `alamat`, `no_hp`, `jenis_kelamin`, `kelas`) VALUES
('201643500177', 'Ade Abdul', 'Jakarta Selatan', '021 6666', 'Laki-Laki', '7'),
('201643500195', 'Mat Syuaeb', 'Jakarta Timur', '021 99999', 'Laki-Laki', '8'),
('201643500198', 'Anisa ', 'Jawa Barat', '021 3335555', 'Perempuan', '7'),
('201643599196', 'Muhammad Ali ', 'Jakarta Timur', '021 888 999', 'Laki-Laki', '8');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id_buku`);

--
-- Indeks untuk tabel `kategori_buku`
--
ALTER TABLE `kategori_buku`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`id_pinjam`);

--
-- Indeks untuk tabel `pengunjung`
--
ALTER TABLE `pengunjung`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `pinjaman`
--
ALTER TABLE `pinjaman`
  ADD PRIMARY KEY (`id_pinjam`);

--
-- Indeks untuk tabel `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nis`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
