import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailCategoriaComponent } from './detail-categoria.component';

describe('DetailCategoriaComponent', () => {
  let component: DetailCategoriaComponent;
  let fixture: ComponentFixture<DetailCategoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailCategoriaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DetailCategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
