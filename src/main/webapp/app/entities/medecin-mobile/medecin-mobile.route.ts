import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMedecinMobile, MedecinMobile } from 'app/shared/model/medecin-mobile.model';
import { MedecinMobileService } from './medecin-mobile.service';
import { MedecinMobileComponent } from './medecin-mobile.component';
import { MedecinMobileDetailComponent } from './medecin-mobile-detail.component';
import { MedecinMobileUpdateComponent } from './medecin-mobile-update.component';

@Injectable({ providedIn: 'root' })
export class MedecinMobileResolve implements Resolve<IMedecinMobile> {
  constructor(private service: MedecinMobileService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMedecinMobile> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((medecinMobile: HttpResponse<MedecinMobile>) => {
          if (medecinMobile.body) {
            return of(medecinMobile.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MedecinMobile());
  }
}

export const medecinMobileRoute: Routes = [
  {
    path: '',
    component: MedecinMobileComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedecinMobiles',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MedecinMobileDetailComponent,
    resolve: {
      medecinMobile: MedecinMobileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedecinMobiles',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MedecinMobileUpdateComponent,
    resolve: {
      medecinMobile: MedecinMobileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedecinMobiles',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MedecinMobileUpdateComponent,
    resolve: {
      medecinMobile: MedecinMobileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MedecinMobiles',
    },
    canActivate: [UserRouteAccessService],
  },
];
