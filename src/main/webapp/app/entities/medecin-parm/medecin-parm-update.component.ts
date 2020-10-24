import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMedecinParm, MedecinParm } from 'app/shared/model/medecin-parm.model';
import { MedecinParmService } from './medecin-parm.service';

@Component({
  selector: 'jhi-medecin-parm-update',
  templateUrl: './medecin-parm-update.component.html',
})
export class MedecinParmUpdateComponent implements OnInit {
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

  constructor(protected medecinParmService: MedecinParmService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medecinParm }) => {
      this.updateForm(medecinParm);
    });
  }

  updateForm(medecinParm: IMedecinParm): void {
    this.editForm.patchValue({
      id: medecinParm.id,
      nom: medecinParm.nom,
      prenom: medecinParm.prenom,
      adresse: medecinParm.adresse,
      telephone: medecinParm.telephone,
      centre: medecinParm.centre,
      etat: medecinParm.etat,
      estMobile: medecinParm.estMobile,
      estFixe: medecinParm.estFixe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medecinParm = this.createFromForm();
    if (medecinParm.id !== undefined) {
      this.subscribeToSaveResponse(this.medecinParmService.update(medecinParm));
    } else {
      this.subscribeToSaveResponse(this.medecinParmService.create(medecinParm));
    }
  }

  private createFromForm(): IMedecinParm {
    return {
      ...new MedecinParm(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedecinParm>>): void {
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
