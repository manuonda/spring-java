import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppButtonComponent } from './components/app-button/app-button.component';
import { FormNewItemComponent } from './components/form-new-item/form-new-item.component';
import { CoreModule } from './core/core.module';

@NgModule({
  declarations: [
    AppComponent,
    AppButtonComponent,
    FormNewItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
