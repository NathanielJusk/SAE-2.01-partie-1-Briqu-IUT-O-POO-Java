public class ViewNewTheme extends VBox {
    private Label titreLabel;
    private Button home;
    private GridPane grid;
    private HBox hbox;

    private Label numThemeLabel;
    private TextField numThemeTextField;
    private Label nomThemeLabel;
    private TextField nomThemeTextField;
    private Label numThemeParentLabel;
    private TextField numThemeParentTextField;

    private Button validerButton;

    private VBox vbox;

    public ViewNewTheme() {
        super();
        this.titreLabel = new Label("Creer un theme ou un sous theme");
        this.home = new Button();

        // Creation GridPane
        this.grid = new GridPane();
        this.numThemeLabel = new Label("Numéro du thème :");
        this.numThemeTextField = new TextField();
        this.nomThemeLabel = new Label("Nom du thème :");
        this.nomThemeTextField = new TextField();
        this.numThemeParentLabel = new Label("Numéro du thème parent :");
        this.numThemeParentTextField = new TextField();

        grid.add(numThemeLabel, 0, 0);
        grid.add(numThemeTextField, 1, 0);
        grid.add(nomThemeLabel, 0, 1);
        grid.add(nomThemeTextField, 1, 1);
        grid.add(numThemeParentLabel, 0, 2);
        grid.add(numThemeParentTextField, 1, 2);

        // Creation HBox
        this.hbox = new HBox();
        this.validerButton = new Button("Valider");
        hbox.getChildren().add(validerButton);

        // Creation VBox
        this.vbox = new VBox();
        vbox.getChildren().addAll(titreLabel, home, grid, hbox);
    }
}