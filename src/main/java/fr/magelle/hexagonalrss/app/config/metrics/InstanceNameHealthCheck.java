package fr.magelle.hexagonalrss.app.config.metrics;

import com.codahale.metrics.health.HealthCheck;

public class InstanceNameHealthCheck extends HealthCheck {
    private final String instanceName;

    public InstanceNameHealthCheck(String instanceName) {
        this.instanceName = instanceName;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(instanceName, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("Instance name doesn't include a name");
        }
        return Result.healthy();
    }
}