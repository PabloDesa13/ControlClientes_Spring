package mx.com.gm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.gm.domain.Persona;

public interface PersonaDao extends JpaRepository<Persona, Long>{

}
