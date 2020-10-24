import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMedecinRegulateur } from 'app/shared/model/medecin-regulateur.model';

type EntityResponseType = HttpResponse<IMedecinRegulateur>;
type EntityArrayResponseType = HttpResponse<IMedecinRegulateur[]>;

@Injectable({ providedIn: 'root' })
export class MedecinRegulateurService {
  public resourceUrl = SERVER_API_URL + 'api/medecin-regulateurs';

  constructor(protected http: HttpClient) {}

  create(medecinRegulateur: IMedecinRegulateur): Observable<EntityResponseType> {
    return this.http.post<IMedecinRegulateur>(this.resourceUrl, medecinRegulateur, { observe: 'response' });
  }

  update(medecinRegulateur: IMedecinRegulateur): Observable<EntityResponseType> {
    return this.http.put<IMedecinRegulateur>(this.resourceUrl, medecinRegulateur, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMedecinRegulateur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMedecinRegulateur[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
