import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MdcRegulateurUpdateComponent } from 'app/entities/mdc-regulateur/mdc-regulateur-update.component';
import { MdcRegulateurService } from 'app/entities/mdc-regulateur/mdc-regulateur.service';
import { MdcRegulateur } from 'app/shared/model/mdc-regulateur.model';

describe('Component Tests', () => {
  describe('MdcRegulateur Management Update Component', () => {
    let comp: MdcRegulateurUpdateComponent;
    let fixture: ComponentFixture<MdcRegulateurUpdateComponent>;
    let service: MdcRegulateurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MdcRegulateurUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MdcRegulateurUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MdcRegulateurUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MdcRegulateurService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MdcRegulateur(123);
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
        const entity = new MdcRegulateur();
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
