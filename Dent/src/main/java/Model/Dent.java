package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dent {
    private int idDent;
    private String code;
    private String nom;

    public List<Dent> AllDent(Connection connection) throws SQLException, ClassNotFoundException {
        List<Dent> dents = new ArrayList<>();
        if (connection != null) {
            String selectQuery = "select * from dent ";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Dent dent = new Dent();
                    dent.setIdDent(resultSet.getInt("iddent"));
                    dent.setCode(resultSet.getString("code"));
                    dent.setNom(resultSet.getString("nom"));
                    dents.add(dent);
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return dents;
    }
    public Dent DentByCode(String code , Connection connection) throws SQLException, ClassNotFoundException {
        Dent dent = new Dent();
        if (connection != null) {
            String selectQuery = "select * from dent where code=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setString(1, code);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    dent.setIdDent(resultSet.getInt("iddent"));
                    dent.setCode(resultSet.getString("code"));
                    dent.setNom(resultSet.getString("nom"));
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return dent;
    }
    public List<Dent> AllDentEntreById(int a , int b ,  Connection connection) throws SQLException, ClassNotFoundException {
        List<Dent> dents = new ArrayList<>();
        if (connection != null) {
            String selectQuery = "select * from dent where idDent between ? and ? ";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1 , a);
                preparedStatement.setInt(2 , b);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Dent dent = new Dent();
                    dent.setIdDent(resultSet.getInt("iddent"));
                    dent.setCode(resultSet.getString("code"));
                    dent.setNom(resultSet.getString("nom"));
                    dents.add(dent);
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return dents;
    }



    public int getIdDent() {
        return idDent;
    }

    public void setIdDent(int idDent) {
        this.idDent = idDent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
