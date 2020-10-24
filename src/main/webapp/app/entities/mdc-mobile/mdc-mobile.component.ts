import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMdcMobile } from 'app/shared/model/mdc-mobile.model';
import { MdcMobileService } from './mdc-mobile.service';
import { MdcMobileDeleteDialogComponent } from './mdc-mobile-delete-dialog.component';

@Component({
  selector: 'jhi-mdc-mobile',
  templateUrl: './mdc-mobile.component.html',
})
export class MdcMobileComponent implements OnInit, OnDestroy {
  mdcMobiles?: IMdcMobile[];
  eventSubscriber?: Subscription;

  constructor(protected mdcMobileService: MdcMobileService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.mdcMobileService.query().subscribe((res: HttpResponse<IMdcMobile[]>) => (this.mdcMobiles = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMdcMobiles();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMdcMobile): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMdcMobiles(): void {
    this.eventSubscriber = this.eventManager.subscribe('mdcMobileListModification', () => this.loadAll());
  }

  delete(mdcMobile: IMdcMobile): void {
    const modalRef = this.modalService.open(MdcMobileDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.mdcMobile = mdcMobile;
  }
}
