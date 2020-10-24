import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMdcRegulateur, MdcRegulateur } from 'app/shared/model/mdc-regulateur.model';
import { MdcRegulateurService } from './mdc-regulateur.service';
import { MdcRegulateurComponent } from './mdc-regulateur.component';
import { MdcRegulateurDetailComponent } from './mdc-regulateur-detail.component';
import { MdcRegulateurUpdateComponent } from './mdc-regulateur-update.component';

@Injectable({ providedIn: 'root' })
export class MdcRegulateurResolve implements Resolve<IMdcRegulateur> {
  constructor(private service: MdcRegulateurService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMdcRegulateur> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mdcRegulateur: HttpResponse<MdcRegulateur>) => {
          if (mdcRegulateur.body) {
            return of(mdcRegulateur.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MdcRegulateur());
  }
}

export const mdcRegulateurRoute: Routes = [
  {
    path: '',
    component: MdcRegulateurComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcRegulateurs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MdcRegulateurDetailComponent,
    resolve: {
      mdcRegulateur: MdcRegulateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcRegulateurs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MdcRegulateurUpdateComponent,
    resolve: {
      mdcRegulateur: MdcRegulateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcRegulateurs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MdcRegulateurUpdateComponent,
    resolve: {
      mdcRegulateur: MdcRegulateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MdcRegulateurs',
    },
    canActivate: [UserRouteAccessService],
  },
];
