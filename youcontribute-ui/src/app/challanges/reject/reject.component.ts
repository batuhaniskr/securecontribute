import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { first } from 'rxjs/operators';
import { ChallengeService } from 'src/app/_services/challenge.service';

@Component({
  selector: 'app-accept',
  templateUrl: './reject.component.html',
  styleUrls: ['./reject.component.css']
})
export class RejectComponent implements OnInit {

  loading = false;

  constructor(private toastr: ToastrService,
              private challengeService: ChallengeService, 
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.reject(params["id"]);
    })
  }

  private reject(id: number) {
    this.loading=true;
    this.challengeService.reject(id)
    .pipe(first())
    .subscribe(resp => {
      this.loading = false;
      this.toastr.success("Challenge accepted succesfully","Success");
      setTimeout(() => {
        this.router.navigate(['challenges'])
      }, 2000)
    }, error => {      
      this.loading = false;
      this.toastr.success(error.error.message,"Error");
    });
  }

}
