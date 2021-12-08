import { HttpClient } from '@angular/common/http';
import { Injectable, SkipSelf } from '@angular/core';
import { Observable } from 'rxjs';
import { ChartDataService } from 'src/app/shared/interfaces/ChartDataService';
import { DataCsv } from 'src/app/shared/interfaces/dataCsv';
import { NuevoPermiso } from '../../home/models/nuevoPermiso';
import { Permiso } from '../model/permiso';
import { PermisoDto } from '../model/permisoDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PermisoService implements DataCsv, ChartDataService {

  //endPoint = 'http://localhost:8080/permiso'
  endPoint = environment.apiURL + '/permiso';

  constructor(private httpClient: HttpClient) { }

  getElements(
    dto: PermisoDto,
    page: number,
    size: number,
    order: string,
    asc: boolean): Observable<any> {

    return this.httpClient.post<any[]>(this.endPoint + `/pagesFiltered?page=${page}&size=${size}&order=${order}&asc=${asc}`, dto)
  }

  public detail(id: number): Observable<Permiso> {
    return this.httpClient.get<Permiso>(this.endPoint + `/${id}`)
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
  getCsvData(dto: PermisoDto): Observable<any> {
    return this.httpClient.post<any[]>(this.endPoint + `/listFiltered`, dto)
  }
  public create(permiso: NuevoPermiso): Observable<any> {
    return this.httpClient.post<any>(this.endPoint + "/create", permiso)
  }

  public getChartData(): Observable<any> {
    return this.httpClient.get<any[]>(this.endPoint + `/count`)
  }
  public getUserTableData(): Observable<any> {
    return this.httpClient.get<any[]>(this.endPoint + `/count/users`)
  }

}
