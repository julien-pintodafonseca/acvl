import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AcvlTestModule } from '../../../test.module';
import { MedecinFixeUpdateComponent } from 'app/entities/medecin-fixe/medecin-fixe-update.component';
import { MedecinFixeService } from 'app/entities/medecin-fixe/medecin-fixe.service';
import { MedecinFixe } from 'app/shared/model/medecin-fixe.model';

describe('Component Tests', () => {
  describe('MedecinFixe Management Update Component', () => {
    let comp: MedecinFixeUpdateComponent;
    let fixture: ComponentFixture<MedecinFixeUpdateComponent>;
    let service: MedecinFixeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MedecinFixeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MedecinFixeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedecinFixeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedecinFixeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MedecinFixe(123);
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
        const entity = new MedecinFixe();
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
