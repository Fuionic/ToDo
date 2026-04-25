package com.ToDomangemnet.TodoDTO;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDTO {

    private Long id;
    private String title;
    private String description;
    private boolean completed;

}
