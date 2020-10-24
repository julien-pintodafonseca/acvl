import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { SecouristeDetailComponent } from 'app/entities/secouriste/secouriste-detail.component';
import { Secouriste } from 'app/shared/model/secouriste.model';

describe('Component Tests', () => {
  describe('Secouriste Management Detail Component', () => {
    let comp: SecouristeDetailComponent;
    let fixture: ComponentFixture<SecouristeDetailComponent>;
    const route = ({ data: of({ secouriste: new Secouriste(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [SecouristeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SecouristeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SecouristeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load secouriste on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.secouriste).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
