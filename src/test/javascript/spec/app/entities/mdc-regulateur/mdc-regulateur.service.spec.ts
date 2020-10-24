import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MdcRegulateurService } from 'app/entities/mdc-regulateur/mdc-regulateur.service';
import { IMdcRegulateur, MdcRegulateur } from 'app/shared/model/mdc-regulateur.model';
import { TypeCentre } from 'app/shared/model/enumerations/type-centre.model';
import { Etat } from 'app/shared/model/enumerations/etat.model';

describe('Service Tests', () => {
  describe('MdcRegulateur Service', () => {
    let injector: TestBed;
    let service: MdcRegulateurService;
    let httpMock: HttpTestingController;
    let elemDefault: IMdcRegulateur;
    let expectedResult: IMdcRegulateur | IMdcRegulateur[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MdcRegulateurService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MdcRegulateur(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', TypeCentre.SECOURS, Etat.DISPO_BASE_OP, false, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a MdcRegulateur', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new MdcRegulateur()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a MdcRegulateur', () => {
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

      it('should return a list of MdcRegulateur', () => {
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

      it('should delete a MdcRegulateur', () => {
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
