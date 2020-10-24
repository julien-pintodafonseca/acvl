import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedecinParm } from 'app/shared/model/medecin-parm.model';
import { MedecinParmService } from './medecin-parm.service';

@Component({
  templateUrl: './medecin-parm-delete-dialog.component.html',
})
export class MedecinParmDeleteDialogComponent {
  medecinParm?: IMedecinParm;

  constructor(
    protected medecinParmService: MedecinParmService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.medecinParmService.delete(id).subscribe(() => {
      this.eventManager.broadcast('medecinParmListModification');
      this.activeModal.close();
    });
  }
}
