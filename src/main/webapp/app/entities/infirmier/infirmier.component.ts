import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInfirmier } from 'app/shared/model/infirmier.model';
import { InfirmierService } from './infirmier.service';
import { InfirmierDeleteDialogComponent } from './infirmier-delete-dialog.component';

@Component({
  selector: 'jhi-infirmier',
  templateUrl: './infirmier.component.html',
})
export class InfirmierComponent implements OnInit, OnDestroy {
  infirmiers?: IInfirmier[];
  eventSubscriber?: Subscription;

  constructor(protected infirmierService: InfirmierService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.infirmierService.query().subscribe((res: HttpResponse<IInfirmier[]>) => (this.infirmiers = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInfirmiers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInfirmier): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInfirmiers(): void {
    this.eventSubscriber = this.eventManager.subscribe('infirmierListModification', () => this.loadAll());
  }

  delete(infirmier: IInfirmier): void {
    const modalRef = this.modalService.open(InfirmierDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.infirmier = infirmier;
  }
}
