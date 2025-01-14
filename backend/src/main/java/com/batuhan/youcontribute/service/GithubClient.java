package com.batuhan.youcontribute.service;

import com.batuhan.youcontribute.config.GithubProperties;
import com.batuhan.youcontribute.service.models.GithubIssueResponse;
import com.batuhan.youcontribute.service.models.GithubPullResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class GithubClient {

    private final RestTemplate restTemplate;

    private final GithubProperties githubProperties;

    public GithubIssueResponse[] listIssues(String owner, String repository, LocalDate since) {
        String issuesUrl = String.format("%s/repos/%s/%s/issues?since=%s",
                this.githubProperties.getApiUrl(), owner, repository, since.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","token " + this.githubProperties.getToken());
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<GithubIssueResponse[]> response = this.restTemplate.exchange(
                issuesUrl, HttpMethod.GET,
                request, GithubIssueResponse[].class);

        return response.getBody();
    }

  public GithubPullResponse[] listPullRequests(String owner, String repository) {
    String pullRequestUrl = String.format("%s/repos/%s/%s/pulls?state=closed",
      this.githubProperties.getApiUrl(), owner, repository);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization","token " + this.githubProperties.getToken());
    HttpEntity request = new HttpEntity(headers);
    ResponseEntity<GithubPullResponse[]> response = this.restTemplate.exchange(
      pullRequestUrl, HttpMethod.GET,
      request, GithubPullResponse[].class);

    return response.getBody();
  }
}
