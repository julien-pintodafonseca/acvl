import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedecinMobile } from 'app/shared/model/medecin-mobile.model';
import { MedecinMobileService } from './medecin-mobile.service';

@Component({
  templateUrl: './medecin-mobile-delete-dialog.component.html',
})
export class MedecinMobileDeleteDialogComponent {
  medecinMobile?: IMedecinMobile;

  constructor(
    protected medecinMobileService: MedecinMobileService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.medecinMobileService.delete(id).subscribe(() => {
      this.eventManager.broadcast('medecinMobileListModification');
      this.activeModal.close();
    });
  }
}
