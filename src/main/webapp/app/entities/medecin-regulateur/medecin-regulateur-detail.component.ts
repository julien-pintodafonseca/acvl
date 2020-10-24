import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedecinRegulateur } from 'app/shared/model/medecin-regulateur.model';

@Component({
  selector: 'jhi-medecin-regulateur-detail',
  templateUrl: './medecin-regulateur-detail.component.html',
})
export class MedecinRegulateurDetailComponent implements OnInit {
  medecinRegulateur: IMedecinRegulateur | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medecinRegulateur }) => (this.medecinRegulateur = medecinRegulateur));
  }

  previousState(): void {
    window.history.back();
  }
}
