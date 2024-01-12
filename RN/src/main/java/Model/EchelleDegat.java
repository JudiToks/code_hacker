package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EchelleDegat {
    private int id;
    private int min;
    private int max;
    private int idprestation;

    public EchelleDegat EchelleDegatByEtat(int etat , Connection connection) throws SQLException, ClassNotFoundException {
        EchelleDegat echelleDegat = new EchelleDegat();
        if (connection != null) {
            String selectQuery = "SELECT * FROM echelleDegat WHERE min <= ? AND max >= ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1 , etat);
                preparedStatement.setInt(2 , etat);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    echelleDegat.setId(resultSet.getInt("id"));
                    echelleDegat.setMin(resultSet.getInt("min"));
                    echelleDegat.setMax(resultSet.getInt("max"));
                    echelleDegat.setIdprestation(resultSet.getInt("idprestation"));
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return echelleDegat;
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

    public int getIdprestation() {
        return idprestation;
    }

    public void setIdprestation(int idprestation) {
        this.idprestation = idprestation;
    }
}
