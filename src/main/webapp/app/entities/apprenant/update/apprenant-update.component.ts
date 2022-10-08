import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IApprenant, Apprenant } from '../apprenant.model';
import { ApprenantService } from '../service/apprenant.service';
import { ISerie } from 'app/entities/serie/serie.model';
import { SerieService } from 'app/entities/serie/service/serie.service';
import { IFiliere } from 'app/entities/filiere/filiere.model';
import { FiliereService } from 'app/entities/filiere/service/filiere.service';
import { INiveauxEtudes } from 'app/entities/niveaux-etudes/niveaux-etudes.model';
import { NiveauxEtudesService } from 'app/entities/niveaux-etudes/service/niveaux-etudes.service';
import { INiveauxCalification } from 'app/entities/niveaux-calification/niveaux-calification.model';
import { NiveauxCalificationService } from 'app/entities/niveaux-calification/service/niveaux-calification.service';
import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { LyceesTechniquesService } from 'app/entities/lycees-techniques/service/lycees-techniques.service';
import { IDirecteurEtude } from 'app/entities/directeur-etude/directeur-etude.model';
import { DirecteurEtudeService } from 'app/entities/directeur-etude/service/directeur-etude.service';
import { Genre } from 'app/entities/enumerations/genre.model';
import { Option } from 'app/entities/enumerations/option.model';
import { Situation } from 'app/entities/enumerations/situation.model';

@Component({
  selector: 'jhi-apprenant-update',
  templateUrl: './apprenant-update.component.html',
  styleUrls: ['./apprenant-update.component.scss'],
})
export class ApprenantUpdateComponent implements OnInit {
  isSaving = false;
  genreValues = Object.keys(Genre);
  optionValues = Object.keys(Option);
  situationValues = Object.keys(Situation);

  seriesCollection: ISerie[] = [];
  filieresCollection: IFiliere[] = [];
  niveauxEtudesCollection: INiveauxEtudes[] = [];
  niveauxCalificationsCollection: INiveauxCalification[] = [];
  lyceesTechniquesSharedCollection: ILyceesTechniques[] = [];
  directeurEtudesSharedCollection: IDirecteurEtude[] = [];

  editForm = this.fb.group({
    id: [],
    nomPrenom: [null, [Validators.required]],
    dateNaissance: [null, [Validators.required]],
    lieuNaissance: [null, [Validators.required]],
    telephone: [null, [Validators.required]],
    adresse: [null, [Validators.required]],
    sexe: [null, [Validators.required]],
    anneeScolaire: [null, [Validators.required]],
    option: [],
    situationMatrimoniale: [null, [Validators.required]],
    tuteur: [null, [Validators.required]],
    contactTuteur: [null, [Validators.required]],
    serie: [],
    filiere: [],
    niveauxEtudes: [],
    niveauxCalification: [],
    lyceesTechniques: [],
    directeur: [],
  });

