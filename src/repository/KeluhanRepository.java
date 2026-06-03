package repository;

import model.Keluhan;
import model.Kategori;
import model.Mahasiswa;
import utils.Koneksi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KeluhanRepository {

    public boolean save(Keluhan keluhan) {

        String sql = """
                INSERT INTO keluhan
                (nim, id_kategori, isi_keluhan, tanggal_pengaduan, status_keluhan)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, keluhan.getMahasiswa().getNim());
            ps.setLong(2, keluhan.getKategori().getIdKategori()); 
            ps.setString(3, keluhan.getIsiKeluhan());
            ps.setTimestamp(4, keluhan.getTanggalPengaduan());
            ps.setString(5, keluhan.getStatusKeluhan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateStatus(long idKeluhan, String status) { 

        String sql = """
                UPDATE keluhan
                SET status_keluhan = ?
                WHERE id_keluhan = ?
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, status);
            ps.setLong(2, idKeluhan); 

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Keluhan findById(long idKeluhan) { 

        String sql = """
                SELECT k.*,
                       m.nama_mahasiswa,
                       m.prodi,
                       m.no_hp,
                       m.email_mahasiswa,
                       kat.nama_kategori,
                       kat.keterangan
                FROM keluhan k
                JOIN mahasiswa m
                    ON k.nim = m.nim
                JOIN kategori kat
                    ON k.id_kategori = kat.id_kategori
                WHERE k.id_keluhan = ?
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idKeluhan); 

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToKeluhan(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Keluhan> findAll() {

        List<Keluhan> keluhanList = new ArrayList<>();

        String sql = """
                SELECT k.*,
                       m.nama_mahasiswa,
                       m.prodi,
                       m.no_hp,
                       m.email_mahasiswa,
                       kat.nama_kategori,
                       kat.keterangan
                FROM keluhan k
                JOIN mahasiswa m -- Hapus tb_
                    ON k.nim = m.nim
                JOIN kategori kat -- Hapus tb_
                    ON k.id_kategori = kat.id_kategori
                ORDER BY k.tanggal_pengaduan DESC
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                keluhanList.add(mapResultSetToKeluhan(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return keluhanList;
    }

    public List<Keluhan> findByMahasiswa(String nim) {

        List<Keluhan> keluhanList = new ArrayList<>();

        String sql = """
                SELECT k.*,
                       m.nama_mahasiswa,
                       m.prodi,
                       m.no_hp,
                       m.email_mahasiswa,
                       kat.nama_kategori,
                       kat.keterangan
                FROM keluhan k -- Hapus tb_
                JOIN mahasiswa m -- Hapus tb_
                    ON k.nim = m.nim
                JOIN kategori kat -- Hapus tb_
                    ON k.id_kategori = kat.id_kategori
                WHERE k.nim = ?
                ORDER BY k.tanggal_pengaduan DESC
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, nim);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                keluhanList.add(mapResultSetToKeluhan(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return keluhanList;
    }

    private Keluhan mapResultSetToKeluhan(ResultSet rs)
            throws SQLException {

        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNim(rs.getString("nim"));
        mahasiswa.setNama(rs.getString("nama_mahasiswa")); 
        mahasiswa.setProdi(rs.getString("prodi"));
        mahasiswa.setPhone(rs.getString("no_hp")); 
        mahasiswa.setEmail(rs.getString("email_mahasiswa")); 
        
        Kategori kategori = new Kategori();
        kategori.setIdKategori(rs.getLong("id_kategori")); 
        kategori.setNamaKategori(rs.getString("nama_kategori"));
        kategori.setKeterangan(rs.getString("keterangan"));

        Keluhan keluhan = new Keluhan();
        keluhan.setIdKeluhan(rs.getLong("id_keluhan")); 
        keluhan.setMahasiswa(mahasiswa);
        keluhan.setKategori(kategori);
        keluhan.setIsiKeluhan(rs.getString("isi_keluhan"));
        keluhan.setTanggalPengaduan(rs.getTimestamp("tanggal_pengaduan"));
        keluhan.setStatusKeluhan(rs.getString("status_keluhan"));

        return keluhan;
    }
}