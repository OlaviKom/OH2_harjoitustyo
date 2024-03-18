package harjoitustyo.grafiikka.oh2_harjoitustyo;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LisaaNaytto extends OsakasKayttoliittyma {

    private final Button tallenna = new Button("Tallenna");

    private final Button poistu = new Button("Poistu");

    private final TextField tfNimi = new TextField();

    private final TextField tfKiinteistotunnus = new TextField();

    private final TextField tfLaskutusosoite = new TextField();

    private final TextField tfEmail = new TextField();

    private final TextField tfPuhnum = new TextField();

    private final TextField tfMatka = new TextField();

    private final TextField tfPainoluku = new TextField();

    private final ComboBox<String> cbLiikennelaji = new ComboBox<>();

    private final String [] liikennelajit = {"Vakituinen asunto", "Vapaa-ajan asunto", "Kesämökki", "Lomamökki"};

    public void lisaaNaytto() {
        Stage lisaaStage = new Stage();
        BorderPane lisaaNayttoPaneeli = new BorderPane();

        GridPane osakkaanTiedot = new GridPane();
        osakkaanTiedot.setHgap(5);
        osakkaanTiedot.setVgap(5);
        osakkaanTiedot.setAlignment(Pos.CENTER);
        osakkaanTiedot.add(new Text("Nimi (Etu- ja sukunimi)"), 0,0);
        osakkaanTiedot.add(tfNimi, 1,0 );
        osakkaanTiedot.add(new Text("Kiinteistötunnus"), 0, 1);
        osakkaanTiedot.add(tfKiinteistotunnus, 1,1);
        osakkaanTiedot.add(new Text("Laskutusosoite"), 0, 2);
        osakkaanTiedot.add(tfLaskutusosoite, 1,2);
        osakkaanTiedot.add(new Text("Sähköpostiosoite"), 0, 3);
        osakkaanTiedot.add(tfEmail, 1,3);
        osakkaanTiedot.add(new Text("Puhelinnumero"), 0, 4);
        osakkaanTiedot.add(tfPuhnum, 1,4);
        osakkaanTiedot.add(new Text("Matka kilometreinä"), 0, 5);
        osakkaanTiedot.add(tfMatka, 1,5);
        osakkaanTiedot.add(new Text("Painoluokka"), 0, 6);
        osakkaanTiedot.add(tfPainoluku, 1,6);
        osakkaanTiedot.add(new Text("Liikennelaji"), 0, 7);
        cbLiikennelaji.setValue("Vakituinen asunto");
        ObservableList<String> alkiot = FXCollections.observableArrayList(liikennelajit);
        cbLiikennelaji.getItems().addAll(alkiot);
        osakkaanTiedot.add(cbLiikennelaji, 1,7);
        lisaaNayttoPaneeli.setCenter(osakkaanTiedot);

        HBox lisaaNayttoButtonit = new HBox(5);
        lisaaNayttoButtonit.setPadding(new Insets(10));
        lisaaNayttoButtonit.getChildren().addAll(tallenna, poistu);
        lisaaNayttoButtonit.setAlignment(Pos.BOTTOM_CENTER);
        lisaaNayttoPaneeli.setBottom(lisaaNayttoButtonit);



         // Asetetaan tallenna buttoni ei klikattavaksi,
         // jos kaikkiin kenttiin ei ole syötetty arvoa
        tallenna.disableProperty().bind(
                Bindings.isEmpty(tfNimi.textProperty())
                        .or(Bindings.isEmpty(tfKiinteistotunnus.textProperty()))
                        .or(Bindings.isEmpty(tfLaskutusosoite.textProperty()))
                        .or(Bindings.isEmpty(tfEmail.textProperty()))
                        .or(Bindings.isEmpty(tfPuhnum.textProperty()))
                        .or(Bindings.isEmpty(tfMatka.textProperty()))
                        .or(Bindings.isEmpty(tfPainoluku.textProperty())));


        poistu.setOnAction(e -> lisaaStage.close());

        tallenna.setOnAction(e -> {
            tiedosto.getOsakkaat().add(new Osakas(tfNimi.getText(),
                    tfKiinteistotunnus.getText(),
                    tfLaskutusosoite.getText(),
                    tfEmail.getText(),
                    tfPuhnum.getText(),
                    Double.parseDouble(tfMatka.getText()),
                    Integer.parseInt(tfPainoluku.getText()),
                    cbLiikennelaji.getValue()
            ));
            OsakasKayttoliittyma.osakasValinta.getItems().addAll(tiedosto.getOsakasNimet().getLast());
        });

        Scene lisaaKehys = new Scene(lisaaNayttoPaneeli, 400, 400);
        lisaaStage.setScene(lisaaKehys);
        lisaaStage.setTitle("Lisää yksityistie osakas");
        lisaaStage.initModality(Modality.APPLICATION_MODAL);
        lisaaStage.show();
    }
}
