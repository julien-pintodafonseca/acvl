import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MdcFixeUpdateComponent } from 'app/entities/mdc-fixe/mdc-fixe-update.component';
import { MdcFixeService } from 'app/entities/mdc-fixe/mdc-fixe.service';
import { MdcFixe } from 'app/shared/model/mdc-fixe.model';

describe('Component Tests', () => {
  describe('MdcFixe Management Update Component', () => {
    let comp: MdcFixeUpdateComponent;
    let fixture: ComponentFixture<MdcFixeUpdateComponent>;
    let service: MdcFixeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MdcFixeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MdcFixeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MdcFixeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MdcFixeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MdcFixe(123);
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
        const entity = new MdcFixe();
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
