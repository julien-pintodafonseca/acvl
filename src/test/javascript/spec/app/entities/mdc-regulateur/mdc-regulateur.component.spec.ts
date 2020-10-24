import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AcvlTestModule } from '../../../test.module';
import { MdcRegulateurComponent } from 'app/entities/mdc-regulateur/mdc-regulateur.component';
import { MdcRegulateurService } from 'app/entities/mdc-regulateur/mdc-regulateur.service';
import { MdcRegulateur } from 'app/shared/model/mdc-regulateur.model';

describe('Component Tests', () => {
  describe('MdcRegulateur Management Component', () => {
    let comp: MdcRegulateurComponent;
    let fixture: ComponentFixture<MdcRegulateurComponent>;
    let service: MdcRegulateurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MdcRegulateurComponent],
      })
        .overrideTemplate(MdcRegulateurComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MdcRegulateurComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MdcRegulateurService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MdcRegulateur(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.mdcRegulateurs && comp.mdcRegulateurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
