import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMedecinFixe } from 'app/shared/model/medecin-fixe.model';
import { MedecinFixeService } from './medecin-fixe.service';
import { MedecinFixeDeleteDialogComponent } from './medecin-fixe-delete-dialog.component';

@Component({
  selector: 'jhi-medecin-fixe',
  templateUrl: './medecin-fixe.component.html',
})
export class MedecinFixeComponent implements OnInit, OnDestroy {
  medecinFixes?: IMedecinFixe[];
  eventSubscriber?: Subscription;

  constructor(
    protected medecinFixeService: MedecinFixeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.medecinFixeService.query().subscribe((res: HttpResponse<IMedecinFixe[]>) => (this.medecinFixes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMedecinFixes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMedecinFixe): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMedecinFixes(): void {
    this.eventSubscriber = this.eventManager.subscribe('medecinFixeListModification', () => this.loadAll());
  }

  delete(medecinFixe: IMedecinFixe): void {
    const modalRef = this.modalService.open(MedecinFixeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.medecinFixe = medecinFixe;
  }
}
