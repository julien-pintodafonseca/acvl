import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMdcParm, MdcParm } from 'app/shared/model/mdc-parm.model';
import { MdcParmService } from './mdc-parm.service';

@Component({
  selector: 'jhi-mdc-parm-update',
  templateUrl: './mdc-parm-update.component.html',
})
export class MdcParmUpdateComponent implements OnInit {
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

  constructor(protected mdcParmService: MdcParmService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mdcParm }) => {
      this.updateForm(mdcParm);
    });
  }

  updateForm(mdcParm: IMdcParm): void {
    this.editForm.patchValue({
      id: mdcParm.id,
      nom: mdcParm.nom,
      prenom: mdcParm.prenom,
      adresse: mdcParm.adresse,
      telephone: mdcParm.telephone,
      centre: mdcParm.centre,
      etat: mdcParm.etat,
      estMobile: mdcParm.estMobile,
      estFixe: mdcParm.estFixe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mdcParm = this.createFromForm();
    if (mdcParm.id !== undefined) {
      this.subscribeToSaveResponse(this.mdcParmService.update(mdcParm));
    } else {
      this.subscribeToSaveResponse(this.mdcParmService.create(mdcParm));
    }
  }

  private createFromForm(): IMdcParm {
    return {
      ...new MdcParm(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMdcParm>>): void {
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
