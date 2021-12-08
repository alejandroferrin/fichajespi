import { HttpClient } from '@angular/common/http';
import { Injectable, SkipSelf } from '@angular/core';
import { Observable } from 'rxjs';
import { DataCsv } from 'src/app/shared/interfaces/dataCsv';
import { DeleteService } from 'src/app/shared/interfaces/DeleteService';
import { Fichaje } from '../model/fichaje';
import { FichajeDto } from '../model/fichajeDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FichajeService implements DataCsv, DeleteService {

  //endPoint = 'http://localhost:8080/fichaje';
  endPoint = environment.apiURL + '/fichaje';

  constructor(private httpClient: HttpClient) { }

  getElements(
    dto: FichajeDto,
    page: number,
    size: number,
    order: string,
    asc: boolean): Observable<any> {

    return this.httpClient.post<any[]>(this.endPoint + `/pagesFiltered?page=${page}&size=${size}&order=${order}&asc=${asc}`, dto)
  }

  public detail(id: number): Observable<Fichaje> {
    return this.httpClient.get<Fichaje>(this.endPoint + `/${id}`)
  }

  public update(id: number, model: Fichaje): Observable<any> {
    return this.httpClient.put<any>(this.endPoint + `/${id}`, model)
  }
  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.endPoint + `/${id}`)
  }

  getCsvData(dto: FichajeDto): Observable<any> {
    return this.httpClient.post<any[]>(this.endPoint + `/listFiltered`, dto)
  }
}
