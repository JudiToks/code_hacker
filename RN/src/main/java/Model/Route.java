package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Route {
    private int idRoute;
    private int idPkDebut;
    private int idPkFin;

    public void inseRoute(Route route, Connection connection) throws SQLException, ClassNotFoundException {
        if (connection != null) {
            String insertQuery = "insert into route (id, pkDebut, pkFin) values (? , ? , ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setInt(1, route.getIdRoute());
                preparedStatement.setInt(2, route.getIdPkDebut());
                preparedStatement.setInt(3, route.getIdPkFin());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête INSERT : " + e.getMessage());
            }
        }
    }
    public List<Route> AllRoute(Connection connection) throws SQLException, ClassNotFoundException {
        List<Route> routeList = new ArrayList<>();
        if (connection != null) {
            String selectQuery = "SELECT * from route;";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Route route = new Route();
                    route.setIdRoute(resultSet.getInt("id"));
                    route.setIdPkDebut(resultSet.getInt("pkdebut"));
                    route.setIdPkFin(resultSet.getInt("pkfin"));
                    routeList.add(route);
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return routeList;
    }

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public int getIdPkDebut() {
        return idPkDebut;
    }

    public void setIdPkDebut(int idPkDebut) {
        this.idPkDebut = idPkDebut;
    }

    public int getIdPkFin() {
        return idPkFin;
    }

    public void setIdPkFin(int idPkFin) {
        this.idPkFin = idPkFin;
    }
}
