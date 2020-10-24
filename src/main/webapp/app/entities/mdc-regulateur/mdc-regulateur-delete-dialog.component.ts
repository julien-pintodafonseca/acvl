import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMdcRegulateur } from 'app/shared/model/mdc-regulateur.model';
import { MdcRegulateurService } from './mdc-regulateur.service';

@Component({
  templateUrl: './mdc-regulateur-delete-dialog.component.html',
})
export class MdcRegulateurDeleteDialogComponent {
  mdcRegulateur?: IMdcRegulateur;

  constructor(
    protected mdcRegulateurService: MdcRegulateurService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mdcRegulateurService.delete(id).subscribe(() => {
      this.eventManager.broadcast('mdcRegulateurListModification');
      this.activeModal.close();
    });
  }
}
