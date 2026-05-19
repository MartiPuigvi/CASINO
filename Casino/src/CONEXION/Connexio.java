/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONEXION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class Connexio {

    /**
     * Intenta establir una connexió activa amb la base de dades del casino
     * @return 
     */
    
    public Connection connectar() {
        String url = "jdbc:mysql://localhost:3306/casino";
        String user = "root";
        String password = "Nico1234";
        
        Connection con = null;
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connexio� OK");
        } catch (SQLException e) {
            System.out.println("No s'ha pogut establir la connexio");

            e.printStackTrace();
        }
        return con;
    }

}
