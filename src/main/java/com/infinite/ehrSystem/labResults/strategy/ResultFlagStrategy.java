package com.infinite.ehrSystem.labResults.strategy;

import com.infinite.ehrSystem.labResults.entity.ResultFlag;

public interface ResultFlagStrategy {

    ResultFlag calculateFlag(String resultValue, String referenceRange
    );
}