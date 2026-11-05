package apijeurole.partie.application;

import apijeurole.partie.domain.PartieRepository;

public class ConsulterPartieUseCase {

    private final PartieRepository partieRepository;
    private final PartieOutputMapper outputMapper;

    public ConsulterPartieUseCase(PartieRepository partieRepository, PartieOutputMapper outputMapper) {
        this.partieRepository = partieRepository;
        this.outputMapper = outputMapper;
    }

    public PartieOutput execute(ConsulterPartieQuery query) {
        return outputMapper.toOutput(partieRepository.fetchById(query.partieId()));
    }
}
