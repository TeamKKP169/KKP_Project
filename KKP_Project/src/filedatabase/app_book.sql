-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 15 Feb 2020 pada 17.18
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
('B0001', 'bobo', 'donal', 'gramedia', 'Sastra', 'tamvan', 100),
('B0002', 'komik', 'donal', 'erlanggga', 'Sastra', 'Deskripsi', 100),
('B0003', 'naruto', 'dodo', 'jatang', 'Sastra', 'tamvan', 100),
('B0004', 'manga dora', 'horas', 'gramed', 'Sastra', '2222 kdksjdkjs', 100),
('B0005', 'dorami', 'juju', 'nipon', 'Sastra', '999 7777', 100),
('B0007', 'malin kundang', 'budi', 'gramedia', 'lain-lain', '1222 87878', 100),
('B0009', 'bahasa indo', 'jened', 'grameda', 'Komik', '2333', 100),
('B0011', 'cinta dan udsta', 'udin', 'gramed', 'Komik', '23333', 100),
('B0012', 'gadis', 'asep', 'gramed', 'Sastra', '222', 1000),
('B0014', 'doa dan ibadah', 'ustad', 'FPI', 'lain-lain', '212', 100),
('B0015', 'naruto', 'hikigaya', 'gramedia', 'Komik', '1222', 100),
('B0016', 'gadis', 'asep', 'gramed', 'Sastra', '222', 1000),
('B0017', 'kiryuu', 'hikigaya', 'xstudio', 'Komik', '1222cff', 100);

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
('1', 'pelajaran'),
('2', 'lain-lain'),
('3', 'Sastra'),
('4', 'Komik'),
('5', 'sejarah');

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

--
-- Dumping data untuk tabel `pengembalian`
--

INSERT INTO `pengembalian` (`id_pinjam`, `npm`, `nama`, `id_buku`, `judul`, `jumlah`, `tgl_pinjam`, `tgl_balik`, `status`) VALUES
('P0001', '201643500179', 'doni', 'B0012', 'gadis', 1, '16 Februari 2020', '16 Februari 2020', 'sudah kembali'),
('P0002', '201643500199', 'ali abidin', 'B0015', 'naruto', 1, '22 Februari 2020', '22 Februari 2020', 'sudah kembali'),
('P0003', '201643500111', 'anisa', 'B0001', 'bobo', 1, '16 Februari 2020', '17 Februari 2020', 'sudah kembali');

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
('P01', '2016', 'susi', '021 888', '13-2-2020', '03:30:06'),
('P02', '2016', 'susi', '021 888', '13-2-2020', '03:31:02'),
('P03', '201643500199', 'alwi', '0878 7898677', '13-2-2020', '03:33:23'),
('P04', '201643500199', 'alwi', '0878 7898677', '13-2-2020', '03:41:51'),
('P05', '201643500199', 'alwi', '0878 7898677', '13-2-2020', '03:42:24'),
('P06', '201643500199', 'alwi', '0878 7898677', '13-2-2020', '03:43:08'),
('P07', '201643500199', 'alwi', '0878 7898677', '13-2-2020', '03:44:05'),
('P08', '2016', 'susi', '021 888', '13-2-2020', '03:44:24'),
('P09', '201643500199', 'alwi', '0878 7898677', '13-2-2020', '03:44:47'),
('P10', '2016', 'susi', '021 888', '13-2-2020', '03:44:56'),
('P11', '2016', 'susi', '021 888', '13-2-2020', '03:45:13'),
('P12', '2016', 'susi', '021 888', '13-2-2020', '03:45:25'),
('P13', '2016', 'susi', '021 888', '13-2-2020', '03:56:57'),
('P14', '2016', 'susi', '021 888', '13-2-2020', '04:04:08'),
('P15', '2016', 'susi', '021 888', '13-2-2020', '04:04:24'),
('P16', '2016', 'susi', '021 888', '13-2-2020', '09:42:08'),
('P17', '201643500199', 'alwi', '0878 7898677', '13-2-2020', '09:42:24'),
('P18', '2016', 'susi', '021 888', '13-2-2020', '09:44:15'),
('P19', '2016', 'susi', '021 888', '13-2-2020', '09:47:16'),
('P20', '201643500199', 'ali abidin', '0878 7898677', '15-2-2020', '22:49:35');

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
('201643500111', 'anisa', 'jakarta utara', '021 3444 7888', 'Perempuan', '11'),
('201643500122', 'andrian', 'jawa barat', '087878976555', 'Laki-Laki', '11'),
('201643500133', 'susi', 'jakarta barat', '021 5678088', 'Perempuan', '12'),
('201643500155', 'alex', 'jakarta barat', '021 788888', 'Laki-Laki', '12'),
('201643500177', 'dea', 'jakarta timur', '021 7888 7888', 'Perempuan', '12'),
('201643500179', 'doni', 'jakarta barat', '087878970878', 'Laki-Laki', '12'),
('201643500199', 'ali abidin', 'jakarta', '0878 7898677', 'Laki-Laki', '12');

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
