import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMdcFixe } from 'app/shared/model/mdc-fixe.model';

type EntityResponseType = HttpResponse<IMdcFixe>;
type EntityArrayResponseType = HttpResponse<IMdcFixe[]>;

@Injectable({ providedIn: 'root' })
export class MdcFixeService {
  public resourceUrl = SERVER_API_URL + 'api/mdc-fixes';

  constructor(protected http: HttpClient) {}

  create(mdcFixe: IMdcFixe): Observable<EntityResponseType> {
    return this.http.post<IMdcFixe>(this.resourceUrl, mdcFixe, { observe: 'response' });
  }

  update(mdcFixe: IMdcFixe): Observable<EntityResponseType> {
    return this.http.put<IMdcFixe>(this.resourceUrl, mdcFixe, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMdcFixe>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMdcFixe[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
