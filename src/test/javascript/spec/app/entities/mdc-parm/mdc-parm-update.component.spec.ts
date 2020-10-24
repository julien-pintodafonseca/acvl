import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MdcParmUpdateComponent } from 'app/entities/mdc-parm/mdc-parm-update.component';
import { MdcParmService } from 'app/entities/mdc-parm/mdc-parm.service';
import { MdcParm } from 'app/shared/model/mdc-parm.model';

describe('Component Tests', () => {
  describe('MdcParm Management Update Component', () => {
    let comp: MdcParmUpdateComponent;
    let fixture: ComponentFixture<MdcParmUpdateComponent>;
    let service: MdcParmService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MdcParmUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MdcParmUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MdcParmUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MdcParmService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MdcParm(123);
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
        const entity = new MdcParm();
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
