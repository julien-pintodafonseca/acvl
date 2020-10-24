import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AcvlTestModule } from '../../../test.module';
import { MdcParmComponent } from 'app/entities/mdc-parm/mdc-parm.component';
import { MdcParmService } from 'app/entities/mdc-parm/mdc-parm.service';
import { MdcParm } from 'app/shared/model/mdc-parm.model';

describe('Component Tests', () => {
  describe('MdcParm Management Component', () => {
    let comp: MdcParmComponent;
    let fixture: ComponentFixture<MdcParmComponent>;
    let service: MdcParmService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MdcParmComponent],
      })
        .overrideTemplate(MdcParmComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MdcParmComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MdcParmService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MdcParm(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.mdcParms && comp.mdcParms[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
