import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMdcParm } from 'app/shared/model/mdc-parm.model';
import { MdcParmService } from './mdc-parm.service';
import { MdcParmDeleteDialogComponent } from './mdc-parm-delete-dialog.component';

@Component({
  selector: 'jhi-mdc-parm',
  templateUrl: './mdc-parm.component.html',
})
export class MdcParmComponent implements OnInit, OnDestroy {
  mdcParms?: IMdcParm[];
  eventSubscriber?: Subscription;

  constructor(protected mdcParmService: MdcParmService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.mdcParmService.query().subscribe((res: HttpResponse<IMdcParm[]>) => (this.mdcParms = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMdcParms();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMdcParm): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMdcParms(): void {
    this.eventSubscriber = this.eventManager.subscribe('mdcParmListModification', () => this.loadAll());
  }

  delete(mdcParm: IMdcParm): void {
    const modalRef = this.modalService.open(MdcParmDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.mdcParm = mdcParm;
  }
}
