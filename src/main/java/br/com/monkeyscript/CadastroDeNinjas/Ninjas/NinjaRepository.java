package br.com.monkeyscript.CadastroDeNinjas.Ninjas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {
    List<NinjaModel> findAllByOrderByIdAsc();
}