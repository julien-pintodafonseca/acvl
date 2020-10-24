import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcvlSharedModule } from 'app/shared/shared.module';
import { MdcParmComponent } from './mdc-parm.component';
import { MdcParmDetailComponent } from './mdc-parm-detail.component';
import { MdcParmUpdateComponent } from './mdc-parm-update.component';
import { MdcParmDeleteDialogComponent } from './mdc-parm-delete-dialog.component';
import { mdcParmRoute } from './mdc-parm.route';

@NgModule({
  imports: [AcvlSharedModule, RouterModule.forChild(mdcParmRoute)],
  declarations: [MdcParmComponent, MdcParmDetailComponent, MdcParmUpdateComponent, MdcParmDeleteDialogComponent],
  entryComponents: [MdcParmDeleteDialogComponent],
})
export class AcvlMdcParmModule {}
