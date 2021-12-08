import { Observable } from "rxjs";

export interface DeleteService {
  getCsvData(dto: any): Observable<any>;
  delete(id: number): Observable<any>;
}