import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Challenge } from '../_models/challenge';

@Injectable({
  providedIn: 'root'
})
export class ChallengeService {  

  constructor(private http: HttpClient) { }

  accept(id: number): Observable<any> {
    return this.http.patch(`${environment.API_URL}/challenges/${id}`, {status: "ACCEPTED"});
  }

  reject(id: number): Observable<any> {
    return this.http.patch(`${environment.API_URL}/challenges/${id}`, {status: "REJECTED"});
  }

  list(): Observable<Challenge[]> {
    return this.http.get<Challenge[]>(`${environment.API_URL}/challenges/`);
  }
}
