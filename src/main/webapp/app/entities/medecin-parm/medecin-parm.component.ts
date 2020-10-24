import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMedecinParm } from 'app/shared/model/medecin-parm.model';
import { MedecinParmService } from './medecin-parm.service';
import { MedecinParmDeleteDialogComponent } from './medecin-parm-delete-dialog.component';

@Component({
  selector: 'jhi-medecin-parm',
  templateUrl: './medecin-parm.component.html',
})
export class MedecinParmComponent implements OnInit, OnDestroy {
  medecinParms?: IMedecinParm[];
  eventSubscriber?: Subscription;

  constructor(
    protected medecinParmService: MedecinParmService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.medecinParmService.query().subscribe((res: HttpResponse<IMedecinParm[]>) => (this.medecinParms = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMedecinParms();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMedecinParm): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMedecinParms(): void {
    this.eventSubscriber = this.eventManager.subscribe('medecinParmListModification', () => this.loadAll());
  }

  delete(medecinParm: IMedecinParm): void {
    const modalRef = this.modalService.open(MedecinParmDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.medecinParm = medecinParm;
  }
}
