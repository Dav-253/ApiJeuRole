package apijeurole.lobby.application;

import apijeurole.joueur.domain.Joueur;
import apijeurole.joueur.infrastructure.InMemoryJoueurRepository;
import apijeurole.lobby.domain.HerosGateway;
import apijeurole.lobby.infrastructure.InMemoryLobbyRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/** Outils partagés par les tests du lobby : vrais dépôts en mémoire, faux gateway de héros. */
abstract class LobbyServiceTestBase {

    protected final InMemoryJoueurRepository joueurs = new InMemoryJoueurRepository();
    protected final Map<UUID, HerosGateway.HerosResume> herosConnus = new HashMap<>();
    protected final HerosGateway herosGateway = id -> Optional.ofNullable(herosConnus.get(id));
    protected LobbyService service;

    protected Joueur nouveauJoueur(String pseudonyme) {
        Joueur joueur = new Joueur(UUID.randomUUID(), pseudonyme, pseudonyme.toLowerCase() + "@mail.com", "hache");
        joueurs.save(joueur);
        return joueur;
    }

    protected UUID nouveauHeros(Joueur proprietaire, String nom) {
        UUID id = UUID.randomUUID();
        herosConnus.put(id, new HerosGateway.HerosResume(id, proprietaire.getId(), nom));
        return id;
    }
}