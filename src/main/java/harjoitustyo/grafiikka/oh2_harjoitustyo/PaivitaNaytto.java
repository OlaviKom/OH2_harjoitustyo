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

import java.util.ArrayList;

public class PaivitaNaytto extends OsakasKayttoliittyma {


    private final Button paivitaTallenna = new Button("Tallenna");

    private final Button paivitaPoistu = new Button("Poistu");

    private final TextField paivitaTfNimi = new TextField();

    private final TextField paivitaTfKiinteistotunnus = new TextField();

    private final TextField paivitaTfLaskutusosoite = new TextField();

    private final TextField paivitaTfEmail = new TextField();

    private final TextField paivitaTfPuhnum = new TextField();

    private final TextField paivitaTfMatka = new TextField();

    private final TextField paivitaTfPainoluku = new TextField();

    private final ComboBox<String> paivitaCbLiikennelaji = new ComboBox<>();

    private final ComboBox<String> paivitaCbOsakkaat = new ComboBox<>();

    private int valittuIndeksi;

    private ArrayList<String> osakkaidenNimet = new ArrayList<>();

    public void asetaNimet() {
        for(Osakas olio: tiedosto.getOsakkaat()){
            osakkaidenNimet.add(olio.getNimi());
        }
        paivitaCbOsakkaat.setItems(FXCollections.observableArrayList(osakkaidenNimet));
    }


