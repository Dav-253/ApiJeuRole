package apijeurole.joueur.api;

import apijeurole.joueur.domain.Joueur;

public record JoueurResponse(String id, String pseudonym, String email) {

    public static JoueurResponse de(Joueur joueur) {
        return new JoueurResponse(joueur.getId().toString(), joueur.getPseudonyme(), joueur.getCourriel());
    }
}
