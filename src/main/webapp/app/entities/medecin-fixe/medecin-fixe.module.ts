import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcvlSharedModule } from 'app/shared/shared.module';
import { MedecinFixeComponent } from './medecin-fixe.component';
import { MedecinFixeDetailComponent } from './medecin-fixe-detail.component';
import { MedecinFixeUpdateComponent } from './medecin-fixe-update.component';
import { MedecinFixeDeleteDialogComponent } from './medecin-fixe-delete-dialog.component';
import { medecinFixeRoute } from './medecin-fixe.route';

@NgModule({
  imports: [AcvlSharedModule, RouterModule.forChild(medecinFixeRoute)],
  declarations: [MedecinFixeComponent, MedecinFixeDetailComponent, MedecinFixeUpdateComponent, MedecinFixeDeleteDialogComponent],
  entryComponents: [MedecinFixeDeleteDialogComponent],
})
export class AcvlMedecinFixeModule {}
