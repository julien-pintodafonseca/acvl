import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MdcRegulateurDetailComponent } from 'app/entities/mdc-regulateur/mdc-regulateur-detail.component';
import { MdcRegulateur } from 'app/shared/model/mdc-regulateur.model';

describe('Component Tests', () => {
  describe('MdcRegulateur Management Detail Component', () => {
    let comp: MdcRegulateurDetailComponent;
    let fixture: ComponentFixture<MdcRegulateurDetailComponent>;
    const route = ({ data: of({ mdcRegulateur: new MdcRegulateur(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MdcRegulateurDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MdcRegulateurDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MdcRegulateurDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load mdcRegulateur on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mdcRegulateur).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
