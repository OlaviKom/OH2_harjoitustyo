package harjoitustyo.grafiikka.oh2_harjoitustyo;

import java.io.*;
import java.util.ArrayList;

public class Tiedosto{
    private File tiedostoNimi = new File("osakkaat.dat");

    private ArrayList<Osakas> osakkaat = new ArrayList<>();

    private ArrayList<String> osakasNimet = new ArrayList<>();



    public void lueTiedosto() {
        boolean tiedostoLoppu = false;
        if(tiedostoNimi.exists()){
            try{
                FileInputStream tiedosto = new FileInputStream(tiedostoNimi);
                ObjectInputStream osakasOlioTiedosto = new ObjectInputStream(tiedosto);
                try {
                    while (!tiedostoLoppu) {
                        //for(int i = 0; i < osakkaat.size(); i++) {
                        this.osakkaat.add((Osakas) osakasOlioTiedosto.readObject());
                    }

                }
                catch (EOFException e){
                    tiedostoLoppu = true;
                    tiedosto.close();
                }

            }
            catch (IOException | ClassNotFoundException e){
                e.getMessage();
            }
        }
        //else {
            //osakkaat.add(new Osakas("Testi", "123", "Testi", "testi", "123",
                    //10, 1200, "Testi"));
       // }
    }

    public ArrayList<Osakas> getOsakkaat() {
        return osakkaat;
    }

    public ArrayList<String> getOsakasNimet() {
       if(!osakkaat.isEmpty()) {
            for (Osakas osakas : osakkaat) {
                osakasNimet.add(osakas.getNimi());
            }
        }
       return osakasNimet;
    }

    public void kirjoitaTiedostoon() {
        try{
            FileOutputStream kirjoitettavaTiedosto = new FileOutputStream(tiedostoNimi);
            ObjectOutputStream olioTiedosto = new ObjectOutputStream(kirjoitettavaTiedosto);
            //if (!osakkaat.isEmpty()){
                for(int i = 0; i < osakkaat.size(); i++){
                    olioTiedosto.writeObject(osakkaat.get(i));
                //}
            }
            kirjoitettavaTiedosto.close();
        }
        catch (IOException e){
            e.getMessage();
        }
    }
}
