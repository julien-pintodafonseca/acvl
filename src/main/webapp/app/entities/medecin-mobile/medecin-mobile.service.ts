import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMedecinMobile } from 'app/shared/model/medecin-mobile.model';

type EntityResponseType = HttpResponse<IMedecinMobile>;
type EntityArrayResponseType = HttpResponse<IMedecinMobile[]>;

@Injectable({ providedIn: 'root' })
export class MedecinMobileService {
  public resourceUrl = SERVER_API_URL + 'api/medecin-mobiles';

  constructor(protected http: HttpClient) {}

  create(medecinMobile: IMedecinMobile): Observable<EntityResponseType> {
    return this.http.post<IMedecinMobile>(this.resourceUrl, medecinMobile, { observe: 'response' });
  }

  update(medecinMobile: IMedecinMobile): Observable<EntityResponseType> {
    return this.http.put<IMedecinMobile>(this.resourceUrl, medecinMobile, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMedecinMobile>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMedecinMobile[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
