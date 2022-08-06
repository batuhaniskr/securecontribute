import { Component, OnInit } from '@angular/core';
import { faBug } from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';
import { first } from 'rxjs/operators';
import { RepositoryService } from '../services/repository.service';
import { Repository } from '../_models/repository';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  repositories: Repository[] = [];
  loading =  false;
  faBug = faBug

  constructor(private repositoryService: RepositoryService,
              private toastr: ToastrService) { }

  ngOnInit(): void {
    this.list();
  }

  list() {
    this.repositoryService.list()
    .subscribe(resp => {
      this.loading = false;     
      this.repositories = resp
    },
      error => {
        this.loading = false;
        console.log(error);
        this.toastr.error(error.error.message,"Error");        
      });      
  }

}
