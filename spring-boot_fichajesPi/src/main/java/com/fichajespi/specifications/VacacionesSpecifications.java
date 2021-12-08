package com.fichajespi.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.fichajespi.entity.Vacaciones;
import com.fichajespi.specifications.common.CommonSpecificationImpl;
import com.fichajespi.specifications.common.SpecificationHelper;

@Component
public final class VacacionesSpecifications extends CommonSpecificationImpl<Vacaciones> {

	public Specification<Vacaciones> inicioDesde(LocalDate dia) {
		return (root, query, builder) -> builder
				.greaterThanOrEqualTo(root.get("inicio"), dia);
	}

	public Specification<Vacaciones> inicioHasta(LocalDate dia) {
		return (root, query, builder) -> builder
				.lessThanOrEqualTo(root.get("inicio"), dia);
	}

	public Specification<Vacaciones> finDesde(LocalDate dia) {
		return (root, query, builder) -> builder
				.greaterThanOrEqualTo(root.get("fin"), dia);
	}

	public Specification<Vacaciones> finHasta(LocalDate dia) {
		return (root, query, builder) -> builder
				.lessThanOrEqualTo(root.get("fin"), dia);
	}

	public Specification<Vacaciones> areConsumidas(Boolean b) {
		return (root, query, builder) -> builder
				.equal(root.get("consumidas"), b);
	}

	public Specification<Vacaciones> areAprobadas(Boolean b) {
		return (root, query, builder) -> builder
				.equal(root.get("aprobado"), b);
	}

	public Specification<Vacaciones> estadoContains(String expression) {
		return (root, query, builder) -> builder
				.like(root.get("estado"), SpecificationHelper.contains(expression));
	}
}
