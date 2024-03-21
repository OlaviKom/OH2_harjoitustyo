module harjoitustyo.grafiikka.oh2_harjoitustyo {
    requires javafx.controls;
    requires javafx.fxml;


    opens harjoitustyo.grafiikka.oh2_harjoitustyo to javafx.fxml;
    exports harjoitustyo.grafiikka.oh2_harjoitustyo;
}