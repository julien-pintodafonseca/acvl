import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MedecinMobileDetailComponent } from 'app/entities/medecin-mobile/medecin-mobile-detail.component';
import { MedecinMobile } from 'app/shared/model/medecin-mobile.model';

describe('Component Tests', () => {
  describe('MedecinMobile Management Detail Component', () => {
    let comp: MedecinMobileDetailComponent;
    let fixture: ComponentFixture<MedecinMobileDetailComponent>;
    const route = ({ data: of({ medecinMobile: new MedecinMobile(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MedecinMobileDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MedecinMobileDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedecinMobileDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load medecinMobile on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.medecinMobile).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
