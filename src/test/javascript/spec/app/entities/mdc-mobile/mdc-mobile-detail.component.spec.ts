import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MdcMobileDetailComponent } from 'app/entities/mdc-mobile/mdc-mobile-detail.component';
import { MdcMobile } from 'app/shared/model/mdc-mobile.model';

describe('Component Tests', () => {
  describe('MdcMobile Management Detail Component', () => {
    let comp: MdcMobileDetailComponent;
    let fixture: ComponentFixture<MdcMobileDetailComponent>;
    const route = ({ data: of({ mdcMobile: new MdcMobile(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MdcMobileDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MdcMobileDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MdcMobileDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load mdcMobile on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mdcMobile).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
