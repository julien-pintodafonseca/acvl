import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedecinFixe } from 'app/shared/model/medecin-fixe.model';
import { MedecinFixeService } from './medecin-fixe.service';

@Component({
  templateUrl: './medecin-fixe-delete-dialog.component.html',
})
export class MedecinFixeDeleteDialogComponent {
  medecinFixe?: IMedecinFixe;

  constructor(
    protected medecinFixeService: MedecinFixeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.medecinFixeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('medecinFixeListModification');
      this.activeModal.close();
    });
  }
}
