import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'medecin-mobile',
        loadChildren: () => import('./medecin-mobile/medecin-mobile.module').then(m => m.AcvlMedecinMobileModule),
      },
      {
        path: 'medecin-parm',
        loadChildren: () => import('./medecin-parm/medecin-parm.module').then(m => m.AcvlMedecinParmModule),
      },
      {
        path: 'medecin-fixe',
        loadChildren: () => import('./medecin-fixe/medecin-fixe.module').then(m => m.AcvlMedecinFixeModule),
      },
      {
        path: 'medecin-regulateur',
        loadChildren: () => import('./medecin-regulateur/medecin-regulateur.module').then(m => m.AcvlMedecinRegulateurModule),
      },
      {
        path: 'infirmier',
        loadChildren: () => import('./infirmier/infirmier.module').then(m => m.AcvlInfirmierModule),
      },
      {
        path: 'secouriste',
        loadChildren: () => import('./secouriste/secouriste.module').then(m => m.AcvlSecouristeModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class AcvlEntityModule {}
