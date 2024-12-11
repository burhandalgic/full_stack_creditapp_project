package com.burhan.creditmanagement.evaluation.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventEvalutionResponseDto  {
    private Long customerNo;
    private boolean result;
    private Long applicationNo;
}
