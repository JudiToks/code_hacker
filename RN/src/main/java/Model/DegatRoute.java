package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DegatRoute {
    private int id;
    private int idRoute;
    private int pkDebut;
    private int pkFin;
    private double etat;

    public List<DegatRoute> AllDegatRouteByIdRoute( int idRoute , Connection connection) throws SQLException, ClassNotFoundException {
        List<DegatRoute> degatRouteList = new ArrayList<>();
        if (connection != null) {
            String selectQuery = "SELECT * FROM degatroute where idroute=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1 , idRoute);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    DegatRoute degatRoute = new DegatRoute();
                    degatRoute.setIdRoute(resultSet.getInt("idroute"));
                    degatRoute.setPkDebut(resultSet.getInt("pkdebut"));
                    degatRoute.setPkFin(resultSet.getInt("pkfin"));
                    degatRoute.setEtat(resultSet.getInt("etat"));
                    degatRouteList.add(degatRoute);
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return degatRouteList;
    }
    public List<DegatRoute> DegatRouteByIdRouteByPriorite( int idRoute , int priorite ,  Connection connection) throws SQLException, ClassNotFoundException {
        List<DegatRoute> degatRouteList = new ArrayList<>();
        if (connection != null) {
            String selectQuery = "SELECT DISTINCT DegatRoute.idRoute, DegatRoute.pkDebut, DegatRoute.pkFin, DegatRoute.etat, MAX(valeur) AS valeur\n" +
                    "FROM DegatRoute\n" +
                    "         JOIN importance ON DegatRoute.idRoute = importance.idRoute\n" +
                    "WHERE DegatRoute.idRoute = ?\n" +
                    "  AND importance.idPriorisation = ?\n" +
                    "  AND importance.idPk BETWEEN DegatRoute.pkDebut AND DegatRoute.pkFin\n" +
                    "GROUP BY DegatRoute.idRoute, DegatRoute.pkDebut, DegatRoute.pkFin, DegatRoute.etat\n" +
                    "ORDER BY valeur DESC;";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1, idRoute);
                preparedStatement.setInt(2, priorite);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    DegatRoute degatRoute = new DegatRoute();
                    degatRoute.setIdRoute(resultSet.getInt("idroute"));
                    degatRoute.setPkDebut(resultSet.getInt("pkdebut"));
                    degatRoute.setPkFin(resultSet.getInt("pkfin"));
                    degatRoute.setEtat(resultSet.getInt("etat"));
                    degatRouteList.add(degatRoute);
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return degatRouteList;
    }
    public List<DegatRoute> DegatRouteNotImportantByIdRouteByPriorite( int idRoute , int priorite ,  Connection connection) throws SQLException, ClassNotFoundException {
        List<DegatRoute> degatRouteList = new ArrayList<>();
        if (connection != null) {
            String selectQuery = "SELECT DISTINCT DegatRoute.idRoute, DegatRoute.pkDebut, DegatRoute.pkFin, DegatRoute.etat\n" +
                    "FROM DegatRoute\n" +
                    "WHERE DegatRoute.idRoute = ?\n" +
                    "  AND NOT EXISTS (\n" +
                    "        SELECT 1\n" +
                    "        FROM importance\n" +
                    "        WHERE importance.idRoute = DegatRoute.idRoute\n" +
                    "          AND importance.idPriorisation = ?\n" +
                    "          AND importance.idPk BETWEEN DegatRoute.pkDebut AND DegatRoute.pkFin\n" +
                    "    )\n" +
                    "ORDER BY DegatRoute.idRoute, DegatRoute.pkDebut, DegatRoute.pkFin, DegatRoute.etat;";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1, idRoute);
                preparedStatement.setInt(2, priorite);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    DegatRoute degatRoute = new DegatRoute();
                    degatRoute.setIdRoute(resultSet.getInt("idroute"));
                    degatRoute.setPkDebut(resultSet.getInt("pkdebut"));
                    degatRoute.setPkFin(resultSet.getInt("pkfin"));
                    degatRoute.setEtat(resultSet.getInt("etat"));
                    degatRouteList.add(degatRoute);
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return degatRouteList;
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

    public int getPkDebut() {
        return pkDebut;
    }

    public void setPkDebut(int pkDebut) {
        this.pkDebut = pkDebut;
    }

    public int getPkFin() {
        return pkFin;
    }

    public void setPkFin(int pkFin) {
        this.pkFin = pkFin;
    }

    public double getEtat() {
        return etat;
    }

    public void setEtat(double etat) {
        this.etat = etat;
    }
}
