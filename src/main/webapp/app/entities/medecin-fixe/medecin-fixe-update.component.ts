import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMedecinFixe, MedecinFixe } from 'app/shared/model/medecin-fixe.model';
import { MedecinFixeService } from './medecin-fixe.service';

@Component({
  selector: 'jhi-medecin-fixe-update',
  templateUrl: './medecin-fixe-update.component.html',
})
export class MedecinFixeUpdateComponent implements OnInit {
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

  constructor(protected medecinFixeService: MedecinFixeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medecinFixe }) => {
      this.updateForm(medecinFixe);
    });
  }

  updateForm(medecinFixe: IMedecinFixe): void {
    this.editForm.patchValue({
      id: medecinFixe.id,
      nom: medecinFixe.nom,
      prenom: medecinFixe.prenom,
      adresse: medecinFixe.adresse,
      telephone: medecinFixe.telephone,
      centre: medecinFixe.centre,
      etat: medecinFixe.etat,
      estMobile: medecinFixe.estMobile,
      estFixe: medecinFixe.estFixe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medecinFixe = this.createFromForm();
    if (medecinFixe.id !== undefined) {
      this.subscribeToSaveResponse(this.medecinFixeService.update(medecinFixe));
    } else {
      this.subscribeToSaveResponse(this.medecinFixeService.create(medecinFixe));
    }
  }

  private createFromForm(): IMedecinFixe {
    return {
      ...new MedecinFixe(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedecinFixe>>): void {
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
