import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AcvlTestModule } from '../../../test.module';
import { MedecinMobileComponent } from 'app/entities/medecin-mobile/medecin-mobile.component';
import { MedecinMobileService } from 'app/entities/medecin-mobile/medecin-mobile.service';
import { MedecinMobile } from 'app/shared/model/medecin-mobile.model';

describe('Component Tests', () => {
  describe('MedecinMobile Management Component', () => {
    let comp: MedecinMobileComponent;
    let fixture: ComponentFixture<MedecinMobileComponent>;
    let service: MedecinMobileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MedecinMobileComponent],
      })
        .overrideTemplate(MedecinMobileComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedecinMobileComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedecinMobileService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MedecinMobile(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.medecinMobiles && comp.medecinMobiles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
