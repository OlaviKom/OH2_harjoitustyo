package harjoitustyo.grafiikka.oh2_harjoitustyo;

import java.io.*;
import java.util.ArrayList;

/**
 * Tämä luokka on käsittelee ohjelman tiedostoa, josta luetaan ja johon tallenetaan yksityis osakkaiden tiedot
 * @author Antti Komulainen
 * @version 1.00 14/03/2024
 */

public class Tiedosto{
    /**
     * tiedosto luettavalle ja kirjoitettavalle tiedostolle
     */
    private File tiedostoNimi = new File("osakkaat.dat");
    /**
     * lista Osakas olioita varten
     */
    private ArrayList<Osakas> osakkaat = new ArrayList<Osakas>();
    /**
     * lista osakkaiden nimiä varten
     */
    private ArrayList<String> osakasNimet = new ArrayList<>();


    /**
     * lukee tiedoston ja asettaa tiedostossa olevat oliot listaan
     */
    public void lueTiedosto() {
        boolean tiedostoLoppu = false;
        if(tiedostoNimi.exists()){
            try{
                FileInputStream tiedosto = new FileInputStream(tiedostoNimi);
                ObjectInputStream osakasOlioTiedosto = new ObjectInputStream(tiedosto);
                    while (!tiedostoLoppu) {
                        try {
                        this.osakkaat.add((Osakas) osakasOlioTiedosto.readObject());
                        }
                        catch (EOFException e){
                            tiedostoLoppu = true;
                            tiedosto.close();
                        }
                    }
            }
            catch (IOException | ClassNotFoundException e){
                e.getMessage();
            }
        }
    }

    /**
     * palauttaa listan jossa Osakas oliot ovat
     * @return osakkaat
     */
    public ArrayList<Osakas> getOsakkaat() {
        return osakkaat;
    }

    /**
     * palauttaa listan jossa osakkaiden nimet ovat
     * @return osakasNimet
     */
    public ArrayList<String> getOsakasNimet() {
       if(!osakkaat.isEmpty()) {
            for (Osakas osakas : osakkaat) {
                osakasNimet.add(osakas.getNimi());
            }
        }
       return osakasNimet;
    }

    /**
     * kirjoittaa tiedostoon osakkaiden tiedot
     */
    public void kirjoitaTiedostoon() {
        try{
            FileOutputStream kirjoitettavaTiedosto = new FileOutputStream(tiedostoNimi);
            ObjectOutputStream olioTiedosto = new ObjectOutputStream(kirjoitettavaTiedosto);
            if (!osakkaat.isEmpty()){
                for(int i = 0; i < osakkaat.size(); i++){
                    olioTiedosto.writeObject(osakkaat.get(i));
                }
            }
            kirjoitettavaTiedosto.close();
        }
        catch (IOException e){
            e.getMessage();
        }
    }
}
