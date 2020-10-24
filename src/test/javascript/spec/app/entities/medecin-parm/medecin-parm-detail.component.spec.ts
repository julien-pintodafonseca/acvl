import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MedecinParmDetailComponent } from 'app/entities/medecin-parm/medecin-parm-detail.component';
import { MedecinParm } from 'app/shared/model/medecin-parm.model';

describe('Component Tests', () => {
  describe('MedecinParm Management Detail Component', () => {
    let comp: MedecinParmDetailComponent;
    let fixture: ComponentFixture<MedecinParmDetailComponent>;
    const route = ({ data: of({ medecinParm: new MedecinParm(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MedecinParmDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MedecinParmDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedecinParmDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load medecinParm on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.medecinParm).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
