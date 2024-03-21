package harjoitustyo.grafiikka.oh2_harjoitustyo;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Osakas.java
 * Tämä luokka kuvaa yksityistie osakkaan tiedot
 * @author Antti Komulainen
 * @version 1.00 12/03/2024
 */

public class Osakas implements Serializable {
    /** Kenttä osakkaan nimelle */
    private String nimi;
    /** Kenttä kiinteistötunnus, osakkaan yksityistiellä sijaitsevalle kiinteistölle*/
    private String kiinteistotunnus;
    /** Kenttä osakkaan laskutusosoiteelle*/
    private String laskutusosoite;
    /** Kenttä osakkaan sähköpostiosoiteelle*/
    private String email;
    /** kenttä sakkaan puhelinnumerolle*/
    private String puhnum;
    /** kenttä osakkaan matkalle yksityistien alusta hänen kiinteistön
     * tien risteykseen kilometreinä*/
    private double matka;
    /** kenttä osakkaan yksityistien käytöä kuvaavalle painoluvulle, yksikkö tonnia*/
    private int painoluku;
    /** kenttä osakkaan liikennelajille, kuvaa miten osakkas käyttää tietä esim. asunto tai loma-asunto
     * termi perustuu MML yksityistien yksiköinti 2023 ohjeeseen*/
    private String liikennelaji;

    /** Kenttä osakkaan tieyksikkojen määrää varten*/
    private double tieyksikkot;
    /** kenttä osakkaan yksityistie maksun määrää varten */
    private double tieMaksu;
    /** vakio yhden tieyksikön hinnalle*/
    final static double tieyksikkoHinta = 0.17;


    /**
     * parametriton alustaja
     * asetaa oletuksena matkalle 0 km
     * asettaa oletuksena painoluvuksi vakituisen asunnon painoluku 1700 t
     * asettaa oletuksena, että osakkaan liikennelaji on vakituinen asunto
     */
    public Osakas(){
        this.matka = 0;
        this.painoluku = 1700;
        this.liikennelaji = "Vakituinen asunto";
    }

    /**
     * parametrillinen alustaja
     */
    public Osakas(String nimi, String kiinteistotunnus, String laskutusosoite,
                  String email, String puhnum, double matka,
                  int painoluku, String liikennelaji) {
        this.nimi = nimi;
        this.kiinteistotunnus = kiinteistotunnus;
        this.laskutusosoite = laskutusosoite;
        this.email = email;
        this.puhnum = puhnum;
        this.matka = matka;
        this.painoluku = painoluku;
        this.liikennelaji = liikennelaji;
        this.laskeTieyksikkot();
        this.laskeTieMaksu();
    }

    /**
     * palauttaa osakkaan nimen
     * @return nimi
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * asettaa osakkaan nimen
     * @param nimi
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    /**
     * palauttaa osakkaan kiinteistötunnuksen
     * @return osoite
     */
    public String getKiinteistotunnus() {
        return kiinteistotunnus;
    }

    /**
     * asettaa osakkaan kiinteistöntunnuksen
     * @param kiinteistotunnus
     */
    public void setKiinteistotunnus(String kiinteistotunnus) {
        this.kiinteistotunnus = kiinteistotunnus;
    }

    /**
     * palauttaa osakkaan laskutusosoitteen
     * @return laskutusosoite
     */
    public String getLaskutusosoite() {
        return laskutusosoite;
    }

    /**
     * asettaa osakkaan laskutusosoitteen
     * @param laskutusosoite
     */
    public void setLaskutusosoite(String laskutusosoite) {
        this.laskutusosoite = laskutusosoite;
    }

    /**
     * palauttaa osakkaan email osoitteen
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * asettaa osakkaan email osoitteen
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * palauttaa osakkaan puhelinnumeron
     * @return puhnum
     */
    public String getPuhnum() {
        return puhnum;
    }

    /**
     * asettaa osakkaan puhelinnumeron
     * @param puhnum
     */
    public void setPuhnum(String puhnum) {
        this.puhnum = puhnum;
    }

    /**
     * palauttaa osakkaan matkan yksityistienalusta osakakan kiinteistön risteykseen
     * @return matka
     */
    public double getMatka() {
        return matka;
    }

    /**
     * asettaa osakkaan matkan yksityistienalusta osakakan kiinteistön risteykseen
     * @param matka
     */
    public void setMatka(double matka) {
        this.matka = matka;
    }

    /**
     * palauttaa osakkaan tienkäyttöä kuvaavan painoluvun
     * @return painoluku
     */
    public int getPainoluku() {
        return painoluku;
    }

    /**
     * asettaa osakkaan tienkäyttöä kuvaavan painoluvun
     * @param painoluku
     */
    public void setPainoluku(int painoluku){
        this.painoluku = painoluku;
    }

    /**
     * palauttaa osakkaan liikennelajin
     * @return liikennelaji
     */
    public String getLiikennelaji() {
        return liikennelaji;
    }

    /**
     * asettaa osakkaan liikennelajin
     * @param liikennelaji
     */
    public void setLiikennelaji(String liikennelaji) {
        this.liikennelaji = liikennelaji;
    }

    /**
     * palauttaa osakkaan tiemaksun määrän
     * @return tieMaksu
     */
    public double getTieMaksu() {
        return tieMaksu;
    }

    /**
     * palauttaa osakkaan tieyksiköiden määrän
     * @return tieyksikko
     */
    public double getTieyksikkot() {
        return tieyksikkot;
    }


    /**
     * laskee osakkaan tieyksiköiden määärän ja asettaa sen tieyksikko kentän arvoksi
     * kaava: painoluku * matka
     */
    public void laskeTieyksikkot(){
        if (this.matka > 0) {
            this.tieyksikkot = (painoluku * matka);
        }
    }
    /**
     * laskee osakkaan tiemaksun summan ja asettaa sen tieMaksu kentän arvoksi
     * kaava: tieyksiköiden määrä * tieyksiköhinta
     */
    public void laskeTieMaksu(){
        this.tieMaksu = (tieyksikkot * tieyksikkoHinta);
    }

    /**
     * tulostaa osakkaan tiedot
     * @return osakasTiedot
     */
    @Override
    public String toString() {
        DecimalFormat f = new DecimalFormat("0.00");
        String osakasTiedot = "Nimi: " + getNimi() +
                "\nosoite: " + getKiinteistotunnus() +
                "\nlaskutusosoite: " + getLaskutusosoite() +
                "\nsähköposti: " + getEmail() +
                "\npuhelinnumero: " + getPuhnum() +
                "\nliikennelaji: " + getLiikennelaji() +
                "\ntietä käytettävä matka: " + getMatka() + " km" +
                "\npainoluku: " + getPainoluku() + " t" +
                "\ntieyksiköt: " + getTieyksikkot() + " kpl" +
                "\ntieyksikön hinta " + tieyksikkoHinta + " €/kpl" +
                "\ntiemaksu: " + f.format(getTieMaksu()) + " €";
        return osakasTiedot;
    }

    public static void main (String[] args){
        Osakas osakas = new Osakas("Testi", "123", "Testi", "Testi", "testi", 0.75, 1700,
                "Vapaa-ajan asunto");

        osakas.setPainoluku(750);
        osakas.laskeTieyksikkot();
        osakas.laskeTieMaksu();
        System.out.println(osakas);
    }
}

