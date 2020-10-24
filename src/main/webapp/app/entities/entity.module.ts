import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'mdc-mobile',
        loadChildren: () => import('./mdc-mobile/mdc-mobile.module').then(m => m.AcvlMdcMobileModule),
      },
      {
        path: 'mdc-parm',
        loadChildren: () => import('./mdc-parm/mdc-parm.module').then(m => m.AcvlMdcParmModule),
      },
      {
        path: 'mdc-fixe',
        loadChildren: () => import('./mdc-fixe/mdc-fixe.module').then(m => m.AcvlMdcFixeModule),
      },
      {
        path: 'mdc-regulateur',
        loadChildren: () => import('./mdc-regulateur/mdc-regulateur.module').then(m => m.AcvlMdcRegulateurModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class AcvlEntityModule {}
