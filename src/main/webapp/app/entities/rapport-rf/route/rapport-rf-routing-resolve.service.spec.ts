import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IRapportRF, RapportRF } from '../rapport-rf.model';
import { RapportRFService } from '../service/rapport-rf.service';

import { RapportRFRoutingResolveService } from './rapport-rf-routing-resolve.service';

describe('RapportRF routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: RapportRFRoutingResolveService;
  let service: RapportRFService;
  let resultRapportRF: IRapportRF | undefined;

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
    routingResolveService = TestBed.inject(RapportRFRoutingResolveService);
    service = TestBed.inject(RapportRFService);
    resultRapportRF = undefined;
  });

  describe('resolve', () => {
    it('should return IRapportRF returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultRapportRF = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultRapportRF).toEqual({ id: 123 });
    });

    it('should return new IRapportRF if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultRapportRF = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultRapportRF).toEqual(new RapportRF());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as RapportRF })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultRapportRF = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultRapportRF).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
