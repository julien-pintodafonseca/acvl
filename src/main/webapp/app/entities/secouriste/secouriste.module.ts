import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcvlSharedModule } from 'app/shared/shared.module';
import { SecouristeComponent } from './secouriste.component';
import { SecouristeDetailComponent } from './secouriste-detail.component';
import { SecouristeUpdateComponent } from './secouriste-update.component';
import { SecouristeDeleteDialogComponent } from './secouriste-delete-dialog.component';
import { secouristeRoute } from './secouriste.route';

@NgModule({
  imports: [AcvlSharedModule, RouterModule.forChild(secouristeRoute)],
  declarations: [SecouristeComponent, SecouristeDetailComponent, SecouristeUpdateComponent, SecouristeDeleteDialogComponent],
  entryComponents: [SecouristeDeleteDialogComponent],
})
export class AcvlSecouristeModule {}
