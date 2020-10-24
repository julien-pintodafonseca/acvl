import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMdcRegulateur } from 'app/shared/model/mdc-regulateur.model';

@Component({
  selector: 'jhi-mdc-regulateur-detail',
  templateUrl: './mdc-regulateur-detail.component.html',
})
export class MdcRegulateurDetailComponent implements OnInit {
  mdcRegulateur: IMdcRegulateur | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mdcRegulateur }) => (this.mdcRegulateur = mdcRegulateur));
  }

  previousState(): void {
    window.history.back();
  }
}
