package booking;

import org.junit.*;

import org.junit.Before;

import java.sql.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookingRegistryTest {
    private Accomodation accomodation;
    private BookingRegistry registry;
    private Connection conn;


    @Before
    public void setup() {
        registry = new BookingRegistry();
        conn = registry.connectToDatabase();
        registry.deleteAllRecords(conn);
    }

    @Test
    public void addRecordsToDatabaseTest() throws SQLException {
        boolean databaseNotEmpty = false;
        accomodation = new Accomodation(1, "room", "single bed", 1, "good", 23, "winter");
        registry.addRecordsToDatabase(accomodation, conn);
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM accomodation;")) {
            if (rs.next()) {
                databaseNotEmpty = true;
            }
            assertThat(databaseNotEmpty, is(true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listRecordsToDatabaseTest() throws SQLException {
        accomodation = new Accomodation(1, "room", "single bed", 1, "good", 23, "winter");
        registry.addRecordsToDatabase(accomodation, conn);
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM accomodation;")) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
        registry.listAccomodationPrices(conn);
    }
}
