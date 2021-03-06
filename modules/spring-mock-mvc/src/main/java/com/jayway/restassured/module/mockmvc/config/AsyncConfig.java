package com.jayway.restassured.module.mockmvc.config;

import com.jayway.restassured.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * Configuration for MockMVC async requests
 */
public class AsyncConfig implements Config {

    private static final long DEFAULT_TIMEOUT_IN_MILLISECONDS = TimeUnit.SECONDS.toMillis(1);

    private final boolean userConfigured;
    private final long duration;
    private final TimeUnit timeUnit;

    /**
     * Creates a default {@link AsyncConfig} with timeout equal 1000 milliseconds (1 second).
     */
    public AsyncConfig() {
        this(DEFAULT_TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS, false);
    }

    /**
     * Creates a new {@link AsyncConfig} with timeout equal to the given number of milliseconds.
     *
     * @param timeoutInMs The timeunit in milliseconds.
     */
    public AsyncConfig(long timeoutInMs) {
        this(timeoutInMs, TimeUnit.MILLISECONDS);
    }

    /**
     * Creates a new {@link AsyncConfig} with timeout.
     *
     * @param duration The duration
     * @param timeUnit The time unit
     */
    public AsyncConfig(long duration, TimeUnit timeUnit) {
        this(duration, timeUnit, true);
    }

    private AsyncConfig(long duration, TimeUnit timeUnit, boolean isUserConfigured) {
        if (timeUnit == null) {
            throw new IllegalArgumentException("TimeUnit cannot be null");
        }
        this.duration = duration;
        this.timeUnit = timeUnit;
        this.userConfigured = isUserConfigured;
    }

    /**
     * Specify the timeout for the async request in milliseconds.
     *
     * @param timeoutInMs The timeout in milliseconds.
     * @return A new instance of the MockMvcAsyncConfig
     */
    public AsyncConfig timeout(long timeoutInMs) {
        return timeout(timeoutInMs, TimeUnit.MILLISECONDS);
    }

    /**
     * Specify the timeout for the async request in milliseconds.
     *
     * @param duration The duration.
     * @param timeUnit The time unit for the duration.
     * @return A new instance of the MockMvcAsyncConfig
     */
    public AsyncConfig timeout(long duration, TimeUnit timeUnit) {
        return new AsyncConfig(duration, timeUnit, true);
    }

    /**
     * @return The timeout converted to milliseconds.
     */
    public long timeoutInMs() {
        return TimeUnit.MILLISECONDS.convert(duration, timeUnit);
    }

    public boolean isUserConfigured() {
        return userConfigured;
    }

    public static AsyncConfig withTimeout(long duration, TimeUnit timeUnit) {
        return new AsyncConfig(timeUnit.toMillis(duration));
    }

    /**
     * Just syntactic sugar to make the DSL more english like.
     */
    public AsyncConfig with() {
        return this;
    }

    /**
     * Just syntactic sugar.
     *
     * @return A new instance of {@link AsyncConfig}.
     */
    public static AsyncConfig asyncConfig() {
        return new AsyncConfig();
    }
}
