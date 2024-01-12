package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Devise {
    private int rn;

    private String action;
    private int pkDebut;
    private int pkFin;
    private double etat;
    private double prix;
    private double prixTotal;

    public List<Devise> DeviseByListDegat(List<DegatRoute> degatRouteList , Connection connection) throws SQLException, ClassNotFoundException {
        List<Devise> deviseList = new ArrayList<>();
        Route route = new Route();
        PrixPrestationDegat prixPrestationDegat = new PrixPrestationDegat();
        EchelleDegat echelleDegat = new EchelleDegat();
        Prestation prestation = new Prestation();
        double valeur = 0;
        double cout = 0;
        double famerimbola = 0;
        if (connection != null) {
            for (DegatRoute degatRoute : degatRouteList){
                if (degatRoute.getEtat()< 10){
                    double compte = 0;
                    while (degatRoute.getEtat() < 10){
                        Devise devise = new Devise();
                        EchelleDegat echelle = echelleDegat.EchelleDegatByEtat((int) degatRoute.getEtat() , connection);
                        prixPrestationDegat = prixPrestationDegat.PrixPrestationByPrestationByRoute( echelle.getIdprestation() , degatRoute.getIdRoute(), connection);
                        cout = prixPrestationDegat.getValue() * (degatRoute.getPkFin()-degatRoute.getPkDebut());
                        degatRoute.setEtat(getNewEtat(degatRoute.getEtat()));
                        compte += cout ;
                        devise.setRn(degatRoute.getIdRoute());
                        devise.setPkDebut(degatRoute.getPkDebut());
                        devise.setPkFin(degatRoute.getPkFin());
                        devise.setAction(prestation.prestateursById(prixPrestationDegat.getIdPrestation() , connection).getNom());
                        devise.setPrix(cout);
                        devise.setEtat(degatRoute.getEtat());
                        devise.setPrixTotal(compte);
                        deviseList.add(devise);
                    }
                }
            }
        }
        return deviseList;
    }
    public double getNewEtat(double vaovao){
        if (vaovao>=8 && vaovao<10) return Math.min(vaovao+1 , 10);
        if (vaovao>=4 && vaovao<8) return Math.min(vaovao+1 , 10);
        if (vaovao>=1 && vaovao<4) return 0;
        if (vaovao == 0) return 10;
        return vaovao;
    }

    public List<Devise> DeviseFinal10(List<Devise> deviseList , double vola , Connection connection) throws SQLException, ClassNotFoundException {
        List<Devise> devises = new ArrayList<>();
        List<Integer> indiceMisyFameriny = new ArrayList<>();
        double value = 0;
        for (int i = 0 ; i < deviseList.size() ; i++){
            if (deviseList.get(i).getEtat() == 10){
                if (deviseList.get(i).getPrixTotal() <= vola){
                    vola -= deviseList.get(i).getPrixTotal();
                    value += deviseList.get(i).getPrixTotal();
                    indiceMisyFameriny.add(i);
                }
            }
        }
        for (int all = 0 ; all < deviseList.size() ; all++){
            for (int vrai = 0 ; vrai < indiceMisyFameriny.size() ; vrai++){
                if (deviseList.get(all).getPkDebut() == deviseList.get(indiceMisyFameriny.get(vrai)).getPkDebut() && deviseList.get(all).getPkFin() == deviseList.get(indiceMisyFameriny.get(vrai)).getPkFin() ){
                    Devise devise = new Devise();
                    devise.setRn(deviseList.get(all).getRn());
                    devise.setPkDebut(deviseList.get(all).getPkDebut());
                    devise.setPkFin(deviseList.get(all).getPkFin());
                    devise.setAction(deviseList.get(all).getAction());
                    devise.setEtat(deviseList.get(all).getEtat());
                    devise.setPrix(deviseList.get(all).getPrix());
                    devise.setPrixTotal(deviseList.get(all).getPrixTotal());
                    devises.add(devise);
                }
            }
        }
        return devises;
    }
    public int getRn() {
        return rn;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setRn(int rn) {
        this.rn = rn;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }
}
