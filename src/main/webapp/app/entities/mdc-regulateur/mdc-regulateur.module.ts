import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AcvlSharedModule } from 'app/shared/shared.module';
import { MdcRegulateurComponent } from './mdc-regulateur.component';
import { MdcRegulateurDetailComponent } from './mdc-regulateur-detail.component';
import { MdcRegulateurUpdateComponent } from './mdc-regulateur-update.component';
import { MdcRegulateurDeleteDialogComponent } from './mdc-regulateur-delete-dialog.component';
import { mdcRegulateurRoute } from './mdc-regulateur.route';

@NgModule({
  imports: [AcvlSharedModule, RouterModule.forChild(mdcRegulateurRoute)],
  declarations: [MdcRegulateurComponent, MdcRegulateurDetailComponent, MdcRegulateurUpdateComponent, MdcRegulateurDeleteDialogComponent],
  entryComponents: [MdcRegulateurDeleteDialogComponent],
})
export class AcvlMdcRegulateurModule {}
