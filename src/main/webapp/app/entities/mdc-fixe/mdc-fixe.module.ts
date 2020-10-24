import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcvlSharedModule } from 'app/shared/shared.module';
import { MdcFixeComponent } from './mdc-fixe.component';
import { MdcFixeDetailComponent } from './mdc-fixe-detail.component';
import { MdcFixeUpdateComponent } from './mdc-fixe-update.component';
import { MdcFixeDeleteDialogComponent } from './mdc-fixe-delete-dialog.component';
import { mdcFixeRoute } from './mdc-fixe.route';

@NgModule({
  imports: [AcvlSharedModule, RouterModule.forChild(mdcFixeRoute)],
  declarations: [MdcFixeComponent, MdcFixeDetailComponent, MdcFixeUpdateComponent, MdcFixeDeleteDialogComponent],
  entryComponents: [MdcFixeDeleteDialogComponent],
})
export class AcvlMdcFixeModule {}
