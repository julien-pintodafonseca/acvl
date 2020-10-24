import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISecouriste } from 'app/shared/model/secouriste.model';

type EntityResponseType = HttpResponse<ISecouriste>;
type EntityArrayResponseType = HttpResponse<ISecouriste[]>;

@Injectable({ providedIn: 'root' })
export class SecouristeService {
  public resourceUrl = SERVER_API_URL + 'api/secouristes';

  constructor(protected http: HttpClient) {}

  create(secouriste: ISecouriste): Observable<EntityResponseType> {
    return this.http.post<ISecouriste>(this.resourceUrl, secouriste, { observe: 'response' });
  }

  update(secouriste: ISecouriste): Observable<EntityResponseType> {
    return this.http.put<ISecouriste>(this.resourceUrl, secouriste, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISecouriste>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISecouriste[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
