import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { SendMessageComponent } from './send-message/send-message.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NotFoundPageComponent } from './not-found-page/not-found-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { AuthenticationService } from './services/authentication.service';
import { JwtUtilsService } from './services/jwt-utils.service';
import { GenericService } from './services/generic.service';
import { ToastrModule } from 'ngx-toastr';
import { RbacPageComponent } from './rbac-page/rbac-page.component';
import { NotAccessPageComponent } from './not-access-page/not-access-page.component';
import { CanActivateUserGuard } from './guard/can-activate-user.guard';
import { TokenInterceptorService } from './services/token-interceptor/token-interceptor.service';


const appRoutes: Routes = [
  { path: 'send-message', 
    component: SendMessageComponent,
    canActivate: [CanActivateUserGuard],
    data: { 
      expectedRoles: ['SECURE_ADMINISTRATOR']
    }  
  },
  { path: 'rbac', 
    component: RbacPageComponent,
    canActivate: [CanActivateUserGuard],
    data: { 
      expectedRoles: ['SECURE_ADMINISTRATOR']
    } 
  },
  { 
    path: 'login', 
    component: LoginPageComponent
  },
  { path: '', // localhost:4200 redirect to localhost:4200/login
    // redirectTo: '/login',
    redirectTo: '/send-message',
    pathMatch: 'full'
  },
  { path: 'not-access-page/:url', component: NotAccessPageComponent },
  { path: '**', component: NotFoundPageComponent } // za sve ostale path-ove izbaci page not found
];

@NgModule({
  declarations: [
    AppComponent,
    SendMessageComponent,
    LoginPageComponent,
    NotFoundPageComponent,
    RbacPageComponent,
    NotAccessPageComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    ),
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot({preventDuplicates: true}) // ToastrModule added
  ],
  providers: [
    GenericService,
    AuthenticationService,
    JwtUtilsService,
    { provide: 'PKI_URL', useValue: 'https://localhost:8443' },  // pki
    CanActivateUserGuard,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
