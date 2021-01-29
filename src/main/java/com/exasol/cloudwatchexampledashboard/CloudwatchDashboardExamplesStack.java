package com.exasol.cloudwatchexampledashboard;

import java.util.*;
import java.util.stream.Collectors;

import software.amazon.awscdk.core.*;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.services.cloudwatch.*;

/**
 * This stack creates an CloudWatch dashboard with graphs for the metrics reported by the exasol cloudwatch-adapter.
 */
public class CloudwatchDashboardExamplesStack extends Stack {
    private final CfnParameter deploymentName;

    /**
     * Create a new instance of {@link CloudwatchDashboardExamplesStack}.
     * 
     * @param scope parent scope
     * @param id    stack id
     */
    public CloudwatchDashboardExamplesStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    /**
     * Create a new instance of {@link CloudwatchDashboardExamplesStack}.
     *
     * @param scope parent scope
     * @param id    stack id
     * @param props properties
     */
    public CloudwatchDashboardExamplesStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);
        this.deploymentName = CfnParameter.Builder.create(this, "deploymentName").type("String")
                .description("Deployment name matching the one configured in the cloud watch adapter.").build();

        final Dashboard dashboard = Dashboard.Builder.create(this, "Exasol Dashboard").build();

        dashboard.addWidgets(//
                createExasolGraph("CPU", "CPU"), createExasolGraph("Load", "LOAD"), //
                createExasolGraph("Users", "USERS"), //
                createExasolGraph("Parallel running queries", "QUERIES"), //
                createExasolGraph("Disk I/O", "HDD_READ", "HDD_WRITE"), //
                createExasolGraph("Read / Write Durations", "LOCAL_READ_DURATION", "LOCAL_WRITE_DURATION",
                        "REMOTE_READ_DURATION", "REMOTE_WRITE_DURATION", "CACHE_READ_DURATION", "CACHE_WRITE_DURATION"), //
                createExasolGraph("Read / Write Sizes", "LOCAL_READ_SIZE", "LOCAL_WRITE_SIZE", "REMOTE_READ_SIZE",
                        "REMOTE_WRITE_SIZE", "CACHE_READ_SIZE", "CACHE_WRITE_SIZE"), //
                createExasolGraph("Recommended DB RAM Size", "RECOMMENDED_DB_RAM_SIZE"), //
                createExasolGraph("Object count", "OBJECT_COUNT"), //
                createExasolGraph("Usage of persistent storage", "USE")//
        );
    }

    private GraphWidget createExasolGraph(final String diagramTitle, final String... metricNames) {
        final Map<String, String> dimensions = Map.of("Cluster Name", "MASTER", "Deployment",
                this.deploymentName.getValueAsString());
        final List<Metric> metrics = Arrays.stream(metricNames).map(
                name -> Metric.Builder.create().metricName(name).namespace("Exasol").dimensions(dimensions).build())
                .collect(Collectors.toList());
        return GraphWidget.Builder.create().title(diagramTitle).left(metrics).build();
    }
}
