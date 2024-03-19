package harjoitustyo.grafiikka.oh2_harjoitustyo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * OsakasKayttoliittyma.java
 * Tämä luokka on yksityistie osakkaan tietoja käsittelevä käyttöliittymä,
 * jolla voidaan lisätä uusi osakas, katsella osakkaiden tietoja sekä muokata osakkaan tietoja,
 * @author Antti Komulainen
 * @version 1.00 17/03/2024
 */

public class OsakasKayttoliittyma extends Application {

    /** painike osakkaan lisäämistä varten */
    private final Button lisaa = new Button("Lisää osakas");
    /** painike osakkaan tietojen päivittämistä varten */
    private final Button paivita = new Button("Päivitä osakkaan tiedot");
    /** painike ohjelman lopettamista varten */
    private final Button lopeta = new Button("Lopeta");
    /** ListView jolla valitaan osakas kenen tietoja halutaan tarkastella */
    static ListView<String> osakasValinta;
    /** Teksti alue, jossa näytetään halutun osakkaan tiedot */
    static TextArea osakasTiedot;
    /** taulu, jossa on mahdolliset valittavat liikennelajit */
    private final String [] liikennelajit = {"Vakituinen asunto", "Vapaa-ajan asunto", "Kesämökki", "Lomamökki"};
    /** luokka tiedosto, ohjelman tiedoston käsittelyä varte */
    static Tiedosto tiedosto = new Tiedosto();
    /**
     * palauttaa taulun, jossa on liikennelajit
     * @return liikennelajit
     */
    public String[] getLiikennelajit() {
        return liikennelajit;
    }

    /**
     * Ohjelmaikkunan käynnistys ja toiminnalisuuden määrittely
     * sisältää paneelin, solmut, kuuntelijat ja tapahtuman käsittelijät
     * @param paaIkkuna
     */
    public void start(Stage paaIkkuna){
        // luetaan tiedosto
        tiedosto.lueTiedosto();
        // paneeli
        BorderPane paneeli = new BorderPane();
        // MIETI POISTETAANKO NÄMÄ
        Menu menu = new Menu("Tiedosto");
        MenuBar mb = new MenuBar();
        MenuItem mtLisaa = new MenuItem("Lisää");
        menu.getItems().add(mtLisaa);
        mb.getMenus().add(menu);
        paneeli.setTop(mb);

        // teksti kenttä osakkaan tietojen näyttämistä varten
        osakasTiedot = new TextArea();
        osakasTiedot.setEditable(false);
        paneeli.setRight(osakasTiedot);

        // ListView osakkaan valitsemista varten
        osakasValinta =
                new ListView<>(FXCollections.<String>observableArrayList(tiedosto.getOsakasNimet()));
        // ListViewin kuuntelija, valinnan perusteella hakee Tiedosto luokassa olevasta osakkaat listasta
        // valitun osakkaan ja asettaa sen tiedot tekstikenttään
        osakasValinta.getSelectionModel().selectedIndexProperty().addListener(ov -> {
            for(int i: osakasValinta.getSelectionModel().getSelectedIndices()){
                osakasTiedot.setText(tiedosto.getOsakkaat().get(i).toString());
            }
        });
        // Asetetaan listViewin leveys
        osakasValinta.setPrefWidth(100);
        paneeli.setLeft(osakasValinta);

        // Hbox painikkeita lisaa, paivita ja lopeta varten
        HBox hBoxButtonit = new HBox(5);
        hBoxButtonit.setPadding(new Insets(10));
        hBoxButtonit.getChildren().addAll(lisaa, paivita, lopeta);
        hBoxButtonit.setAlignment(Pos.BOTTOM_CENTER);
        paneeli.setBottom(hBoxButtonit);

        // lisaa painikkeen taphtuman käsittelijä
        // kun painiketta painetaan, tulee näkyviin uusi näyttö, jolla voidaan lisätä uusi osakas
        // luo uuden LiisaNaytto luokan olion ja käyttää luokan lisaaNaytto metodia
        this.lisaa.setOnAction(e -> {
            LisaaNaytto lisaa = new LisaaNaytto();
            lisaa.lisaaNaytto();
        });
        // paivita painikkeen taphtuman käsittelijä
        // kun painiketta painetaan, tulee näkyviin uusi näyttö, jolla voidaan päivittää osakkaan tietoja
        // luo uuden PaivitaNaytto luokan olion ja käyttää luokan paivitaNaytto metodia
        this.paivita.setOnAction(e -> {
            PaivitaNaytto paivita = new PaivitaNaytto();
            paivita.paivitaNaytto();
        });
        // lopeta painikkeen taphtuman käsittelijä
        // kun painiketta painetaan, kirjoitetaan tiedot tiedostoon ja lopetetaan ohjelman suoritus
        // tiedosto olio käyttää Tiedosto luokassa olevaa metodia kirjoitaTiedostoon.
        this.lopeta.setOnAction(e -> {
            tiedosto.kirjoitaTiedostoon();
            Platform.exit();
        });

        Scene kehys = new Scene(paneeli, 500, 500);
        paaIkkuna.setScene(kehys);
        paaIkkuna.setTitle("Yksityistie osakkaat");
        paaIkkuna.show();

    }

    /**
     * Pääohelma
     */
    public static void main(String[] args){
        Application.launch(args);
    }
}
