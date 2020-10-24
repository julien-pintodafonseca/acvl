import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AcvlTestModule } from '../../../test.module';
import { MdcFixeComponent } from 'app/entities/mdc-fixe/mdc-fixe.component';
import { MdcFixeService } from 'app/entities/mdc-fixe/mdc-fixe.service';
import { MdcFixe } from 'app/shared/model/mdc-fixe.model';

describe('Component Tests', () => {
  describe('MdcFixe Management Component', () => {
    let comp: MdcFixeComponent;
    let fixture: ComponentFixture<MdcFixeComponent>;
    let service: MdcFixeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MdcFixeComponent],
      })
        .overrideTemplate(MdcFixeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MdcFixeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MdcFixeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MdcFixe(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.mdcFixes && comp.mdcFixes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
