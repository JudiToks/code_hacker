package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrixPrestation {
    private int id;
    private String code;
    private int idPrestation;
    private double prix;

    public PrixPrestation PrixPrestationByPrestationByCode(int idPrestation , String code , Connection connection) throws SQLException, ClassNotFoundException {
        PrixPrestation prixPrestation = new PrixPrestation();
        if (connection != null) {
            String selectQuery = "select * from prixPrestation where idprestation=? AND codedent=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1 , idPrestation);
                preparedStatement.setString(2 , code);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    prixPrestation.setId(resultSet.getInt("id"));
                    prixPrestation.setCode(resultSet.getString("codedent"));
                    prixPrestation.setIdPrestation(resultSet.getInt("idprestation"));
                    prixPrestation.setPrix(resultSet.getDouble("prix"));
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return prixPrestation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIdPrestation() {
        return idPrestation;
    }

    public void setIdPrestation(int idPrestation) {
        this.idPrestation = idPrestation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
