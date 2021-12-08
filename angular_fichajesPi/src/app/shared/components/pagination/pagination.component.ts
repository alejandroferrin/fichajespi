import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Pagination } from './model/pagination.model';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {


  @Input() pag: Pagination | null = null;
  @Output() paginacion = new EventEmitter<Pagination>();

  constructor() { }

  ngOnInit(): void {
  }

  rewind(): void {
    if (!this.pag!.isFirst) {
      this.pag!.page--
      this.paginacion.emit(this.pag!);
    }
  }
  forward(): void {
    if (!this.pag!.isLast) {
      this.pag!.page++
      this.paginacion.emit(this.pag!);
    }
  }

  setPage(page: number): void {
    this.pag!.page = page;
    this.paginacion.emit(this.pag!);
  }

  setSize(size: number): void {
    if (size < this.pag!.sizeLimit) {
      this.pag!.size = size
      this.setPage(0)
    } else {
      this.pag!.size = this.pag!.sizeLimit
      this.setPage(0)
    }
  }
}
