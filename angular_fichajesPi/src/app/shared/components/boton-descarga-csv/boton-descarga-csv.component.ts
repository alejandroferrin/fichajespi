import { Component, Input, OnInit } from '@angular/core';
import { DataCsv } from '../../interfaces/dataCsv';
//https://www.npmjs.com/package/angular-csv-ext
import { AngularCsv } from 'angular-csv-ext/dist/Angular-csv';
//https://www.npmjs.com/package/json-unpacker
import { unpack } from 'json-unpacker';

@Component({
  selector: 'app-boton-descarga-csv',
  templateUrl: './boton-descarga-csv.component.html',
  styleUrls: ['./boton-descarga-csv.component.css']
})
export class BotonDescargaCsvComponent implements OnInit {

  listaElementos: Array<any> = []

  @Input() apiEndPoint: string = '';
  @Input() headers: string[] = [];
  @Input() service: DataCsv | null = null;
  @Input() dto: any | null = null;
  @Input() fileName: string = 'downloaded-csv';

  constructor() { }

  ngOnInit(): void {
  }

  downloadCsv() {
    this.service!.getCsvData(this.dto).subscribe(
      data => {

        let options = {
          fieldSeparator: ',',
          quoteStrings: '"',
          decimalseparator: '.',
          showLabels: false,
          showTitle: false,
          title: this.fileName,
          useBom: false,
          noDownload: false,
          headers: this.headers,
          useHeader: true,
          nullToEmptyString: false,
        };

        let date = new Date();
        let stringDate = `-${date.getDate()}-${date.getMonth() + 1}-${date.getFullYear()}`;

        //console.log(unpack(data));
        //console.log(this.headers)

        new AngularCsv(unpack(data), this.fileName + stringDate, options);
      },
      err => {
        console.log(err)
      }
    )
  }

}
