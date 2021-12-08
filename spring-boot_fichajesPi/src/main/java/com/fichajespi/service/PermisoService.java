package com.fichajespi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fichajespi.dto.entity.ChartDataDto;
import com.fichajespi.dto.interfaces.IUsuarioDtoEstadistica;
import com.fichajespi.entity.Permiso;
import com.fichajespi.entity.Usuario;
import com.fichajespi.repository.PermisoRepository;
import com.fichajespi.service.common.CommonServiceImpl;

@Service
@Transactional
public class PermisoService extends CommonServiceImpl<Permiso, PermisoRepository> {

	public List<Permiso> findByUsuarioAprobado(Usuario usuario) {
		return repository.findByUsuarioAndAprobadoTrue(usuario);
	}

	public ChartDataDto numberOfPermisosLast12Months() {

		ChartDataDto result = new ChartDataDto();

		List<LocalDate> datesList = new ArrayList<>();
		List<Integer> countList = new ArrayList<>();

		LocalDate dateNow = LocalDate.now();

		for (int i = 12; i >= 1; i--) {

			LocalDate dateRequest = dateNow.minusMonths(i);

			Integer count = repository.countNumberOfPermisosOfMonth(
					dateRequest.getMonthValue(),
					dateRequest.getYear());

			datesList.add(dateRequest);
			countList.add(count);

		}
		result.setCantidades(countList);
		result.setFechas(datesList);

		return result;
	}

	public List<IUsuarioDtoEstadistica> numberOfPermisosPerUserLast12Months() {

		return repository.numberOfPermisosPerUserLast12Months(LocalDate.now());

	}

}
