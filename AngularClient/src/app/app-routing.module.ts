import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ListaProductoComponent } from './productos/lista-producto.component';
import { NuevoProductoComponent } from './productos/nuevo-producto.component'; 
import { DetalleProductoComponent } from './productos/detalle-producto.component'; 
import { EditarProductoComponent } from './productos/editar-producto.component'; 
import { LoginComponent } from './auth/login.component';

import { GuardService as guard} from './guards/guard.service';
import { AdminComponent } from './users/admin.component';
import { UserComponent } from './users/user.component';
import { RegistroComponent } from './users/registro.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'lista', component: ListaProductoComponent}, 
  //ME llevara al detalle del producto 
  // productos: utilizamos canActivate , Y los roles para las diferentes acciones
  {path: 'detalle/:id', component: DetalleProductoComponent,canActivate: [guard], data: { expectedRol: ['admin', 'user']}}, 
  //ME llevara a nuevo producto
  {path: 'nuevo', component: NuevoProductoComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'editar/:id', component: EditarProductoComponent,canActivate: [guard], data: { expectedRol: ['admin']}},

   // user y admin

   {path: 'admin', component: AdminComponent, canActivate: [guard], data: {expectedRol: ['admin']}},
   {path: 'user', component: UserComponent, canActivate: [guard], data: {expectedRol: ['user']}},

  // rutas a login y registro
  {path: 'login', component: LoginComponent}, 
  {path: 'registro', component: RegistroComponent},
  {path: '**', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
