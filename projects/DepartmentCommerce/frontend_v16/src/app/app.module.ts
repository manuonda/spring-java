import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppButtonComponent } from './components/app-button/app-button.component';
import { FormNewItemComponent } from './components/form-new-item/form-new-item.component';
import { CoreModule } from './core/core.module';
import { CitiesComponent } from './components/cities/cities.component';
import { FilterPipe } from './core/pipes/filter.pipe';
import { FormsModule } from '@angular/forms';
import { ContactComponent } from './components/contact/contact.component';

@NgModule({
  declarations: [
    AppComponent,
    AppButtonComponent,
    FormNewItemComponent,
    CitiesComponent,
    FilterPipe,
    ContactComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
