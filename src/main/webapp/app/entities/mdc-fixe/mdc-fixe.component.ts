import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMdcFixe } from 'app/shared/model/mdc-fixe.model';
import { MdcFixeService } from './mdc-fixe.service';
import { MdcFixeDeleteDialogComponent } from './mdc-fixe-delete-dialog.component';

@Component({
  selector: 'jhi-mdc-fixe',
  templateUrl: './mdc-fixe.component.html',
})
export class MdcFixeComponent implements OnInit, OnDestroy {
  mdcFixes?: IMdcFixe[];
  eventSubscriber?: Subscription;

  constructor(protected mdcFixeService: MdcFixeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.mdcFixeService.query().subscribe((res: HttpResponse<IMdcFixe[]>) => (this.mdcFixes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMdcFixes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMdcFixe): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMdcFixes(): void {
    this.eventSubscriber = this.eventManager.subscribe('mdcFixeListModification', () => this.loadAll());
  }

  delete(mdcFixe: IMdcFixe): void {
    const modalRef = this.modalService.open(MdcFixeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.mdcFixe = mdcFixe;
  }
}
