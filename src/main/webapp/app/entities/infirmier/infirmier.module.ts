import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcvlSharedModule } from 'app/shared/shared.module';
import { InfirmierComponent } from './infirmier.component';
import { InfirmierDetailComponent } from './infirmier-detail.component';
import { InfirmierUpdateComponent } from './infirmier-update.component';
import { InfirmierDeleteDialogComponent } from './infirmier-delete-dialog.component';
import { infirmierRoute } from './infirmier.route';

@NgModule({
  imports: [AcvlSharedModule, RouterModule.forChild(infirmierRoute)],
  declarations: [InfirmierComponent, InfirmierDetailComponent, InfirmierUpdateComponent, InfirmierDeleteDialogComponent],
  entryComponents: [InfirmierDeleteDialogComponent],
})
export class AcvlInfirmierModule {}
