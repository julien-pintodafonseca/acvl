import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMdcMobile, MdcMobile } from 'app/shared/model/mdc-mobile.model';
import { MdcMobileService } from './mdc-mobile.service';

@Component({
  selector: 'jhi-mdc-mobile-update',
  templateUrl: './mdc-mobile-update.component.html',
})
export class MdcMobileUpdateComponent implements OnInit {
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

  constructor(protected mdcMobileService: MdcMobileService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mdcMobile }) => {
      this.updateForm(mdcMobile);
    });
  }

  updateForm(mdcMobile: IMdcMobile): void {
    this.editForm.patchValue({
      id: mdcMobile.id,
      nom: mdcMobile.nom,
      prenom: mdcMobile.prenom,
      adresse: mdcMobile.adresse,
      telephone: mdcMobile.telephone,
      centre: mdcMobile.centre,
      etat: mdcMobile.etat,
      estMobile: mdcMobile.estMobile,
      estFixe: mdcMobile.estFixe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mdcMobile = this.createFromForm();
    if (mdcMobile.id !== undefined) {
      this.subscribeToSaveResponse(this.mdcMobileService.update(mdcMobile));
    } else {
      this.subscribeToSaveResponse(this.mdcMobileService.create(mdcMobile));
    }
  }

  private createFromForm(): IMdcMobile {
    return {
      ...new MdcMobile(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMdcMobile>>): void {
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
