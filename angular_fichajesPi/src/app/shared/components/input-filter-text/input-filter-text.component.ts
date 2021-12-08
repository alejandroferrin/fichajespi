import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-input-filter-text',
  templateUrl: './input-filter-text.component.html',
  styleUrls: ['./input-filter-text.component.css']
})
export class InputFilterTextComponent implements OnInit {

  @Input() inputValue: string = "";
  @Input() name: string = "Text filter";
  @Input() id: string = "text";
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
