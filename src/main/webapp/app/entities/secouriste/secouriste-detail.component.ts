import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISecouriste } from 'app/shared/model/secouriste.model';

@Component({
  selector: 'jhi-secouriste-detail',
  templateUrl: './secouriste-detail.component.html',
})
export class SecouristeDetailComponent implements OnInit {
  secouriste: ISecouriste | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ secouriste }) => (this.secouriste = secouriste));
  }

  previousState(): void {
    window.history.back();
  }
}
