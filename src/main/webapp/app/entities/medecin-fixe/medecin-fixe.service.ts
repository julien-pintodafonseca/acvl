import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMedecinFixe } from 'app/shared/model/medecin-fixe.model';

type EntityResponseType = HttpResponse<IMedecinFixe>;
type EntityArrayResponseType = HttpResponse<IMedecinFixe[]>;

@Injectable({ providedIn: 'root' })
export class MedecinFixeService {
  public resourceUrl = SERVER_API_URL + 'api/medecin-fixes';

  constructor(protected http: HttpClient) {}

  create(medecinFixe: IMedecinFixe): Observable<EntityResponseType> {
    return this.http.post<IMedecinFixe>(this.resourceUrl, medecinFixe, { observe: 'response' });
  }

  update(medecinFixe: IMedecinFixe): Observable<EntityResponseType> {
    return this.http.put<IMedecinFixe>(this.resourceUrl, medecinFixe, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMedecinFixe>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMedecinFixe[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
