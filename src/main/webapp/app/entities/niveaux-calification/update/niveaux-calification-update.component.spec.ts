import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { NiveauxCalificationService } from '../service/niveaux-calification.service';
import { INiveauxCalification, NiveauxCalification } from '../niveaux-calification.model';
import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { LyceesTechniquesService } from 'app/entities/lycees-techniques/service/lycees-techniques.service';
import { IDirecteurEtude } from 'app/entities/directeur-etude/directeur-etude.model';
import { DirecteurEtudeService } from 'app/entities/directeur-etude/service/directeur-etude.service';
import { IFiliere } from 'app/entities/filiere/filiere.model';
import { FiliereService } from 'app/entities/filiere/service/filiere.service';

import { NiveauxCalificationUpdateComponent } from './niveaux-calification-update.component';

describe('NiveauxCalification Management Update Component', () => {
  let comp: NiveauxCalificationUpdateComponent;
  let fixture: ComponentFixture<NiveauxCalificationUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let niveauxCalificationService: NiveauxCalificationService;
  let lyceesTechniquesService: LyceesTechniquesService;
  let directeurEtudeService: DirecteurEtudeService;
  let filiereService: FiliereService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [NiveauxCalificationUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(NiveauxCalificationUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(NiveauxCalificationUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    niveauxCalificationService = TestBed.inject(NiveauxCalificationService);
    lyceesTechniquesService = TestBed.inject(LyceesTechniquesService);
    directeurEtudeService = TestBed.inject(DirecteurEtudeService);
    filiereService = TestBed.inject(FiliereService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call LyceesTechniques query and add missing value', () => {
      const niveauxCalification: INiveauxCalification = { id: 456 };
      const lyceesTechniques: ILyceesTechniques = { id: 80180 };
      niveauxCalification.lyceesTechniques = lyceesTechniques;

      const lyceesTechniquesCollection: ILyceesTechniques[] = [{ id: 96547 }];
      jest.spyOn(lyceesTechniquesService, 'query').mockReturnValue(of(new HttpResponse({ body: lyceesTechniquesCollection })));
      const additionalLyceesTechniques = [lyceesTechniques];
      const expectedCollection: ILyceesTechniques[] = [...additionalLyceesTechniques, ...lyceesTechniquesCollection];
      jest.spyOn(lyceesTechniquesService, 'addLyceesTechniquesToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ niveauxCalification });
      comp.ngOnInit();

      expect(lyceesTechniquesService.query).toHaveBeenCalled();
      expect(lyceesTechniquesService.addLyceesTechniquesToCollectionIfMissing).toHaveBeenCalledWith(
        lyceesTechniquesCollection,
        ...additionalLyceesTechniques
      );
      expect(comp.lyceesTechniquesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call DirecteurEtude query and add missing value', () => {
      const niveauxCalification: INiveauxCalification = { id: 456 };
      const directeur: IDirecteurEtude = { id: 39159 };
      niveauxCalification.directeur = directeur;

      const directeurEtudeCollection: IDirecteurEtude[] = [{ id: 14700 }];
      jest.spyOn(directeurEtudeService, 'query').mockReturnValue(of(new HttpResponse({ body: directeurEtudeCollection })));
      const additionalDirecteurEtudes = [directeur];
      const expectedCollection: IDirecteurEtude[] = [...additionalDirecteurEtudes, ...directeurEtudeCollection];
      jest.spyOn(directeurEtudeService, 'addDirecteurEtudeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ niveauxCalification });
      comp.ngOnInit();

      expect(directeurEtudeService.query).toHaveBeenCalled();
      expect(directeurEtudeService.addDirecteurEtudeToCollectionIfMissing).toHaveBeenCalledWith(
        directeurEtudeCollection,
        ...additionalDirecteurEtudes
      );
      expect(comp.directeurEtudesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Filiere query and add missing value', () => {
      const niveauxCalification: INiveauxCalification = { id: 456 };
      const filiere: IFiliere = { id: 18789 };
      niveauxCalification.filiere = filiere;

      const filiereCollection: IFiliere[] = [{ id: 39996 }];
      jest.spyOn(filiereService, 'query').mockReturnValue(of(new HttpResponse({ body: filiereCollection })));
      const additionalFilieres = [filiere];
      const expectedCollection: IFiliere[] = [...additionalFilieres, ...filiereCollection];
      jest.spyOn(filiereService, 'addFiliereToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ niveauxCalification });
      comp.ngOnInit();

      expect(filiereService.query).toHaveBeenCalled();
      expect(filiereService.addFiliereToCollectionIfMissing).toHaveBeenCalledWith(filiereCollection, ...additionalFilieres);
      expect(comp.filieresSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const niveauxCalification: INiveauxCalification = { id: 456 };
      const lyceesTechniques: ILyceesTechniques = { id: 14942 };
      niveauxCalification.lyceesTechniques = lyceesTechniques;
      const directeur: IDirecteurEtude = { id: 61080 };
      niveauxCalification.directeur = directeur;
      const filiere: IFiliere = { id: 23418 };
      niveauxCalification.filiere = filiere;

      activatedRoute.data = of({ niveauxCalification });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(niveauxCalification));
      expect(comp.lyceesTechniquesSharedCollection).toContain(lyceesTechniques);
      expect(comp.directeurEtudesSharedCollection).toContain(directeur);
      expect(comp.filieresSharedCollection).toContain(filiere);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<NiveauxCalification>>();
      const niveauxCalification = { id: 123 };
      jest.spyOn(niveauxCalificationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ niveauxCalification });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: niveauxCalification }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(niveauxCalificationService.update).toHaveBeenCalledWith(niveauxCalification);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<NiveauxCalification>>();
      const niveauxCalification = new NiveauxCalification();
      jest.spyOn(niveauxCalificationService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ niveauxCalification });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: niveauxCalification }));
      saveSubject.complete();

      // THEN
      expect(niveauxCalificationService.create).toHaveBeenCalledWith(niveauxCalification);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<NiveauxCalification>>();
      const niveauxCalification = { id: 123 };
      jest.spyOn(niveauxCalificationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ niveauxCalification });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(niveauxCalificationService.update).toHaveBeenCalledWith(niveauxCalification);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackLyceesTechniquesById', () => {
      it('Should return tracked LyceesTechniques primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackLyceesTechniquesById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackDirecteurEtudeById', () => {
      it('Should return tracked DirecteurEtude primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackDirecteurEtudeById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackFiliereById', () => {
      it('Should return tracked Filiere primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackFiliereById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
