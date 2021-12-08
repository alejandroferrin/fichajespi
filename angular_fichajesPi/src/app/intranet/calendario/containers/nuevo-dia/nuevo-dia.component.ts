import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DiaDto } from '../../model/diaDto';
import { DiaService } from '../../service/dia.service';
import { Popup } from 'src/app/shared/helper/popup';

@Component({
  selector: 'app-nuevo-dia',
  templateUrl: './nuevo-dia.component.html',
  styleUrls: ['./nuevo-dia.component.css']
})
export class NuevoDiaComponent implements OnInit {

  model: DiaDto = new DiaDto('', '', '', '')
  id: number = 0

  constructor(
    private service: DiaService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params.id
    this.id = id
  }


  onSave(): void {


    const id = this.activatedRoute.snapshot.params.id

    console.log(id)
    console.log(this.model)

    this.service.save(id, this.model).subscribe(
      data => {
        Popup.toastSucess('', 'Día Guardado');
        this.router.navigate([`intranet/calendario/list/${id}`])
      },
      err => {
        Popup.toastDanger('Ocurrió un error', err.message);
        console.log(err)
      }
    )
  }



}
