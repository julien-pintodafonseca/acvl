import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMdcFixe } from 'app/shared/model/mdc-fixe.model';
import { MdcFixeService } from './mdc-fixe.service';

@Component({
  templateUrl: './mdc-fixe-delete-dialog.component.html',
})
export class MdcFixeDeleteDialogComponent {
  mdcFixe?: IMdcFixe;

  constructor(protected mdcFixeService: MdcFixeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mdcFixeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('mdcFixeListModification');
      this.activeModal.close();
    });
  }
}
