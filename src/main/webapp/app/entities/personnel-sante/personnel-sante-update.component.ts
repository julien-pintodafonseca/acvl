import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPersonnelSante, PersonnelSante } from 'app/shared/model/personnel-sante.model';
import { PersonnelSanteService } from './personnel-sante.service';

@Component({
  selector: 'jhi-personnel-sante-update',
  templateUrl: './personnel-sante-update.component.html',
})
export class PersonnelSanteUpdateComponent implements OnInit {
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

  constructor(protected personnelSanteService: PersonnelSanteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personnelSante }) => {
      this.updateForm(personnelSante);
    });
  }

  updateForm(personnelSante: IPersonnelSante): void {
    this.editForm.patchValue({
      id: personnelSante.id,
      nom: personnelSante.nom,
      prenom: personnelSante.prenom,
      adresse: personnelSante.adresse,
      telephone: personnelSante.telephone,
      centre: personnelSante.centre,
      etat: personnelSante.etat,
      estMobile: personnelSante.estMobile,
      estFixe: personnelSante.estFixe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const personnelSante = this.createFromForm();
    if (personnelSante.id !== undefined) {
      this.subscribeToSaveResponse(this.personnelSanteService.update(personnelSante));
    } else {
      this.subscribeToSaveResponse(this.personnelSanteService.create(personnelSante));
    }
  }

  private createFromForm(): IPersonnelSante {
    return {
      ...new PersonnelSante(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersonnelSante>>): void {
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
