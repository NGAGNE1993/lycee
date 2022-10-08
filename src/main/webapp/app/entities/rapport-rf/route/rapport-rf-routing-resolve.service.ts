import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IRapportRF, RapportRF } from '../rapport-rf.model';
import { RapportRFService } from '../service/rapport-rf.service';

@Injectable({ providedIn: 'root' })
export class RapportRFRoutingResolveService implements Resolve<IRapportRF> {
  constructor(protected service: RapportRFService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRapportRF> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((rapportRF: HttpResponse<RapportRF>) => {
          if (rapportRF.body) {
            return of(rapportRF.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RapportRF());
  }
}
