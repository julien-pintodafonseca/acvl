import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcvlSharedModule } from 'app/shared/shared.module';
import { MedecinMobileComponent } from './medecin-mobile.component';
import { MedecinMobileDetailComponent } from './medecin-mobile-detail.component';
import { MedecinMobileUpdateComponent } from './medecin-mobile-update.component';
import { MedecinMobileDeleteDialogComponent } from './medecin-mobile-delete-dialog.component';
import { medecinMobileRoute } from './medecin-mobile.route';

@NgModule({
  imports: [AcvlSharedModule, RouterModule.forChild(medecinMobileRoute)],
  declarations: [MedecinMobileComponent, MedecinMobileDetailComponent, MedecinMobileUpdateComponent, MedecinMobileDeleteDialogComponent],
  entryComponents: [MedecinMobileDeleteDialogComponent],
})
export class AcvlMedecinMobileModule {}
