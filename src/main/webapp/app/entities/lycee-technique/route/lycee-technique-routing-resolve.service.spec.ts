import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ILyceeTechnique, LyceeTechnique } from '../lycee-technique.model';
import { LyceeTechniqueService } from '../service/lycee-technique.service';

import { LyceeTechniqueRoutingResolveService } from './lycee-technique-routing-resolve.service';

describe('LyceeTechnique routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: LyceeTechniqueRoutingResolveService;
  let service: LyceeTechniqueService;
  let resultLyceeTechnique: ILyceeTechnique | undefined;

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
    routingResolveService = TestBed.inject(LyceeTechniqueRoutingResolveService);
    service = TestBed.inject(LyceeTechniqueService);
    resultLyceeTechnique = undefined;
  });

  describe('resolve', () => {
    it('should return ILyceeTechnique returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultLyceeTechnique = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultLyceeTechnique).toEqual({ id: 123 });
    });

    it('should return new ILyceeTechnique if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultLyceeTechnique = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultLyceeTechnique).toEqual(new LyceeTechnique());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as LyceeTechnique })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultLyceeTechnique = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultLyceeTechnique).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
