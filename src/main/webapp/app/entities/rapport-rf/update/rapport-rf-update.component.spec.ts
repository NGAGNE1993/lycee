import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { RapportRFService } from '../service/rapport-rf.service';
import { IRapportRF, RapportRF } from '../rapport-rf.model';
import { ILyceesTechniques } from 'app/entities/lycees-techniques/lycees-techniques.model';
import { LyceesTechniquesService } from 'app/entities/lycees-techniques/service/lycees-techniques.service';
import { IProviseur } from 'app/entities/proviseur/proviseur.model';
import { ProviseurService } from 'app/entities/proviseur/service/proviseur.service';

import { RapportRFUpdateComponent } from './rapport-rf-update.component';

describe('RapportRF Management Update Component', () => {
  let comp: RapportRFUpdateComponent;
  let fixture: ComponentFixture<RapportRFUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let rapportRFService: RapportRFService;
  let lyceesTechniquesService: LyceesTechniquesService;
  let proviseurService: ProviseurService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [RapportRFUpdateComponent],
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
      .overrideTemplate(RapportRFUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RapportRFUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    rapportRFService = TestBed.inject(RapportRFService);
    lyceesTechniquesService = TestBed.inject(LyceesTechniquesService);
    proviseurService = TestBed.inject(ProviseurService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call LyceesTechniques query and add missing value', () => {
      const rapportRF: IRapportRF = { id: 456 };
      const lyceesTechniques: ILyceesTechniques = { id: 63683 };
      rapportRF.lyceesTechniques = lyceesTechniques;

      const lyceesTechniquesCollection: ILyceesTechniques[] = [{ id: 87338 }];
      jest.spyOn(lyceesTechniquesService, 'query').mockReturnValue(of(new HttpResponse({ body: lyceesTechniquesCollection })));
      const additionalLyceesTechniques = [lyceesTechniques];
      const expectedCollection: ILyceesTechniques[] = [...additionalLyceesTechniques, ...lyceesTechniquesCollection];
      jest.spyOn(lyceesTechniquesService, 'addLyceesTechniquesToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ rapportRF });
      comp.ngOnInit();

      expect(lyceesTechniquesService.query).toHaveBeenCalled();
      expect(lyceesTechniquesService.addLyceesTechniquesToCollectionIfMissing).toHaveBeenCalledWith(
        lyceesTechniquesCollection,
        ...additionalLyceesTechniques
      );
      expect(comp.lyceesTechniquesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Proviseur query and add missing value', () => {
      const rapportRF: IRapportRF = { id: 456 };
      const proviseur: IProviseur = { id: 57584 };
      rapportRF.proviseur = proviseur;

      const proviseurCollection: IProviseur[] = [{ id: 36834 }];
      jest.spyOn(proviseurService, 'query').mockReturnValue(of(new HttpResponse({ body: proviseurCollection })));
      const additionalProviseurs = [proviseur];
      const expectedCollection: IProviseur[] = [...additionalProviseurs, ...proviseurCollection];
      jest.spyOn(proviseurService, 'addProviseurToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ rapportRF });
      comp.ngOnInit();

      expect(proviseurService.query).toHaveBeenCalled();
      expect(proviseurService.addProviseurToCollectionIfMissing).toHaveBeenCalledWith(proviseurCollection, ...additionalProviseurs);
      expect(comp.proviseursSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const rapportRF: IRapportRF = { id: 456 };
      const lyceesTechniques: ILyceesTechniques = { id: 81112 };
      rapportRF.lyceesTechniques = lyceesTechniques;
      const proviseur: IProviseur = { id: 25970 };
      rapportRF.proviseur = proviseur;

      activatedRoute.data = of({ rapportRF });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(rapportRF));
      expect(comp.lyceesTechniquesSharedCollection).toContain(lyceesTechniques);
      expect(comp.proviseursSharedCollection).toContain(proviseur);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<RapportRF>>();
      const rapportRF = { id: 123 };
      jest.spyOn(rapportRFService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rapportRF });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: rapportRF }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(rapportRFService.update).toHaveBeenCalledWith(rapportRF);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<RapportRF>>();
      const rapportRF = new RapportRF();
      jest.spyOn(rapportRFService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rapportRF });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: rapportRF }));
      saveSubject.complete();

      // THEN
      expect(rapportRFService.create).toHaveBeenCalledWith(rapportRF);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<RapportRF>>();
      const rapportRF = { id: 123 };
      jest.spyOn(rapportRFService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rapportRF });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(rapportRFService.update).toHaveBeenCalledWith(rapportRF);
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

    describe('trackProviseurById', () => {
      it('Should return tracked Proviseur primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackProviseurById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
