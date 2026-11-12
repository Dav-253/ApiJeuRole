package apijeurole.lobby.api;

import apijeurole.lobby.application.LobbyResume;

public record LobbyResumeResponse(String id, String hostId, int playerCount, int maxPlayers) {

    public static LobbyResumeResponse de(LobbyResume resume) {
        return new LobbyResumeResponse(resume.id().toString(), resume.hostId().toString(),
                resume.nombreJoueurs(), resume.maximumJoueurs());
    }
}
