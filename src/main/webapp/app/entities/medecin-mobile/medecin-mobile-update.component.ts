import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMedecinMobile, MedecinMobile } from 'app/shared/model/medecin-mobile.model';
import { MedecinMobileService } from './medecin-mobile.service';

@Component({
  selector: 'jhi-medecin-mobile-update',
  templateUrl: './medecin-mobile-update.component.html',
})
export class MedecinMobileUpdateComponent implements OnInit {
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

  constructor(protected medecinMobileService: MedecinMobileService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medecinMobile }) => {
      this.updateForm(medecinMobile);
    });
  }

  updateForm(medecinMobile: IMedecinMobile): void {
    this.editForm.patchValue({
      id: medecinMobile.id,
      nom: medecinMobile.nom,
      prenom: medecinMobile.prenom,
      adresse: medecinMobile.adresse,
      telephone: medecinMobile.telephone,
      centre: medecinMobile.centre,
      etat: medecinMobile.etat,
      estMobile: medecinMobile.estMobile,
      estFixe: medecinMobile.estFixe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medecinMobile = this.createFromForm();
    if (medecinMobile.id !== undefined) {
      this.subscribeToSaveResponse(this.medecinMobileService.update(medecinMobile));
    } else {
      this.subscribeToSaveResponse(this.medecinMobileService.create(medecinMobile));
    }
  }

  private createFromForm(): IMedecinMobile {
    return {
      ...new MedecinMobile(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedecinMobile>>): void {
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
