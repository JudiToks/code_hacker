package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Priorisation {
    private int id;
    private String nom;

    public List<Priorisation> AllPriorisation(Connection connection) throws SQLException, ClassNotFoundException {
        List<Priorisation> priorisationList = new ArrayList<>();
        if (connection != null) {
            String selectQuery = "select * from priorisation;";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Priorisation priorisation = new Priorisation();
                    priorisation.setId(resultSet.getInt("id"));
                    priorisation.setNom(resultSet.getString("nom"));
                    priorisationList.add(priorisation);
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return priorisationList;
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
