package com.fichajespi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fichajespi.entity.Fichaje;
import com.fichajespi.entity.Usuario;

@Repository
public interface FichajeRepository extends JpaRepository<Fichaje, Long>, JpaSpecificationExecutor<Fichaje> {

	List<Fichaje> findByUsuarioAndDiaOrderByHora(Usuario usuario, LocalDate dia);
}
