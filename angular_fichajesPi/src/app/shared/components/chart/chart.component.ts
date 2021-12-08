import { Component, Input, OnInit } from '@angular/core';
import { ChartDataService } from '../../interfaces/ChartDataService';

//https://primefaces.org/primeng/showcase/#/



@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {


  @Input() service: ChartDataService | null = null;
  @Input() serieName: string = 'serie';
  @Input() chartTitle: string = 'chart title';

  basicData: any;
  basicOptions: any;


  constructor() {
  }

  ngOnInit(): void {
    this.service?.getChartData().subscribe(
      data => {
        this.initChart(data.fechas, data.cantidades);
      }
    )

  }


  initChart(labels: any[], cantidades: any[]) {
    this.basicData = {
      //labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      labels: labels,
      datasets: [
        {
          label: this.serieName,
          data: cantidades,
          fill: true,
          //borderColor: '#42A5F5',
          borderColor: '#D21757',
          backgroundColor: '#D7EBED',
          //tension: .4
          tension: 0.2
        },
      ]
    };

    this.basicOptions = {
      plugins: {
        legend: {
          labels: {
            color: '#495057'
          }
        }
      },
      scales: {
        x: {
          ticks: {
            color: '#495057'
          },
          grid: {
            color: '#ebedef'
          }
        },
        y: {
          ticks: {
            color: '#495057'
          },
          grid: {
            color: '#ebedef'
          }
        }
      }
    };

  }

}
