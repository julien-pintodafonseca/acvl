import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AcvlTestModule } from '../../../test.module';
import { SecouristeComponent } from 'app/entities/secouriste/secouriste.component';
import { SecouristeService } from 'app/entities/secouriste/secouriste.service';
import { Secouriste } from 'app/shared/model/secouriste.model';

describe('Component Tests', () => {
  describe('Secouriste Management Component', () => {
    let comp: SecouristeComponent;
    let fixture: ComponentFixture<SecouristeComponent>;
    let service: SecouristeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [SecouristeComponent],
      })
        .overrideTemplate(SecouristeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SecouristeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SecouristeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Secouriste(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.secouristes && comp.secouristes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
