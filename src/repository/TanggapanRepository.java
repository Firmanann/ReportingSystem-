package repository;

import model.Keluhan;
import model.Petugas;
import model.Tanggapan;
import utils.Koneksi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TanggapanRepository {

    public boolean save(Tanggapan tanggapan) {

        String sql = """
                INSERT INTO tanggapan
                (id_keluhan, id_petugas, isi_tanggapan, tanggal_tanggapan)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, tanggapan.getKeluhan().getIdKeluhan()); 
            ps.setLong(2, tanggapan.getPetugas().getIdPetugas()); 
            ps.setString(3, tanggapan.getIsiTanggapan());
            ps.setTimestamp(4, tanggapan.getTanggalTanggapan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Tanggapan findById(long idTanggapan) { 

        String sql = """
                SELECT t.*,
                       p.nama_petugas,
                       p.jabatan
                FROM tanggapan t
                JOIN petugas p
                    ON t.id_petugas = p.id_petugas
                WHERE t.id_tanggapan = ?
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idTanggapan); 

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToTanggapan(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Tanggapan> findAll() {

        List<Tanggapan> tanggapanList = new ArrayList<>();

        String sql = """
                SELECT t.*,
                       p.nama_petugas,
                       p.jabatan
                FROM tanggapan t
                JOIN petugas p
                    ON t.id_petugas = p.id_petugas
                ORDER BY t.tanggal_tanggapan DESC
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                tanggapanList.add(mapResultSetToTanggapan(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tanggapanList;
    }

    public List<Tanggapan> findByKeluhan(long idKeluhan) { 

        List<Tanggapan> tanggapanList = new ArrayList<>();

        String sql = """
                SELECT t.*,
                       p.nama_petugas,
                       p.jabatan
                FROM tanggapan t
                JOIN petugas p
                    ON t.id_petugas = p.id_petugas
                WHERE t.id_keluhan = ?
                ORDER BY t.tanggal_tanggapan DESC
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idKeluhan); 

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tanggapanList.add(mapResultSetToTanggapan(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tanggapanList;
    }

    private Tanggapan mapResultSetToTanggapan(ResultSet rs)
            throws SQLException {

        Keluhan keluhan = new Keluhan();
        keluhan.setIdKeluhan(rs.getLong("id_keluhan")); 

        Petugas petugas = new Petugas();
        petugas.setIdPetugas(rs.getLong("id_petugas")); 
        petugas.setNamaPetugas(rs.getString("nama_petugas"));
        petugas.setJabatan(rs.getString("jabatan"));

        Tanggapan tanggapan = new Tanggapan();

        tanggapan.setIdTanggapan(rs.getLong("id_tanggapan")); 
        tanggapan.setKeluhan(keluhan);
        tanggapan.setPetugas(petugas);
        tanggapan.setIsiTanggapan(rs.getString("isi_tanggapan"));
        tanggapan.setTanggalTanggapan(
                rs.getTimestamp("tanggal_tanggapan")
        );

        return tanggapan;
    }
}