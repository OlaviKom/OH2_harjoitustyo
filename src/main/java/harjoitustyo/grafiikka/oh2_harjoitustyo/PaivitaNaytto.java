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

/**
 * Tämä luokka on uuden yksityistieo osakkaan tietojen päivittämistä käsittelävä popup ikkuna/naytto
 * toimii OsakasKayttoliittyman aliluokkana
 * @author Antti Komulainen
 * @version 1.10 24/03/2024
 */

public class PaivitaNaytto extends OsakasKayttoliittyma {
    /** painike tietojen tallentamista varten */
    private final Button paivitaTallenna = new Button("Tallenna");
    /** painike poistumista varten */
    private final Button paivitaPoistu = new Button("Poistu");
    /** tekstikenttä nimen syöttämistä varten */
    private final TextField paivitaTfNimi = new TextField();
    /** tekstikenttä kiinteistötunnuksen syöttämistä varten */
    private final TextField paivitaTfKiinteistotunnus = new TextField();
    /** tekstikenttä laskutusosoitteen syöttämistä varten */
    private final TextField paivitaTfLaskutusosoite = new TextField();
    /** tekstikenttä sähköpostiosoitteen syöttämistä varten */
    private final TextField paivitaTfEmail = new TextField();
    /** tekstikenttä puhelinnumeron syöttämistä varten */
    private final TextField paivitaTfPuhnum = new TextField();
    /** tekstikenttä matkan syöttämistä varten */
    private final TextField paivitaTfMatka = new TextField();
    /** tekstikenttä painoluvun syöttämistä varten */
    private final TextField paivitaTfPainoluku = new TextField();
    /** valinta lista, josta voidaan valita osakkaan liikennelaji */
    private final ComboBox<String> paivitaCbLiikennelaji = new ComboBox<>();
    /** valinta lista, päivitettävän osakkaan valitsemista varten */
    private final ComboBox<String> paivitaCbOsakkaat = new ComboBox<>();
    /** valitun osakkaan ideksi */
    private int valittuIndeksi;
    /** lista osakkaiden nimiä varten */
    private ArrayList<String> osakkaidenNimet = new ArrayList<>();

    /**
     * asettaa osakkaiden nimet osakkaidenNimet listaan
     * lisää nimet comboboxiin josta päivitettävän osakkaan voi valita
     */
    public void asetaNimet() {
        for(Osakas olio: tiedosto.getOsakkaat()){
            osakkaidenNimet.add(olio.getNimi());
        }
        paivitaCbOsakkaat.setItems(FXCollections.observableArrayList(osakkaidenNimet));
    }

    /**
     * Popup ikkunan/nayton käynnistys ja toiminnalisuuksien määrittely
     * sisältää paneelin, solmut, kuuntelijat ja tapahtuman käsittelijät
     */
    public void paivitaNaytto() {
        // kutsutaan asetaNimet metodia
        asetaNimet();
        // Stage
        Stage paivitaStage = new Stage();
        // Paneeli
        BorderPane paivitaNayttoPaneeli = new BorderPane();

        // gridpane johon, lisätään tekstikentät ja valinta lista
        GridPane osakkaanTiedot = new GridPane();
        // määritettään valit
        osakkaanTiedot.setHgap(5);
        osakkaanTiedot.setVgap(5);
        osakkaanTiedot.setAlignment(Pos.CENTER);
        osakkaanTiedot.add(new Text("Osakas"), 0,0);
        // aseteaan comboboxiin oletus teksti
        paivitaCbOsakkaat.setValue("Osakkas kenen tiedot päivitetään");

        // combobxin kuuntelija, asettaa valitun osakkaan tiedot valmiiksi tekstikenttiin
        paivitaCbOsakkaat.setOnAction(e -> asetaTiedotKenttiin());

        // loppujen solmujen lisääminen gridpaneen
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
        // combobox josta valitaan sopiva liikkennelaji osakkaalle
        // oletus valintana vakituinen asunto
        paivitaCbLiikennelaji.setValue("Vakituinen asunto");
        ObservableList<String> alkiot = FXCollections.observableArrayList(getLiikennelajit());
        paivitaCbLiikennelaji.getItems().addAll(alkiot);
        osakkaanTiedot.add(paivitaCbLiikennelaji, 1,8);
        paivitaNayttoPaneeli.setCenter(osakkaanTiedot);

        // Hbox painikkeille tallenna ja poistu
        HBox paivitaNayttoButtonit = new HBox(5);
        paivitaNayttoButtonit.setPadding(new Insets(10));
        paivitaNayttoButtonit.getChildren().addAll(paivitaTallenna, paivitaPoistu);
        paivitaNayttoButtonit.setAlignment(Pos.BOTTOM_CENTER);
        paivitaNayttoPaneeli.setBottom(paivitaNayttoButtonit);


         // Asetetaan tallenna buttoni ei klikattavaksi,
         // jos kaikkiin kenttiin ei ole syötetty arvoa
        // toteutukseen otettu mallia linkistä:
        // https://stackoverflow.com/questions/23040531/how-to-disable-button-when-textfield-is-empty
        paivitaTallenna.disableProperty().bind(
                Bindings.isEmpty(paivitaTfNimi.textProperty())
                        .or(Bindings.isEmpty(paivitaTfKiinteistotunnus.textProperty()))
                        .or(Bindings.isEmpty(paivitaTfLaskutusosoite.textProperty()))
                        .or(Bindings.isEmpty(paivitaTfEmail.textProperty()))
                        .or(Bindings.isEmpty(paivitaTfPuhnum.textProperty()))
                        .or(Bindings.isEmpty(paivitaTfMatka.textProperty()))
                        .or(Bindings.isEmpty(paivitaTfPainoluku.textProperty())));

        // tallenna painikkeen tapahtuman käsittelijä
        paivitaTallenna.setOnAction(e -> paivitaTiedot());

        // poistu painikkeen tapahtuman käsittelijä
        // sulkee ikkunan/nayton
        paivitaPoistu.setOnAction(e -> paivitaStage.close());

        Scene lisaaKehys = new Scene(paivitaNayttoPaneeli, 400, 400);
        paivitaStage.setScene(lisaaKehys);
        paivitaStage.setTitle("päivitä yksityistieosakkaan tiedot");
        paivitaStage.initModality(Modality.APPLICATION_MODAL);
        paivitaStage.show();
    }

