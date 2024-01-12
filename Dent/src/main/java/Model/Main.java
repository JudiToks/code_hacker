package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        PatientEtatDent patientEtatDent = new PatientEtatDent();
        Controle controle = new Controle();
        ConnexionBDD connexionBDD = new ConnexionBDD();
        Connection connection = connexionBDD.connect();
        List<PatientEtatDent> patientEtatDentList = patientEtatDent.DentPatienbyIdPriorite(1 , 2 , connection);
        List<Controle> controleList = controle.Traitement(patientEtatDentList , connection);
        /*for (Controle controle1 : controleList){
                    System.out.println("Code "+controle1.getDent()+" Action :"+controle1.getAction()+" Etat :"+controle1.getEtatActuel()+" Prix :"+controle1.getPrix()+" Total :"+controle1.getPrixTotal());
        }*/
        List<Controle> controles = controle.TraitementFinal(controleList , 965 , connection);
        /*for (Controle controle1 : controles){
                    System.out.println("Code "+controle1.getDent()+" Action :"+controle1.getAction()+" Etat :"+controle1.getEtatActuel()+" Prix :"+controle1.getPrix()+" Total :"+controle1.getPrixTotal());
        }*/
        List<Controle> controles1 = controle.TraitementFinal10(controleList , 900 , connection);
        double somme = 0;
        for (Controle controle1 : controles1){
            somme+= controle1.getPrix();
            System.out.println("Code "+controle1.getDent()+" Action :"+controle1.getAction()+" Etat :"+controle1.getEtatActuel()+" Prix :"+controle1.getPrix()+" Total :"+controle1.getPrixTotal());
        }
        System.out.println(somme);
        connection.close();
    }
}
