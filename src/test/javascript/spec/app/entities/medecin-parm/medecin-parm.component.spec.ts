import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AcvlTestModule } from '../../../test.module';
import { MedecinParmComponent } from 'app/entities/medecin-parm/medecin-parm.component';
import { MedecinParmService } from 'app/entities/medecin-parm/medecin-parm.service';
import { MedecinParm } from 'app/shared/model/medecin-parm.model';

describe('Component Tests', () => {
  describe('MedecinParm Management Component', () => {
    let comp: MedecinParmComponent;
    let fixture: ComponentFixture<MedecinParmComponent>;
    let service: MedecinParmService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MedecinParmComponent],
      })
        .overrideTemplate(MedecinParmComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedecinParmComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedecinParmService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MedecinParm(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.medecinParms && comp.medecinParms[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
