package br.com.monkeyscript.CadastroDeNinjas.Missoes;

import br.com.monkeyscript.CadastroDeNinjas.Ninjas.NinjaModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table (name = "tb_missoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "nome")
    private String nome;

    @Column (name = "dificuldade")
    private String dificuldade;

    // @OneToMany uma missao pode ter varios ninjas
    @OneToMany(mappedBy = "missao")
    @JsonIgnore
    private List<NinjaModel> ninjas;

}
