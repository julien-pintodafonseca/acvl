import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMdcParm } from 'app/shared/model/mdc-parm.model';

type EntityResponseType = HttpResponse<IMdcParm>;
type EntityArrayResponseType = HttpResponse<IMdcParm[]>;

@Injectable({ providedIn: 'root' })
export class MdcParmService {
  public resourceUrl = SERVER_API_URL + 'api/mdc-parms';

  constructor(protected http: HttpClient) {}

  create(mdcParm: IMdcParm): Observable<EntityResponseType> {
    return this.http.post<IMdcParm>(this.resourceUrl, mdcParm, { observe: 'response' });
  }

  update(mdcParm: IMdcParm): Observable<EntityResponseType> {
    return this.http.put<IMdcParm>(this.resourceUrl, mdcParm, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMdcParm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMdcParm[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
