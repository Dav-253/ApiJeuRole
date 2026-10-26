package partie.domain;

import partie.domain.exception.*;

import java.util.*;


public class Partie {

    public static final int TAILLE_GROUPE_MAX = 4;

    private final UUID id;
    private final UUID lobbyId;
    private final List<MembreGroupe> groupe;
    private EtatPartie etat;
    private UUID combatActifId;
    private String rencontreEnCours;
    private final Set<String> rencontresRemportees;

    public Partie(UUID id, UUID lobbyId, List<MembreGroupe> groupe) {
        this(id, lobbyId, groupe, EtatPartie.EXPLORATION, null, null, Set.of());
    }

    public Partie(UUID id, UUID lobbyId, List<MembreGroupe> groupe, EtatPartie etat,
                  UUID combatActifId, String rencontreEnCours, Collection<String> rencontresRemportees) {
        this.id = Objects.requireNonNull(id, "L'identifiant de la partie est obligatoire.");
        this.lobbyId = Objects.requireNonNull(lobbyId, "Le lobby est obligatoire.");
        this.groupe = List.copyOf(verifierGroupe(groupe));
        this.etat = Objects.requireNonNull(etat, "L'état est obligatoire.");
        this.combatActifId = combatActifId;
        this.rencontreEnCours = rencontreEnCours;
        this.rencontresRemportees = new LinkedHashSet<>(rencontresRemportees);

        boolean enCombat = etat == EtatPartie.EN_COMBAT;
        if (enCombat != (combatActifId != null) || enCombat != (rencontreEnCours != null)) {
            throw new IllegalArgumentException(
                    "Un combat actif et sa rencontre existent si et seulement si la partie est EN_COMBAT.");
        }
    }

    private static List<MembreGroupe> verifierGroupe(List<MembreGroupe> groupe) {
        if (groupe == null || groupe.isEmpty() || groupe.size() > TAILLE_GROUPE_MAX) {
            throw new GroupeInvalideException("Taille reçue : " + (groupe == null ? 0 : groupe.size()) + ".");
        }
        Set<UUID> joueurs = new HashSet<>();
        Set<UUID> heros = new HashSet<>();
        for (MembreGroupe membre : groupe) {
            if (!joueurs.add(membre.joueurId()) || !heros.add(membre.herosId())) {
                throw new GroupeInvalideException("Un joueur ou un héros apparaît deux fois.");
            }
        }
        return new ArrayList<>(groupe);
    }

    public UUID getId() { return id; }

    public UUID getLobbyId() { return lobbyId; }

    public List<MembreGroupe> getGroupe() { return groupe; }

    public EtatPartie getEtat() { return etat; }

    public UUID getCombatActifId() { return combatActifId; }

    public Set<String> getRencontresRemportees() { return Collections.unmodifiableSet(rencontresRemportees); }

    public boolean estTerminee() { return etat == EtatPartie.TERMINEE; }


    public void commencerCombat(UUID combatId, String rencontre) {
        Objects.requireNonNull(combatId, "Le combat est obligatoire.");
        verifierQuUnCombatPeutCommencer(rencontre);

        this.etat = EtatPartie.EN_COMBAT;
        this.combatActifId = combatId;
        this.rencontreEnCours = rencontre;
    }

    public void verifierQuUnCombatPeutCommencer(String rencontre) {
        Objects.requireNonNull(rencontre, "La rencontre est obligatoire.");

        if (etat == EtatPartie.TERMINEE) throw new PartieTermineeException();
        if (etat == EtatPartie.EN_COMBAT) throw new CombatDejaActifException();
        if (rencontresRemportees.contains(rencontre)) throw new RencontreDejaRemporteeException(rencontre);
    }

    public void terminerCombat(boolean victoireDesHeros) {
        if (etat != EtatPartie.EN_COMBAT) throw new AucunCombatActifException();

        if (victoireDesHeros) {
            rencontresRemportees.add(rencontreEnCours);
            this.etat = EtatPartie.EXPLORATION;
        } else {
            this.etat = EtatPartie.TERMINEE;
        }
        this.combatActifId = null;
        this.rencontreEnCours = null;
    }

    public String getRencontreEnCours() { return rencontreEnCours; }

}
