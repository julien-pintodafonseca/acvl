import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISecouriste } from 'app/shared/model/secouriste.model';
import { SecouristeService } from './secouriste.service';

@Component({
  templateUrl: './secouriste-delete-dialog.component.html',
})
export class SecouristeDeleteDialogComponent {
  secouriste?: ISecouriste;

  constructor(
    protected secouristeService: SecouristeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.secouristeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('secouristeListModification');
      this.activeModal.close();
    });
  }
}
