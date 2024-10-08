import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FrigoComponent } from './frigo.component';

describe('FrigoComponent', () => {
  let component: FrigoComponent;
  let fixture: ComponentFixture<FrigoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FrigoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FrigoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
