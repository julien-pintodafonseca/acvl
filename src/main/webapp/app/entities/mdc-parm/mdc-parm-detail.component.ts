import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMdcParm } from 'app/shared/model/mdc-parm.model';

@Component({
  selector: 'jhi-mdc-parm-detail',
  templateUrl: './mdc-parm-detail.component.html',
})
export class MdcParmDetailComponent implements OnInit {
  mdcParm: IMdcParm | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mdcParm }) => (this.mdcParm = mdcParm));
  }

  previousState(): void {
    window.history.back();
  }
}
