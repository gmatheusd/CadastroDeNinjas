package br.com.monkeyscript.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Essa é minha mensagem na rota de missoes";
    }


    // GET -- Mandar uma requisisao para mostrar as missoes
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        if (missoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(missoes);
        }
    }

    // GET -- Mandar uma requisisao para mostrar as missoes por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissoesPorId(@PathVariable Long id) {
        MissoesDTO missao = missoesService.listarMissoesPorId(id);
        if (missao!= null) {
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missao com o (id): " + id + ", não existe em nossos registros");
        }
    }

    // POST -- Manda uma requisisao para criar uma missao
    @PostMapping("/criar")
    public ResponseEntity<MissoesDTO> criarMissao(@RequestBody MissoesDTO missao) {
        MissoesDTO novaMissao = missoesService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(novaMissao);
    }

    // PUT -- Manda uma requisisao para alterar as missoes
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissaoPorId(@PathVariable Long id, @RequestBody MissoesDTO missaoAtualizada) {
        MissoesDTO missao = missoesService.alterarMissaoPorId(id, missaoAtualizada);
        if (missao != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missao com o (ID): " + id + ", não existe nos nossos registros!");
        }
    }

    // Delete -- Mandar uma requisisao para deletar as missoes
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissaoPorId(@PathVariable Long id) {
        MissoesDTO missaoDeletada = missoesService.listarMissoesPorId(id);
        if (missaoDeletada != null) {
            missoesService.deletarMissaoPorId(id);
            return ResponseEntity.ok("Missao: " + missaoDeletada.getNome() + ", (id): " + id  + ", Deletada com Sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missao com o (ID): " + id + ", não encontrada em nossos registros!");
        }
    }
}

