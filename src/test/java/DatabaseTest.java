import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {
    public static void main(String[] args) {
        testJDBCConnection();
        testHibernateConnection();
    }

    private static void testJDBCConnection() {
        System.out.println("\n=== Testing JDBC Connection ===");
        String url = "jdbc:postgresql://localhost:5432/studentdb";
        String user = "cankurttekin";
        String password = "password";

        try {
            // Explicitly load the driver
            Class.forName("org.postgresql.Driver");

            // Try to establish a connection
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("JDBC Connection: Connected to PostgreSQL successfully!");
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found. Include it in your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("JDBC Connection failed!");
            e.printStackTrace();
        }
    }

    private static void testHibernateConnection() {
        System.out.println("\n=== Testing Hibernate Connection ===");
        SessionFactory sessionFactory = null;
        Session session = null;

        try {
            // Create session factory
            sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
            
            // Open a session and test connection
            session = sessionFactory.openSession();
            System.out.println("Hibernate Connection: Successfully created SessionFactory");
            
            // Test schema by running a simple query
            session.beginTransaction();
            session.createNativeQuery("SELECT 1").list();
            session.getTransaction().commit();
            
            // Verify students table exists
            session.beginTransaction();
            session.createNativeQuery("SELECT count(*) FROM students").list();
            session.getTransaction().commit();
            System.out.println("Schema verification: 'students' table exists and is accessible");
            
        } catch (Exception e) {
            System.err.println("Hibernate Connection/Schema Test Failed!");
            e.printStackTrace();
        } finally {
            // Clean up resources
            if (session != null && session.isOpen()) {
                session.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}
