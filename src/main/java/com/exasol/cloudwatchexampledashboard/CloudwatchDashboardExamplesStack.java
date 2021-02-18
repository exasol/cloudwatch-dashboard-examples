package com.exasol.cloudwatchexampledashboard;

import java.util.List;
import java.util.Map;

import software.amazon.awscdk.core.*;
import software.amazon.awscdk.services.cloudwatch.*;

/**
 * This stack creates an CloudWatch dashboard with graphs for the metrics reported by the exasol cloudwatch-adapter.
 */
public class CloudwatchDashboardExamplesStack extends Stack {
    private final CfnParameter deploymentName;
    private final Map<String, String> dimensions;

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
        this.dimensions = Map.of("Cluster Name", "MASTER", "Deployment", this.deploymentName.getValueAsString());

        final Dashboard dashboard = Dashboard.Builder.create(this, "Exasol Dashboard").build();

        final Metric queriesMetric = getExasolMetricBuilder().metricName("QUERIES")
                .label("Parallel running queries (5 min MAX)").statistic("Maximum").build();
        final Metric usersMetric = getExasolMetricBuilder().metricName("USERS").label("Users (5 min MAX)")
                .statistic("Maximum").build();
        dashboard.addWidgets(//
                cpuWidget(), //
                tempDbRamWidget(), //
                diskIoWidget(), //
                networkIoWidget(), //
                usageWidget(queriesMetric, usersMetric), //
                recommendedDbRamSizeWidget(), //
                currentDbSizeWidget(), //
                currentQueriesAndUsersWidget(queriesMetric, usersMetric));

        addTempdbRamAlarm();
    }

    private void addTempdbRamAlarm() {
        Alarm.Builder.create(this, "Temp DB RAM Alarm")
                .metric(MathExpression.Builder.create().expression("(tem_db_ram/1000) / ram")
                        .usingMetrics(Map.of("tem_db_ram", getExasolMetricBuilder().metricName("TEMP_DB_RAM").build(),
                                "ram", getExasolMetricBuilder().metricName("DB_RAM_SIZE").build()))
                        .period(Duration.minutes(5)).label("temporary db ram to available ram ratio").build())
                .threshold(0.7).comparisonOperator(ComparisonOperator.GREATER_THAN_THRESHOLD).evaluationPeriods(1)
                .datapointsToAlarm(1).alarmName("Temp DB RAM Alarm")
                .alarmDescription("The temporary db ram consumed more that 70% of the available memory.")
                .actionsEnabled(false).build();
    }

    private GraphWidget cpuWidget() {
        return GraphWidget.Builder.create().title("CPU")
                .left(List.of(getExasolMetricBuilder().metricName("CPU").label("CPU (5 min AVG)").build()))
                .right(List.of(getExasolMetricBuilder().metricName("LOAD").label("Load (5 min AVG)").build())).build();
    }

    private GraphWidget tempDbRamWidget() {
        return GraphWidget.Builder.create().title("Temporary DB Ram").left(List.of(getExasolMetricBuilder()
                .metricName("TEMP_DB_RAM").statistic("Maximum").label("TEMP_DB_RAM (5 min MAX)").build())).build();
    }

    private GraphWidget diskIoWidget() {
        return GraphWidget.Builder.create().title("Disk I/O")
                .left(List.of(getExasolMetricBuilder().metricName("HDD_READ").label("HDD_READ (5 min AVG)").build(),
                        getExasolMetricBuilder().metricName("HDD_WRITE").label("HDD_WRITE (5 min AVG)").build(),
                        getExasolMetricBuilder().metricName("SWAP").label("Swap (5 min AVG)").build()))
                .build();
    }

    private GraphWidget networkIoWidget() {
        return GraphWidget.Builder.create().title("Network I/O")
                .left(List.of(getExasolMetricBuilder().metricName("NET").label("Network I/O (5 min AVG)").build()))
                .build();
    }

    private GraphWidget usageWidget(final Metric queriesMetric, final Metric usersMetric) {
        return GraphWidget.Builder.create().title("Usage").left(List.of(queriesMetric)).right(List.of(usersMetric))
                .build();
    }

    private GraphWidget recommendedDbRamSizeWidget() {
        return GraphWidget.Builder.create().title("Recommended DB RAM Size")
                .left(List.of(
                        getExasolMetricBuilder().metricName("RECOMMENDED_DB_RAM_SIZE")
                                .label("Recommended DB RAM (5 min AVG)").build(),
                        getExasolMetricBuilder().metricName("DB_RAM_SIZE").label("Actual DB RAM (5 min AVG)").build()))
                .build();
    }

    private SingleValueWidget currentQueriesAndUsersWidget(final Metric queriesMetric, final Metric usersMetric) {
        return SingleValueWidget.Builder.create().title("Current Queries and Users").setPeriodToTimeRange(false)
                .metrics(List.of(queriesMetric, usersMetric)).width(6).build();
    }

    private SingleValueWidget currentDbSizeWidget() {
        final Metric rawObjectSize = getExasolMetricBuilder().metricName("RAW_OBJECT_SIZE").label("Raw Object Size")
                .period(Duration.hours(1)).build();
        final Metric memObjSizeMetric = getExasolMetricBuilder().metricName("MEM_OBJECT_SIZE").label("Mem Object Size")
                .period(Duration.hours(1)).build();
        return SingleValueWidget.Builder.create().title("Current Database Size").metrics(List.of(//
                rawObjectSize, //
                memObjSizeMetric, //
                MathExpression.Builder.create().expression("rawObjectSize / memObjectSize")
                        .usingMetrics(Map.of("rawObjectSize", rawObjectSize, "memObjectSize", memObjSizeMetric))
                        .label("Compression Factor").build(),
                getExasolMetricBuilder().metricName("STATISTICS_SIZE").label("Statistics Size")
                        .period(Duration.hours(1)).build(), //
                getExasolMetricBuilder().metricName("AUXILIARY_SIZE").label("Auxiliary Size").period(Duration.hours(1))
                        .build(), //
                getExasolMetricBuilder().metricName("USE").label("Persistent storage use").period(Duration.hours(1))
                        .build(), //
                getExasolMetricBuilder().metricName("OBJECT_COUNT").label("Object count").period(Duration.hours(1))
                        .build(), //
                getExasolMetricBuilder().metricName("NODES").label("Node count").period(Duration.days(1)).build() //
        )).setPeriodToTimeRange(false).width(12).height(6).build();
    }

    private Metric.Builder getExasolMetricBuilder() {
        return Metric.Builder.create().namespace("Exasol").dimensions(this.dimensions);
    }
}
