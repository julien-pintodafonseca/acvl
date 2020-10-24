import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMdcFixe, MdcFixe } from 'app/shared/model/mdc-fixe.model';
import { MdcFixeService } from './mdc-fixe.service';
import { MdcFixeComponent } from './mdc-fixe.component';
import { MdcFixeDetailComponent } from './mdc-fixe-detail.component';
import { MdcFixeUpdateComponent } from './mdc-fixe-update.component';

@Injectable({ providedIn: 'root' })
export class MdcFixeResolve implements Resolve<IMdcFixe> {
  constructor(private service: MdcFixeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMdcFixe> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mdcFixe: HttpResponse<MdcFixe>) => {
          if (mdcFixe.body) {
            return of(mdcFixe.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MdcFixe());
  }
}

export const mdcFixeRoute: Routes = [
  {
    path: '',
    component: MdcFixeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcFixes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MdcFixeDetailComponent,
    resolve: {
      mdcFixe: MdcFixeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcFixes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MdcFixeUpdateComponent,
    resolve: {
      mdcFixe: MdcFixeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcFixes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MdcFixeUpdateComponent,
    resolve: {
      mdcFixe: MdcFixeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcFixes',
    },
    canActivate: [UserRouteAccessService],
  },
];
