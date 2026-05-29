package br.com.fiap.satguard.repository;

import br.com.fiap.satguard.model.Satelite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SateliteRepository extends JpaRepository<Satelite, Integer> {

}
