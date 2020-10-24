import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMedecinRegulateur, MedecinRegulateur } from 'app/shared/model/medecin-regulateur.model';
import { MedecinRegulateurService } from './medecin-regulateur.service';
import { MedecinRegulateurComponent } from './medecin-regulateur.component';
import { MedecinRegulateurDetailComponent } from './medecin-regulateur-detail.component';
import { MedecinRegulateurUpdateComponent } from './medecin-regulateur-update.component';

@Injectable({ providedIn: 'root' })
export class MedecinRegulateurResolve implements Resolve<IMedecinRegulateur> {
  constructor(private service: MedecinRegulateurService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMedecinRegulateur> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((medecinRegulateur: HttpResponse<MedecinRegulateur>) => {
          if (medecinRegulateur.body) {
            return of(medecinRegulateur.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MedecinRegulateur());
  }
}

export const medecinRegulateurRoute: Routes = [
  {
    path: '',
    component: MedecinRegulateurComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedecinRegulateurs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MedecinRegulateurDetailComponent,
    resolve: {
      medecinRegulateur: MedecinRegulateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedecinRegulateurs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MedecinRegulateurUpdateComponent,
    resolve: {
      medecinRegulateur: MedecinRegulateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedecinRegulateurs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MedecinRegulateurUpdateComponent,
    resolve: {
      medecinRegulateur: MedecinRegulateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedecinRegulateurs',
    },
    canActivate: [UserRouteAccessService],
  },
];
