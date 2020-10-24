import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMdcFixe, MdcFixe } from 'app/shared/model/mdc-fixe.model';
import { MdcFixeService } from './mdc-fixe.service';

@Component({
  selector: 'jhi-mdc-fixe-update',
  templateUrl: './mdc-fixe-update.component.html',
})
export class MdcFixeUpdateComponent implements OnInit {
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

  constructor(protected mdcFixeService: MdcFixeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mdcFixe }) => {
      this.updateForm(mdcFixe);
    });
  }

  updateForm(mdcFixe: IMdcFixe): void {
    this.editForm.patchValue({
      id: mdcFixe.id,
      nom: mdcFixe.nom,
      prenom: mdcFixe.prenom,
      adresse: mdcFixe.adresse,
      telephone: mdcFixe.telephone,
      centre: mdcFixe.centre,
      etat: mdcFixe.etat,
      estMobile: mdcFixe.estMobile,
      estFixe: mdcFixe.estFixe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mdcFixe = this.createFromForm();
    if (mdcFixe.id !== undefined) {
      this.subscribeToSaveResponse(this.mdcFixeService.update(mdcFixe));
    } else {
      this.subscribeToSaveResponse(this.mdcFixeService.create(mdcFixe));
    }
  }

  private createFromForm(): IMdcFixe {
    return {
      ...new MdcFixe(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMdcFixe>>): void {
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
