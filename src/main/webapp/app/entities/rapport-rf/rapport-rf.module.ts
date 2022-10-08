import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RapportRFComponent } from './list/rapport-rf.component';
import { RapportRFDetailComponent } from './detail/rapport-rf-detail.component';
import { RapportRFUpdateComponent } from './update/rapport-rf-update.component';
import { RapportRFDeleteDialogComponent } from './delete/rapport-rf-delete-dialog.component';
import { RapportRFRoutingModule } from './route/rapport-rf-routing.module';

@NgModule({
  imports: [SharedModule, RapportRFRoutingModule],
  declarations: [RapportRFComponent, RapportRFDetailComponent, RapportRFUpdateComponent, RapportRFDeleteDialogComponent],
  entryComponents: [RapportRFDeleteDialogComponent],
})
export class RapportRFModule {}
