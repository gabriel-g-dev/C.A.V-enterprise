package br.com.fiap.satguard.repository;

import br.com.fiap.satguard.model.DetritoEspacial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DetritoEspacialRepository extends JpaRepository<DetritoEspacial, Integer> {

}
