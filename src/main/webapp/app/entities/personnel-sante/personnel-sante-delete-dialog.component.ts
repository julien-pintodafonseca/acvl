import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPersonnelSante } from 'app/shared/model/personnel-sante.model';
import { PersonnelSanteService } from './personnel-sante.service';

@Component({
  templateUrl: './personnel-sante-delete-dialog.component.html',
})
export class PersonnelSanteDeleteDialogComponent {
  personnelSante?: IPersonnelSante;

  constructor(
    protected personnelSanteService: PersonnelSanteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.personnelSanteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('personnelSanteListModification');
      this.activeModal.close();
    });
  }
}
