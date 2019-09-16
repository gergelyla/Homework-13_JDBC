package booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingRegistry {
    List registry = new ArrayList();

    public Connection connectToDatabase() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.err.println("Can’t load driver. Verify CLASSPATH");
            System.err.println(e.getMessage());
        }

        Connection conn = null;

        DriverManager.setLoginTimeout(120);
        try {
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append("postgresql")
                    .append("://")
                    .append("127.0.0.1")
                    .append(":")
                    .append(5432)
                    .append("/")
                    .append("Booking")
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("k0nyvtar").toString();
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println("Cannot connect to the database: " + e.getMessage());
        }

        if (conn == null) {
            System.out.println("NO CONNECTION");
        }
        return conn;
    }

    public void addRecordsToDatabase(Accomodation accomodation, Connection conn) {
        try (PreparedStatement ps1 = conn
                .prepareStatement("insert into accomodation (id,type, bed_type, max_guests, description)"
                        + "values (?, ?, ?, ?, ?)")) {
            ps1.setInt(1, accomodation.getAccomodationId());
            ps1.setString(2, accomodation.getAccomodationType());
            ps1.setString(3, accomodation.getAccomodationBedType());
            ps1.setInt(4, accomodation.getMaxGuestNr());
            ps1.setString(5, accomodation.getAccomodationDescription());
            ps1.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Cannot insert accomodation: " + e.getMessage());
        }

        try (PreparedStatement ps2 = conn
                .prepareStatement("insert into room_fair (id, value, season)"
                        + "values (?, ?, ?)")) {
            ps2.setInt(1, accomodation.getAccomodationId());
            ps2.setDouble(2, accomodation.getAccomodationValue());
            ps2.setString(3, accomodation.getSeason());
            ps2.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Cannot insert accomodation: " + e.getMessage());
        }

        try (PreparedStatement ps3 = conn
                .prepareStatement("insert into accomodation_fair_relation (id, id_accomodation, id_room_fair)"
                        + "values (?, ?, ?)")) {
            ps3.setInt(1, accomodation.getAccomodationId());
            ps3.setInt(2, accomodation.getAccomodationId());
            ps3.setInt(3, accomodation.getAccomodationId());
            ps3.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Cannot insert accomodation: " + e.getMessage());
        }
        System.out.println("Accomodation info succesfully added to registry!");
        System.out.println("-------------------------------------------");
        System.out.println(" ");
    }

    public void listAccomodationPrices(Connection conn) {
        boolean recordCounter = false;
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT accomodation.*,room_fair.* FROM accomodation \n" +
                     "RIGHT JOIN accomodation_fair_relation ON accomodation.id=accomodation_fair_relation.id_accomodation \n" +
                     "RIGHT JOIN room_fair ON accomodation_fair_relation.id_room_fair=room_fair.id")) {
            while (rs.next()) {
                System.out.println("Room type: " + rs.getString("type") + " | bed type: " + rs.getString("bed_type") + " | max number of guests: " + rs.getInt("max_guests") + " | description of room: " + rs.getString("description") + " | price of room: " + rs.getDouble("value") + " | season: " + rs.getString("season"));
                recordCounter = true;
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        if (recordCounter == false) {
            System.out.println("No records on file!");
        }
        System.out.println("-------------------------------------------");
        System.out.println(" ");
    }

    public void deleteAllRecords(Connection conn) {
        try (PreparedStatement ps1 = conn.prepareStatement("DELETE FROM accomodation_fair_relation; DELETE FROM accomodation; DELETE FROM room_fair;")) {
            ps1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Records deleted succesfully!");
        System.out.println("-------------------------------------------");
        System.out.println(" ");
    }

    public double userInputDouble() {
        Scanner input = new Scanner(System.in);
        double value = input.nextDouble();
        return value;
    }

    public int userInputInt() {
        Scanner input = new Scanner(System.in);
        int value = input.nextInt();
        return value;
    }

    public String userInputString() {
        Scanner scan = new Scanner(System.in);
        String value = scan.nextLine();
        return value;
    }
}
