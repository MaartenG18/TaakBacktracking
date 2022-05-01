package be.domino;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Maarten Gielkens
 * Ik heb ongeveer 12 uur gewerkt aan deze taak.
 */
public class Algoritme {
    /**
     * Methode die de ketting maakt en recursief kan worden opgeroepen.
     * @param stenen De ArrayList van de stenen die meegegeven worden door de main
     * @param geplaatsteStenen De stenen waarin de ketting wordt gelegd
     * @param steenNummer De index die bijhoudt welke steen uit de originele lijst moeten geprobeerd toe te voegen
     * @param aantalBacktracks Het aantal stenen die terug verwijderd zijn met backtracken
     * @param volledigeBacktracks Het aantal keer dat het algoritme een nieuwe steen vooraan legt
     * @return Recursief maakKetting opnieuw oproepen
     */
    public Optional<ArrayList<Steen>> maakKetting(ArrayList<Steen> stenen, ArrayList<Steen> geplaatsteStenen, int steenNummer, int aantalBacktracks, int volledigeBacktracks) {
        Steen huidigeSteen;

        //controle die zorgt dat het algoritme stopt wanneer alle stenen op de eerste plaats hebben gelegen.
        if (volledigeBacktracks > stenen.size() + geplaatsteStenen.size()) {
            if(controleerSteen(geplaatsteStenen.get(geplaatsteStenen.size() - 1), geplaatsteStenen.get(0))) {
                return Optional.of(geplaatsteStenen);
            }
            else {
                return Optional.empty();
            }
        }

        //Controle die zorgt dat het algoritme stopt wanneer alle te plaatsen stenen zijn opgebruikt
        if (stenen.size() == 0) {
            if(controleerSteen(geplaatsteStenen.get(geplaatsteStenen.size() - 1), geplaatsteStenen.get(0))) {
                return Optional.of(geplaatsteStenen);
            }
            else {
                return Optional.empty();
            }
        }

        huidigeSteen = stenen.get(steenNummer);

        //Vult de lege lijst met geplaatste stenen aan met het eerste element van de te plaatsen stenen.
        if (geplaatsteStenen.isEmpty()) {
            stenen.remove(huidigeSteen);
            geplaatsteStenen.add(huidigeSteen);
            return maakKetting(stenen, geplaatsteStenen, steenNummer, 0, volledigeBacktracks + 1);
        }

        Steen vorigeSteen = geplaatsteStenen.get(geplaatsteStenen.size()-1);

        //Controleert als de volgende steen kan toegevoegd worden en doet dit ook indien mogelijk.
        if (controleerSteen(vorigeSteen, huidigeSteen)) {
            stenen.remove(steenNummer);
            geplaatsteStenen.add(huidigeSteen);
            steenNummer = 0;
            return maakKetting(stenen, geplaatsteStenen, steenNummer, aantalBacktracks, volledigeBacktracks);
        }
        //Als er niet meer kan worden bijgeplaatst dan begint het volgende stuk met backtracken
        else if (stenen.size() -1 - aantalBacktracks <= steenNummer){
            if(controleerSteen(geplaatsteStenen.get(geplaatsteStenen.size() - 1), geplaatsteStenen.get(0))) {
                return Optional.of(geplaatsteStenen);
            }

            controleerSteen(geplaatsteStenen.get(geplaatsteStenen.size() - 1), geplaatsteStenen.get(0));
            geplaatsteStenen.remove(vorigeSteen);
            stenen.add(vorigeSteen);
            aantalBacktracks += 1;
            return maakKetting(stenen, geplaatsteStenen, 0, aantalBacktracks, volledigeBacktracks);
        }
        else{
            return maakKetting(stenen, geplaatsteStenen, steenNummer + 1, aantalBacktracks, volledigeBacktracks);
        }
    }

    /**
     * Methode die controleert als 2 stenen langs elkaar kunnen liggen, indien een steen geflipt moet worden dan doet deze methode dat ook.
     * @param steen1 De eerste steen die gecontroleerd moet worden.
     * @param steen2 De tweede steen die gecontroleerd moet worden
     * @return Boolean om aan te geven als de stenen achter elkaar gelegd kunnen worden of niet.
     */
    public boolean controleerSteen(Steen steen1, Steen steen2) {
        if (steen1.getKleur() == steen2.getKleur()) {
            return false;
        }

        if(steen1.getOgen2() != steen2.getOgen1()) {
            if(steen1.getOgen2() == steen2.getOgen2()) {
                steen2.flip();
                return true;
            }
            else return false;
        }
        return true;
    }
}