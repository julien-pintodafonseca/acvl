import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedecinMobile } from 'app/shared/model/medecin-mobile.model';

@Component({
  selector: 'jhi-medecin-mobile-detail',
  templateUrl: './medecin-mobile-detail.component.html',
})
export class MedecinMobileDetailComponent implements OnInit {
  medecinMobile: IMedecinMobile | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medecinMobile }) => (this.medecinMobile = medecinMobile));
  }

  previousState(): void {
    window.history.back();
  }
}
