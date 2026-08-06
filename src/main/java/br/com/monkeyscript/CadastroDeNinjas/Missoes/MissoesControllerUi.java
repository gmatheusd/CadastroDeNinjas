package br.com.monkeyscript.CadastroDeNinjas.Missoes;

import br.com.monkeyscript.CadastroDeNinjas.Ninjas.NinjaDTO;
import br.com.monkeyscript.CadastroDeNinjas.Ninjas.NinjaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/listar/{id}")
    public String listarMissoesPorId(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        MissoesDTO missao = missoesService.listarMissoesPorId(id);
        if (missao == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Missão não encontrada em nossos registros");
            return "redirect:/missoes/ui/listar";
        }

        model.addAttribute("missao", missao);
        return "detalhesMissao";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarMissao(Model model) {
        model.addAttribute("missao", new MissoesDTO());
        return "adicionarMissao";
    }

    @PostMapping("/salvar")
    public String salvarMissao(@ModelAttribute MissoesDTO missao, RedirectAttributes redirectAttributes) {
        missoesService.criarMissao(missao);
        redirectAttributes.addFlashAttribute("mensagem", "Missao Cadastrado!");
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/alterar/{id}")
    public String mostrarFormularioAlterar(@PathVariable Long id, Model model) {
        MissoesDTO missao = missoesService.listarMissoesPorId(id);
        if (missao != null) {
            model.addAttribute("missao", missao);
            return "alterarMissao";
        }
        return "redirect:/missoes/ui/listar";
    }

    @PostMapping("/alterar/{id}")
    public String alterarMissao(
            @PathVariable Long id,
            @ModelAttribute MissoesDTO missaoAtualizada,
            RedirectAttributes redirectAttributes) {
        missoesService.alterarMissaoPorId(id, missaoAtualizada);
        redirectAttributes.addFlashAttribute(
                "mensagem",
                "Missao alterada com sucesso!"
        );
        return "redirect:/missoes/ui/listar";
    }


}
