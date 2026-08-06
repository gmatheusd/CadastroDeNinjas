package br.com.monkeyscript.CadastroDeNinjas.Missoes;

import br.com.monkeyscript.CadastroDeNinjas.Ninjas.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;
    private final NinjaRepository ninjaRepository;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper, NinjaRepository ninjaRepository) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
        this.ninjaRepository = ninjaRepository;
    }

    // Listar todas as missoes
    public List<MissoesDTO> listarMissoes() {
        List<MissoesModel> missoes = missoesRepository.findAllByOrderByIdAsc();
        return missoes.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    // Listar as missoes por Id
    public MissoesDTO listarMissoesPorId(Long id) {
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id);
        return missaoPorId.map(missoesMapper::map).orElse(null);
    }

    // Criar uma nova missao
    public MissoesDTO criarMissao(MissoesDTO missoesDTO) {
        MissoesModel missao = missoesMapper.map(missoesDTO);
        missao = missoesRepository.save(missao);
        return missoesMapper.map(missao);
    }

    // Deletar uma missao
    public void deletarMissaoPorId(Long id) {
        MissoesModel missao = missoesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missão não encontrada"));

        if (missao.getNinjas() != null && !missao.getNinjas().isEmpty()) {
            missao.getNinjas().forEach(ninja -> ninja.setMissao(null));
            ninjaRepository.saveAll(missao.getNinjas());
        }

        missoesRepository.deleteById(id);
    }

    // Alterar missoes
    public MissoesDTO alterarMissaoPorId(Long id, MissoesDTO missoesDTO) {
        Optional<MissoesModel> missoaoExistente = missoesRepository.findById(id);
        if (missoaoExistente.isPresent()) {
            MissoesModel missaoAtualizada = missoesMapper.map(missoesDTO);
            missaoAtualizada.setId(id);
            MissoesModel missaoSalva = missoesRepository.save(missaoAtualizada);
            return missoesMapper.map(missaoSalva);
        }
        return null;
    }
}
