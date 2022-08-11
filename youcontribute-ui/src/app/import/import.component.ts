  import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { RepositoryService } from '../_services/repository.service';
import {first} from "rxjs/operators";
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-import',
  templateUrl: './import.component.html',
  styleUrls: ['./import.component.css']
})
export class ImportComponent implements OnInit {

  faPLus = faPlus
  loading = false;

  importRepositoryForm: FormGroup = this.formBuilder.group({
    organization: ['', Validators.required],
    repository: ['', Validators.required],     
  });

  constructor(private formBuilder: FormBuilder, 
              private repositoryService: RepositoryService, 
              private toastr: ToastrService,
              private router: Router) { }

  ngOnInit(): void {

  }

  import() {
    this.loading = true;
    this.repositoryService.import(this.importRepositoryForm.get('organization')?.value, this.importRepositoryForm.get('repository')?.value)
    .pipe(first())
    .subscribe(() => {
      this.loading = false;
      this.toastr.success("Imported successfully","Success");
      setTimeout(() => {
        this.router.navigate(['home'])
      }, 2000);
    },
      error => {
        this.loading = false;
        console.log(error);
        this.toastr.error(error.error.message,"Error");
      });
  }  

}
