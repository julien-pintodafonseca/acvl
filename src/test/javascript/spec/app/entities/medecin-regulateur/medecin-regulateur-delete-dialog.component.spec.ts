import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AcvlTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { MedecinRegulateurDeleteDialogComponent } from 'app/entities/medecin-regulateur/medecin-regulateur-delete-dialog.component';
import { MedecinRegulateurService } from 'app/entities/medecin-regulateur/medecin-regulateur.service';

describe('Component Tests', () => {
  describe('MedecinRegulateur Management Delete Component', () => {
    let comp: MedecinRegulateurDeleteDialogComponent;
    let fixture: ComponentFixture<MedecinRegulateurDeleteDialogComponent>;
    let service: MedecinRegulateurService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AcvlTestModule],
        declarations: [MedecinRegulateurDeleteDialogComponent],
      })
        .overrideTemplate(MedecinRegulateurDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedecinRegulateurDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedecinRegulateurService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
