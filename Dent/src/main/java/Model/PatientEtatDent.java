package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientEtatDent {
    private int id;
    private Date date;
    private int idPatient;
    private String code;
    private double etat;

    public void inserPatientEtatDent(PatientEtatDent patientEtatDent, Connection connection) throws SQLException, ClassNotFoundException {
        if (connection != null) {
            String insertQuery = "insert into patientEtatDent ( \"date\", idpatient, codedent, etat) VALUES (? , ? , ? , ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setDate(1, new Date(new java.util.Date().getTime()));
                preparedStatement.setInt(2, patientEtatDent.getIdPatient());
                preparedStatement.setString(3, patientEtatDent.getCode());
                preparedStatement.setDouble(4, patientEtatDent.getEtat());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête INSERT : " + e.getMessage());
            }
        }
    }

    public List<PatientEtatDent> DentPatienbyIdPriorite(int idPriorite , int idPatient, Connection connection) throws SQLException, ClassNotFoundException {
        List<PatientEtatDent> patientEtatDentList = new ArrayList<>();
        if (connection != null) {
            String selectQuery = "select patientEtatDent.idpatient , patientEtatDent.codedent , patientEtatDent.etat\n" +
                    "    from patientEtatDent , importanceDent\n" +
                    "        WHERE patientEtatDent.codedent = importanceDent.code\n" +
                    "        AND importanceDent.IdPriorisation = ?\n" +
                    "        AND patientEtatDent.idpatient = ?\n" +
                    "    ORDER BY importanceDent.value DESC ;";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1, idPriorite);
                preparedStatement.setInt(2, idPatient);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    PatientEtatDent patientEtatDent = new PatientEtatDent();
                    patientEtatDent.setIdPatient(resultSet.getInt("idpatient"));
                    patientEtatDent.setCode(resultSet.getString("codedent"));
                    patientEtatDent.setEtat(resultSet.getInt("etat"));
                    patientEtatDentList.add(patientEtatDent);
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return patientEtatDentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getEtat() {
        return etat;
    }

    public void setEtat(double etat) {
        this.etat = etat;
    }
}
