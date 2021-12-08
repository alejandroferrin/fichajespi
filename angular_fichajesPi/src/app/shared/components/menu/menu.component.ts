import { Component, OnInit } from '@angular/core';
import { TokenService } from 'src/app/core/auth/service/token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {


  isAdmin: boolean = false;
  numero:string='';


  constructor(
    private service: TokenService
  ) { }

  ngOnInit(): void {
    this.isAdmin = this.service.isAdmin();
    this.numero = this.service.getNumero();
  }


}
