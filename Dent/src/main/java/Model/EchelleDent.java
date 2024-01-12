package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EchelleDent {
    private int id;
    private int min;
    private int max;
    private int idPrestation;

    public EchelleDent EchelleDentByEtat(int etat , Connection connection) throws SQLException, ClassNotFoundException {
        EchelleDent echelleDent = new EchelleDent();
        if (connection != null) {
            String selectQuery = "SELECT * FROM echelleDent WHERE min <= ? AND max >= ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1 , etat);
                preparedStatement.setInt(2 , etat);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    echelleDent.setId(resultSet.getInt("id"));
                    echelleDent.setMin(resultSet.getInt("min"));
                    echelleDent.setMax(resultSet.getInt("max"));
                    echelleDent.setIdPrestation(resultSet.getInt("idprestation"));
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return echelleDent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getIdPrestation() {
        return idPrestation;
    }

    public void setIdPrestation(int idPrestation) {
        this.idPrestation = idPrestation;
    }
}
