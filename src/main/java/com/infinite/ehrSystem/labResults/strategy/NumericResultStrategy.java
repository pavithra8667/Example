package com.infinite.ehrSystem.labResults.strategy;

import com.infinite.ehrSystem.exception.InvalidResultValueException;
import com.infinite.ehrSystem.labResults.entity.ResultFlag;
import org.springframework.stereotype.Component;

@Component
public class NumericResultStrategy implements ResultFlagStrategy {


    @Override
    public ResultFlag calculateFlag(
            String resultValue,
            String referenceRange) {

        try {
            double value = Double.parseDouble(resultValue.trim());
            String[] range = referenceRange.split("-");
            double min = Double.parseDouble(range[0].trim());
            double max = Double.parseDouble(range[1].trim());

            if (value >= min && value <= max)
            {
                return ResultFlag.NORMAL;
            }

            return ResultFlag.ABNORMAL;

        } catch (NumberFormatException ex) {
            throw new InvalidResultValueException(
                    "Numeric result value expected"
            );
        }
    }
}