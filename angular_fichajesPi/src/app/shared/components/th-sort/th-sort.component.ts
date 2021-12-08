import { ChangeDetectionStrategy, Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-th-sort',
  templateUrl: './th-sort.component.html',
  styleUrls: ['./th-sort.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ThSortComponent implements OnInit {

  @Input() entity: string = "";
  @Input() name: string = "Title";
  @Input() asc: boolean = true;
  @Input() order: string = "id";
  @Output() sort: EventEmitter<string> = new EventEmitter<string>();

  con1=false;
  con2=false;
  con3=true;

  constructor() { }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges) {
  }

  setOrder(): void {
    this.sort.emit(this.entity);
  }

}
