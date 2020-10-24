import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMdcMobile } from 'app/shared/model/mdc-mobile.model';

@Component({
  selector: 'jhi-mdc-mobile-detail',
  templateUrl: './mdc-mobile-detail.component.html',
})
export class MdcMobileDetailComponent implements OnInit {
  mdcMobile: IMdcMobile | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mdcMobile }) => (this.mdcMobile = mdcMobile));
  }

  previousState(): void {
    window.history.back();
  }
}
