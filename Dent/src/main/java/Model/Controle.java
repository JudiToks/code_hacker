package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controle {
    private String dent;
    private String action;
    private double EtatActuel;
    private double Prix;
    private double PrixTotal;



    public List<Controle> Traitement(List<PatientEtatDent> patientEtatDents ,Connection connection) throws SQLException, ClassNotFoundException {
        List<Controle> controleList = new ArrayList<>();
        Dent dent = new Dent();
        PrixPrestation prixPrestation = new PrixPrestation();
        EchelleDent echelleDent = new EchelleDent();
        Prestation prestation = new Prestation();

        double valeur = 0;
        double cout = 0;
        double famerimbola = 0;
        if (connection != null) {
            for (PatientEtatDent patientEtatDent : patientEtatDents){
                if (patientEtatDent.getEtat()< 10){
                    double compte = 0;
                    while (patientEtatDent.getEtat() < 10){
                        Controle controle = new Controle();
                        EchelleDent echelle = echelleDent.EchelleDentByEtat((int)patientEtatDent.getEtat() , connection);
                        prixPrestation = prixPrestation.PrixPrestationByPrestationByCode( echelle.getIdPrestation() , patientEtatDent.getCode(), connection);
                        System.out.println(prixPrestation.getPrix());
                        cout = prixPrestation.getPrix();
                        patientEtatDent.setEtat(getNewEtat(patientEtatDent.getEtat()));
                        compte += cout;
                        controle.setDent(patientEtatDent.getCode());
                        controle.setAction(prestation.prestateursById(prixPrestation.getIdPrestation() , connection).getNom());
                        controle.setPrix(cout);
                        controle.setEtatActuel(patientEtatDent.getEtat());
                        controle.setPrixTotal(compte);
                        controleList.add(controle);
                    }
                }
            }
        }
        return controleList;
    }
    public double getNewEtat(double vaovao){
        if (vaovao>=8 && vaovao<10) return Math.min(vaovao+1 , 10);
        if (vaovao>=4 && vaovao<8) return Math.min(vaovao+1 , 10);
        if (vaovao>=1 && vaovao<4) return Math.min(vaovao+1 , 10);
        if (vaovao == 0) return 10;
        return vaovao;
    }
    public List<Controle> TraitementFinal(List<Controle> controleList , double vola , Connection connection) throws SQLException, ClassNotFoundException {
        List<Controle> controles = new ArrayList<>();
        double argent = vola;
        int i = 0;
        for (Controle controle : controleList){
            Controle control = new Controle();
            if (vola >= 0){
                vola -= controle.getPrix();
                if (vola < 0 ) break;
                control.setDent(controle.getDent());
                control.setAction(controle.getAction());
                control.setEtatActuel(controle.getEtatActuel());
                control.setPrix(controle.getPrix());
                control.setPrixTotal(controle.getPrixTotal());
                controles.add(control);
            }
            i++;
        }
        double somme = controles.get(controles.size()-1).getPrixTotal();
        double fameriny = argent - controles.get(controles.size()-1).getPrixTotal();
        int z =0;
        if (fameriny > 0){
            for (int j = i ; j < controleList.size() ; j++){
                z = j+1 ;
                Controle control = new Controle();
               if (controleList.get(j).getPrix() <= fameriny &&  controleList.get(j-1).getPrix() != controleList.get(j).getPrix()){
                   fameriny -= controleList.get(j).getPrix();
                   somme += controleList.get(j).getPrix();
                   control.setDent(controleList.get(j).getDent());
                   control.setAction(controleList.get(j).getAction());
                   control.setEtatActuel(controleList.get(j).getEtatActuel());
                   control.setPrix(controleList.get(j).getPrix());
                   control.setPrixTotal(somme);
                   controles.add(control);
                   while (controleList.get(z-1).getPrix() == controleList.get(z).getPrix() && controleList.get(z).getPrix()<= fameriny && controleList.get(z).getPrix()<= (fameriny+controleList.get(z-1).getPrix())){
                       fameriny -= controleList.get(z).getPrix();
                       somme += controleList.get(z).getPrix();
                       control.setDent(controleList.get(z).getDent());
                       control.setAction(controleList.get(z).getAction());
                       control.setEtatActuel(controleList.get(z).getEtatActuel());
                       control.setPrix(controleList.get(z).getPrix());
                       control.setPrixTotal(somme);
                       controles.add(control);
                       z++;
                   }
               }
            }
        }
        return controles;
    }
    public List<Controle> TraitementFinal10(List<Controle> controleList , double vola , Connection connection) throws SQLException, ClassNotFoundException {
        List<Controle> controles = new ArrayList<>();
        List<Integer> indiceMisyFameriny = new ArrayList<>();
        double value = 0;
        for (int i = 0 ; i < controleList.size() ; i++){
            if (controleList.get(i).getEtatActuel() == 10){
                if (controleList.get(i).getPrixTotal() <= vola){
                    vola -= controleList.get(i).getPrixTotal();
                    value += controleList.get(i).getPrixTotal();
                    indiceMisyFameriny.add(i);
                }
            }
        }
        for (int all = 0 ; all < controleList.size() ; all++){
            for (int vrai = 0 ; vrai < indiceMisyFameriny.size() ; vrai++){
                if (controleList.get(all).getDent() == controleList.get(indiceMisyFameriny.get(vrai)).getDent()){
                    Controle controle = new Controle();
                    controle.setDent(controleList.get(all).getDent());
                    controle.setAction(controleList.get(all).getAction());
                    controle.setEtatActuel(controleList.get(all).getEtatActuel());
                    controle.setPrix(controleList.get(all).getPrix());
                    controle.setPrixTotal(controleList.get(all).getPrixTotal());
                    controles.add(controle);
                }
            }
        }
        return controles;
    }


    /*public List<Controle> Traitement(List<PatientEtatDent> patientEtatDents , double vola , Connection connection) throws SQLException, ClassNotFoundException {
        List<Controle> controleList = new ArrayList<>();
        Dent dent = new Dent();
        double compte = 0;
        double valeur = 0;
        double cout = 0;
        double famerimbola = 0;
        if (connection != null) {
            for (PatientEtatDent patientEtatDent : patientEtatDents){
                cout = prix.PrixByDent(patientEtatDent.getCode() , connection );
                if (patientEtatDent.getEtat() < 10){
                    while (patientEtatDent.getEtat() < 10 && vola >= 0){
                        Controle controle = new Controle();
                        controle.setDent(dent.DentById(patientEtatDent.getCode() , connection).getNom());
                        if(patientEtatDent.getEtat()==0){
                            compte += cout * 10;
                            vola -= cout * 10;
                            controle.setPrix(cout * 10);
                            patientEtatDent.setEtat(10);
                            controle.setAction("Misolo");
                        }
                        if(patientEtatDent.getEtat()>=1 && patientEtatDent.getEtat()<=3){
                            compte += cout ;
                            vola -= cout;
                            controle.setPrix(cout);
                            patientEtatDent.setEtat(0);
                            controle.setAction("Esorina");
                        }
                        if(patientEtatDent.getEtat()>3 && patientEtatDent.getEtat()<=9) {
                            valeur = coefficent.CoefficentByEtat((int) patientEtatDent.getEtat(), connection);
                            compte += cout * valeur;
                            vola -= cout * valeur;
                            controle.setPrix(cout * valeur);
                            patientEtatDent.setEtat(patientEtatDent.getEtat()+1);
                            if (patientEtatDent.getEtat() > 3 && patientEtatDent.getEtat() <= 7){
                                controle.setAction("Soin");
                            }
                            if (patientEtatDent.getEtat() > 7){
                                controle.setAction("Netoyage");
                            }
                            if (patientEtatDent.getEtat() == 10){
                                controle.setAction("Bonne sante");
                            }
                        }
                        if (vola <= 0) break;
                        controle.setEtatActuel(patientEtatDent.getEtat());
                        controle.setPrixTotal(compte);
                        controleList.add(controle);
                    }
                    while (vola > 0){
                        if (dentClient.getEtat() !=10){
                            cout = prix.PrixByDent(dentClient.getIdDent() , connection );
                            valeur = coefficent.CoefficentByEtat((int) dentClient.getEtat(), connection);
                            famerimbola = cout * valeur;
                            if (vola < famerimbola){
                                vola-=  famerimbola;
                                compte+= famerimbola;
                                dentClient.setEtat(dentClient.getEtat()+1);
                            }
                        }
                    }
                }
            }
        }
        return controleList;
    }*/
    public String getDent() {
        return dent;
    }

    public void setDent(String dent) {
        this.dent = dent;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getEtatActuel() {
        return EtatActuel;
    }

    public void setEtatActuel(double etatActuel) {
        EtatActuel = etatActuel;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(double prix) {
        Prix = prix;
    }

    public double getPrixTotal() {
        return PrixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        PrixTotal = prixTotal;
    }
}
