import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'personnel-sante',
        loadChildren: () => import('./personnel-sante/personnel-sante.module').then(m => m.AcvlPersonnelSanteModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class AcvlEntityModule {}
