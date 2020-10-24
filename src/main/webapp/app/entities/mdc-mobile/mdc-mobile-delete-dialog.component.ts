import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMdcMobile } from 'app/shared/model/mdc-mobile.model';
import { MdcMobileService } from './mdc-mobile.service';

@Component({
  templateUrl: './mdc-mobile-delete-dialog.component.html',
})
export class MdcMobileDeleteDialogComponent {
  mdcMobile?: IMdcMobile;

  constructor(protected mdcMobileService: MdcMobileService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mdcMobileService.delete(id).subscribe(() => {
      this.eventManager.broadcast('mdcMobileListModification');
      this.activeModal.close();
    });
  }
}
