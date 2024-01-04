package com.flutter.project_flutter.dto;

import com.flutter.project_flutter.exceptions.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private Integer httpcode;

    private ErrorCodes codes;

    private String message;

    private List<String> error=new ArrayList<>();
}
