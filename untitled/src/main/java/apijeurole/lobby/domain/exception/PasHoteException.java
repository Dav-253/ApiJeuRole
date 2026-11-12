package apijeurole.lobby.domain.exception;

import apijeurole.commun.domain.CategorieErreur;

public class PasHoteException extends LobbyException {
    public PasHoteException() {
        super("NOT_HOST", CategorieErreur.INTERDIT, "Seul l'hôte du lobby peut lancer la partie.");
    }
}