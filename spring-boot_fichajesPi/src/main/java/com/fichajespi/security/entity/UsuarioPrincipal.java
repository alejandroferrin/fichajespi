package com.fichajespi.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fichajespi.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPrincipal implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numero;
	private String nombre;
	private String password;
	private Long id;
	private Collection<? extends GrantedAuthority> authorities;

	public static UsuarioPrincipal build(Usuario usuario) {

		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol
						.getRolNombre().name()))
				.collect(Collectors.toList());

		return new UsuarioPrincipal(
				usuario.getNumero(),
				usuario.getNombreEmpleado(),
				usuario.getPassword(),
				usuario.getId(),
				authorities);
	}

	@Override
	public String getUsername() {
		return numero;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
