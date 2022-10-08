import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IRapportRF, getRapportRFIdentifier } from '../rapport-rf.model';

export type EntityResponseType = HttpResponse<IRapportRF>;
export type EntityArrayResponseType = HttpResponse<IRapportRF[]>;

@Injectable({ providedIn: 'root' })
export class RapportRFService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/rapport-rfs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(rapportRF: IRapportRF): Observable<EntityResponseType> {
    return this.http.post<IRapportRF>(this.resourceUrl, rapportRF, { observe: 'response' });
  }

  update(rapportRF: IRapportRF): Observable<EntityResponseType> {
    return this.http.put<IRapportRF>(`${this.resourceUrl}/${getRapportRFIdentifier(rapportRF) as number}`, rapportRF, {
      observe: 'response',
    });
  }

  partialUpdate(rapportRF: IRapportRF): Observable<EntityResponseType> {
    return this.http.patch<IRapportRF>(`${this.resourceUrl}/${getRapportRFIdentifier(rapportRF) as number}`, rapportRF, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRapportRF>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRapportRF[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addRapportRFToCollectionIfMissing(
    rapportRFCollection: IRapportRF[],
    ...rapportRFSToCheck: (IRapportRF | null | undefined)[]
  ): IRapportRF[] {
    const rapportRFS: IRapportRF[] = rapportRFSToCheck.filter(isPresent);
    if (rapportRFS.length > 0) {
      const rapportRFCollectionIdentifiers = rapportRFCollection.map(rapportRFItem => getRapportRFIdentifier(rapportRFItem)!);
      const rapportRFSToAdd = rapportRFS.filter(rapportRFItem => {
        const rapportRFIdentifier = getRapportRFIdentifier(rapportRFItem);
        if (rapportRFIdentifier == null || rapportRFCollectionIdentifiers.includes(rapportRFIdentifier)) {
          return false;
        }
        rapportRFCollectionIdentifiers.push(rapportRFIdentifier);
        return true;
      });
      return [...rapportRFSToAdd, ...rapportRFCollection];
    }
    return rapportRFCollection;
  }
}
