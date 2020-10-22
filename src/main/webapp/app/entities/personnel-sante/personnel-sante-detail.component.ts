import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPersonnelSante } from 'app/shared/model/personnel-sante.model';

@Component({
  selector: 'jhi-personnel-sante-detail',
  templateUrl: './personnel-sante-detail.component.html',
})
export class PersonnelSanteDetailComponent implements OnInit {
  personnelSante: IPersonnelSante | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personnelSante }) => (this.personnelSante = personnelSante));
  }

  previousState(): void {
    window.history.back();
  }
}
