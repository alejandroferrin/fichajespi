import { Observable } from "rxjs";

export interface ChartDataService {
  getChartData(): Observable<any>;
  getUserTableData(): Observable<any>;
}