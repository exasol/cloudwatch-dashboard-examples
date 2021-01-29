package com.exasol.cloudwatchexampledashboard;

import software.amazon.awscdk.core.App;

/**
 * This class is the main entry point of the template. You can find the relevant template parts, however, in
 * {@link @CloudwatchDashboardExamplesStack}.
 */
public class CloudwatchDashboardExamplesApp {

    /**
     * Main method.
     * 
     * @param args arguments
     */
    public static void main(final String[] args) {
        final App app = new App();

        new CloudwatchDashboardExamplesStack(app, "CloudwatchDashboardExamplesStack");

        app.synth();
    }
}
