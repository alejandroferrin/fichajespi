package com.fichajespi.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fichajespi.entity.Calendario;
import com.fichajespi.entity.DiaLaborable;

@Repository
public interface DiaLaborableRepository extends JpaRepository<DiaLaborable, Long>,JpaSpecificationExecutor<DiaLaborable> {

	boolean existsByDia(Date dia);

	@Query("select d from DiaLaborable d where d.dia = :dia and d.calendario = :calendario")
	Optional<DiaLaborable> getByDiaActive(@Param("dia") LocalDate dia, @Param("calendario") Calendario calendario);

}
