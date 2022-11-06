import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowBookDetailsComponent } from './show-book-details.component';

describe('ShowBookDetailsComponent', () => {
  let component: ShowBookDetailsComponent;
  let fixture: ComponentFixture<ShowBookDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowBookDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowBookDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
