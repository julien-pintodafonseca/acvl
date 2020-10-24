import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMedecinRegulateur, MedecinRegulateur } from 'app/shared/model/medecin-regulateur.model';
import { MedecinRegulateurService } from './medecin-regulateur.service';

@Component({
  selector: 'jhi-medecin-regulateur-update',
  templateUrl: './medecin-regulateur-update.component.html',
})
export class MedecinRegulateurUpdateComponent implements OnInit {
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

  constructor(
    protected medecinRegulateurService: MedecinRegulateurService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medecinRegulateur }) => {
      this.updateForm(medecinRegulateur);
    });
  }

  updateForm(medecinRegulateur: IMedecinRegulateur): void {
    this.editForm.patchValue({
      id: medecinRegulateur.id,
      nom: medecinRegulateur.nom,
      prenom: medecinRegulateur.prenom,
      adresse: medecinRegulateur.adresse,
      telephone: medecinRegulateur.telephone,
      centre: medecinRegulateur.centre,
      etat: medecinRegulateur.etat,
      estMobile: medecinRegulateur.estMobile,
      estFixe: medecinRegulateur.estFixe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medecinRegulateur = this.createFromForm();
    if (medecinRegulateur.id !== undefined) {
      this.subscribeToSaveResponse(this.medecinRegulateurService.update(medecinRegulateur));
    } else {
      this.subscribeToSaveResponse(this.medecinRegulateurService.create(medecinRegulateur));
    }
  }

  private createFromForm(): IMedecinRegulateur {
    return {
      ...new MedecinRegulateur(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedecinRegulateur>>): void {
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