  constructor(
    protected apprenantService: ApprenantService,
    protected serieService: SerieService,
    protected filiereService: FiliereService,
    protected niveauxEtudesService: NiveauxEtudesService,
    protected niveauxCalificationService: NiveauxCalificationService,
    protected lyceesTechniquesService: LyceesTechniquesService,
    protected directeurEtudeService: DirecteurEtudeService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ apprenant }) => {
      this.updateForm(apprenant);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const apprenant = this.createFromForm();
    if (apprenant.id !== undefined) {
      this.subscribeToSaveResponse(this.apprenantService.update(apprenant));
    } else {
      this.subscribeToSaveResponse(this.apprenantService.create(apprenant));
    }
  }

  trackSerieById(_index: number, item: ISerie): number {
    return item.id!;
  }

  trackFiliereById(_index: number, item: IFiliere): number {
    return item.id!;
  }

  trackNiveauxEtudesById(_index: number, item: INiveauxEtudes): number {
    return item.id!;
  }

  trackNiveauxCalificationById(_index: number, item: INiveauxCalification): number {
    return item.id!;
  }

  trackLyceesTechniquesById(_index: number, item: ILyceesTechniques): number {
    return item.id!;
  }

  trackDirecteurEtudeById(_index: number, item: IDirecteurEtude): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApprenant>>): void {
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

  protected updateForm(apprenant: IApprenant): void {
    this.editForm.patchValue({
      id: apprenant.id,
      nomPrenom: apprenant.nomPrenom,
      dateNaissance: apprenant.dateNaissance,
      lieuNaissance: apprenant.lieuNaissance,
      telephone: apprenant.telephone,
      adresse: apprenant.adresse,
      sexe: apprenant.sexe,
      anneeScolaire: apprenant.anneeScolaire,
      option: apprenant.option,
      situationMatrimoniale: apprenant.situationMatrimoniale,
      tuteur: apprenant.tuteur,
      contactTuteur: apprenant.contactTuteur,
      serie: apprenant.serie,
      filiere: apprenant.filiere,
      niveauxEtudes: apprenant.niveauxEtudes,
      niveauxCalification: apprenant.niveauxCalification,
      lyceesTechniques: apprenant.lyceesTechniques,
      directeur: apprenant.directeur,
    });

    this.seriesCollection = this.serieService.addSerieToCollectionIfMissing(this.seriesCollection, apprenant.serie);
    this.filieresCollection = this.filiereService.addFiliereToCollectionIfMissing(this.filieresCollection, apprenant.filiere);
    this.niveauxEtudesCollection = this.niveauxEtudesService.addNiveauxEtudesToCollectionIfMissing(
      this.niveauxEtudesCollection,
      apprenant.niveauxEtudes
    );
    this.niveauxCalificationsCollection = this.niveauxCalificationService.addNiveauxCalificationToCollectionIfMissing(
      this.niveauxCalificationsCollection,
      apprenant.niveauxCalification
    );
    this.lyceesTechniquesSharedCollection = this.lyceesTechniquesService.addLyceesTechniquesToCollectionIfMissing(
      this.lyceesTechniquesSharedCollection,
      apprenant.lyceesTechniques
    );
    this.directeurEtudesSharedCollection = this.directeurEtudeService.addDirecteurEtudeToCollectionIfMissing(
      this.directeurEtudesSharedCollection,
      apprenant.directeur
    );
  }

  protected loadRelationshipsOptions(): void {
    this.serieService
      .query({ filter: 'apprenant-is-null' })
      .pipe(map((res: HttpResponse<ISerie[]>) => res.body ?? []))
      .pipe(map((series: ISerie[]) => this.serieService.addSerieToCollectionIfMissing(series, this.editForm.get('serie')!.value)))
      .subscribe((series: ISerie[]) => (this.seriesCollection = series));

    this.filiereService
      .query({ filter: 'apprenant-is-null' })
      .pipe(map((res: HttpResponse<IFiliere[]>) => res.body ?? []))
      .pipe(
        map((filieres: IFiliere[]) => this.filiereService.addFiliereToCollectionIfMissing(filieres, this.editForm.get('filiere')!.value))
      )
      .subscribe((filieres: IFiliere[]) => (this.filieresCollection = filieres));

    this.niveauxEtudesService
      .query({ filter: 'apprenant-is-null' })
      .pipe(map((res: HttpResponse<INiveauxEtudes[]>) => res.body ?? []))
      .pipe(
        map((niveauxEtudes: INiveauxEtudes[]) =>
          this.niveauxEtudesService.addNiveauxEtudesToCollectionIfMissing(niveauxEtudes, this.editForm.get('niveauxEtudes')!.value)
        )
      )
      .subscribe((niveauxEtudes: INiveauxEtudes[]) => (this.niveauxEtudesCollection = niveauxEtudes));

    this.niveauxCalificationService
      .query({ filter: 'apprenant-is-null' })
      .pipe(map((res: HttpResponse<INiveauxCalification[]>) => res.body ?? []))
      .pipe(
        map((niveauxCalifications: INiveauxCalification[]) =>
          this.niveauxCalificationService.addNiveauxCalificationToCollectionIfMissing(
            niveauxCalifications,
            this.editForm.get('niveauxCalification')!.value
          )
        )
      )
      .subscribe((niveauxCalifications: INiveauxCalification[]) => (this.niveauxCalificationsCollection = niveauxCalifications));

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

  protected createFromForm(): IApprenant {
    return {
      ...new Apprenant(),
      id: this.editForm.get(['id'])!.value,
      nomPrenom: this.editForm.get(['nomPrenom'])!.value,
      dateNaissance: this.editForm.get(['dateNaissance'])!.value,
      lieuNaissance: this.editForm.get(['lieuNaissance'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      sexe: this.editForm.get(['sexe'])!.value,
      anneeScolaire: this.editForm.get(['anneeScolaire'])!.value,
      option: this.editForm.get(['option'])!.value,
      situationMatrimoniale: this.editForm.get(['situationMatrimoniale'])!.value,
      tuteur: this.editForm.get(['tuteur'])!.value,
      contactTuteur: this.editForm.get(['contactTuteur'])!.value,
      serie: this.editForm.get(['serie'])!.value,
      filiere: this.editForm.get(['filiere'])!.value,
      niveauxEtudes: this.editForm.get(['niveauxEtudes'])!.value,
      niveauxCalification: this.editForm.get(['niveauxCalification'])!.value,
      lyceesTechniques: this.editForm.get(['lyceesTechniques'])!.value,
      directeur: this.editForm.get(['directeur'])!.value,
    };
  }
}
