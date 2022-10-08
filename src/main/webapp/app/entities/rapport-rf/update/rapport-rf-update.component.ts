import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IRapportRF, RapportRF } from '../rapport-rf.model';
import { RapportRFService } from '../service/rapport-rf.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { LyceesTechniquesService } from 'app/entities/lycees-techniques/service/lycees-techniques.service';
import { IProviseur } from 'app/entities/proviseur/proviseur.model';
import { ProviseurService } from 'app/entities/proviseur/service/proviseur.service';
import { TypeRapport } from 'app/entities/enumerations/type-rapport.model';

@Component({
  selector: 'jhi-rapport-rf-update',
  templateUrl: './rapport-rf-update.component.html',
  styleUrls: ['./rapport-rf-update.component.scss'],
})
export class RapportRFUpdateComponent implements OnInit {
  isSaving = false;
  typeRapportValues = Object.keys(TypeRapport);

  lyceesTechniquesSharedCollection: ILyceesTechniques[] = [];
  proviseursSharedCollection: IProviseur[] = [];

  editForm = this.fb.group({
    id: [],
    typeRaport: [],
    rentre: [],
    rentreContentType: [],
    fin: [],
    finContentType: [],
    lyceesTechniques: [],
    proviseur: [],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected rapportRFService: RapportRFService,
    protected lyceesTechniquesService: LyceesTechniquesService,
    protected proviseurService: ProviseurService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rapportRF }) => {
      this.updateForm(rapportRF);

      this.loadRelationshipsOptions();
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('projetDeBaseJhipsterApp.error', { message: err.message })),
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rapportRF = this.createFromForm();
    if (rapportRF.id !== undefined) {
      this.subscribeToSaveResponse(this.rapportRFService.update(rapportRF));
    } else {
      this.subscribeToSaveResponse(this.rapportRFService.create(rapportRF));
    }
  }

  trackLyceesTechniquesById(_index: number, item: ILyceesTechniques): number {
    return item.id!;
  }

  trackProviseurById(_index: number, item: IProviseur): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRapportRF>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(rapportRF: IRapportRF): void {
    this.editForm.patchValue({
      id: rapportRF.id,
      typeRaport: rapportRF.typeRaport,
      rentre: rapportRF.rentre,
      rentreContentType: rapportRF.rentreContentType,
      fin: rapportRF.fin,
      finContentType: rapportRF.finContentType,
      lyceesTechniques: rapportRF.lyceesTechniques,
      proviseur: rapportRF.proviseur,
    });

    this.lyceesTechniquesSharedCollection = this.lyceesTechniquesService.addLyceesTechniquesToCollectionIfMissing(
      this.lyceesTechniquesSharedCollection,
      rapportRF.lyceesTechniques
    );
    this.proviseursSharedCollection = this.proviseurService.addProviseurToCollectionIfMissing(
      this.proviseursSharedCollection,
      rapportRF.proviseur
    );
  }

  protected loadRelationshipsOptions(): void {
    this.lyceesTechniquesService
      .query()
      .pipe(map((res: HttpResponse<ILyceesTechniques[]>) => res.body ?? []))
      .pipe(
        map((lyceesTechniques: ILyceesTechniques[]) =>
          this.lyceesTechniquesService.addLyceesTechniquesToCollectionIfMissing(
            lyceesTechniques,
            this.editForm.get('lyceesTechniques')!.value
          )
        )
      )
      .subscribe((lyceesTechniques: ILyceesTechniques[]) => (this.lyceesTechniquesSharedCollection = lyceesTechniques));

    this.proviseurService
      .query()
      .pipe(map((res: HttpResponse<IProviseur[]>) => res.body ?? []))
      .pipe(
        map((proviseurs: IProviseur[]) =>
          this.proviseurService.addProviseurToCollectionIfMissing(proviseurs, this.editForm.get('proviseur')!.value)
        )
      )
      .subscribe((proviseurs: IProviseur[]) => (this.proviseursSharedCollection = proviseurs));
  }

  protected createFromForm(): IRapportRF {
    return {
      ...new RapportRF(),
      id: this.editForm.get(['id'])!.value,
      typeRaport: this.editForm.get(['typeRaport'])!.value,
      rentreContentType: this.editForm.get(['rentreContentType'])!.value,
      rentre: this.editForm.get(['rentre'])!.value,
      finContentType: this.editForm.get(['finContentType'])!.value,
      fin: this.editForm.get(['fin'])!.value,
      lyceesTechniques: this.editForm.get(['lyceesTechniques'])!.value,
      proviseur: this.editForm.get(['proviseur'])!.value,
    };
  }
}
