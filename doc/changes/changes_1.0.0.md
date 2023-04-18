# Cloudwatch Dashboard Examples 1.0.0, released 2023-04-18

Code name: Migrate to AWS CDK v2

## Summary

This release migrates the AWS CDK v2 as support for v1 will end on June 1, 2023. If you customized your dashboard you might need to adapt to the new version. See AWS' [migration guide](https://docs.aws.amazon.com/cdk/v2/guide/migrating-v2.html) for details.

## Features

* #23: Migrate to AWS CDK v2

## Refactoring

* #19: Removed workaround for project-keeper bug

## Dependency Updates

### Compile Dependency Updates

* Added `software.amazon.awscdk:aws-cdk-lib:2.75.0`
* Removed `software.amazon.awscdk:cloudwatch:1.197.0`
* Removed `software.amazon.awscdk:core:1.197.0`
* Added `software.constructs:constructs:10.1.314`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.2.2` to `1.2.3`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.4` to `2.9.7`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.10.1` to `3.11.0`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.2.1` to `3.3.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M8` to `3.0.0`
* Added `org.basepom.maven:duplicate-finder-maven-plugin:1.5.1`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.3.0` to `1.4.1`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.14.2` to `2.15.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.8` to `0.8.9`
