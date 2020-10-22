import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AcvlTestModule } from '../../../test.module';
import { PersonnelSanteComponent } from 'app/entities/personnel-sante/personnel-sante.component';
import { PersonnelSanteService } from 'app/entities/personnel-sante/personnel-sante.service';
import { PersonnelSante } from 'app/shared/model/personnel-sante.model';

describe('Component Tests', () => {
  describe('PersonnelSante Management Component', () => {
    let comp: PersonnelSanteComponent;
    let fixture: ComponentFixture<PersonnelSanteComponent>;
    let service: PersonnelSanteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [PersonnelSanteComponent],
      })
        .overrideTemplate(PersonnelSanteComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PersonnelSanteComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PersonnelSanteService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PersonnelSante(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.personnelSantes && comp.personnelSantes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
