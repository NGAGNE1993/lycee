import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { TypeRapport } from 'app/entities/enumerations/type-rapport.model';
import { IRapportRF, RapportRF } from '../rapport-rf.model';

import { RapportRFService } from './rapport-rf.service';

describe('RapportRF Service', () => {
  let service: RapportRFService;
  let httpMock: HttpTestingController;
  let elemDefault: IRapportRF;
  let expectedResult: IRapportRF | IRapportRF[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RapportRFService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      typeRaport: TypeRapport.FIN,
      rentreContentType: 'image/png',
      rentre: 'AAAAAAA',
      finContentType: 'image/png',
      fin: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a RapportRF', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new RapportRF()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RapportRF', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          typeRaport: 'BBBBBB',
          rentre: 'BBBBBB',
          fin: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RapportRF', () => {
      const patchObject = Object.assign(
        {
          typeRaport: 'BBBBBB',
          rentre: 'BBBBBB',
        },
        new RapportRF()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RapportRF', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          typeRaport: 'BBBBBB',
          rentre: 'BBBBBB',
          fin: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a RapportRF', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addRapportRFToCollectionIfMissing', () => {
      it('should add a RapportRF to an empty array', () => {
        const rapportRF: IRapportRF = { id: 123 };
        expectedResult = service.addRapportRFToCollectionIfMissing([], rapportRF);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(rapportRF);
      });

      it('should not add a RapportRF to an array that contains it', () => {
        const rapportRF: IRapportRF = { id: 123 };
        const rapportRFCollection: IRapportRF[] = [
          {
            ...rapportRF,
          },
          { id: 456 },
        ];
        expectedResult = service.addRapportRFToCollectionIfMissing(rapportRFCollection, rapportRF);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RapportRF to an array that doesn't contain it", () => {
        const rapportRF: IRapportRF = { id: 123 };
        const rapportRFCollection: IRapportRF[] = [{ id: 456 }];
        expectedResult = service.addRapportRFToCollectionIfMissing(rapportRFCollection, rapportRF);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(rapportRF);
      });

      it('should add only unique RapportRF to an array', () => {
        const rapportRFArray: IRapportRF[] = [{ id: 123 }, { id: 456 }, { id: 25011 }];
        const rapportRFCollection: IRapportRF[] = [{ id: 123 }];
        expectedResult = service.addRapportRFToCollectionIfMissing(rapportRFCollection, ...rapportRFArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const rapportRF: IRapportRF = { id: 123 };
        const rapportRF2: IRapportRF = { id: 456 };
        expectedResult = service.addRapportRFToCollectionIfMissing([], rapportRF, rapportRF2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(rapportRF);
        expect(expectedResult).toContain(rapportRF2);
      });

      it('should accept null and undefined values', () => {
        const rapportRF: IRapportRF = { id: 123 };
        expectedResult = service.addRapportRFToCollectionIfMissing([], null, rapportRF, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(rapportRF);
      });

      it('should return initial array if no RapportRF is added', () => {
        const rapportRFCollection: IRapportRF[] = [{ id: 123 }];
        expectedResult = service.addRapportRFToCollectionIfMissing(rapportRFCollection, undefined, null);
        expect(expectedResult).toEqual(rapportRFCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
