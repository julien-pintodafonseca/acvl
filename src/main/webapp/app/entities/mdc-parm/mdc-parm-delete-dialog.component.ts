import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMdcParm } from 'app/shared/model/mdc-parm.model';
import { MdcParmService } from './mdc-parm.service';

@Component({
  templateUrl: './mdc-parm-delete-dialog.component.html',
})
export class MdcParmDeleteDialogComponent {
  mdcParm?: IMdcParm;

  constructor(protected mdcParmService: MdcParmService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mdcParmService.delete(id).subscribe(() => {
      this.eventManager.broadcast('mdcParmListModification');
      this.activeModal.close();
    });
  }
}
