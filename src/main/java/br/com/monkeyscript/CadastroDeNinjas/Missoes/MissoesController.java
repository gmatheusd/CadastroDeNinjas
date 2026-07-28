package br.com.monkeyscript.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Essa é minha mensagem na rota de missoes";
    }


    // GET -- Mandar uma requisisao para mostrar as missoes
    @GetMapping("/listar")
    public List<MissoesDTO> listarMissoes() {
        return missoesService.listarMissoes();
    }

    // GET -- Mandar uma requisisao para mostrar as missoes por ID
    @GetMapping("/listar/{id}")
    public MissoesDTO listarMissoesPorId(@PathVariable Long id) {
        return missoesService.listarMissoesPorId(id);
    }

    // POST -- Manda uma requisisao para criar uma missao
    @PostMapping("/criar")
    public MissoesDTO criarMissao(@RequestBody MissoesDTO missao) {
        return missoesService.criarMissao(missao);
    }

    // PUT -- Manda uma requisisao para alterar as missoes
    @PutMapping("/alterar/{id}")
    public MissoesDTO alterarMissaoPorId(@PathVariable Long id, @RequestBody MissoesDTO missaoAtualizada) {
        return missoesService.alterarMissaoPorId(id, missaoAtualizada);
    }

    // Delete -- Mandar uma requisisao para deletar as missoes
    @DeleteMapping("/deletar/{id}")
    public void deletarMissaoPorId(@PathVariable Long id) {
        missoesService.deletarMissaoPorId(id);
    }
}

