-- Database
CREATE DATABASE ReportingSystem;
USE ReportingSystem;

-- Mahasiswa
CREATE TABLE mahasiswa (
    nim VARCHAR(15) PRIMARY KEY,
    nama_mahasiswa VARCHAR(100) NOT NULL,
    prodi VARCHAR(100) NOT NULL,
    no_hp VARCHAR(20),
    email_mahasiswa VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Petugas
CREATE TABLE petugas (
    id_petugas SERIAL PRIMARY KEY,
    nama_petugas VARCHAR(100) NOT NULL,
    email_petugas VARCHAR(100) UNIQUE,
    jabatan VARCHAR(50) NOT NULL,
    no_hp_petugas VARCHAR(20),
    password VARCHAR(255) NOT NULL
);

-- Kategori
CREATE TABLE kategori (
    id_kategori SERIAL PRIMARY KEY,
    nama_kategori VARCHAR(50) NOT NULL UNIQUE,
    keterangan TEXT
);

-- Keluhan 
CREATE TABLE keluhan (
    id_keluhan SERIAL PRIMARY KEY,
    nim VARCHAR(15) NOT NULL,
    id_kategori BIGINT UNSIGNED NOT NULL,
    isi_keluhan TEXT NOT NULL,
    tanggal_pengaduan TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status_keluhan VARCHAR(20) NOT NULL DEFAULT 'menunggu',

    -- Relasi 
    CONSTRAINT fk_keluhan_mahasiswa
        FOREIGN KEY (nim)
        REFERENCES mahasiswa(nim)
        ON UPDATE CASCADE
        ON DELETE CASCADE,

    CONSTRAINT fk_keluhan_kategori
        FOREIGN KEY (id_kategori)
        REFERENCES kategori(id_kategori)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT chk_status_keluhan
        CHECK (status_keluhan IN ('menunggu', 'diproses', 'selesai'))
);

-- Tanggapan
CREATE TABLE tanggapan (
    id_tanggapan SERIAL PRIMARY KEY,
    id_keluhan BIGINT UNSIGNED NOT NULL, 
    id_petugas BIGINT UNSIGNED NOT NULL,  
    isi_tanggapan TEXT NOT NULL,
    tanggal_tanggapan TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Relasi 
    CONSTRAINT fk_tanggapan_keluhan
        FOREIGN KEY (id_keluhan)
        REFERENCES keluhan(id_keluhan)
        ON UPDATE CASCADE
        ON DELETE CASCADE,

    CONSTRAINT fk_tanggapan_petugas
        FOREIGN KEY (id_petugas)
        REFERENCES petugas(id_petugas)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

-- Dummy Data 

-- Kategori 
INSERT INTO kategori (nama_kategori, keterangan)
VALUES
('Fasilitas', 'Keluhan terkait sarana dan prasarana kampus'),
('Akademik', 'Keluhan terkait proses akademik'),
('Keuangan', 'Keluhan terkait pembayaran dan administrasi keuangan');

-- Petugas 
INSERT INTO petugas (nama_petugas, jabatan, no_hp_petugas, password)
VALUES
('Firman Ardiyan', 'Staff Sarpras', '081234567890', 'password123'),
('Jajang', 'Admin BAAK', '081234567891', 'password123'),
('Joko', 'Admin IT', '081234567892', 'password123');