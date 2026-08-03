package br.com.monkeyscript.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mesagem de boas vindas para quem acessa ela")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um ninja e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criacao do ninja")
    })
    public ResponseEntity<NinjaDTO> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(novoNinja);
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    @Operation(summary = "Lista todos os Ninjas", description = "Lista todos os ninjas disponiveis no banco de dados e suas caracteristicas")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas =  ninjaService.listarNinjas();
        if (ninjas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(ninjas);
        }
    }

    // Mostrar ninja por Id (READ)
    @GetMapping("/listar/{id}")
    @PostMapping("/criar")
    @Operation(summary = "Lista o ninja por Id", description = "Ninja encontrado com sucesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado")
    })
    public ResponseEntity<?> listarNinjasPorId(
            @Parameter(description = "Usuario manda o id no caminho da requisicao")
            @PathVariable Long id) {

        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O Ninja com o (id): " + id + ", não existe em nossos registros");
        }
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping ("/alterar/{id}")
    @PostMapping("/criar")
    @Operation(summary = "Altera o ninja por Id", description = "Altera o ninja pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado, nao foi possivel alterar")
    })
    public ResponseEntity<?> alterarNinjaPorId(
            @Parameter(description = "Usuario manda o id no caminho da requisicao")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados do ninja a ser atualizado no corpo da requisicao")
            @RequestBody NinjaDTO ninjaAtualizado) {

        NinjaDTO ninja = ninjaService.alterarNinjaPorId(id, ninjaAtualizado);
        if (ninja != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O Ninja com o (ID): " + id + ", não existe nos nossos registros!");
        }
    }

    // Deletar Ninjas (DELETE)
    @DeleteMapping ("/deletar/{id}")
    @Operation(summary = "Deleta o ninja por Id", description = "Deleta um Ninja a pelo seu respectivo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja nao encontrado, nao foi possivel deletar")
    })
    public ResponseEntity<String> deletarNinjaPorId(
            @Parameter(description = "Usuario manda o id no caminho da requisicao")
            @PathVariable Long id) {

        NinjaDTO ninjaDeletado = ninjaService.listarNinjasPorId(id);
        if (ninjaDeletado != null) {
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja: " + ninjaDeletado.getNome() + ", (id): " + id  + ", Deletado com Sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O Ninja com o (ID): " + id + ", não encontrado em nossos registros!");
        }
    }

}
