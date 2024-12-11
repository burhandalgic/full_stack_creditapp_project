package com.burhan.creditmanagement.aplication.business.dto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateApplicationRequest {

    @NotNull
    private Long applicationNo ;

    @NotNull
    @Min(value = 1, message = "Value must be greater than or equal to 1")
    @Max(value = 3, message = "Value must be less than or equal to 3")
    private Integer status;



}
