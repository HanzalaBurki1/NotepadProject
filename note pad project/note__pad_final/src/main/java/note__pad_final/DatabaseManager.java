package note__pad_final;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

   
    private static final String URL = "jdbc:mysql://localhost:3306/notepad_db";
    private static final String USER = "root"; 
    private static final String PASSWORD = "Anmptyset%1"; 

    /**
     * Establishes a connection to the database.
     * @return A Connection object.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Saves a note to the database.
     * @param title The title of the note.
     * @param content The content of the note.
     * @return true if the note was saved successfully, false otherwise.
     */
    public static boolean saveNote(String title, String content) {
        
        String sql = "INSERT INTO notes (title, content) VALUES (?, ?)";

       
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

      
            pstmt.setString(1, title);
            pstmt.setString(2, content);

            
            int affectedRows = pstmt.executeUpdate();

           
            return affectedRows > 0;

        } catch (SQLException e) {
           
            e.printStackTrace();
            return false;
        }
    }
}