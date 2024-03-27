package harjoitustyo.grafiikka.oh2_harjoitustyo;

import javafx.scene.control.TextField;

public class Tarkistaja {

    public Tarkistaja(){
    }

    /**
     * tarkistaa onko arvo double vai ei
     * @param kentta TextField, joka halutaan tarkastaa
     * @return true tai false
     */
    public boolean onDouble(TextField kentta){
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
     * @param kentta TextField, joka halutaan tarkastaa
     * @return true or false
     */
    public boolean onInteger(TextField kentta){
        try{
            Integer.parseInt(kentta.getText());
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
}
