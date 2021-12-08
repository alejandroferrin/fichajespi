import { HttpClient } from '@angular/common/http';
import { Injectable, SkipSelf } from '@angular/core';
import { Observable } from 'rxjs';
import { FichajeDto } from '../../fichajes/model/fichajeDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  //endPoint = 'http://localhost:8080/'
  endPoint = environment.apiURL + '/fichaje';

  constructor(private httpClient: HttpClient) { }

  public now(dto: FichajeDto): Observable<any> {
    return this.httpClient.post<any[]>(this.endPoint + `/now`, dto)
  }
}
