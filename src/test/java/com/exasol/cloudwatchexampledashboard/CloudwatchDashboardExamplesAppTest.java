package com.exasol.cloudwatchexampledashboard;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class CloudwatchDashboardExamplesAppTest {
    @Test
    void synthesizeApp() {
        assertDoesNotThrow(() -> CloudwatchDashboardExamplesApp.main(null));
    }
}
