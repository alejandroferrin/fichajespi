import { HttpClient } from '@angular/common/http';
import { Injectable, SkipSelf } from '@angular/core';
import { Observable } from 'rxjs';
import { Calendario } from '../model/calendario';
import { CalendarioDto } from '../model/calendarioDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CalendarioService {

  //endPoint = 'http://localhost:8080/calendario'
  endPoint = environment.apiURL + '/calendario';

  constructor(private httpClient: HttpClient) { }

  saveCalendar(dto: CalendarioDto): Observable<any> {
    return this.httpClient.post<any>(this.endPoint + '/create', dto)
  }
  getElements(): Observable<any> {
    return this.httpClient.get<any>(this.endPoint + '/list/dto')
  }
  detail(id: number): Observable<Calendario> {
    return this.httpClient.get<Calendario>(this.endPoint + `/${id}`)
  }
  update(id: number, model: Calendario): Observable<any> {
    return this.httpClient.put<any>(this.endPoint + `/${id}`, model)
  }
  delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.endPoint + `/${id}`)
  }


}
