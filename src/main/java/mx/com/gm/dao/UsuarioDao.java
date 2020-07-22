package mx.com.gm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.gm.domain.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long>{
	
	Usuario findByEmail(String email);
}
