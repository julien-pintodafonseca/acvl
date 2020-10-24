import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IInfirmier, Infirmier } from 'app/shared/model/infirmier.model';
import { InfirmierService } from './infirmier.service';

@Component({
  selector: 'jhi-infirmier-update',
  templateUrl: './infirmier-update.component.html',
})
export class InfirmierUpdateComponent implements OnInit {
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

  constructor(protected infirmierService: InfirmierService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ infirmier }) => {
      this.updateForm(infirmier);
    });
  }

  updateForm(infirmier: IInfirmier): void {
    this.editForm.patchValue({
      id: infirmier.id,
      nom: infirmier.nom,
      prenom: infirmier.prenom,
      adresse: infirmier.adresse,
      telephone: infirmier.telephone,
      centre: infirmier.centre,
      etat: infirmier.etat,
      estMobile: infirmier.estMobile,
      estFixe: infirmier.estFixe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const infirmier = this.createFromForm();
    if (infirmier.id !== undefined) {
      this.subscribeToSaveResponse(this.infirmierService.update(infirmier));
    } else {
      this.subscribeToSaveResponse(this.infirmierService.create(infirmier));
    }
  }

  private createFromForm(): IInfirmier {
    return {
      ...new Infirmier(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInfirmier>>): void {
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
