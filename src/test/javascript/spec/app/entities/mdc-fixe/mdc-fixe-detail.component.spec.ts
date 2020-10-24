import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MdcFixeDetailComponent } from 'app/entities/mdc-fixe/mdc-fixe-detail.component';
import { MdcFixe } from 'app/shared/model/mdc-fixe.model';

describe('Component Tests', () => {
  describe('MdcFixe Management Detail Component', () => {
    let comp: MdcFixeDetailComponent;
    let fixture: ComponentFixture<MdcFixeDetailComponent>;
    const route = ({ data: of({ mdcFixe: new MdcFixe(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MdcFixeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MdcFixeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MdcFixeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load mdcFixe on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mdcFixe).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
