import { HttpClient } from '@angular/common/http';
import { Injectable, SkipSelf } from '@angular/core';
import { Observable } from 'rxjs';
import { DataCsv } from 'src/app/shared/interfaces/dataCsv';
import { NuevasVacaciones } from '../../home/models/nuevasVacaciones';
import { Vacaciones } from '../models/vacaciones';
import { VacacionesDto } from '../models/vacacionesDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VacacionesService implements DataCsv {

  //endPoint = 'http://localhost:8080/vacaciones'
  endPoint = environment.apiURL + '/vacaciones';

  constructor( private httpClient: HttpClient) { }

  getElements(
    dto: VacacionesDto,
    page: number,
    size: number,
    order: string,
    asc: boolean): Observable<any> {

    return this.httpClient.post<any[]>(this.endPoint + `/pagesFiltered?page=${page}&size=${size}&order=${order}&asc=${asc}`, dto)
  }

  public detail(id: number): Observable<Vacaciones> {
    return this.httpClient.get<Vacaciones>(this.endPoint + `/${id}`)
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.endPoint + `/${id}`)
  }

  public aprobar(id: number): Observable<any> {
    return this.httpClient.put<any>(this.endPoint + `/aprobar/${id}`, null)
  }

  public denegar(id: number): Observable<any> {
    return this.httpClient.put<any>(this.endPoint + `/denegar/${id}`, null)
  }
  getCsvData(dto: VacacionesDto): Observable<any> {
    return this.httpClient.post<any[]>(this.endPoint + `/listFiltered`, dto)
  }

  public create(vacaciones: NuevasVacaciones): Observable<any> {
    return this.httpClient.post<any>(this.endPoint + "/create", vacaciones)
  }
}
