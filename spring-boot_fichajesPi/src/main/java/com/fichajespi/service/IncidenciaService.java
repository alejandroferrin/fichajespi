package com.fichajespi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fichajespi.dto.entity.ChartDataDto;
import com.fichajespi.dto.interfaces.ITopIncidencias;
import com.fichajespi.dto.interfaces.IUsuarioDtoEstadistica;
import com.fichajespi.entity.Incidencia;
import com.fichajespi.repository.IncidenciaRepository;
import com.fichajespi.service.common.CommonServiceImpl;

@Service
@Transactional
public class IncidenciaService extends CommonServiceImpl<Incidencia, IncidenciaRepository> {

	public List<Incidencia> findByUser(Long id) {
		return repository.findByUsuarioId(id);
	}

	public ChartDataDto numberOfIncidenciasLast12Months() {

		ChartDataDto result = new ChartDataDto();

		List<LocalDate> datesList = new ArrayList<>();
		List<Integer> countList = new ArrayList<>();

		LocalDate dateNow = LocalDate.now();

		for (int i = 12; i >= 1; i--) {

			LocalDate dateRequest = dateNow.minusMonths(i);

			Integer count = repository.countNumberOfIncidenciasOfMonth(
					dateRequest.getMonthValue(),
					dateRequest.getYear());

			datesList.add(dateRequest);
			countList.add(count);

		}
		result.setCantidades(countList);
		result.setFechas(datesList);

		return result;
	}

	public List<IUsuarioDtoEstadistica> numberOfIncidenciasPerUserLast12Months() {

		return repository.numberOfIncidenciasPerUserLast12Months();

	}

	public List<ITopIncidencias> topIncidenciasLast12Months() {

		return repository.topIncidenciasLast12Months();

	}

}
