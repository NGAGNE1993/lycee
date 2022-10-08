import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IFiliere, Filiere } from '../filiere.model';
import { FiliereService } from '../service/filiere.service';
import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { LyceesTechniquesService } from 'app/entities/lycees-techniques/service/lycees-techniques.service';
import { IDirecteurEtude } from 'app/entities/directeur-etude/directeur-etude.model';
import { DirecteurEtudeService } from 'app/entities/directeur-etude/service/directeur-etude.service';
import { Filieres } from 'app/entities/enumerations/filieres.model';
import { SANTEBIOLOGIECHIMIE } from 'app/entities/enumerations/santebiologiechimie.model';
import { ELEVAGE } from 'app/entities/enumerations/elevage.model';

@Component({
  selector: 'jhi-filiere-update',
  templateUrl: './filiere-update.component.html',
  styleUrls: ['./filiere-update.component.scss'],
})
export class FiliereUpdateComponent implements OnInit {
  isSaving = false;
  filieresValues = Object.keys(Filieres);
  sANTEBIOLOGIECHIMIEValues = Object.keys(SANTEBIOLOGIECHIMIE);
  eLEVAGEValues = Object.keys(ELEVAGE);

  lyceesTechniquesSharedCollection: ILyceesTechniques[] = [];
  directeurEtudesSharedCollection: IDirecteurEtude[] = [];

  editForm = this.fb.group({
    id: [],
    nomFiliere: [null, [Validators.required]],
    nomAutre: [],
    nomProgramme: [],
    autreAGR: [],
    nomProgrammeEl: [],
    autreEL: [],
    autreMIN: [],
    autreBAT: [],
    autreMECAN: [],
    autreMENU: [],
    autreAGRO: [],
    autreELECTRO: [],
    autreSTRUC: [],
    autreEC: [],
    autreCOM: [],
    autreIN: [],
    autreSAN: [],
    autreProgramme: [],
    lyceesTechniques: [],
    directeur: [],
  });

  constructor(
    protected filiereService: FiliereService,
    protected lyceesTechniquesService: LyceesTechniquesService,
    protected directeurEtudeService: DirecteurEtudeService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ filiere }) => {
      this.updateForm(filiere);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const filiere = this.createFromForm();
    if (filiere.id !== undefined) {
      this.subscribeToSaveResponse(this.filiereService.update(filiere));
    } else {
      this.subscribeToSaveResponse(this.filiereService.create(filiere));
    }
  }

  trackLyceesTechniquesById(_index: number, item: ILyceesTechniques): number {
    return item.id!;
  }

  trackDirecteurEtudeById(_index: number, item: IDirecteurEtude): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFiliere>>): void {
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

  protected updateForm(filiere: IFiliere): void {
    this.editForm.patchValue({
      id: filiere.id,
      nomFiliere: filiere.nomFiliere,
      nomAutre: filiere.nomAutre,
      nomProgramme: filiere.nomProgramme,
      autreAGR: filiere.autreAGR,
      nomProgrammeEl: filiere.nomProgrammeEl,
      autreEL: filiere.autreEL,
      autreMIN: filiere.autreMIN,
      autreBAT: filiere.autreBAT,
      autreMECAN: filiere.autreMECAN,
      autreMENU: filiere.autreMENU,
      autreAGRO: filiere.autreAGRO,
      autreELECTRO: filiere.autreELECTRO,
      autreSTRUC: filiere.autreSTRUC,
      autreEC: filiere.autreEC,
      autreCOM: filiere.autreCOM,
      autreIN: filiere.autreIN,
      autreSAN: filiere.autreSAN,
      autreProgramme: filiere.autreProgramme,
      lyceesTechniques: filiere.lyceesTechniques,
      directeur: filiere.directeur,
    });

    this.lyceesTechniquesSharedCollection = this.lyceesTechniquesService.addLyceesTechniquesToCollectionIfMissing(
      this.lyceesTechniquesSharedCollection,
      filiere.lyceesTechniques
    );
    this.directeurEtudesSharedCollection = this.directeurEtudeService.addDirecteurEtudeToCollectionIfMissing(
      this.directeurEtudesSharedCollection,
      filiere.directeur
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

  protected createFromForm(): IFiliere {
    return {
      ...new Filiere(),
      id: this.editForm.get(['id'])!.value,
      nomFiliere: this.editForm.get(['nomFiliere'])!.value,
      nomAutre: this.editForm.get(['nomAutre'])!.value,
      nomProgramme: this.editForm.get(['nomProgramme'])!.value,
      autreAGR: this.editForm.get(['autreAGR'])!.value,
      nomProgrammeEl: this.editForm.get(['nomProgrammeEl'])!.value,
      autreEL: this.editForm.get(['autreEL'])!.value,
      autreMIN: this.editForm.get(['autreMIN'])!.value,
      autreBAT: this.editForm.get(['autreBAT'])!.value,
      autreMECAN: this.editForm.get(['autreMECAN'])!.value,
      autreMENU: this.editForm.get(['autreMENU'])!.value,
      autreAGRO: this.editForm.get(['autreAGRO'])!.value,
      autreELECTRO: this.editForm.get(['autreELECTRO'])!.value,
      autreSTRUC: this.editForm.get(['autreSTRUC'])!.value,
      autreEC: this.editForm.get(['autreEC'])!.value,
      autreCOM: this.editForm.get(['autreCOM'])!.value,
      autreIN: this.editForm.get(['autreIN'])!.value,
      autreSAN: this.editForm.get(['autreSAN'])!.value,
      autreProgramme: this.editForm.get(['autreProgramme'])!.value,
      lyceesTechniques: this.editForm.get(['lyceesTechniques'])!.value,
      directeur: this.editForm.get(['directeur'])!.value,
    };
  }
}
