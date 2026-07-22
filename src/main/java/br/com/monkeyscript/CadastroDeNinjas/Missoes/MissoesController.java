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
    public List<MissoesModel> listarMissoes() {
        return missoesService.listarMissoes();
    }

    // POST -- Manda uma requisisao para criar uma missao
    @PostMapping("/criar")
    public String criarMissao() {
        return "Missao criada com sucesso";
    }

    // PUT -- Manda uma requisisao para alterar as missoes
    @PutMapping("/alterarID")
    public String alterarMissaoPorId() {
        return "Missao alterada com sucesso";
    }

    // Delete -- Mandar uma requisisao para deletar as missoes
    @DeleteMapping("/deletarID")
    public String deletarMissaoPorId() {
        return "Missao deletada com sucesso";
    }
}

