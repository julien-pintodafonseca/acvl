import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMedecinFixe, MedecinFixe } from 'app/shared/model/medecin-fixe.model';
import { MedecinFixeService } from './medecin-fixe.service';
import { MedecinFixeComponent } from './medecin-fixe.component';
import { MedecinFixeDetailComponent } from './medecin-fixe-detail.component';
import { MedecinFixeUpdateComponent } from './medecin-fixe-update.component';

@Injectable({ providedIn: 'root' })
export class MedecinFixeResolve implements Resolve<IMedecinFixe> {
  constructor(private service: MedecinFixeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMedecinFixe> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((medecinFixe: HttpResponse<MedecinFixe>) => {
          if (medecinFixe.body) {
            return of(medecinFixe.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MedecinFixe());
  }
}

export const medecinFixeRoute: Routes = [
  {
    path: '',
    component: MedecinFixeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedecinFixes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MedecinFixeDetailComponent,
    resolve: {
      medecinFixe: MedecinFixeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedecinFixes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MedecinFixeUpdateComponent,
    resolve: {
      medecinFixe: MedecinFixeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedecinFixes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MedecinFixeUpdateComponent,
    resolve: {
      medecinFixe: MedecinFixeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedecinFixes',
    },
    canActivate: [UserRouteAccessService],
  },
];
