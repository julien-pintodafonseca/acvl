import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMdcMobile, MdcMobile } from 'app/shared/model/mdc-mobile.model';
import { MdcMobileService } from './mdc-mobile.service';
import { MdcMobileComponent } from './mdc-mobile.component';
import { MdcMobileDetailComponent } from './mdc-mobile-detail.component';
import { MdcMobileUpdateComponent } from './mdc-mobile-update.component';

@Injectable({ providedIn: 'root' })
export class MdcMobileResolve implements Resolve<IMdcMobile> {
  constructor(private service: MdcMobileService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMdcMobile> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mdcMobile: HttpResponse<MdcMobile>) => {
          if (mdcMobile.body) {
            return of(mdcMobile.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MdcMobile());
  }
}

export const mdcMobileRoute: Routes = [
  {
    path: '',
    component: MdcMobileComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcMobiles',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MdcMobileDetailComponent,
    resolve: {
      mdcMobile: MdcMobileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcMobiles',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MdcMobileUpdateComponent,
    resolve: {
      mdcMobile: MdcMobileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcMobiles',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MdcMobileUpdateComponent,
    resolve: {
      mdcMobile: MdcMobileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcMobiles',
    },
    canActivate: [UserRouteAccessService],
  },
];
