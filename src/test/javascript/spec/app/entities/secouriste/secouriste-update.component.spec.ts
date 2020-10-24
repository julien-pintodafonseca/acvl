import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { SecouristeUpdateComponent } from 'app/entities/secouriste/secouriste-update.component';
import { SecouristeService } from 'app/entities/secouriste/secouriste.service';
import { Secouriste } from 'app/shared/model/secouriste.model';

describe('Component Tests', () => {
  describe('Secouriste Management Update Component', () => {
    let comp: SecouristeUpdateComponent;
    let fixture: ComponentFixture<SecouristeUpdateComponent>;
    let service: SecouristeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [SecouristeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SecouristeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SecouristeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SecouristeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Secouriste(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Secouriste();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
