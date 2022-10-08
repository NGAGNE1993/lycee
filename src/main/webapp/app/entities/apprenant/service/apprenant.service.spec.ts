import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_FORMAT } from 'app/config/input.constants';
import { Genre } from 'app/entities/enumerations/genre.model';
import { Option } from 'app/entities/enumerations/option.model';
import { Situation } from 'app/entities/enumerations/situation.model';
import { IApprenant, Apprenant } from '../apprenant.model';

import { ApprenantService } from './apprenant.service';

describe('Apprenant Service', () => {
  let service: ApprenantService;
  let httpMock: HttpTestingController;
  let elemDefault: IApprenant;
  let expectedResult: IApprenant | IApprenant[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ApprenantService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      nomPrenom: 'AAAAAAA',
      dateNaissance: currentDate,
      lieuNaissance: 'AAAAAAA',
      telephone: 'AAAAAAA',
      adresse: 'AAAAAAA',
      sexe: Genre.F,
      anneeScolaire: currentDate,
      option: Option.ENSEIGNEMENT_TECHNIQUE,
      situationMatrimoniale: Situation.MARIE,
      tuteur: 'AAAAAAA',
      contactTuteur: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          dateNaissance: currentDate.format(DATE_FORMAT),
          anneeScolaire: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Apprenant', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          dateNaissance: currentDate.format(DATE_FORMAT),
          anneeScolaire: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          dateNaissance: currentDate,
          anneeScolaire: currentDate,
        },
        returnedFromService
      );

      service.create(new Apprenant()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Apprenant', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          nomPrenom: 'BBBBBB',
          dateNaissance: currentDate.format(DATE_FORMAT),
          lieuNaissance: 'BBBBBB',
          telephone: 'BBBBBB',
          adresse: 'BBBBBB',
          sexe: 'BBBBBB',
          anneeScolaire: currentDate.format(DATE_FORMAT),
          option: 'BBBBBB',
          situationMatrimoniale: 'BBBBBB',
          tuteur: 'BBBBBB',
          contactTuteur: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          dateNaissance: currentDate,
          anneeScolaire: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Apprenant', () => {
      const patchObject = Object.assign(
        {
          dateNaissance: currentDate.format(DATE_FORMAT),
          telephone: 'BBBBBB',
          sexe: 'BBBBBB',
          option: 'BBBBBB',
          situationMatrimoniale: 'BBBBBB',
          tuteur: 'BBBBBB',
          contactTuteur: 'BBBBBB',
        },
        new Apprenant()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          dateNaissance: currentDate,
          anneeScolaire: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Apprenant', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          nomPrenom: 'BBBBBB',
          dateNaissance: currentDate.format(DATE_FORMAT),
          lieuNaissance: 'BBBBBB',
          telephone: 'BBBBBB',
          adresse: 'BBBBBB',
          sexe: 'BBBBBB',
          anneeScolaire: currentDate.format(DATE_FORMAT),
          option: 'BBBBBB',
          situationMatrimoniale: 'BBBBBB',
          tuteur: 'BBBBBB',
          contactTuteur: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          dateNaissance: currentDate,
          anneeScolaire: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Apprenant', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addApprenantToCollectionIfMissing', () => {
      it('should add a Apprenant to an empty array', () => {
        const apprenant: IApprenant = { id: 123 };
        expectedResult = service.addApprenantToCollectionIfMissing([], apprenant);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(apprenant);
      });

      it('should not add a Apprenant to an array that contains it', () => {
        const apprenant: IApprenant = { id: 123 };
        const apprenantCollection: IApprenant[] = [
          {
            ...apprenant,
          },
          { id: 456 },
        ];
        expectedResult = service.addApprenantToCollectionIfMissing(apprenantCollection, apprenant);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Apprenant to an array that doesn't contain it", () => {
        const apprenant: IApprenant = { id: 123 };
        const apprenantCollection: IApprenant[] = [{ id: 456 }];
        expectedResult = service.addApprenantToCollectionIfMissing(apprenantCollection, apprenant);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(apprenant);
      });

      it('should add only unique Apprenant to an array', () => {
        const apprenantArray: IApprenant[] = [{ id: 123 }, { id: 456 }, { id: 77774 }];
        const apprenantCollection: IApprenant[] = [{ id: 123 }];
        expectedResult = service.addApprenantToCollectionIfMissing(apprenantCollection, ...apprenantArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const apprenant: IApprenant = { id: 123 };
        const apprenant2: IApprenant = { id: 456 };
        expectedResult = service.addApprenantToCollectionIfMissing([], apprenant, apprenant2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(apprenant);
        expect(expectedResult).toContain(apprenant2);
      });

      it('should accept null and undefined values', () => {
        const apprenant: IApprenant = { id: 123 };
        expectedResult = service.addApprenantToCollectionIfMissing([], null, apprenant, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(apprenant);
      });

      it('should return initial array if no Apprenant is added', () => {
        const apprenantCollection: IApprenant[] = [{ id: 123 }];
        expectedResult = service.addApprenantToCollectionIfMissing(apprenantCollection, undefined, null);
        expect(expectedResult).toEqual(apprenantCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
