import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AcvlTestModule } from '../../../test.module';
import { MedecinRegulateurComponent } from 'app/entities/medecin-regulateur/medecin-regulateur.component';
import { MedecinRegulateurService } from 'app/entities/medecin-regulateur/medecin-regulateur.service';
import { MedecinRegulateur } from 'app/shared/model/medecin-regulateur.model';

describe('Component Tests', () => {
  describe('MedecinRegulateur Management Component', () => {
    let comp: MedecinRegulateurComponent;
    let fixture: ComponentFixture<MedecinRegulateurComponent>;
    let service: MedecinRegulateurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MedecinRegulateurComponent],
      })
        .overrideTemplate(MedecinRegulateurComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedecinRegulateurComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedecinRegulateurService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MedecinRegulateur(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.medecinRegulateurs && comp.medecinRegulateurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