    public void paivitaNaytto() {
        asetaNimet();
        Stage paivitaStage = new Stage();
        BorderPane paivitaNayttoPaneeli = new BorderPane();

        GridPane osakkaanTiedot = new GridPane();
        osakkaanTiedot.setHgap(5);
        osakkaanTiedot.setVgap(5);
        osakkaanTiedot.setAlignment(Pos.CENTER);
        osakkaanTiedot.add(new Text("Osakas"), 0,0);

        paivitaCbOsakkaat.setValue("Osakkas kenen tiedot päivitetään");


        paivitaCbOsakkaat.setOnAction(e -> {
            valittuIndeksi = paivitaCbOsakkaat.getSelectionModel().getSelectedIndex();
            paivitaTfNimi.setText(tiedosto.getOsakkaat().get(valittuIndeksi).getNimi());
            paivitaTfKiinteistotunnus.setText(tiedosto.getOsakkaat().get(valittuIndeksi).getKiinteistotunnus());
            paivitaTfLaskutusosoite.setText(tiedosto.getOsakkaat().get(valittuIndeksi).getLaskutusosoite());
            paivitaTfEmail.setText(tiedosto.getOsakkaat().get(valittuIndeksi).getEmail());
            paivitaTfPuhnum.setText(tiedosto.getOsakkaat().get(valittuIndeksi).getPuhnum());
            paivitaTfMatka.setText(String.valueOf(tiedosto.getOsakkaat().get(valittuIndeksi).getMatka()));
            paivitaTfPainoluku.setText(String.valueOf(tiedosto.getOsakkaat().get(valittuIndeksi).getPainoluku()));
            paivitaCbLiikennelaji.setValue(tiedosto.getOsakkaat().get(valittuIndeksi).getLiikennelaji());
        });


        osakkaanTiedot.add(paivitaCbOsakkaat, 1,0);
        osakkaanTiedot.add(new Text("Nimi (Etu- ja sukunimi)"), 0,1);
        osakkaanTiedot.add(paivitaTfNimi, 1,1 );
        osakkaanTiedot.add(new Text("Kiinteistötunnus"), 0, 2);
        osakkaanTiedot.add(paivitaTfKiinteistotunnus, 1,2);
        osakkaanTiedot.add(new Text("Laskutusosoite"), 0, 3);
        osakkaanTiedot.add(paivitaTfLaskutusosoite, 1,3);
        osakkaanTiedot.add(new Text("Sähköpostiosoite"), 0, 4);
        osakkaanTiedot.add(paivitaTfEmail, 1,4);
        osakkaanTiedot.add(new Text("Puhelinnumero"), 0, 5);
        osakkaanTiedot.add(paivitaTfPuhnum, 1,5);
        osakkaanTiedot.add(new Text("Matka kilometreinä"), 0, 6);
        osakkaanTiedot.add(paivitaTfMatka, 1,6);
        osakkaanTiedot.add(new Text("Painoluku"), 0, 7);
        osakkaanTiedot.add(paivitaTfPainoluku, 1,7);
        osakkaanTiedot.add(new Text("Liikennelaji"), 0, 8);
        paivitaCbLiikennelaji.setValue("Vakituinen asunto");
        // HUOMM!!tässä bugi, lisää aina liikennelajit uudestaan valikkoon,
        // kun mennään lisaa näyttöön KORJAA
        ObservableList<String> alkiot = FXCollections.observableArrayList(getLiikennelajit());
        paivitaCbLiikennelaji.getItems().addAll(alkiot);
        osakkaanTiedot.add(paivitaCbLiikennelaji, 1,8);
        paivitaNayttoPaneeli.setCenter(osakkaanTiedot);

        HBox paivitaNayttoButtonit = new HBox(5);
        paivitaNayttoButtonit.setPadding(new Insets(10));
        paivitaNayttoButtonit.getChildren().addAll(paivitaTallenna, paivitaPoistu);
        paivitaNayttoButtonit.setAlignment(Pos.BOTTOM_CENTER);
        paivitaNayttoPaneeli.setBottom(paivitaNayttoButtonit);


         // Asetetaan tallenna buttoni ei klikattavaksi,
         // jos kaikkiin kenttiin ei ole syötetty arvoa
        paivitaTallenna.disableProperty().bind(
                Bindings.isEmpty(paivitaTfNimi.textProperty())
                        .or(Bindings.isEmpty(paivitaTfKiinteistotunnus.textProperty()))
                        .or(Bindings.isEmpty(paivitaTfLaskutusosoite.textProperty()))
                        .or(Bindings.isEmpty(paivitaTfEmail.textProperty()))
                        .or(Bindings.isEmpty(paivitaTfPuhnum.textProperty()))
                        .or(Bindings.isEmpty(paivitaTfMatka.textProperty()))
                        .or(Bindings.isEmpty(paivitaTfPainoluku.textProperty())));

        paivitaTallenna.setOnAction(e -> {
            tiedosto.getOsakkaat().get(valittuIndeksi).setNimi(paivitaTfNimi.getText());
            tiedosto.getOsakkaat().get(valittuIndeksi).setKiinteistotunnus(paivitaTfKiinteistotunnus.getText());
            tiedosto.getOsakkaat().get(valittuIndeksi).setLaskutusosoite(paivitaTfLaskutusosoite.getText());
            tiedosto.getOsakkaat().get(valittuIndeksi).setEmail(paivitaTfEmail.getText());
            tiedosto.getOsakkaat().get(valittuIndeksi).setPuhnum(paivitaTfPuhnum.getText());
            tiedosto.getOsakkaat().get(valittuIndeksi).setMatka(Double.parseDouble(paivitaTfMatka.getText()));
            tiedosto.getOsakkaat().get(valittuIndeksi).setPainoluku(Integer.parseInt(paivitaTfPainoluku.getText()));
            tiedosto.getOsakkaat().get(valittuIndeksi).setLiikennelaji(paivitaCbLiikennelaji.getValue());
            OsakasKayttoliittyma.osakasTiedot.setText(tiedosto.getOsakkaat().get(valittuIndeksi).toString());
        });

        paivitaPoistu.setOnAction(e -> paivitaStage.close());

        Scene lisaaKehys = new Scene(paivitaNayttoPaneeli, 400, 400);
        paivitaStage.setScene(lisaaKehys);
        paivitaStage.setTitle("päivitä yksityistieosakkaan tiedot");
        paivitaStage.initModality(Modality.APPLICATION_MODAL);
        paivitaStage.show();
    }
}
