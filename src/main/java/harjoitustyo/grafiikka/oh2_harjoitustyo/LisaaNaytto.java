package harjoitustyo.grafiikka.oh2_harjoitustyo;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * LisaaNaytto.java
 * Tämä luokka on uuden yksityistieo osakkaan tietojen lisäämistä käsittelevä popup ikkuna/naytto
 * toimii OsakasKayttoliittyman aliluokkana
 * @author Antti Komulainen
 * @version 1.00 16/03/2024
 */
public class LisaaNaytto extends OsakasKayttoliittyma {
    /** painike tietojen tallentamista varten */
    private final Button tallenna = new Button("Tallenna");
    /** painike poistumista varten */
    private final Button poistu = new Button("Poistu");
    /** tekstikenttä nimen syöttämistä varten */
    private final TextField tfNimi = new TextField();
    /** tekstikenttä kiinteistötunnuksen syöttämistä varten */
    private final TextField tfKiinteistotunnus = new TextField();
    /** tekstikenttä laskutusosoitteen syöttämistä varten */
    private final TextField tfLaskutusosoite = new TextField();
    /** tekstikenttä sähköpostiosoitteen syöttämistä varten */
    private final TextField tfEmail = new TextField();
    /** tekstikenttä puhelinnumeron syöttämistä varten */
    private final TextField tfPuhnum = new TextField();
    /** tekstikenttä matkan syöttämistä varten */
    private final TextField tfMatka = new TextField();
    /** tekstikenttä painoluvun syöttämistä varten */
    private final TextField tfPainoluku = new TextField();
    /** valinta lista, josta voidaan valita osakkaan liikennelaji */
    private final ComboBox<String> cbLiikennelaji = new ComboBox<>();

    /**
     * Popup ikkunan/nayton käynnistys ja toiminnalisuuksien määrittely
     * sisältää paneelin, solmut, kuuntelijat ja tapahtuman käsittelijät
     */
    public void lisaaNaytto() {
        // Stage
        Stage lisaaStage = new Stage();
        // paneeli
        BorderPane lisaaNayttoPaneeli = new BorderPane();

        // gridpane johon, lisätään tekstikentät ja valinta lista
        GridPane osakkaanTiedot = new GridPane();
        // määritettään valit
        osakkaanTiedot.setHgap(5);
        osakkaanTiedot.setVgap(5);
        // määritetään sijainti keskelle
        osakkaanTiedot.setAlignment(Pos.CENTER);
        // asetetaan solmut gridpaneen
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
        // combobox josta valitaan sopiva liikkennelaji osakkaalle
        // oletus valintana vakituinen asunto
        cbLiikennelaji.setValue("Vakituinen asunto");
        ObservableList<String> alkiot = FXCollections.observableArrayList(getLiikennelajit());
        cbLiikennelaji.getItems().addAll(alkiot);
        osakkaanTiedot.add(cbLiikennelaji, 1,7);
        lisaaNayttoPaneeli.setCenter(osakkaanTiedot);

        // Hbox painikkeille tallenna ja poistu
        HBox lisaaNayttoButtonit = new HBox(5);
        lisaaNayttoButtonit.setPadding(new Insets(10));
        lisaaNayttoButtonit.getChildren().addAll(tallenna, poistu);
        lisaaNayttoButtonit.setAlignment(Pos.BOTTOM_CENTER);
        lisaaNayttoPaneeli.setBottom(lisaaNayttoButtonit);

         // Asetetaan tallenna painike ei klikattavaksi,
         // jos kaikkiin kenttiin ei ole syötetty arvoa
        tallenna.disableProperty().bind(
                Bindings.isEmpty(tfNimi.textProperty())
                        .or(Bindings.isEmpty(tfKiinteistotunnus.textProperty()))
                        .or(Bindings.isEmpty(tfLaskutusosoite.textProperty()))
                        .or(Bindings.isEmpty(tfEmail.textProperty()))
                        .or(Bindings.isEmpty(tfPuhnum.textProperty()))
                        .or(Bindings.isEmpty(tfMatka.textProperty()))
                        .or(Bindings.isEmpty(tfPainoluku.textProperty())));

        // tallenna painikkeen tapahtuman käsittelijä
        // luo uuden Osakas olion ja antaa sille parametreinä tekstikenttien tiedot
        // asettaa lopuksi osakasValinta listViewiin lisätyn osakkaan nimen
        // tarkistaa onko textfieldien matka ja painoluku arvot numeerisia, jos ei textfieldeihin asetetaan
        // virhe teksti ja uutta oliota ei saada luotua ennen kuin syötteiden arvot ovat numeeriset
        tallenna.setOnAction(e -> {
            String virheTeksti = "Anna arvo lukuna";
            if (onNumeerinen(tfMatka) && onNumeerinen(tfPainoluku)) {
                tiedosto.getOsakkaat().add(new Osakas(tfNimi.getText(),
                        tfKiinteistotunnus.getText(),
                        tfLaskutusosoite.getText(),
                        tfEmail.getText(),
                        tfPuhnum.getText(),
                        Double.parseDouble(tfMatka.getText()),
                        Integer.parseInt(tfPainoluku.getText()),
                        cbLiikennelaji.getValue()));
                OsakasKayttoliittyma.osakasValinta.getItems().addAll(tiedosto.getOsakasNimet().getLast());
            } else {
                if(!onNumeerinen(tfMatka)) {
                    tfMatka.setText(virheTeksti);
                    tfMatka.setOnMouseClicked(event -> {
                        tfMatka.setText("");
                    });
                }
                if(!onNumeerinen(tfPainoluku)) {
                    tfPainoluku.setText(virheTeksti);
                    tfPainoluku.setOnMouseClicked(event -> {
                        tfPainoluku.setText("");
                    });
                }
            }
        });

        // poistu painikkeen tapahtuman käsittelijä
        // sulkee ikkunan/nayton
        poistu.setOnAction(e -> lisaaStage.close());

        Scene lisaaKehys = new Scene(lisaaNayttoPaneeli, 400, 400);
        lisaaStage.setScene(lisaaKehys);
        lisaaStage.setTitle("Lisää yksityistie osakas");
        lisaaStage.initModality(Modality.APPLICATION_MODAL);
        lisaaStage.show();
    }

    private boolean onNumeerinen(TextField kentta){
        try{
            Double.parseDouble(kentta.getText());
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
}
