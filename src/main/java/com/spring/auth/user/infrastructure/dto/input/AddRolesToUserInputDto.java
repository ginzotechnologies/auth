package com.spring.auth.user.infrastructure.dto.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddRolesToUserInputDto {
  // id of the roles
  @NotEmpty private List<String> roleIds = new ArrayList<>(); // todo: validate roleIds format
}