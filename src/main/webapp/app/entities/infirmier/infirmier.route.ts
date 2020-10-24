import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInfirmier, Infirmier } from 'app/shared/model/infirmier.model';
import { InfirmierService } from './infirmier.service';
import { InfirmierComponent } from './infirmier.component';
import { InfirmierDetailComponent } from './infirmier-detail.component';
import { InfirmierUpdateComponent } from './infirmier-update.component';

@Injectable({ providedIn: 'root' })
export class InfirmierResolve implements Resolve<IInfirmier> {
  constructor(private service: InfirmierService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInfirmier> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((infirmier: HttpResponse<Infirmier>) => {
          if (infirmier.body) {
            return of(infirmier.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Infirmier());
  }
}

export const infirmierRoute: Routes = [
  {
    path: '',
    component: InfirmierComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Infirmiers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InfirmierDetailComponent,
    resolve: {
      infirmier: InfirmierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Infirmiers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InfirmierUpdateComponent,
    resolve: {
      infirmier: InfirmierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Infirmiers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InfirmierUpdateComponent,
    resolve: {
      infirmier: InfirmierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Infirmiers',
    },
    canActivate: [UserRouteAccessService],
  },
];
