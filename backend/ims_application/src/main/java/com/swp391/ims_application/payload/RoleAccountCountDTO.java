package com.swp391.ims_application.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoleAccountCountDTO {
    private String roleName;
    private long count;
}