    /**
     * tarkistaa onko arvo double vai ei
     * @param kentta
     * @return true tai false
     */
    private boolean onDouble(TextField kentta){
        try{
            Double.parseDouble(kentta.getText());
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * tarkistaa onko arvo integer vai ei
     * @param kentta
     * @return true or false
     */
    private boolean onInteger(TextField kentta){
        try{
            Integer.parseInt(kentta.getText());
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * asettaa comboboxista valitun osakkaan tiedot valmiiksi textfield kenttiin
     */
    private void asetaTiedotKenttiin() {
        valittuIndeksi = paivitaCbOsakkaat.getSelectionModel().getSelectedIndex();
        paivitaTfNimi.setText(tiedosto.getOsakkaat().get(valittuIndeksi).getNimi());
        paivitaTfKiinteistotunnus.setText(tiedosto.getOsakkaat().get(valittuIndeksi).getKiinteistotunnus());
        paivitaTfLaskutusosoite.setText(tiedosto.getOsakkaat().get(valittuIndeksi).getLaskutusosoite());
        paivitaTfEmail.setText(tiedosto.getOsakkaat().get(valittuIndeksi).getEmail());
        paivitaTfPuhnum.setText(tiedosto.getOsakkaat().get(valittuIndeksi).getPuhnum());
        paivitaTfMatka.setText(String.valueOf(tiedosto.getOsakkaat().get(valittuIndeksi).getMatka()));
        paivitaTfPainoluku.setText(String.valueOf(tiedosto.getOsakkaat().get(valittuIndeksi).getPainoluku()));
        paivitaCbLiikennelaji.setValue(tiedosto.getOsakkaat().get(valittuIndeksi).getLiikennelaji());

    }

    /**
     * päivittää valitun osakkaan tiedot
     * tarkastaa onko matka kentän arvo double ja onko painoluku kentän arvo integer
     * jos ei ole asettaa kyseisiin textfieldiin virhetekstin ja ei anna päivityksen mennä läpi
     * ennen kuin arvot ovat oikeat
     */
    private void paivitaTiedot(){
        String virheTeksti = "Anna arvo lukuna";
        String virheTeksti2 = "Anna arvo kokonaislukuna";
        if (onDouble(paivitaTfMatka) && onInteger(paivitaTfPainoluku)) {
            tiedosto.getOsakkaat().get(valittuIndeksi).setNimi(paivitaTfNimi.getText());
            tiedosto.getOsakkaat().get(valittuIndeksi).setKiinteistotunnus(paivitaTfKiinteistotunnus.getText());
            tiedosto.getOsakkaat().get(valittuIndeksi).setLaskutusosoite(paivitaTfLaskutusosoite.getText());
            tiedosto.getOsakkaat().get(valittuIndeksi).setEmail(paivitaTfEmail.getText());
            tiedosto.getOsakkaat().get(valittuIndeksi).setPuhnum(paivitaTfPuhnum.getText());
            tiedosto.getOsakkaat().get(valittuIndeksi).setMatka(Double.parseDouble(paivitaTfMatka.getText()));
            tiedosto.getOsakkaat().get(valittuIndeksi).setPainoluku(Integer.parseInt(paivitaTfPainoluku.getText()));
            tiedosto.getOsakkaat().get(valittuIndeksi).setLiikennelaji(paivitaCbLiikennelaji.getValue());
            tiedosto.getOsakkaat().get(valittuIndeksi).laskeTieyksikkot();
            tiedosto.getOsakkaat().get(valittuIndeksi).laskeTieMaksu();
            OsakasKayttoliittyma.osakasTiedot.setText(tiedosto.getOsakkaat().get(valittuIndeksi).toString());
        }  else {
            if(!onDouble(paivitaTfMatka)) {
                paivitaTfMatka.setText(virheTeksti);
                paivitaTfMatka.setOnMouseClicked(event -> {
                    paivitaTfMatka.setText("");
                });
            }
            if(!onInteger(paivitaTfPainoluku)) {
                paivitaTfPainoluku.setText(virheTeksti2);
                paivitaTfPainoluku.setOnMouseClicked(event -> {
                    paivitaTfPainoluku.setText("");
                });
            }
        }
    }
}
