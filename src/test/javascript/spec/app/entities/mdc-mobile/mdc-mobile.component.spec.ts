import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AcvlTestModule } from '../../../test.module';
import { MdcMobileComponent } from 'app/entities/mdc-mobile/mdc-mobile.component';
import { MdcMobileService } from 'app/entities/mdc-mobile/mdc-mobile.service';
import { MdcMobile } from 'app/shared/model/mdc-mobile.model';

describe('Component Tests', () => {
  describe('MdcMobile Management Component', () => {
    let comp: MdcMobileComponent;
    let fixture: ComponentFixture<MdcMobileComponent>;
    let service: MdcMobileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MdcMobileComponent],
      })
        .overrideTemplate(MdcMobileComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MdcMobileComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MdcMobileService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MdcMobile(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.mdcMobiles && comp.mdcMobiles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
