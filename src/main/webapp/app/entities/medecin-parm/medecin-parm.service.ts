import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMedecinParm } from 'app/shared/model/medecin-parm.model';

type EntityResponseType = HttpResponse<IMedecinParm>;
type EntityArrayResponseType = HttpResponse<IMedecinParm[]>;

@Injectable({ providedIn: 'root' })
export class MedecinParmService {
  public resourceUrl = SERVER_API_URL + 'api/medecin-parms';

  constructor(protected http: HttpClient) {}

  create(medecinParm: IMedecinParm): Observable<EntityResponseType> {
    return this.http.post<IMedecinParm>(this.resourceUrl, medecinParm, { observe: 'response' });
  }

  update(medecinParm: IMedecinParm): Observable<EntityResponseType> {
    return this.http.put<IMedecinParm>(this.resourceUrl, medecinParm, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMedecinParm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMedecinParm[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
