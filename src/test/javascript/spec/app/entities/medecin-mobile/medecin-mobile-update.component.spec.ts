import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MedecinMobileUpdateComponent } from 'app/entities/medecin-mobile/medecin-mobile-update.component';
import { MedecinMobileService } from 'app/entities/medecin-mobile/medecin-mobile.service';
import { MedecinMobile } from 'app/shared/model/medecin-mobile.model';

describe('Component Tests', () => {
  describe('MedecinMobile Management Update Component', () => {
    let comp: MedecinMobileUpdateComponent;
    let fixture: ComponentFixture<MedecinMobileUpdateComponent>;
    let service: MedecinMobileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MedecinMobileUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MedecinMobileUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedecinMobileUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedecinMobileService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MedecinMobile(123);
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
        const entity = new MedecinMobile();
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
