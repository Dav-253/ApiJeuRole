package apijeurole.heros.api;

import apijeurole.heros.domain.Heros;
import apijeurole.heros.domain.caracteristique.TypeCaracteristique;

import java.util.LinkedHashMap;
import java.util.Map;

public record HerosResponse(
        String id,
        String proprietaireId,
        String nom,
        String espece,
        String classe,
        int niveau,
        int experience,
        int pvActuel,
        int pvMax,
        int pmActuel,
        int pmMax,
        Map<String, Integer> caracteristiques) {

    public static HerosResponse de(Heros heros) {
        Map<String, Integer> valeurs = new LinkedHashMap<>();
        for (TypeCaracteristique type : TypeCaracteristique.values()) {
            valeurs.put(type.name().toLowerCase(), heros.getCaracteristiques(type));
        }
        return new HerosResponse(
                heros.getId().toString(),
                heros.getProprietaireId().toString(),
                heros.getNom(),
                heros.getEspece().getNom(),
                heros.getClasseHeros().name(),
                heros.getNiveauActuel(),
                heros.getExperience(),
                heros.getPvActuel(),
                heros.getPvMax(),
                heros.getPmActuel(),
                heros.getPmMax(),
                valeurs);
    }
}
