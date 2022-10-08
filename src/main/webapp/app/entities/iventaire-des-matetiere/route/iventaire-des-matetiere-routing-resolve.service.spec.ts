import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IIventaireDesMatetiere, IventaireDesMatetiere } from '../iventaire-des-matetiere.model';
import { IventaireDesMatetiereService } from '../service/iventaire-des-matetiere.service';

import { IventaireDesMatetiereRoutingResolveService } from './iventaire-des-matetiere-routing-resolve.service';

describe('IventaireDesMatetiere routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: IventaireDesMatetiereRoutingResolveService;
  let service: IventaireDesMatetiereService;
  let resultIventaireDesMatetiere: IIventaireDesMatetiere | undefined;

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
    routingResolveService = TestBed.inject(IventaireDesMatetiereRoutingResolveService);
    service = TestBed.inject(IventaireDesMatetiereService);
    resultIventaireDesMatetiere = undefined;
  });

  describe('resolve', () => {
    it('should return IIventaireDesMatetiere returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultIventaireDesMatetiere = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultIventaireDesMatetiere).toEqual({ id: 123 });
    });

    it('should return new IIventaireDesMatetiere if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultIventaireDesMatetiere = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultIventaireDesMatetiere).toEqual(new IventaireDesMatetiere());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as IventaireDesMatetiere })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultIventaireDesMatetiere = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultIventaireDesMatetiere).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
