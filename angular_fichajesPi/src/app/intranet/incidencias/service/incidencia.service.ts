import { HttpClient } from '@angular/common/http';
import { Injectable, SkipSelf } from '@angular/core';
import { Observable } from 'rxjs';
import { ChartDataService } from 'src/app/shared/interfaces/ChartDataService';
import { DataCsv } from 'src/app/shared/interfaces/dataCsv';
import { Incidencia } from '../model/incidencia';
import { IncidenciaDto } from '../model/incidenciaDto'
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IncidenciaService implements DataCsv, ChartDataService {

  //endPoint = 'http://localhost:8080/incidencia'
  endPoint = environment.apiURL + '/incidencia';

  constructor(private httpClient: HttpClient) { }

  getElements(
    dto: IncidenciaDto,
    page: number,
    size: number,
    order: string,
    asc: boolean): Observable<any> {

    return this.httpClient.post<any[]>(this.endPoint + `/pagesFiltered?page=${page}&size=${size}&order=${order}&asc=${asc}`, dto)
  }

  public detail(id: number): Observable<Incidencia> {
    return this.httpClient.get<Incidencia>(this.endPoint + `/${id}`)
  }

  public update(id: number, model: Incidencia): Observable<any> {
    return this.httpClient.put<any>(this.endPoint + `/${id}`, model)
  }
  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.endPoint + `/${id}`)
  }
  public getCsvData(dto: IncidenciaDto): Observable<any> {
    return this.httpClient.post<any[]>(this.endPoint + `/listFiltered`, dto)
  }
  public getChartData(): Observable<any> {
    return this.httpClient.get<any[]>(this.endPoint + `/count`)
  }
  public getUserTableData(): Observable<any> {
    return this.httpClient.get<any[]>(this.endPoint + `/count/users`)
  }
  public getRankingIncidencias(): Observable<any> {
    return this.httpClient.get<any[]>(this.endPoint + `/count/top`)
  }

}
