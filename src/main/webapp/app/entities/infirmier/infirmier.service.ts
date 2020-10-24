import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInfirmier } from 'app/shared/model/infirmier.model';

type EntityResponseType = HttpResponse<IInfirmier>;
type EntityArrayResponseType = HttpResponse<IInfirmier[]>;

@Injectable({ providedIn: 'root' })
export class InfirmierService {
  public resourceUrl = SERVER_API_URL + 'api/infirmiers';

  constructor(protected http: HttpClient) {}

  create(infirmier: IInfirmier): Observable<EntityResponseType> {
    return this.http.post<IInfirmier>(this.resourceUrl, infirmier, { observe: 'response' });
  }

  update(infirmier: IInfirmier): Observable<EntityResponseType> {
    return this.http.put<IInfirmier>(this.resourceUrl, infirmier, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInfirmier>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInfirmier[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
