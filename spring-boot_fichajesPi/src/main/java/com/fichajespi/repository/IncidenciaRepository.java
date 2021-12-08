package com.fichajespi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fichajespi.dto.interfaces.ITopIncidencias;
import com.fichajespi.dto.interfaces.IUsuarioDtoEstadistica;
import com.fichajespi.entity.Incidencia;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long>,
		JpaSpecificationExecutor<Incidencia> {

	List<Incidencia> findByUsuarioId(Long id);

	@Query(value = "SELECT COUNT(i.dia) numero FROM incidencias i WHERE MONTH(i.dia)=?1 AND YEAR(i.dia)=?2", nativeQuery = true)
	Integer countNumberOfIncidenciasOfMonth(int mes, int year);

	@Query(value = "SELECT COUNT(i.id) cantidad, u.nombre_empleado nombreEmpleado, u.numero id FROM incidencias i, usuarios u WHERE i.usuario_id=u.id AND i.dia >= DATE_SUB(NOW(), INTERVAL 12 MONTH) GROUP BY i.usuario_id ORDER BY cantidad DESC", nativeQuery = true)
	List<IUsuarioDtoEstadistica> numberOfIncidenciasPerUserLast12Months();

	@Query(value = "SELECT COUNT(id) cantidad, resumen FROM incidencias WHERE dia >= DATE_SUB(NOW(), INTERVAL 12 MONTH) GROUP BY resumen ORDER BY cantidad DESC", nativeQuery = true)
	List<ITopIncidencias> topIncidenciasLast12Months();

}
