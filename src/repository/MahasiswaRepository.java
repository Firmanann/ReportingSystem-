package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mahasiswa;
import utils.Koneksi;

public class MahasiswaRepository {

    public Mahasiswa login(String nim, String password) {

        String sql = """
                SELECT * FROM mahasiswa
                WHERE nim = ? AND password = ?
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, nim);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToMahasiswa(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Mahasiswa findByNim(String nim) {

        String sql = """
                SELECT * FROM mahasiswa
                WHERE nim = ?
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, nim);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToMahasiswa(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Mahasiswa> findAll() {

        List<Mahasiswa> mahasiswaList = new ArrayList<>();

        String sql = "SELECT * FROM mahasiswa";

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                mahasiswaList.add(mapResultSetToMahasiswa(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mahasiswaList;
    }

    public boolean save(Mahasiswa mahasiswa) {

        String sql = """
                INSERT INTO mahasiswa
                (nim, nama_mahasiswa, prodi, no_hp, email_mahasiswa, password)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, mahasiswa.getNim());
            ps.setString(2, mahasiswa.getNama());
            ps.setString(3, mahasiswa.getProdi());
            ps.setString(4, mahasiswa.getPhone());
            ps.setString(5, mahasiswa.getEmail());
            ps.setString(6, mahasiswa.getPassword());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Mahasiswa mapResultSetToMahasiswa(ResultSet rs) throws SQLException {

        Mahasiswa mahasiswa = new Mahasiswa();

        mahasiswa.setNim(rs.getString("nim"));
        mahasiswa.setNama(rs.getString("nama_mahasiswa"));
        mahasiswa.setProdi(rs.getString("prodi"));
        mahasiswa.setPhone(rs.getString("no_hp"));
        mahasiswa.setEmail(rs.getString("email_mahasiswa"));
        mahasiswa.setPassword(rs.getString("password"));

        return mahasiswa;
    }
}