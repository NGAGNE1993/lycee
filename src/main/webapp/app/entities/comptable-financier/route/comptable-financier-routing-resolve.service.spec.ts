import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IComptableFinancier, ComptableFinancier } from '../comptable-financier.model';
import { ComptableFinancierService } from '../service/comptable-financier.service';

import { ComptableFinancierRoutingResolveService } from './comptable-financier-routing-resolve.service';

describe('ComptableFinancier routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: ComptableFinancierRoutingResolveService;
  let service: ComptableFinancierService;
  let resultComptableFinancier: IComptableFinancier | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(ComptableFinancierRoutingResolveService);
    service = TestBed.inject(ComptableFinancierService);
    resultComptableFinancier = undefined;
  });

  describe('resolve', () => {
    it('should return IComptableFinancier returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultComptableFinancier = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultComptableFinancier).toEqual({ id: 123 });
    });

    it('should return new IComptableFinancier if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultComptableFinancier = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultComptableFinancier).toEqual(new ComptableFinancier());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as ComptableFinancier })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultComptableFinancier = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultComptableFinancier).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
