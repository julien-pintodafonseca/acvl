import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISecouriste, Secouriste } from 'app/shared/model/secouriste.model';
import { SecouristeService } from './secouriste.service';

@Component({
  selector: 'jhi-secouriste-update',
  templateUrl: './secouriste-update.component.html',
})
export class SecouristeUpdateComponent implements OnInit {
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
    estAmbulancier: [],
  });

  constructor(protected secouristeService: SecouristeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ secouriste }) => {
      this.updateForm(secouriste);
    });
  }

  updateForm(secouriste: ISecouriste): void {
    this.editForm.patchValue({
      id: secouriste.id,
      nom: secouriste.nom,
      prenom: secouriste.prenom,
      adresse: secouriste.adresse,
      telephone: secouriste.telephone,
      centre: secouriste.centre,
      etat: secouriste.etat,
      estMobile: secouriste.estMobile,
      estFixe: secouriste.estFixe,
      estAmbulancier: secouriste.estAmbulancier,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const secouriste = this.createFromForm();
    if (secouriste.id !== undefined) {
      this.subscribeToSaveResponse(this.secouristeService.update(secouriste));
    } else {
      this.subscribeToSaveResponse(this.secouristeService.create(secouriste));
    }
  }

  private createFromForm(): ISecouriste {
    return {
      ...new Secouriste(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      centre: this.editForm.get(['centre'])!.value,
      etat: this.editForm.get(['etat'])!.value,
      estMobile: this.editForm.get(['estMobile'])!.value,
      estFixe: this.editForm.get(['estFixe'])!.value,
      estAmbulancier: this.editForm.get(['estAmbulancier'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISecouriste>>): void {
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
