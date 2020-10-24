import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMdcRegulateur } from 'app/shared/model/mdc-regulateur.model';
import { MdcRegulateurService } from './mdc-regulateur.service';
import { MdcRegulateurDeleteDialogComponent } from './mdc-regulateur-delete-dialog.component';

@Component({
  selector: 'jhi-mdc-regulateur',
  templateUrl: './mdc-regulateur.component.html',
})
export class MdcRegulateurComponent implements OnInit, OnDestroy {
  mdcRegulateurs?: IMdcRegulateur[];
  eventSubscriber?: Subscription;

  constructor(
    protected mdcRegulateurService: MdcRegulateurService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.mdcRegulateurService.query().subscribe((res: HttpResponse<IMdcRegulateur[]>) => (this.mdcRegulateurs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMdcRegulateurs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMdcRegulateur): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMdcRegulateurs(): void {
    this.eventSubscriber = this.eventManager.subscribe('mdcRegulateurListModification', () => this.loadAll());
  }

  delete(mdcRegulateur: IMdcRegulateur): void {
    const modalRef = this.modalService.open(MdcRegulateurDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.mdcRegulateur = mdcRegulateur;
  }
}
