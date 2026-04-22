/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONEXION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nicod
 */
public class Queries {
    
        public static void mostrarAlumnes() {
        String sql = "SELECT id, nom FROM alumnes";

        try (
                Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");

                alumnes.add(new Alumne(id, nom));
                //System.out.println(id + " - " + nom + " - ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
