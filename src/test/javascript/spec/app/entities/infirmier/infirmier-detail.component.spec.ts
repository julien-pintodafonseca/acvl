import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { InfirmierDetailComponent } from 'app/entities/infirmier/infirmier-detail.component';
import { Infirmier } from 'app/shared/model/infirmier.model';

describe('Component Tests', () => {
  describe('Infirmier Management Detail Component', () => {
    let comp: InfirmierDetailComponent;
    let fixture: ComponentFixture<InfirmierDetailComponent>;
    const route = ({ data: of({ infirmier: new Infirmier(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [InfirmierDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InfirmierDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InfirmierDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load infirmier on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.infirmier).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
