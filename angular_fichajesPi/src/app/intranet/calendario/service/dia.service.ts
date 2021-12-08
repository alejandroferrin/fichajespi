import { HttpClient } from '@angular/common/http';
import { Injectable, SkipSelf } from '@angular/core';
import { Observable } from 'rxjs';
import { Dia } from '../model/dia';
import { DiaDto } from '../model/diaDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DiaService {

  //endPoint = 'http://localhost:8080/dia'
  endPoint = environment.apiURL + '/dia';

  constructor(private httpClient: HttpClient) { }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.endPoint + `/${id}`)
  }
  public save(id: number, dto: DiaDto): Observable<any> {
    return this.httpClient.post<any>(this.endPoint + `/create/${id}`, dto)
  }

}
