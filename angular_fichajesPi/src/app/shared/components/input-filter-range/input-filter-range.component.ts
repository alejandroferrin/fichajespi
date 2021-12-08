import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-input-filter-range',
  templateUrl: './input-filter-range.component.html',
  styleUrls: ['./input-filter-range.component.css']
})
export class InputFilterRangeComponent implements OnInit {

  @Input() name: string = "Text filter";
  @Input() id: string = "text";
  @Input() inputValue: number | null = null;
  @Output() inputValueChange: EventEmitter<number | null> = new EventEmitter<number | null>();
  @Output() list: EventEmitter<any> = new EventEmitter();
  @Output() clear: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  listElements(): void {
    this.list.emit();
  }

  clearField(): void {
    this.clear.emit();
  }

}
