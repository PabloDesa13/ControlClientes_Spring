package mx.com.gm.servicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.gm.dao.UsuarioDao;
import mx.com.gm.domain.Rol;
import mx.com.gm.domain.UserRegistrationDto;
import mx.com.gm.domain.Usuario;

@Service("userDetailsService")
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private UsuarioDao usuarioDao;
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByEmail(email);
		
		if(usuario == null) {
			throw new UsernameNotFoundException(email);
		}
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		
		for(Rol rol: usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
			
		return new User(usuario.getEmail(), usuario.getPassword(), roles);
	}
	
	public Usuario findByEmail(String email){
        return usuarioDao.findByEmail(email);
    }

    public Usuario save(UserRegistrationDto registration){
        Usuario user = new Usuario();
        user.setNombre(registration.getFirstName());
        user.setApellido(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        /*if(registration.getTipoRol().equals("admin")) {
        	user.setRoles(Arrays.asList(new Rol("ROLE_ADMIN")));
        } else {*/
        	user.setRoles(Arrays.asList(new Rol("ROLE_USER")));
        //}
        
        return usuarioDao.save(user);
    }
}
