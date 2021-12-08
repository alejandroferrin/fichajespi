import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-boton-guardar',
  templateUrl: './boton-guardar.component.html',
  styleUrls: ['./boton-guardar.component.css']
})
export class BotonGuardarComponent implements OnInit {

  @Input() valid: boolean | null = null;

  constructor() { }

  ngOnInit(): void {
  }

}
