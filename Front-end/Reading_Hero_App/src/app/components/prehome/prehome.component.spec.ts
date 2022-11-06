import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrehomeComponent } from './prehome.component';

describe('PrehomeComponent', () => {
  let component: PrehomeComponent;
  let fixture: ComponentFixture<PrehomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PrehomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrehomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
