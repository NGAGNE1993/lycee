import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IPersonnelAppui, PersonnelAppui } from '../personnel-appui.model';
import { PersonnelAppuiService } from '../service/personnel-appui.service';

import { PersonnelAppuiRoutingResolveService } from './personnel-appui-routing-resolve.service';

describe('PersonnelAppui routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: PersonnelAppuiRoutingResolveService;
  let service: PersonnelAppuiService;
  let resultPersonnelAppui: IPersonnelAppui | undefined;

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
    routingResolveService = TestBed.inject(PersonnelAppuiRoutingResolveService);
    service = TestBed.inject(PersonnelAppuiService);
    resultPersonnelAppui = undefined;
  });

  describe('resolve', () => {
    it('should return IPersonnelAppui returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultPersonnelAppui = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultPersonnelAppui).toEqual({ id: 123 });
    });

    it('should return new IPersonnelAppui if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultPersonnelAppui = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultPersonnelAppui).toEqual(new PersonnelAppui());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as PersonnelAppui })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultPersonnelAppui = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultPersonnelAppui).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
