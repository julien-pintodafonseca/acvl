import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMedecinMobile } from 'app/shared/model/medecin-mobile.model';
import { MedecinMobileService } from './medecin-mobile.service';
import { MedecinMobileDeleteDialogComponent } from './medecin-mobile-delete-dialog.component';

@Component({
  selector: 'jhi-medecin-mobile',
  templateUrl: './medecin-mobile.component.html',
})
export class MedecinMobileComponent implements OnInit, OnDestroy {
  medecinMobiles?: IMedecinMobile[];
  eventSubscriber?: Subscription;

  constructor(
    protected medecinMobileService: MedecinMobileService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.medecinMobileService.query().subscribe((res: HttpResponse<IMedecinMobile[]>) => (this.medecinMobiles = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMedecinMobiles();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMedecinMobile): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMedecinMobiles(): void {
    this.eventSubscriber = this.eventManager.subscribe('medecinMobileListModification', () => this.loadAll());
  }

  delete(medecinMobile: IMedecinMobile): void {
    const modalRef = this.modalService.open(MedecinMobileDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.medecinMobile = medecinMobile;
  }
}
