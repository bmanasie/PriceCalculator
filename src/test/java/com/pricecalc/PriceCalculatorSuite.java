package com.redbubble;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        JSONReaderTest.class,
        BasePriceFileProcessorTest.class,
        CartPriceCalculatorTest.class
        })
public class PriceCalculatorSuite {




}
