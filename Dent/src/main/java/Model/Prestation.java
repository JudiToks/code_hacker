package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Prestation {
    private int id;
    private String nom;

    public Prestation prestateursById(int idPrestateur, Connection connection) throws SQLException, ClassNotFoundException {
        Prestation prestation = new Prestation();
        if (connection != null) {
            String selectQuery = "select * from prestation where id=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1, idPrestateur);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    prestation.setId(resultSet.getInt("id"));
                    prestation.setNom(resultSet.getString("nom"));
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return prestation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
