import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IOrganeGestion, OrganeGestion } from '../organe-gestion.model';
import { OrganeGestionService } from '../service/organe-gestion.service';

import { OrganeGestionRoutingResolveService } from './organe-gestion-routing-resolve.service';

describe('OrganeGestion routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: OrganeGestionRoutingResolveService;
  let service: OrganeGestionService;
  let resultOrganeGestion: IOrganeGestion | undefined;

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
    routingResolveService = TestBed.inject(OrganeGestionRoutingResolveService);
    service = TestBed.inject(OrganeGestionService);
    resultOrganeGestion = undefined;
  });

  describe('resolve', () => {
    it('should return IOrganeGestion returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultOrganeGestion = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultOrganeGestion).toEqual({ id: 123 });
    });

    it('should return new IOrganeGestion if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultOrganeGestion = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultOrganeGestion).toEqual(new OrganeGestion());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as OrganeGestion })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultOrganeGestion = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultOrganeGestion).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
