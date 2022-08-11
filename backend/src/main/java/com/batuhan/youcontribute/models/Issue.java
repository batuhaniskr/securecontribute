package com.batuhan.youcontribute.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Integer id;

    private Long githubIssueId;

    private String title;

    private String url;

    private Integer githubIssueNumber;

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private Repository repository;
}
