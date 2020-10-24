import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedecinRegulateur } from 'app/shared/model/medecin-regulateur.model';
import { MedecinRegulateurService } from './medecin-regulateur.service';

@Component({
  templateUrl: './medecin-regulateur-delete-dialog.component.html',
})
export class MedecinRegulateurDeleteDialogComponent {
  medecinRegulateur?: IMedecinRegulateur;

  constructor(
    protected medecinRegulateurService: MedecinRegulateurService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.medecinRegulateurService.delete(id).subscribe(() => {
      this.eventManager.broadcast('medecinRegulateurListModification');
      this.activeModal.close();
    });
  }
}
