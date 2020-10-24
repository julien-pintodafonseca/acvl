import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISecouriste, Secouriste } from 'app/shared/model/secouriste.model';
import { SecouristeService } from './secouriste.service';
import { SecouristeComponent } from './secouriste.component';
import { SecouristeDetailComponent } from './secouriste-detail.component';
import { SecouristeUpdateComponent } from './secouriste-update.component';

@Injectable({ providedIn: 'root' })
export class SecouristeResolve implements Resolve<ISecouriste> {
  constructor(private service: SecouristeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISecouriste> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((secouriste: HttpResponse<Secouriste>) => {
          if (secouriste.body) {
            return of(secouriste.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Secouriste());
  }
}

export const secouristeRoute: Routes = [
  {
    path: '',
    component: SecouristeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Secouristes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SecouristeDetailComponent,
    resolve: {
      secouriste: SecouristeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Secouristes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SecouristeUpdateComponent,
    resolve: {
      secouriste: SecouristeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Secouristes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SecouristeUpdateComponent,
    resolve: {
      secouriste: SecouristeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Secouristes',
    },
    canActivate: [UserRouteAccessService],
  },
];
