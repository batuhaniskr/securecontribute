import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faBug, faLink } from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';
import { Issue } from 'src/app/_models/issue';
import { IssueService } from 'src/app/_services/issue.service';

@Component({
  selector: 'app-issue-list',
  templateUrl: './issue-list.component.html',
  styleUrls: ['./issue-list.component.css']
})
export class IssueListComponent implements OnInit {

  issues: Issue[] = [];
  loading = false;
  faLink = faLink

  constructor(private issueService: IssueService,
              private toastr: ToastrService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.list(params["id"]);
    })
  }

  list(repositoryId: number) {
    this.loading = true;
    this.issueService.list(repositoryId)
    .subscribe(resp => {
      this.loading = false;
      this.issues = resp;
    }, error => {
      this.toastr.error(error,"Error")
      this.loading = false;
    })
  }

}
