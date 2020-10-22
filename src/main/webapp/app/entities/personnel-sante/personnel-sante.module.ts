import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcvlSharedModule } from 'app/shared/shared.module';
import { PersonnelSanteComponent } from './personnel-sante.component';
import { PersonnelSanteDetailComponent } from './personnel-sante-detail.component';
import { PersonnelSanteUpdateComponent } from './personnel-sante-update.component';
import { PersonnelSanteDeleteDialogComponent } from './personnel-sante-delete-dialog.component';
import { personnelSanteRoute } from './personnel-sante.route';

@NgModule({
  imports: [AcvlSharedModule, RouterModule.forChild(personnelSanteRoute)],
  declarations: [
    PersonnelSanteComponent,
    PersonnelSanteDetailComponent,
    PersonnelSanteUpdateComponent,
    PersonnelSanteDeleteDialogComponent,
  ],
  entryComponents: [PersonnelSanteDeleteDialogComponent],
})
export class AcvlPersonnelSanteModule {}
