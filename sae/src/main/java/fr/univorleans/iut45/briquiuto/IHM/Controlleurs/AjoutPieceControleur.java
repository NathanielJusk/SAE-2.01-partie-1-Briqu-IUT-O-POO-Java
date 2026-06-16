package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AjoutPieceVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminHomeVue;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AjoutPieceControleur {

    private AjoutPieceVue vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale; 

    public AjoutPieceControleur(AjoutPieceVue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    public void initialiser() {
        vue.getBtnValider().setOnAction(event -> handleValiderPiece());
        vue.getBtnHome().setOnAction(event -> actionRetourAdmin());
    }

    private void actionRetourAdmin() {
        AdminHomeVue vueAdmin = new AdminHomeVue();
        new AdminHomeControleur(vueAdmin, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAdmin, 600, 500));
    }

    public void handleValiderPiece() {
        String numero = vue.getTxtNumero().getText().trim();
        String categorie = vue.getTxtCategorie().getText().trim(); 
        String nom = vue.getTxtNom().getText().trim();

        if (numero.isEmpty() || nom.isEmpty()) {
            vue.getLblErreur().setTextFill(javafx.scene.paint.Color.RED);
            vue.afficherErreur("Veuillez remplir le numéro et le nom.");
            return;
        }

        try {
            Piece nouvellePiece = new Piece(numero, nom);
            modele.ajouterPiece(nouvellePiece);
            
            vue.reinitialiserFormulaire();
            vue.getLblErreur().setTextFill(javafx.scene.paint.Color.GREEN);
            vue.afficherErreur("Pièce ajoutée avec succès !");

        } catch (SQLException e) {
            vue.getLblErreur().setTextFill(javafx.scene.paint.Color.RED);
            vue.afficherErreur("Numero de piece deja pris");
        }
    }
}