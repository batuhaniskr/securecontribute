import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './home/home.component';
import { ImportComponent } from './import/import.component';
import { RouterModule, Routes } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { IssueListComponent } from './issues/issue-list/issue-list.component';
import { AcceptComponent } from './challenges/accept/accept.component';
import { RejectComponent } from './challanges/reject/reject.component';
import { ChallengeListComponent } from './challenges/challenge-list/challenge-list.component';
import { StatusComponent } from './badges/status/status.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent},
  { path: 'import', component: ImportComponent},
  { path: 'repository/:id/issues', component: IssueListComponent},
  { path: 'challenge/:id/accept', component: AcceptComponent},
  { path: 'challenge/:id/reject', component: RejectComponent},
  { path: 'challenges', component: ChallengeListComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' },
]

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ImportComponent,
    IssueListComponent,
    AcceptComponent,
    RejectComponent,
    ChallengeListComponent,
    StatusComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule,
    NgbModule,
    ReactiveFormsModule,
    HttpClientModule,
    FontAwesomeModule,
    ToastrModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
