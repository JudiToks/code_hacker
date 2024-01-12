package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DegatRoute degatRoute = new DegatRoute();
        Devise devise = new Devise();
        ConnexionBDD connexionBDD = new ConnexionBDD();
        Connection connection = connexionBDD.connect();
        List<DegatRoute> degatRouteList = degatRoute.DegatRouteByIdRouteByPriorite(1 , 2 ,  connection);
        List<DegatRoute> degatRoutes = degatRoute.DegatRouteNotImportantByIdRouteByPriorite(1 , 2 ,  connection);
        if (degatRoutes.size() != 0){
            for (int i = 0 ; i < degatRoutes.size() ; i++){
                degatRouteList.add(degatRoutes.get(i));
            }
        }
        for (DegatRoute route : degatRouteList){
            System.out.println("Rn"+route.getIdRoute()+" Debut :"+route.getPkDebut()+" Fin :"+route.getPkFin()+" Etat"+route.getEtat());
        }
        List<Devise> deviseList = devise.DeviseByListDegat(degatRouteList , connection);
        /*for (Devise devise1 : deviseList){
            System.out.println("Rn"+devise1.getRn()+" "+devise1.getPkDebut()+"-"+devise1.getPkFin()+" | Action : "+devise1.getAction()+" | etat : "+devise1.getEtat()+" | Prix :"+devise1.getPrix()+" | Totat :"+devise1.getPrixTotal());
        }*/
        List<Devise> devises = devise.DeviseFinal10(deviseList , 205 , connection);
        /*for (Devise devise1 : devises){
            System.out.println("Rn"+devise1.getRn()+" "+devise1.getPkDebut()+"-"+devise1.getPkFin()+" | Action : "+devise1.getAction()+" | etat : "+devise1.getEtat()+" | Prix :"+devise1.getPrix()+" | Totat :"+devise1.getPrixTotal());
        }*/
        connection.close();
    }
}
