import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { PersonnelSanteUpdateComponent } from 'app/entities/personnel-sante/personnel-sante-update.component';
import { PersonnelSanteService } from 'app/entities/personnel-sante/personnel-sante.service';
import { PersonnelSante } from 'app/shared/model/personnel-sante.model';

describe('Component Tests', () => {
  describe('PersonnelSante Management Update Component', () => {
    let comp: PersonnelSanteUpdateComponent;
    let fixture: ComponentFixture<PersonnelSanteUpdateComponent>;
    let service: PersonnelSanteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [PersonnelSanteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PersonnelSanteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PersonnelSanteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PersonnelSanteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PersonnelSante(123);
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
        const entity = new PersonnelSante();
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
