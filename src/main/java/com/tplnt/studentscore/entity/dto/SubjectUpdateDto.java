package com.tplnt.studentscore.entity.dto;

import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class SubjectUpdateDto {

    @Size(min = 10, max = 150 , message = "{subject.title.size}")
    private String title;

    @Size(min = 6, max = 6, message = "{subject.code.size}")
    private String code;

    @Size(min = 4, max = 4, message = "{subject.year.size}")
    private String year;

}
