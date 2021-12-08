package com.fichajespi.service.common;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CommonService<E> {

	public E save(E entity);

	public List<E> list();

	public List<E> filterAndList(Specification<E> s);

	public Page<E> pages(Pageable pageable);

	public Page<E> pagesAndFilter(Example<E> e, Pageable pageable);

	public Page<E> pagesAndSpec(Specification<E> s, Pageable pageable);

	public Optional<E> findById(Long id);

	public void delete(Long id);

}
