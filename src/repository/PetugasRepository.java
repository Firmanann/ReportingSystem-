package repository;

import model.Petugas;
import utils.Koneksi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetugasRepository {

    public Petugas login(long idPetugas, String password) {

        String sql = """
                SELECT *
                FROM petugas
                WHERE id_petugas = ? AND password = ?
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idPetugas);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToPetugas(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Petugas findById(long idPetugas) {

        String sql = """
                SELECT *
                FROM petugas
                WHERE id_petugas = ?
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idPetugas);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToPetugas(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Petugas> findAll() {

        List<Petugas> petugasList = new ArrayList<>();

        String sql = "SELECT * FROM petugas";

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                petugasList.add(mapResultSetToPetugas(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return petugasList;
    }

    public boolean save(Petugas petugas) {

        String sql = """
                INSERT INTO petugas
                (nama_petugas, jabatan, no_hp_petugas, password)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, petugas.getNamaPetugas());
            ps.setString(2, petugas.getJabatan());
            ps.setString(3, petugas.getPhone());
            ps.setString(4, petugas.getPassword());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Petugas petugas) {

        String sql = """
                UPDATE petugas
                SET nama_petugas = ?,
                    jabatan = ?,
                    no_hp_petugas = ?,
                    password = ?
                WHERE id_petugas = ?
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, petugas.getNamaPetugas());
            ps.setString(2, petugas.getJabatan());
            ps.setString(3, petugas.getPhone());
            ps.setString(4, petugas.getPassword());
            ps.setLong(5, petugas.getIdPetugas());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(long idPetugas) {

        String sql = """
                DELETE FROM petugas
                WHERE id_petugas = ?
                """;

        try (
                Connection conn = Koneksi.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, idPetugas);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Petugas mapResultSetToPetugas(ResultSet rs) throws SQLException {

        Petugas petugas = new Petugas();

        petugas.setIdPetugas(rs.getLong("id_petugas"));
        petugas.setNamaPetugas(rs.getString("nama_petugas"));
        petugas.setJabatan(rs.getString("jabatan"));
        petugas.setPhone(rs.getString("no_hp_petugas"));
        petugas.setPassword(rs.getString("password"));

        return petugas;
    }
}