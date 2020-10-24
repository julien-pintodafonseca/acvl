import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MdcMobileUpdateComponent } from 'app/entities/mdc-mobile/mdc-mobile-update.component';
import { MdcMobileService } from 'app/entities/mdc-mobile/mdc-mobile.service';
import { MdcMobile } from 'app/shared/model/mdc-mobile.model';

describe('Component Tests', () => {
  describe('MdcMobile Management Update Component', () => {
    let comp: MdcMobileUpdateComponent;
    let fixture: ComponentFixture<MdcMobileUpdateComponent>;
    let service: MdcMobileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MdcMobileUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MdcMobileUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MdcMobileUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MdcMobileService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MdcMobile(123);
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
        const entity = new MdcMobile();
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
