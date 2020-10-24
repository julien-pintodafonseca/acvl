import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMdcRegulateur, MdcRegulateur } from 'app/shared/model/mdc-regulateur.model';
import { MdcRegulateurService } from './mdc-regulateur.service';

@Component({
  selector: 'jhi-mdc-regulateur-update',
  templateUrl: './mdc-regulateur-update.component.html',
})
export class MdcRegulateurUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    prenom: [null, [Validators.required]],
    adresse: [],
    telephone: [],
    centre: [],
    etat: [],
    estMobile: [],
    estFixe: [],
  });

  constructor(protected mdcRegulateurService: MdcRegulateurService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mdcRegulateur }) => {
      this.updateForm(mdcRegulateur);
    });
  }

  updateForm(mdcRegulateur: IMdcRegulateur): void {
    this.editForm.patchValue({
      id: mdcRegulateur.id,
      nom: mdcRegulateur.nom,
      prenom: mdcRegulateur.prenom,
      adresse: mdcRegulateur.adresse,
      telephone: mdcRegulateur.telephone,
      centre: mdcRegulateur.centre,
      etat: mdcRegulateur.etat,
      estMobile: mdcRegulateur.estMobile,
      estFixe: mdcRegulateur.estFixe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mdcRegulateur = this.createFromForm();
    if (mdcRegulateur.id !== undefined) {
      this.subscribeToSaveResponse(this.mdcRegulateurService.update(mdcRegulateur));
    } else {
      this.subscribeToSaveResponse(this.mdcRegulateurService.create(mdcRegulateur));
    }
  }

  private createFromForm(): IMdcRegulateur {
    return {
      ...new MdcRegulateur(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      centre: this.editForm.get(['centre'])!.value,
      etat: this.editForm.get(['etat'])!.value,
      estMobile: this.editForm.get(['estMobile'])!.value,
      estFixe: this.editForm.get(['estFixe'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMdcRegulateur>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
