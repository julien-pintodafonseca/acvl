import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MedecinParmUpdateComponent } from 'app/entities/medecin-parm/medecin-parm-update.component';
import { MedecinParmService } from 'app/entities/medecin-parm/medecin-parm.service';
import { MedecinParm } from 'app/shared/model/medecin-parm.model';

describe('Component Tests', () => {
  describe('MedecinParm Management Update Component', () => {
    let comp: MedecinParmUpdateComponent;
    let fixture: ComponentFixture<MedecinParmUpdateComponent>;
    let service: MedecinParmService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MedecinParmUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MedecinParmUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedecinParmUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedecinParmService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MedecinParm(123);
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
        const entity = new MedecinParm();
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
