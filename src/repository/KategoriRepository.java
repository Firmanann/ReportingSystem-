package repository;

import model.Kategori;
import utils.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KategoriRepository {

    //Save Kategori 
    public boolean save(Kategori kategori) {

        String sql = """
                INSERT INTO kategori
                (nama_kategori, keterangan)
                VALUES (?, ?)
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, kategori.getNamaKategori());
            ps.setString(2, kategori.getKeterangan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Update Kategori
    public boolean update(Kategori kategori) {

        String sql = """
                UPDATE kategori
                SET nama_kategori = ?,
                    keterangan = ?
                WHERE id_kategori = ?
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, kategori.getNamaKategori());
            ps.setString(2, kategori.getKeterangan());
            ps.setLong(3, kategori.getIdKategori()); 
            
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Delete Kategori
    public boolean delete(long idKategori) { 

        String sql = """
                DELETE FROM kategori
                WHERE id_kategori = ?
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idKategori); 

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Method findById
    public Kategori findById(long idKategori) { 

        String sql = """
                SELECT *
                FROM kategori
                WHERE id_kategori = ?
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idKategori); 

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToKategori(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Method findAll
    public List<Kategori> findAll() {

        List<Kategori> kategoriList = new ArrayList<>();

        String sql = "SELECT * FROM kategori ORDER BY nama_kategori";

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                kategoriList.add(mapResultSetToKategori(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kategoriList;
    }

    private Kategori mapResultSetToKategori(ResultSet rs)
            throws SQLException {

        Kategori kategori = new Kategori();

        kategori.setIdKategori(rs.getLong("id_kategori")); 
        kategori.setNamaKategori(rs.getString("nama_kategori"));
        kategori.setKeterangan(rs.getString("keterangan"));

        return kategori;
    }
}