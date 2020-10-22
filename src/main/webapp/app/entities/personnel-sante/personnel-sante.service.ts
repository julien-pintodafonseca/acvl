import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPersonnelSante } from 'app/shared/model/personnel-sante.model';

type EntityResponseType = HttpResponse<IPersonnelSante>;
type EntityArrayResponseType = HttpResponse<IPersonnelSante[]>;

@Injectable({ providedIn: 'root' })
export class PersonnelSanteService {
  public resourceUrl = SERVER_API_URL + 'api/personnel-santes';

  constructor(protected http: HttpClient) {}

  create(personnelSante: IPersonnelSante): Observable<EntityResponseType> {
    return this.http.post<IPersonnelSante>(this.resourceUrl, personnelSante, { observe: 'response' });
  }

  update(personnelSante: IPersonnelSante): Observable<EntityResponseType> {
    return this.http.put<IPersonnelSante>(this.resourceUrl, personnelSante, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPersonnelSante>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPersonnelSante[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
