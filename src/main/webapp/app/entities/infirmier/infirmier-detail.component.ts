import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInfirmier } from 'app/shared/model/infirmier.model';

@Component({
  selector: 'jhi-infirmier-detail',
  templateUrl: './infirmier-detail.component.html',
})
export class InfirmierDetailComponent implements OnInit {
  infirmier: IInfirmier | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ infirmier }) => (this.infirmier = infirmier));
  }

  previousState(): void {
    window.history.back();
  }
}
