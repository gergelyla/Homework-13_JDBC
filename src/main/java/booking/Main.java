package booking;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        BookingRegistry registry = new BookingRegistry();
        manageRegistry(connectToDatabase(), registry);
    }

    public static void manageRegistry(Connection conn, BookingRegistry registry) {
        String menuOption = " ";
        while (!menuOption.equals("4")) {
            System.out.println("1) Read new accomodation into database ");
            System.out.println("2) List database ");
            System.out.println("3) Delete all records from database ");
            System.out.println("4) Exit ");
            System.out.println("-------------------------------------------");
            System.out.print("Enter choice: ");
            switch (menuOption = registry.userInputString()) {
                case "1":
                    System.out.println("-------------------------------------------");
                    addNewAccomodation(registry, conn);
                    break;
                case "2":
                    System.out.println("-------------------------------------------");
                    registry.listAccomodationPrices(conn);
                    break;
                case "3":
                    System.out.println("-------------------------------------------");
                    registry.deleteAllRecords(conn);
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Not a correct menu option! ");
                    break;
            }
        }
    }

    public static void addNewAccomodation(BookingRegistry registry, Connection conn) {
        Accomodation newAccomodation = new Accomodation();
        registry.addRecordsToDatabase(newAccomodation.addAccomodation(registry), conn);
    }

    public static Connection connectToDatabase() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.err.println("Can’t load driver. Verify CLASSPATH");
            System.err.println(e.getMessage());
        }

        Connection conn = null;

        DriverManager.setLoginTimeout(60);
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
}
