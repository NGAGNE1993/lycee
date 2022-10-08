import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { RapportRFComponent } from '../list/rapport-rf.component';
import { RapportRFDetailComponent } from '../detail/rapport-rf-detail.component';
import { RapportRFUpdateComponent } from '../update/rapport-rf-update.component';
import { RapportRFRoutingResolveService } from './rapport-rf-routing-resolve.service';

const rapportRFRoute: Routes = [
  {
    path: '',
    component: RapportRFComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RapportRFDetailComponent,
    resolve: {
      rapportRF: RapportRFRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RapportRFUpdateComponent,
    resolve: {
      rapportRF: RapportRFRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RapportRFUpdateComponent,
    resolve: {
      rapportRF: RapportRFRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(rapportRFRoute)],
  exports: [RouterModule],
})
export class RapportRFRoutingModule {}
