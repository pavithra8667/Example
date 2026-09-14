package com.infinite.ehrSystem.labResults.strategy;

import com.infinite.ehrSystem.exception.InvalidResultValueException;
import com.infinite.ehrSystem.labResults.entity.ResultFlag;
import org.springframework.stereotype.Component;

@Component
public class PositiveNegativeResultStrategy
        implements ResultFlagStrategy {

    @Override
    public ResultFlag calculateFlag(String resultValue, String referenceRange) {

        if ("NEGATIVE".equalsIgnoreCase(resultValue.trim())) {
            return ResultFlag.NORMAL;
        }

        if ("POSITIVE".equalsIgnoreCase(resultValue.trim())) {
            return ResultFlag.ABNORMAL;
        }

        throw new InvalidResultValueException("Result must be POSITIVE or NEGATIVE"
        );
    }
}