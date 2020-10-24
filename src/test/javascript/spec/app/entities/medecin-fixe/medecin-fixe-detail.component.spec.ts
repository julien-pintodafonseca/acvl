import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MedecinFixeDetailComponent } from 'app/entities/medecin-fixe/medecin-fixe-detail.component';
import { MedecinFixe } from 'app/shared/model/medecin-fixe.model';

describe('Component Tests', () => {
  describe('MedecinFixe Management Detail Component', () => {
    let comp: MedecinFixeDetailComponent;
    let fixture: ComponentFixture<MedecinFixeDetailComponent>;
    const route = ({ data: of({ medecinFixe: new MedecinFixe(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MedecinFixeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MedecinFixeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedecinFixeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load medecinFixe on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.medecinFixe).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
