package com.fichajespi.specifications;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.fichajespi.entity.Permiso;
import com.fichajespi.specifications.common.CommonSpecificationImpl;
import com.fichajespi.specifications.common.SpecificationHelper;

@Component
public final class PermisoSpecifications extends CommonSpecificationImpl<Permiso> {

	public Specification<Permiso> diaDesde(LocalDate dia) {
		return (root, query, builder) -> builder
				.greaterThanOrEqualTo(root.get("dia"), dia);
	}

	public Specification<Permiso> diaHasta(LocalDate dia) {
		return (root, query, builder) -> builder
				.lessThanOrEqualTo(root.get("dia"), dia);
	}

	public Specification<Permiso> horaInicioDesde(LocalTime time) {
		return (root, query, builder) -> builder
				.greaterThanOrEqualTo(root.get("horaInicio"), time);
	}

	public Specification<Permiso> horaInicioHasta(LocalTime time) {
		return (root, query, builder) -> builder
				.lessThanOrEqualTo(root.get("horaInicio"), time);
	}

	public Specification<Permiso> horaFinDesde(LocalTime time) {
		return (root, query, builder) -> builder
				.greaterThanOrEqualTo(root.get("horaFin"), time);
	}

	public Specification<Permiso> horaFinHasta(LocalTime time) {
		return (root, query, builder) -> builder
				.lessThanOrEqualTo(root.get("horaFin"), time);
	}

	public Specification<Permiso> descripcionContains(String expression) {
		return (root, query, builder) -> builder
				.like(root.get("descripcion"), SpecificationHelper.contains(expression));
	}

	public Specification<Permiso> estadoContains(String expression) {
		return (root, query, builder) -> builder
				.like(root.get("estado"), SpecificationHelper.contains(expression));
	}

//	public Specification<Permiso> isAprobada(Boolean b) {
//		return (root, query, builder) -> builder
//				.equal(root.get("aprobado"), b);
//	}

}
