import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Challenge } from 'src/app/_models/challenge';
import { ChallengeService } from 'src/app/_services/challenge.service';

@Component({
  selector: 'app-challenge-list',
  templateUrl: './challenge-list.component.html',
  styleUrls: ['./challenge-list.component.css']
})
export class ChallengeListComponent implements OnInit {

  challenges: Challenge[] = [];
  loading = false;

  constructor(private challengeService: ChallengeService,
              private toastr: ToastrService) { }

  ngOnInit(): void {
    this.list();
  }

  list() {
    this.loading = true;
    this.challengeService.list()
    .subscribe(resp => {
      this.loading = false;
      this.challenges = resp;
    }, error => {
      this.toastr.error(error);
      this.loading = false;
    });
  }

}