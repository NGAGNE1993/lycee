import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ExamenService } from '../service/examen.service';
import { IExamen, Examen } from '../examen.model';
import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { LyceesTechniquesService } from 'app/entities/lycees-techniques/service/lycees-techniques.service';
import { IDirecteurEtude } from 'app/entities/directeur-etude/directeur-etude.model';
import { DirecteurEtudeService } from 'app/entities/directeur-etude/service/directeur-etude.service';

import { ExamenUpdateComponent } from './examen-update.component';

describe('Examen Management Update Component', () => {
  let comp: ExamenUpdateComponent;
  let fixture: ComponentFixture<ExamenUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let examenService: ExamenService;
  let lyceesTechniquesService: LyceesTechniquesService;
  let directeurEtudeService: DirecteurEtudeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ExamenUpdateComponent],
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
      .overrideTemplate(ExamenUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ExamenUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    examenService = TestBed.inject(ExamenService);
    lyceesTechniquesService = TestBed.inject(LyceesTechniquesService);
    directeurEtudeService = TestBed.inject(DirecteurEtudeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call LyceesTechniques query and add missing value', () => {
      const examen: IExamen = { id: 456 };
      const lyceesTechniques: ILyceesTechniques = { id: 2976 };
      examen.lyceesTechniques = lyceesTechniques;

      const lyceesTechniquesCollection: ILyceesTechniques[] = [{ id: 6060 }];
      jest.spyOn(lyceesTechniquesService, 'query').mockReturnValue(of(new HttpResponse({ body: lyceesTechniquesCollection })));
      const additionalLyceesTechniques = [lyceesTechniques];
      const expectedCollection: ILyceesTechniques[] = [...additionalLyceesTechniques, ...lyceesTechniquesCollection];
      jest.spyOn(lyceesTechniquesService, 'addLyceesTechniquesToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ examen });
      comp.ngOnInit();

      expect(lyceesTechniquesService.query).toHaveBeenCalled();
      expect(lyceesTechniquesService.addLyceesTechniquesToCollectionIfMissing).toHaveBeenCalledWith(
        lyceesTechniquesCollection,
        ...additionalLyceesTechniques
      );
      expect(comp.lyceesTechniquesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call DirecteurEtude query and add missing value', () => {
      const examen: IExamen = { id: 456 };
      const directeur: IDirecteurEtude = { id: 47301 };
      examen.directeur = directeur;

      const directeurEtudeCollection: IDirecteurEtude[] = [{ id: 33466 }];
      jest.spyOn(directeurEtudeService, 'query').mockReturnValue(of(new HttpResponse({ body: directeurEtudeCollection })));
      const additionalDirecteurEtudes = [directeur];
      const expectedCollection: IDirecteurEtude[] = [...additionalDirecteurEtudes, ...directeurEtudeCollection];
      jest.spyOn(directeurEtudeService, 'addDirecteurEtudeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ examen });
      comp.ngOnInit();

      expect(directeurEtudeService.query).toHaveBeenCalled();
      expect(directeurEtudeService.addDirecteurEtudeToCollectionIfMissing).toHaveBeenCalledWith(
        directeurEtudeCollection,
        ...additionalDirecteurEtudes
      );
      expect(comp.directeurEtudesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const examen: IExamen = { id: 456 };
      const lyceesTechniques: ILyceesTechniques = { id: 15788 };
      examen.lyceesTechniques = lyceesTechniques;
      const directeur: IDirecteurEtude = { id: 74874 };
      examen.directeur = directeur;

      activatedRoute.data = of({ examen });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(examen));
      expect(comp.lyceesTechniquesSharedCollection).toContain(lyceesTechniques);
      expect(comp.directeurEtudesSharedCollection).toContain(directeur);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Examen>>();
      const examen = { id: 123 };
      jest.spyOn(examenService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ examen });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: examen }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(examenService.update).toHaveBeenCalledWith(examen);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Examen>>();
      const examen = new Examen();
      jest.spyOn(examenService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ examen });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: examen }));
      saveSubject.complete();

      // THEN
      expect(examenService.create).toHaveBeenCalledWith(examen);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Examen>>();
      const examen = { id: 123 };
      jest.spyOn(examenService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ examen });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(examenService.update).toHaveBeenCalledWith(examen);
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
  });
});
