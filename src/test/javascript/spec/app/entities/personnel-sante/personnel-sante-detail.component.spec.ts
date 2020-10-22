import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { PersonnelSanteDetailComponent } from 'app/entities/personnel-sante/personnel-sante-detail.component';
import { PersonnelSante } from 'app/shared/model/personnel-sante.model';

describe('Component Tests', () => {
  describe('PersonnelSante Management Detail Component', () => {
    let comp: PersonnelSanteDetailComponent;
    let fixture: ComponentFixture<PersonnelSanteDetailComponent>;
    const route = ({ data: of({ personnelSante: new PersonnelSante(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [PersonnelSanteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PersonnelSanteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PersonnelSanteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load personnelSante on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.personnelSante).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
