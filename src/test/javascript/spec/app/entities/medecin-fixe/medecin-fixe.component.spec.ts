import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AcvlTestModule } from '../../../test.module';
import { MedecinFixeComponent } from 'app/entities/medecin-fixe/medecin-fixe.component';
import { MedecinFixeService } from 'app/entities/medecin-fixe/medecin-fixe.service';
import { MedecinFixe } from 'app/shared/model/medecin-fixe.model';

describe('Component Tests', () => {
  describe('MedecinFixe Management Component', () => {
    let comp: MedecinFixeComponent;
    let fixture: ComponentFixture<MedecinFixeComponent>;
    let service: MedecinFixeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MedecinFixeComponent],
      })
        .overrideTemplate(MedecinFixeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedecinFixeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedecinFixeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MedecinFixe(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.medecinFixes && comp.medecinFixes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
