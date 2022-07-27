package com.batuhan.youcontribute.controllers.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRepositoryRequest {

    private String organization;
    private String repository;
}
