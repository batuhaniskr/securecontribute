import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { first } from 'rxjs/operators';
import { ChallengeService } from 'src/app/_services/challenge.service';

@Component({
  selector: 'app-accept',
  templateUrl: './accept.component.html',
  styleUrls: ['./accept.component.css']
})
export class AcceptComponent implements OnInit {

  loading = false;

  constructor(private toastr: ToastrService,
              private challengeService: ChallengeService, 
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.accept(params["id"]);
    })
  }

  private accept(id: number) {
    this.loading=true;
    this.challengeService.accept(id)
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
