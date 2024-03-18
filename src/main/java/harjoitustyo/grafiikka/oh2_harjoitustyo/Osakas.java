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
    /** Kenttä nimi, osakkaan nimelle*/
    private String nimi;
    /** Kenttä kiinteistötunnus, osakkaan yksityistiellä sijaitsevalle kiinteistölle*/
    private String kiinteistotunnus;
    /** Kenttä laskutusosoite, osakkaan laskutusosoite*/
    private String laskutusosoite;
    /** Kenttä email, osakkaan sähköpostiosoiteelle*/
    private String email;
    /** kenttä puhnum, osakkaan puhelinnumerolle*/
    private String puhnum;
    /** kenttä matka, osakkaan matka yksityistien alusta hänen kiinteistön
     * tien risteykseen kilometreinä*/
    private double matka;
    /** kenttä painoluku, osakkaan yksityistien käytöä kuvaava painoluku, yksikkö tonni*/
    private int painoluku;
    /** kenttä liikennelaji, kuvaa miten osakkas käyttää tietä esim. asunto tai loma-asunto
     * termi perustuu MML yksityistien yksiköinti 2023 ohjeeseen*/
    private String liikennelaji;

    /** Kenttä osakkaan tieyksikkojen määrää varten*/
    private double tieyksikkot;
    /** kenttä tieMaksu, osakkaan yksityistie maksun määrää varten */
    private double tieMaksu;
    /** vakio yhden tieyksikön hinnalle*/
    final static double tieyksikkoHinta = 0.17;


    /**
     * Luokan Osakan parametriton alustaja
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
     * Luokan Osakas parametrillinen alustaja
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

    /** getNimi metodi
     * palauttaa osakkaan nimen
     * @return nimi
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * setNimi metodi
     * asettaa osakkaan nimen
     * @param nimi
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    /** getKiinteistotunnus metodi
     * palauttaa osakkaan kiinteistötunnuksen
     * @return osoite
     */
    public String getKiinteistotunnus() {
        return kiinteistotunnus;
    }

    /**
     * setKiinteistotunnus metodi
     * asettaa osakkaan kiinteistöntunnuksen
     * @param kiinteistotunnus
     */
    public void setKiinteistotunnus(String kiinteistotunnus) {
        this.kiinteistotunnus = kiinteistotunnus;
    }

    /** getLaskutusosoite metodi
     * palauttaa osakkaan laskutusosoitteen
     * @return laskutusosoite
     */
    public String getLaskutusosoite() {
        return laskutusosoite;
    }

    /**
     * set laskutusosoite metodi
     * asettaa osakkaan laskutusosoitteen
     * @param laskutusosoite
     */
    public void setLaskutusosoite(String laskutusosoite) {
        this.laskutusosoite = laskutusosoite;
    }

    /** getEmail metodi
     * palauttaa osakkaan email osoitteen
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /** setEmail metodi
     * asettaa osakkaan email osoitteen
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** getPuhnum metodi
     * palauttaa osakkaan puhelinnumeron
     * @return puhnum
     */
    public String getPuhnum() {
        return puhnum;
    }

    /** setPuhnum metodi
     * asettaa osakkaan puhelinnumeron
     * @param puhnum
     */
    public void setPuhnum(String puhnum) {
        this.puhnum = puhnum;
    }

    /** getMatka metodi
     * palauttaa osakkaan matkan yksityistienalusta osakakan kiinteistön risteykseen
     * @return matka
     */
    public double getMatka() {
        return matka;
    }

    /** setMatka metodi
     * asettaa osakkaan matkan yksityistienalusta osakakan kiinteistön risteykseen
     * @param matka
     */
    public void setMatka(double matka) {
        this.matka = matka;
    }

    /** getPainoLuku metodi
     * palauttaa osakkaan tienkäyttöä kuvaavan painoluvun
     * @return painoluku
     */
    public int getPainoluku() {
        return painoluku;
    }

    /** setPainoLuku metodi
     * asettaa osakkaan tienkäyttöä kuvaavan painoluvun
     * @param painoluku
     */
    public void setPainoluku(int painoluku){
        this.painoluku = painoluku;
    }

    /** getLiikennelaji metodi
     * palauttaa osakkaan liikennelajin
     * @return liikennelaji
     */
    public String getLiikennelaji() {
        return liikennelaji;
    }

    /** setLiikennelaji metodi
     * asettaa osakkaan liikennelajin
     * @param liikennelaji
     */
    public void setLiikennelaji(String liikennelaji) {
        this.liikennelaji = liikennelaji;
    }

    /** getTieMaksu metodi
     * palauttaa osakkaan tiemaksun määrän
     * @return tieMaksu
     */
    public double getTieMaksu() {
        return tieMaksu;
    }

    /** getTieyksikko metodi
     * palauttaa osakkaan tieyksiköiden määrän
     * @return tieyksikko
     */
    public double getTieyksikkot() {
        return tieyksikkot;
    }


    /** laskeTieyksikko metodi
     * laskee osakkaan tieyksiköiden määärän ja asettaa sen tieyksikko kentän arvoksi
     * kaava: painoluku * matka
     * @param
     */
    public void laskeTieyksikkot(){
        if (this.matka > 0) {
            this.tieyksikkot = (painoluku * matka);
        }
    }
    /** laskeTieMaksu metodi
     * laskee osakkaan tiemaksun summan ja asettaa sen tieMaksu kentän arvoksi
     * kaava: tieyksiköiden määrä * tieyksiköhinta
     * @param
     */
    public void laskeTieMaksu(){
        this.tieMaksu = (tieyksikkot * tieyksikkoHinta);
    }

    /**
     * toString metodi
     * tulostaa osakkaan tiedot
     * @return osakasTiedot
     */
    @Override
    public String toString() {
        DecimalFormat f = new DecimalFormat("##.00");
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

