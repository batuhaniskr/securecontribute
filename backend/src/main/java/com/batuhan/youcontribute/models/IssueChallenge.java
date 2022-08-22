package com.batuhan.youcontribute.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssueChallenge {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  private Integer id;

  @OneToOne
  @JsonManagedReference
  private Issue issue;

  @Enumerated(EnumType.STRING)
  private IssueChallengeStatus status;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;
}
