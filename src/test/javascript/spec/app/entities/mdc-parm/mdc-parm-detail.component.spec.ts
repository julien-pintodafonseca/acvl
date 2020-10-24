import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MdcParmDetailComponent } from 'app/entities/mdc-parm/mdc-parm-detail.component';
import { MdcParm } from 'app/shared/model/mdc-parm.model';

describe('Component Tests', () => {
  describe('MdcParm Management Detail Component', () => {
    let comp: MdcParmDetailComponent;
    let fixture: ComponentFixture<MdcParmDetailComponent>;
    const route = ({ data: of({ mdcParm: new MdcParm(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MdcParmDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MdcParmDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MdcParmDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load mdcParm on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mdcParm).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
