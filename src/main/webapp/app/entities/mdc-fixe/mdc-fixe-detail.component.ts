import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMdcFixe } from 'app/shared/model/mdc-fixe.model';

@Component({
  selector: 'jhi-mdc-fixe-detail',
  templateUrl: './mdc-fixe-detail.component.html',
})
export class MdcFixeDetailComponent implements OnInit {
  mdcFixe: IMdcFixe | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mdcFixe }) => (this.mdcFixe = mdcFixe));
  }

  previousState(): void {
    window.history.back();
  }
}
