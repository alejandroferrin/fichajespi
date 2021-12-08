import { Observable } from "rxjs";

export interface DataCsv {
  getCsvData(dto: any): Observable<any>;
}