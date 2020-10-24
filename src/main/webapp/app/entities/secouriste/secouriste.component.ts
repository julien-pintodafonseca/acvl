import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISecouriste } from 'app/shared/model/secouriste.model';
import { SecouristeService } from './secouriste.service';
import { SecouristeDeleteDialogComponent } from './secouriste-delete-dialog.component';

@Component({
  selector: 'jhi-secouriste',
  templateUrl: './secouriste.component.html',
})
export class SecouristeComponent implements OnInit, OnDestroy {
  secouristes?: ISecouriste[];
  eventSubscriber?: Subscription;

  constructor(protected secouristeService: SecouristeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.secouristeService.query().subscribe((res: HttpResponse<ISecouriste[]>) => (this.secouristes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSecouristes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISecouriste): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSecouristes(): void {
    this.eventSubscriber = this.eventManager.subscribe('secouristeListModification', () => this.loadAll());
  }

  delete(secouriste: ISecouriste): void {
    const modalRef = this.modalService.open(SecouristeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.secouriste = secouriste;
  }
}
