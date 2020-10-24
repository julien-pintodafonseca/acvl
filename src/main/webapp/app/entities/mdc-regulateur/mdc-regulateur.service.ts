import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMdcRegulateur } from 'app/shared/model/mdc-regulateur.model';

type EntityResponseType = HttpResponse<IMdcRegulateur>;
type EntityArrayResponseType = HttpResponse<IMdcRegulateur[]>;

@Injectable({ providedIn: 'root' })
export class MdcRegulateurService {
  public resourceUrl = SERVER_API_URL + 'api/mdc-regulateurs';

  constructor(protected http: HttpClient) {}

  create(mdcRegulateur: IMdcRegulateur): Observable<EntityResponseType> {
    return this.http.post<IMdcRegulateur>(this.resourceUrl, mdcRegulateur, { observe: 'response' });
  }

  update(mdcRegulateur: IMdcRegulateur): Observable<EntityResponseType> {
    return this.http.put<IMdcRegulateur>(this.resourceUrl, mdcRegulateur, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMdcRegulateur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMdcRegulateur[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
