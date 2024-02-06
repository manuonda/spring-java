import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContainerComponent } from './container/container.component';
import { MenuComponent } from './menu/menu.component';
import { HeaderComponent } from './header/header.component';
import { NotificationModule } from '../notification/notification.module';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    ContainerComponent,
    MenuComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    NotificationModule,
    FormsModule
  ],
  exports:[ContainerComponent] //permite exportar el container components
})
export class ContainerModule { }
