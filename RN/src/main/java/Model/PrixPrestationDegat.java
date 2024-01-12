package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrixPrestationDegat {
    private int id;
    private int idRoute;
    private int idPrestation;
    private double value;

    public PrixPrestationDegat PrixPrestationByPrestationByRoute(int idPrestation , int route , Connection connection) throws SQLException, ClassNotFoundException {
        PrixPrestationDegat prixPrestationDegat = new PrixPrestationDegat();
        if (connection != null) {
            String selectQuery = "select * from prixPrestationDegat where idprestation=? AND idRoute=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1 , idPrestation);
                preparedStatement.setInt(2 , route);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    prixPrestationDegat.setId(resultSet.getInt("id"));
                    prixPrestationDegat.setIdRoute(resultSet.getInt("idroute"));
                    prixPrestationDegat.setIdPrestation(resultSet.getInt("idprestation"));
                    prixPrestationDegat.setValue(resultSet.getDouble("valuer"));
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return prixPrestationDegat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public int getIdPrestation() {
        return idPrestation;
    }

    public void setIdPrestation(int idPrestation) {
        this.idPrestation = idPrestation;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
