import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-input-filter-range-dates',
  templateUrl: './input-filter-range-dates.component.html',
  styleUrls: ['./input-filter-range-dates.component.css']
})
export class InputFilterRangeDatesComponent implements OnInit {

  @Input() name: string = "Text filter";
  @Input() id: string = "text";
  @Input() inputValue: string = '';
  @Output() inputValueChange: EventEmitter<string> = new EventEmitter<string>();
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
