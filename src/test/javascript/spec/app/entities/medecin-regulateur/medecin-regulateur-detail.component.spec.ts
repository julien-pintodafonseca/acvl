import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MedecinRegulateurDetailComponent } from 'app/entities/medecin-regulateur/medecin-regulateur-detail.component';
import { MedecinRegulateur } from 'app/shared/model/medecin-regulateur.model';

describe('Component Tests', () => {
  describe('MedecinRegulateur Management Detail Component', () => {
    let comp: MedecinRegulateurDetailComponent;
    let fixture: ComponentFixture<MedecinRegulateurDetailComponent>;
    const route = ({ data: of({ medecinRegulateur: new MedecinRegulateur(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MedecinRegulateurDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MedecinRegulateurDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedecinRegulateurDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load medecinRegulateur on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.medecinRegulateur).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
