import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IRapportRF } from '../rapport-rf.model';
import { RapportRFService } from '../service/rapport-rf.service';

@Component({
  templateUrl: './rapport-rf-delete-dialog.component.html',
})
export class RapportRFDeleteDialogComponent {
  rapportRF?: IRapportRF;

  constructor(protected rapportRFService: RapportRFService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rapportRFService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
