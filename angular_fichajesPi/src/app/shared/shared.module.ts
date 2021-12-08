import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from './components/header/header.component';
import { MenuComponent } from './components/menu/menu.component';
import { RouterModule } from '@angular/router';
import { PaginationComponent } from './components/pagination/pagination.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { InputFilterTextComponent } from './components/input-filter-text/input-filter-text.component';
import { InputFilterRangeComponent } from './components/input-filter-range/input-filter-range.component';
import { ThSortComponent } from './components/th-sort/th-sort.component';
import { InputFilterRangeDatesComponent } from './components/input-filter-range-dates/input-filter-range-dates.component';
import { InputFilterRangeTimeComponent } from './components/input-filter-range-time/input-filter-range-time.component';
import { BotonDescargaCsvComponent } from './components/boton-descarga-csv/boton-descarga-csv.component';
import { CoreModule } from '../core/core.module';
import { BotonBorrarFiltrosComponent } from './components/boton-borrar-filtros/boton-borrar-filtros.component';
import { BotonGuardarComponent } from './components/boton-guardar/boton-guardar.component';
import { BotonVolverComponent } from './components/boton-volver/boton-volver.component';
import { ChartComponent } from './components/chart/chart.component';
import { ChartModule } from 'primeng/chart';
import { StaticsTableComponent } from './components/statics-table/statics-table.component';




@NgModule({
  declarations: [
    HeaderComponent,
    MenuComponent,
    PaginationComponent,
    InputFilterTextComponent,
    InputFilterRangeComponent,
    ThSortComponent,
    InputFilterRangeDatesComponent,
    InputFilterRangeTimeComponent,
    BotonDescargaCsvComponent,
    BotonBorrarFiltrosComponent,
    BotonGuardarComponent,
    BotonVolverComponent,
    ChartComponent,
    StaticsTableComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    CoreModule,
    ChartModule

  ],
  exports: [
    HeaderComponent,
    MenuComponent,
    PaginationComponent,
    FormsModule,
    RouterModule,
    InputFilterTextComponent,
    InputFilterRangeComponent,
    ThSortComponent,
    InputFilterRangeDatesComponent,
    InputFilterRangeTimeComponent,
    BotonDescargaCsvComponent,
    BotonBorrarFiltrosComponent,
    BotonGuardarComponent,
    BotonVolverComponent,
    ChartComponent,
    StaticsTableComponent
  ],
  providers: [
  ]
})
export class SharedModule { }
