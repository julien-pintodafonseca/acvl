import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcvlSharedModule } from 'app/shared/shared.module';
import { MedecinParmComponent } from './medecin-parm.component';
import { MedecinParmDetailComponent } from './medecin-parm-detail.component';
import { MedecinParmUpdateComponent } from './medecin-parm-update.component';
import { MedecinParmDeleteDialogComponent } from './medecin-parm-delete-dialog.component';
import { medecinParmRoute } from './medecin-parm.route';

@NgModule({
  imports: [AcvlSharedModule, RouterModule.forChild(medecinParmRoute)],
  declarations: [MedecinParmComponent, MedecinParmDetailComponent, MedecinParmUpdateComponent, MedecinParmDeleteDialogComponent],
  entryComponents: [MedecinParmDeleteDialogComponent],
})
export class AcvlMedecinParmModule {}
