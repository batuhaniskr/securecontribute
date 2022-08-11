import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Issue } from '../_models/issue';

@Injectable({
  providedIn: 'root'
})
export class IssueService {

  constructor(private http: HttpClient) {}

   list(repositoryId: number): Observable<Issue[]> {
    return this.http.get<Issue[]>(`${environment.API_URL}/issues?repository_id=${repositoryId}`);
   }
}
