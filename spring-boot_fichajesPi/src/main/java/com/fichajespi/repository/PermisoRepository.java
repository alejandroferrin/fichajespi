package com.fichajespi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fichajespi.dto.interfaces.IUsuarioDtoEstadistica;
import com.fichajespi.entity.Permiso;
import com.fichajespi.entity.Usuario;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long>,
		JpaSpecificationExecutor<Permiso> {

	List<Permiso> findByUsuarioAndAprobadoTrue(Usuario usuario);

	@Query(value = "SELECT SUM(TIME_TO_SEC(TIMEDIFF(p.hora_fin, p.hora_inicio))) numero FROM permisos p WHERE MONTH(p.dia)=?1 AND YEAR(p.dia)=?2 AND aprobado =1", nativeQuery = true)
	Integer countNumberOfPermisosOfMonth(int mes, int year);

	@Query(value = "SELECT SUM(TIME_TO_SEC(TIMEDIFF(p.hora_fin, p.hora_inicio))) cantidad, u.nombre_empleado nombreEmpleado, u.numero id FROM permisos p, usuarios u WHERE p.usuario_id=u.id AND p.dia>=DATE_SUB(NOW(), INTERVAL 3 MONTH) AND p.aprobado=1 GROUP BY p.usuario_id ORDER By cantidad DESC", nativeQuery = true)
	List<IUsuarioDtoEstadistica> numberOfPermisosPerUserLast12Months(LocalDate date);

}
