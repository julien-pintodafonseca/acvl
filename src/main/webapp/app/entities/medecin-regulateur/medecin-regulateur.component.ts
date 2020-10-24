import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMedecinRegulateur } from 'app/shared/model/medecin-regulateur.model';
import { MedecinRegulateurService } from './medecin-regulateur.service';
import { MedecinRegulateurDeleteDialogComponent } from './medecin-regulateur-delete-dialog.component';

@Component({
  selector: 'jhi-medecin-regulateur',
  templateUrl: './medecin-regulateur.component.html',
})
export class MedecinRegulateurComponent implements OnInit, OnDestroy {
  medecinRegulateurs?: IMedecinRegulateur[];
  eventSubscriber?: Subscription;

  constructor(
    protected medecinRegulateurService: MedecinRegulateurService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.medecinRegulateurService
      .query()
      .subscribe((res: HttpResponse<IMedecinRegulateur[]>) => (this.medecinRegulateurs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMedecinRegulateurs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMedecinRegulateur): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMedecinRegulateurs(): void {
    this.eventSubscriber = this.eventManager.subscribe('medecinRegulateurListModification', () => this.loadAll());
  }

  delete(medecinRegulateur: IMedecinRegulateur): void {
    const modalRef = this.modalService.open(MedecinRegulateurDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.medecinRegulateur = medecinRegulateur;
  }
}
