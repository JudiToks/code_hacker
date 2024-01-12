package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    private int id;
    private String nom;
    private Date dtn;

    public void inserPatient(Patient patient, Connection connection) throws SQLException, ClassNotFoundException {
        if (connection != null) {
                String insertQuery = "insert into patient ( nom, dtn) VALUES (? , ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, patient.getNom());
                preparedStatement.setDate(2, patient.getDtn());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête INSERT : " + e.getMessage());
            }
        }
    }

    public int MaxPatient(Connection connection) throws SQLException, ClassNotFoundException {
        int result = 0;
        if (connection != null) {
            String selectQuery = "select MAX(id) as maxD from patient;";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    result =resultSet.getInt("maxD");
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return result;
    }
    public List<Patient> AllPatient(Connection connection) throws SQLException, ClassNotFoundException {
        List<Patient> patientList = new ArrayList<>();
        if (connection != null) {
            String selectQuery = "select * from patient;";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Patient patient = new Patient();
                    patient.setId(resultSet.getInt("id"));
                    patient.setDtn(resultSet.getDate("dtn"));
                    patient.setNom(resultSet.getString("nom"));
                    patientList.add(patient);
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return patientList;
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

    public Date getDtn() {
        return dtn;
    }

    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }
}
