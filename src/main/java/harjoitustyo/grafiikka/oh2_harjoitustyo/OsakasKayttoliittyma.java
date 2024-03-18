package harjoitustyo.grafiikka.oh2_harjoitustyo;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.stage.Stage;

import java.util.ArrayList;

public class OsakasKayttoliittyma extends Application {

    private final Button lisaa = new Button("Lisää osakas");
    private final Button paivita = new Button("Päivitä osakkaan tiedot");

    private final Button lopeta = new Button("Lopeta");

    static ListView<String> osakasValinta;
    static TextArea osakasTiedot;

    private final String [] liikennelajit = {"Vakituinen asunto", "Vapaa-ajan asunto", "Kesämökki", "Lomamökki"};

    static Tiedosto tiedosto = new Tiedosto();

    public String[] getLiikennelajit() {
        return liikennelajit;
    }

    public ListView<String> getOsakasValinta() {
        return osakasValinta;
    }

    public void start(Stage paaIkkuna){
        tiedosto.lueTiedosto();
        BorderPane paneeli = new BorderPane();
        Menu menu = new Menu("Tiedosto");
        MenuBar mb = new MenuBar();
        MenuItem mtLisaa = new MenuItem("Lisää");
        menu.getItems().add(mtLisaa);
        mb.getMenus().add(menu);
        paneeli.setTop(mb);

        osakasTiedot = new TextArea();
        paneeli.setRight(osakasTiedot);

        osakasValinta =
                new ListView<>(FXCollections.<String>observableArrayList(tiedosto.getOsakasNimet()));
        osakasValinta.getSelectionModel().selectedIndexProperty().addListener(ov -> {
            for(int i: osakasValinta.getSelectionModel().getSelectedIndices()){
                osakasTiedot.setText(tiedosto.getOsakkaat().get(i).toString());
            }
        });
        osakasValinta.setPrefWidth(100);
        paneeli.setLeft(osakasValinta);

        HBox hBoxButtonit = new HBox(5);
        hBoxButtonit.setPadding(new Insets(10));
        hBoxButtonit.getChildren().addAll(lisaa, paivita, lopeta);
        hBoxButtonit.setAlignment(Pos.BOTTOM_CENTER);
        paneeli.setBottom(hBoxButtonit);

        this.lisaa.setOnAction(e -> {
            LisaaNaytto lisaa = new LisaaNaytto();
            lisaa.lisaaNaytto();
        });
        //this.paivita.setOnAction(e -> paivitaNaytto());
        this.paivita.setOnAction(e -> {
            PaivitaNaytto paivita = new PaivitaNaytto();
            paivita.paivitaNaytto();
        });
        this.lopeta.setOnAction(e -> {
            tiedosto.kirjoitaTiedostoon();
            Platform.exit();
        });


        Scene kehys = new Scene(paneeli, 500, 500);
        paaIkkuna.setScene(kehys);
        paaIkkuna.setTitle("Yksityistie osakkaat");
        paaIkkuna.show();

    }
    public static void main(String[] args){
        Application.launch(args);
    }
}
