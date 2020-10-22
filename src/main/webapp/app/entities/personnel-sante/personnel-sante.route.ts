import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPersonnelSante, PersonnelSante } from 'app/shared/model/personnel-sante.model';
import { PersonnelSanteService } from './personnel-sante.service';
import { PersonnelSanteComponent } from './personnel-sante.component';
import { PersonnelSanteDetailComponent } from './personnel-sante-detail.component';
import { PersonnelSanteUpdateComponent } from './personnel-sante-update.component';

@Injectable({ providedIn: 'root' })
export class PersonnelSanteResolve implements Resolve<IPersonnelSante> {
  constructor(private service: PersonnelSanteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPersonnelSante> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((personnelSante: HttpResponse<PersonnelSante>) => {
          if (personnelSante.body) {
            return of(personnelSante.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PersonnelSante());
  }
}

export const personnelSanteRoute: Routes = [
  {
    path: '',
    component: PersonnelSanteComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'PersonnelSantes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PersonnelSanteDetailComponent,
    resolve: {
      personnelSante: PersonnelSanteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'PersonnelSantes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PersonnelSanteUpdateComponent,
    resolve: {
      personnelSante: PersonnelSanteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'PersonnelSantes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PersonnelSanteUpdateComponent,
    resolve: {
      personnelSante: PersonnelSanteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'PersonnelSantes',
    },
    canActivate: [UserRouteAccessService],
  },
];
