import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { InfirmierUpdateComponent } from 'app/entities/infirmier/infirmier-update.component';
import { InfirmierService } from 'app/entities/infirmier/infirmier.service';
import { Infirmier } from 'app/shared/model/infirmier.model';

describe('Component Tests', () => {
  describe('Infirmier Management Update Component', () => {
    let comp: InfirmierUpdateComponent;
    let fixture: ComponentFixture<InfirmierUpdateComponent>;
    let service: InfirmierService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [InfirmierUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(InfirmierUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InfirmierUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InfirmierService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Infirmier(123);
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
        const entity = new Infirmier();
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
