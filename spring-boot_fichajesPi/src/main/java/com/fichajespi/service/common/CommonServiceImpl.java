package com.fichajespi.service.common;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public class CommonServiceImpl<E, R extends JpaRepository<E, Long> & JpaSpecificationExecutor<E>>
		implements CommonService<E> {

	@Autowired
	protected R repository;

	@Override
	public E save(E entity) {
		return repository.save(entity);
	}

	@Override
	public List<E> list() {
		return repository.findAll();
	}

	@Override
	public List<E> filterAndList(Specification<E> s) {
		return repository.findAll(s);
	}

	@Override
	public Optional<E> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Page<E> pages(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<E> pagesAndFilter(Example<E> e, Pageable pageable) {
		return repository.findAll(e, pageable);
	}

	@Override
	public Page<E> pagesAndSpec(Specification<E> s, Pageable pageable) {
		return repository.findAll(s, pageable);
	}
}
