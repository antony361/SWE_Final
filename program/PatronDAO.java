import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatronDAO {
    private Connection conn;

    public PatronDAO() throws SQLException {
        conn = DatabaseConnection.getConnection();
    }

    public Connection getConnection() {
        return conn;
    }

    public void addPatron(Patron patron) throws SQLException {
        String sql = "INSERT INTO patrons (patronId, name, contactInfo) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, patron.getPatronId());
        pstmt.setString(2, patron.getName());
        pstmt.setString(3, patron.getContactInfo());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public List<Patron> getAllPatrons() throws SQLException {
        List<Patron> patrons = new ArrayList<>();
        String sql = "SELECT * FROM patrons";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Patron patron = new Patron(rs.getString("patronId"), rs.getString("name"), rs.getString("contactInfo"));
            patrons.add(patron);
        }
        rs.close();
        stmt.close();
        return patrons;
    }

    public void addPatrons(List<Patron> patrons) throws SQLException {
        for (Patron patron : patrons) {
            addPatron(patron);
        }
    }
}