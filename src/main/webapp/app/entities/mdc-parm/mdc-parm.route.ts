import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMdcParm, MdcParm } from 'app/shared/model/mdc-parm.model';
import { MdcParmService } from './mdc-parm.service';
import { MdcParmComponent } from './mdc-parm.component';
import { MdcParmDetailComponent } from './mdc-parm-detail.component';
import { MdcParmUpdateComponent } from './mdc-parm-update.component';

@Injectable({ providedIn: 'root' })
export class MdcParmResolve implements Resolve<IMdcParm> {
  constructor(private service: MdcParmService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMdcParm> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mdcParm: HttpResponse<MdcParm>) => {
          if (mdcParm.body) {
            return of(mdcParm.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MdcParm());
  }
}

export const mdcParmRoute: Routes = [
  {
    path: '',
    component: MdcParmComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcParms',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MdcParmDetailComponent,
    resolve: {
      mdcParm: MdcParmResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcParms',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MdcParmUpdateComponent,
    resolve: {
      mdcParm: MdcParmResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcParms',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MdcParmUpdateComponent,
    resolve: {
      mdcParm: MdcParmResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcParms',
    },
    canActivate: [UserRouteAccessService],
  },
];
