import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { INiveauxCalification, NiveauxCalification } from '../niveaux-calification.model';
import { NiveauxCalificationService } from '../service/niveaux-calification.service';
import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { LyceesTechniquesService } from 'app/entities/lycees-techniques/service/lycees-techniques.service';
import { IDirecteurEtude } from 'app/entities/directeur-etude/directeur-etude.model';
import { DirecteurEtudeService } from 'app/entities/directeur-etude/service/directeur-etude.service';
import { IFiliere } from 'app/entities/filiere/filiere.model';
import { FiliereService } from 'app/entities/filiere/service/filiere.service';
import { NiveauQualif } from 'app/entities/enumerations/niveau-qualif.model';

@Component({
  selector: 'jhi-niveaux-calification-update',
  templateUrl: './niveaux-calification-update.component.html',
  styleUrls: ['./niveaux-calification-update.component.scss'],
})
export class NiveauxCalificationUpdateComponent implements OnInit {
  isSaving = false;
  niveauQualifValues = Object.keys(NiveauQualif);

  lyceesTechniquesSharedCollection: ILyceesTechniques[] = [];
  directeurEtudesSharedCollection: IDirecteurEtude[] = [];
  filieresSharedCollection: IFiliere[] = [];

  editForm = this.fb.group({
    id: [],
    niveauCalification: [null, [Validators.required]],
    typeNiveauCalification: [],
    lyceesTechniques: [],
    directeur: [],
    filiere: [],
  });

  constructor(
    protected niveauxCalificationService: NiveauxCalificationService,
    protected lyceesTechniquesService: LyceesTechniquesService,
    protected directeurEtudeService: DirecteurEtudeService,
    protected filiereService: FiliereService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ niveauxCalification }) => {
      this.updateForm(niveauxCalification);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const niveauxCalification = this.createFromForm();
    if (niveauxCalification.id !== undefined) {
      this.subscribeToSaveResponse(this.niveauxCalificationService.update(niveauxCalification));
    } else {
      this.subscribeToSaveResponse(this.niveauxCalificationService.create(niveauxCalification));
    }
  }

  trackLyceesTechniquesById(_index: number, item: ILyceesTechniques): number {
    return item.id!;
  }

  trackDirecteurEtudeById(_index: number, item: IDirecteurEtude): number {
    return item.id!;
  }

  trackFiliereById(_index: number, item: IFiliere): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INiveauxCalification>>): void {
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

  protected updateForm(niveauxCalification: INiveauxCalification): void {
    this.editForm.patchValue({
      id: niveauxCalification.id,
      niveauCalification: niveauxCalification.niveauCalification,
      typeNiveauCalification: niveauxCalification.typeNiveauCalification,
      lyceesTechniques: niveauxCalification.lyceesTechniques,
      directeur: niveauxCalification.directeur,
      filiere: niveauxCalification.filiere,
    });

    this.lyceesTechniquesSharedCollection = this.lyceesTechniquesService.addLyceesTechniquesToCollectionIfMissing(
      this.lyceesTechniquesSharedCollection,
      niveauxCalification.lyceesTechniques
    );
    this.directeurEtudesSharedCollection = this.directeurEtudeService.addDirecteurEtudeToCollectionIfMissing(
      this.directeurEtudesSharedCollection,
      niveauxCalification.directeur
    );
    this.filieresSharedCollection = this.filiereService.addFiliereToCollectionIfMissing(
      this.filieresSharedCollection,
      niveauxCalification.filiere
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

    this.filiereService
      .query()
      .pipe(map((res: HttpResponse<IFiliere[]>) => res.body ?? []))
      .pipe(
        map((filieres: IFiliere[]) => this.filiereService.addFiliereToCollectionIfMissing(filieres, this.editForm.get('filiere')!.value))
      )
      .subscribe((filieres: IFiliere[]) => (this.filieresSharedCollection = filieres));
  }

  protected createFromForm(): INiveauxCalification {
    return {
      ...new NiveauxCalification(),
      id: this.editForm.get(['id'])!.value,
      niveauCalification: this.editForm.get(['niveauCalification'])!.value,
      typeNiveauCalification: this.editForm.get(['typeNiveauCalification'])!.value,
      lyceesTechniques: this.editForm.get(['lyceesTechniques'])!.value,
      directeur: this.editForm.get(['directeur'])!.value,
      filiere: this.editForm.get(['filiere'])!.value,
    };
  }
}
