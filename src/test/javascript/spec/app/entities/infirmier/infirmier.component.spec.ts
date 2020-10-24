import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AcvlTestModule } from '../../../test.module';
import { InfirmierComponent } from 'app/entities/infirmier/infirmier.component';
import { InfirmierService } from 'app/entities/infirmier/infirmier.service';
import { Infirmier } from 'app/shared/model/infirmier.model';

describe('Component Tests', () => {
  describe('Infirmier Management Component', () => {
    let comp: InfirmierComponent;
    let fixture: ComponentFixture<InfirmierComponent>;
    let service: InfirmierService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [InfirmierComponent],
      })
        .overrideTemplate(InfirmierComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InfirmierComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InfirmierService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Infirmier(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.infirmiers && comp.infirmiers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
