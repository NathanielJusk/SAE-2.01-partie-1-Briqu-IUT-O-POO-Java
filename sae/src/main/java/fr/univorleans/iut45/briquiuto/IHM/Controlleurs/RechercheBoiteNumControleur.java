package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.CollectionneurHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.VueRechercheBoiteParNumero;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RechercheBoiteNumControleur {

    private VueRechercheBoiteParNumero vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public RechercheBoiteNumControleur(VueRechercheBoiteParNumero vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    private void initialiser() {
        this.vue.getBtnRechercher().setOnAction(e -> actionRechercher());
        this.vue.getBtnRetour().setOnAction(e -> actionRetour());
    }

    private void actionRechercher() {
        String numeroSaisi = vue.getTxtNumeroBoite().getText().trim();

        if (numeroSaisi.isEmpty()) {
            return;
        }

        // ⚠️ ICI ON UTILISE LE MANAGER EN MÉMOIRE (La POO)
        Boite boiteTrouvee = modele.getManager().rechercherBoiteParNumero(numeroSaisi);

        if (boiteTrouvee != null) {
            vue.getLblResultatNom().setText("Boîte trouvée : " + boiteTrouvee.getNom());
            vue.getLblResultatAnnee().setText("Année de sortie : " + boiteTrouvee.getAnnee());
            vue.getLblResultatNbPieces().setText("Nombre de pièces : " + boiteTrouvee.getNbPiece());
        } else {
            vue.getLblResultatNom().setText("Aucune boîte ne porte le numéro : " + numeroSaisi);
            vue.getLblResultatAnnee().setText("");
            vue.getLblResultatNbPieces().setText("");
        }
        
        vue.getLblResultatNom().getParent().setVisible(true); // On affiche la zone
    }

    private void actionRetour() {
        CollectionneurHomeVue vueCollec = new CollectionneurHomeVue();
        new CollectionneurHomeControleur(vueCollec, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueCollec, 1000, 700));
    }
}