import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedecinParm } from 'app/shared/model/medecin-parm.model';

@Component({
  selector: 'jhi-medecin-parm-detail',
  templateUrl: './medecin-parm-detail.component.html',
})
export class MedecinParmDetailComponent implements OnInit {
  medecinParm: IMedecinParm | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medecinParm }) => (this.medecinParm = medecinParm));
  }

  previousState(): void {
    window.history.back();
  }
}
