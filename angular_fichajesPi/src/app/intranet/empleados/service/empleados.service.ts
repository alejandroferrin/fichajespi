import { HttpClient } from '@angular/common/http';
import { Injectable, SkipSelf } from '@angular/core';
import { Observable } from 'rxjs';
import { DataCsv } from 'src/app/shared/interfaces/dataCsv';
import { Password } from '../../home/models/password';
import { Empleado } from '../model/empleado';
import { EmpleadoDto } from '../model/empleadoDto'
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmpleadosService implements DataCsv {

  //endPoint = 'http://localhost:8080/usuario'
  endPoint = environment.apiURL + '/usuario';


  constructor(private httpClient: HttpClient) { }

  getElements(
    dto: EmpleadoDto,
    page: number,
    size: number,
    order: string,
    asc: boolean): Observable<any> {

    return this.httpClient.post<any[]>(this.endPoint + `/pagesFiltered?page=${page}&size=${size}&order=${order}&asc=${asc}`, dto)

  }

  public detail(id: number): Observable<Empleado> {
    return this.httpClient.get<Empleado>(this.endPoint + `/${id}`)
  }
  public update(id: number, model: Empleado): Observable<any> {
    return this.httpClient.put<any>(this.endPoint + `/${id}`, model)
  }
  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.endPoint + `/${id}`)
  }
  getCsvData(dto: EmpleadoDto): Observable<any> {
    return this.httpClient.post<any[]>(this.endPoint + `/listFiltered`, dto)
  }
  public getMyUsuario(): Observable<Empleado> {
    return this.httpClient.get<Empleado>(this.endPoint + '/miusuario')
  }
  public changePassword(id: number, model: Password): Observable<any> {
    return this.httpClient.put<any>(this.endPoint + `/password/${id}`, model)
  }
}
