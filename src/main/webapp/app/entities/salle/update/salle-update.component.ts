import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { ISalle, Salle } from '../salle.model';
import { SalleService } from '../service/salle.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { LyceesTechniquesService } from 'app/entities/lycees-techniques/service/lycees-techniques.service';
import { IDirecteurEtude } from 'app/entities/directeur-etude/directeur-etude.model';
import { DirecteurEtudeService } from 'app/entities/directeur-etude/service/directeur-etude.service';
import { Specialise } from 'app/entities/enumerations/specialise.model';
import { Cdi } from 'app/entities/enumerations/cdi.model';
import { Gym } from 'app/entities/enumerations/gym.model';
import { TerrainSport } from 'app/entities/enumerations/terrain-sport.model';

@Component({
  selector: 'jhi-salle-update',
  templateUrl: './salle-update.component.html',
  styleUrls: ['./salle-update.component.scss'],
})
export class SalleUpdateComponent implements OnInit {
  isSaving = false;
  specialiseValues = Object.keys(Specialise);
  cdiValues = Object.keys(Cdi);
  gymValues = Object.keys(Gym);
  terrainSportValues = Object.keys(TerrainSport);

  lyceesTechniquesSharedCollection: ILyceesTechniques[] = [];
  directeurEtudesSharedCollection: IDirecteurEtude[] = [];

  editForm = this.fb.group({
    id: [],
    nombreSalleclasse: [null, [Validators.required]],
    nombreAteliers: [null, [Validators.required]],
    specialiase: [],
    nombreSalleSpecialise: [null, [Validators.required]],
    justificationSalleSpe: [],
    cdi: [],
    nombreCDI: [],
    justifiactifSalleCDI: [],
    gym: [],
    terrainSport: [],
    autreSalle: [],
    lyceesTechniques: [],
    directeur: [],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected salleService: SalleService,
    protected lyceesTechniquesService: LyceesTechniquesService,
    protected directeurEtudeService: DirecteurEtudeService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ salle }) => {
      this.updateForm(salle);

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
    const salle = this.createFromForm();
    if (salle.id !== undefined) {
      this.subscribeToSaveResponse(this.salleService.update(salle));
    } else {
      this.subscribeToSaveResponse(this.salleService.create(salle));
    }
  }

  trackLyceesTechniquesById(_index: number, item: ILyceesTechniques): number {
    return item.id!;
  }

  trackDirecteurEtudeById(_index: number, item: IDirecteurEtude): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISalle>>): void {
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

  protected updateForm(salle: ISalle): void {
    this.editForm.patchValue({
      id: salle.id,
      nombreSalleclasse: salle.nombreSalleclasse,
      nombreAteliers: salle.nombreAteliers,
      specialiase: salle.specialiase,
      nombreSalleSpecialise: salle.nombreSalleSpecialise,
      justificationSalleSpe: salle.justificationSalleSpe,
      cdi: salle.cdi,
      nombreCDI: salle.nombreCDI,
      justifiactifSalleCDI: salle.justifiactifSalleCDI,
      gym: salle.gym,
      terrainSport: salle.terrainSport,
      autreSalle: salle.autreSalle,
      lyceesTechniques: salle.lyceesTechniques,
      directeur: salle.directeur,
    });

    this.lyceesTechniquesSharedCollection = this.lyceesTechniquesService.addLyceesTechniquesToCollectionIfMissing(
      this.lyceesTechniquesSharedCollection,
      salle.lyceesTechniques
    );
    this.directeurEtudesSharedCollection = this.directeurEtudeService.addDirecteurEtudeToCollectionIfMissing(
      this.directeurEtudesSharedCollection,
      salle.directeur
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

    this.directeurEtudeService
      .query()
      .pipe(map((res: HttpResponse<IDirecteurEtude[]>) => res.body ?? []))
      .pipe(
        map((directeurEtudes: IDirecteurEtude[]) =>
          this.directeurEtudeService.addDirecteurEtudeToCollectionIfMissing(directeurEtudes, this.editForm.get('directeur')!.value)
        )
      )
      .subscribe((directeurEtudes: IDirecteurEtude[]) => (this.directeurEtudesSharedCollection = directeurEtudes));
  }

  protected createFromForm(): ISalle {
    return {
      ...new Salle(),
      id: this.editForm.get(['id'])!.value,
      nombreSalleclasse: this.editForm.get(['nombreSalleclasse'])!.value,
      nombreAteliers: this.editForm.get(['nombreAteliers'])!.value,
      specialiase: this.editForm.get(['specialiase'])!.value,
      nombreSalleSpecialise: this.editForm.get(['nombreSalleSpecialise'])!.value,
      justificationSalleSpe: this.editForm.get(['justificationSalleSpe'])!.value,
      cdi: this.editForm.get(['cdi'])!.value,
      nombreCDI: this.editForm.get(['nombreCDI'])!.value,
      justifiactifSalleCDI: this.editForm.get(['justifiactifSalleCDI'])!.value,
      gym: this.editForm.get(['gym'])!.value,
      terrainSport: this.editForm.get(['terrainSport'])!.value,
      autreSalle: this.editForm.get(['autreSalle'])!.value,
      lyceesTechniques: this.editForm.get(['lyceesTechniques'])!.value,
      directeur: this.editForm.get(['directeur'])!.value,
    };
  }
}
