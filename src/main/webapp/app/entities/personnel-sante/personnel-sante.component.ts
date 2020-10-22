import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPersonnelSante } from 'app/shared/model/personnel-sante.model';
import { PersonnelSanteService } from './personnel-sante.service';
import { PersonnelSanteDeleteDialogComponent } from './personnel-sante-delete-dialog.component';

@Component({
  selector: 'jhi-personnel-sante',
  templateUrl: './personnel-sante.component.html',
})
export class PersonnelSanteComponent implements OnInit, OnDestroy {
  personnelSantes?: IPersonnelSante[];
  eventSubscriber?: Subscription;

  constructor(
    protected personnelSanteService: PersonnelSanteService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.personnelSanteService.query().subscribe((res: HttpResponse<IPersonnelSante[]>) => (this.personnelSantes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPersonnelSantes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPersonnelSante): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPersonnelSantes(): void {
    this.eventSubscriber = this.eventManager.subscribe('personnelSanteListModification', () => this.loadAll());
  }

  delete(personnelSante: IPersonnelSante): void {
    const modalRef = this.modalService.open(PersonnelSanteDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.personnelSante = personnelSante;
  }
}
