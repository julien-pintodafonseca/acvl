import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcvlSharedModule } from 'app/shared/shared.module';
import { MdcMobileComponent } from './mdc-mobile.component';
import { MdcMobileDetailComponent } from './mdc-mobile-detail.component';
import { MdcMobileUpdateComponent } from './mdc-mobile-update.component';
import { MdcMobileDeleteDialogComponent } from './mdc-mobile-delete-dialog.component';
import { mdcMobileRoute } from './mdc-mobile.route';

@NgModule({
  imports: [AcvlSharedModule, RouterModule.forChild(mdcMobileRoute)],
  declarations: [MdcMobileComponent, MdcMobileDetailComponent, MdcMobileUpdateComponent, MdcMobileDeleteDialogComponent],
  entryComponents: [MdcMobileDeleteDialogComponent],
})
export class AcvlMdcMobileModule {}
