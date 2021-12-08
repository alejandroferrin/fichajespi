package com.fichajespi.specifications;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.fichajespi.entity.Fichaje;
import com.fichajespi.specifications.common.CommonSpecificationImpl;
import com.fichajespi.specifications.common.SpecificationHelper;

@Component
public final class FichajeSpecifications extends CommonSpecificationImpl<Fichaje> {

	public Specification<Fichaje> tipoContains(String expression) {
		return (root, query, builder) -> builder.like(root.get("tipo"),
				SpecificationHelper.contains(expression));
	}

	public Specification<Fichaje> diaMayorQue(LocalDate dia) {
		return (root, query, builder) -> builder
				.greaterThanOrEqualTo(root.get("dia"), dia);
	}

	public Specification<Fichaje> diaMenorQue(LocalDate dia) {
		return (root, query, builder) -> builder
				.lessThanOrEqualTo(root.get("dia"), dia);
	}

	public Specification<Fichaje> horaMayorQue(LocalTime hora) {
		return (root, query, builder) -> builder
				.greaterThanOrEqualTo(root.get("hora"), hora);
	}

	public Specification<Fichaje> horaMenorQue(LocalTime hora) {
		return (root, query, builder) -> builder
				.lessThanOrEqualTo(root.get("hora"), hora);
	}

}
