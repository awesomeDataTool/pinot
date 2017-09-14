package com.linkedin.thirdeye.taskexecution.impl.operator;

import com.linkedin.thirdeye.taskexecution.operator.ExecutionResult;
import com.linkedin.thirdeye.taskexecution.operator.Operator;
import com.linkedin.thirdeye.taskexecution.operator.OperatorConfig;
import com.linkedin.thirdeye.taskexecution.operator.OperatorContext;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AbstractOperatorRunnerTest {

  @Test
  public void testSuccessInitializeOperator() throws InstantiationException, IllegalAccessException {
    AbstractOperatorRunner.initiateOperatorInstance(OperatorRunnerTest.DummyOperator.class);
  }

  @Test
  public void testFailureInitializeOperator() {
    try {
      AbstractOperatorRunner.initiateOperatorInstance(FailedInitializedOperator.class);
    } catch (Exception e) {
      return;
    }
    Assert.fail();
  }

  public static class FailedInitializedOperator extends Operator {
    private FailedInitializedOperator() {
    }

    @Override
    public void initialize(OperatorConfig operatorConfig) {
      throw new UnsupportedOperationException("Failed during initialization IN PURPOSE.");
    }

    @Override
    public ExecutionResult run(OperatorContext operatorContext) {
      return new ExecutionResult();
    }
  }

}