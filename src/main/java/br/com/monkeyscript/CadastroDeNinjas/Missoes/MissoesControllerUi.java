package br.com.monkeyscript.CadastroDeNinjas.Missoes;

import br.com.monkeyscript.CadastroDeNinjas.Ninjas.NinjaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("missoes/ui")
public class MissoesControllerUi {

    private final MissoesService missoesService;
    private final NinjaService ninjaService;

    public MissoesControllerUi(MissoesService missoesService, NinjaService ninjaService) {
        this.missoesService = missoesService;
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model) {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes", missoes);
        return "listarMissoes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinjaPorId(@PathVariable Long id,
                                    @RequestParam(required = false, defaultValue = "false") boolean confirmar,
                                    Model model) {
        MissoesDTO missao = missoesService.listarMissoesPorId(id);
        if (missao == null) {
            return "redirect:/missoes/ui/listar";
        }

        boolean temNinjas = missao.getNinjas() != null && !missao.getNinjas().isEmpty();

        if (temNinjas && !confirmar) {
            model.addAttribute("missao", missao);
            return "confirmarDelecaoMissao";
        }

        missoesService.deletarMissaoPorId(id);
        return "redirect:/missoes/ui/listar";
    }
}
