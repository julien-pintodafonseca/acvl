import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcvlSharedModule } from 'app/shared/shared.module';
import { MedecinRegulateurComponent } from './medecin-regulateur.component';
import { MedecinRegulateurDetailComponent } from './medecin-regulateur-detail.component';
import { MedecinRegulateurUpdateComponent } from './medecin-regulateur-update.component';
import { MedecinRegulateurDeleteDialogComponent } from './medecin-regulateur-delete-dialog.component';
import { medecinRegulateurRoute } from './medecin-regulateur.route';

@NgModule({
  imports: [AcvlSharedModule, RouterModule.forChild(medecinRegulateurRoute)],
  declarations: [
    MedecinRegulateurComponent,
    MedecinRegulateurDetailComponent,
    MedecinRegulateurUpdateComponent,
    MedecinRegulateurDeleteDialogComponent,
  ],
  entryComponents: [MedecinRegulateurDeleteDialogComponent],
})
export class AcvlMedecinRegulateurModule {}
