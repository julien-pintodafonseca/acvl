import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MedecinMobileService } from 'app/entities/medecin-mobile/medecin-mobile.service';
import { IMedecinMobile, MedecinMobile } from 'app/shared/model/medecin-mobile.model';
import { TypeCentre } from 'app/shared/model/enumerations/type-centre.model';
import { Etat } from 'app/shared/model/enumerations/etat.model';

describe('Service Tests', () => {
  describe('MedecinMobile Service', () => {
    let injector: TestBed;
    let service: MedecinMobileService;
    let httpMock: HttpTestingController;
    let elemDefault: IMedecinMobile;
    let expectedResult: IMedecinMobile | IMedecinMobile[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MedecinMobileService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MedecinMobile(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', TypeCentre.SECOURS, Etat.DISPO_BASE_OP, false, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a MedecinMobile', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new MedecinMobile()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a MedecinMobile', () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            adresse: 'BBBBBB',
            telephone: 'BBBBBB',
            centre: 'BBBBBB',
            etat: 'BBBBBB',
            estMobile: true,
            estFixe: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of MedecinMobile', () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            adresse: 'BBBBBB',
            telephone: 'BBBBBB',
            centre: 'BBBBBB',
            etat: 'BBBBBB',
            estMobile: true,
            estFixe: true,
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

      it('should delete a MedecinMobile', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
