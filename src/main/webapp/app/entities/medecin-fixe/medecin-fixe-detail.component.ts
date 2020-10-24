import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedecinFixe } from 'app/shared/model/medecin-fixe.model';

@Component({
  selector: 'jhi-medecin-fixe-detail',
  templateUrl: './medecin-fixe-detail.component.html',
})
export class MedecinFixeDetailComponent implements OnInit {
  medecinFixe: IMedecinFixe | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medecinFixe }) => (this.medecinFixe = medecinFixe));
  }

  previousState(): void {
    window.history.back();
  }
}
