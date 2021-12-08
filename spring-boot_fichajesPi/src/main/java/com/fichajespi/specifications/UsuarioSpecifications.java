package com.fichajespi.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.fichajespi.entity.Usuario;
import com.fichajespi.specifications.common.UserSpecificationImpl;

@Component
public final class UsuarioSpecifications extends UserSpecificationImpl<Usuario> {
	

	public Specification<Usuario> diasEquals(
			Integer d) {
		return (root, query, builder) -> builder
				.equal(root.get("diasVacaciones"),
						d);
	}

	public Specification<Usuario> horasEquals(
			Double h) {
		return (root, query, builder) -> builder
				.equal(root.get("horasGeneradas"),
						h);
	}

	public Specification<Usuario> isWorking(
			Boolean b) {
		return (root, query, builder) -> builder
				.equal(root.get("working"),
						b);
	}

	public Specification<Usuario> isEnVacaciones(
			Boolean b) {
		return (root, query, builder) -> builder
				.equal(root.get("enVacaciones"),
						b);
	}

	public Specification<Usuario> isDeBaja(
			Boolean b) {
		return (root, query, builder) -> builder
				.equal(root.get("deBaja"),
						b);
	}

	public Specification<Usuario> diasDesde(Integer d) {
		return (root, query, builder) -> builder
				.greaterThanOrEqualTo(root.get("diasVacaciones"), d);
	}

	public Specification<Usuario> diasHasta(Integer d) {
		return (root, query, builder) -> builder
				.lessThanOrEqualTo(root.get("diasVacaciones"), d);
	}

	public Specification<Usuario> horasDesde(Double h) {
		return (root, query, builder) -> builder
				.greaterThanOrEqualTo(root.get("horasGeneradas"), h);
	}

	public Specification<Usuario> horasHasta(Double h) {
		return (root, query, builder) -> builder
				.lessThanOrEqualTo(root.get("horasGeneradas"), h);
	}


}
