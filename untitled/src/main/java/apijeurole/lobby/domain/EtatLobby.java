package apijeurole.lobby.domain;

/** OPEN jusqu'au lancement de la partie, STARTED ensuite, CLOSED quand le dernier joueur quitte. */
public enum EtatLobby {
    OPEN,
    STARTED,
    CLOSED
}