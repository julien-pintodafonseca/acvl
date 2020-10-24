import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInfirmier } from 'app/shared/model/infirmier.model';
import { InfirmierService } from './infirmier.service';

@Component({
  templateUrl: './infirmier-delete-dialog.component.html',
})
export class InfirmierDeleteDialogComponent {
  infirmier?: IInfirmier;

  constructor(protected infirmierService: InfirmierService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.infirmierService.delete(id).subscribe(() => {
      this.eventManager.broadcast('infirmierListModification');
      this.activeModal.close();
    });
  }
}
