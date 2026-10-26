# Cloudwatch Dashboard Examples 2.0.2, released 2026-10-26

Code name: Jackson security fixes

## Summary

This release tracks nine security fixes for `jackson-core` and `jackson-databind`.

- Fixes #34
- Fixes #35
- Fixes #37
- Fixes #38
- Fixes #39
- Fixes #40
- Fixes #42
- Fixes #43
- Fixes #45

## Security

* #37: Fixed CVE-2026-68498 (oversized property names exhaust memory) in dependency `com.fasterxml.jackson.core:jackson-core:jar:2.22.1:compile`
* #42: Fixed CVE-2026-89407 (quadratic numeric validation exhausts CPU) in dependency `com.fasterxml.jackson.core:jackson-core:jar:2.22.1:compile`
* #43: Fixed CVE-2026-89425 (unbounded invalid token error exhausts memory) in dependency `com.fasterxml.jackson.core:jackson-core:jar:2.22.1:compile`
* #45: Fixed CVE-2026-110879 (polymorphic type validation bypass) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #34: Fixed CVE-2026-19032 (unrestricted URI schemes in Path deserialization) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #35: Fixed CVE-2026-68497 (unbounded XML datatype parsing exhausts CPU) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #38: Fixed CVE-2026-91776 (unknown type IDs grow cache without bounds) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #39: Fixed CVE-2026-91777 (quadratic object reference resolution exhausts CPU) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #40: Fixed CVE-2026-83557 (unsafe Comparable polymorphic type resolution) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`

## Dependency Updates

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.1.0` to `2.1.2`
* Updated `com.exasol:project-keeper-maven-plugin:5.7.4` to `5.8.1`
* Updated `io.github.git-commit-id:git-commit-id-maven-plugin:10.0.0` to `10.0.1`
* Updated `org.apache.maven.plugins:maven-artifact-plugin:3.6.1` to `3.7.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.15.0` to `3.16.0`
* Updated `org.apache.maven.plugins:maven-install-plugin:3.1.4` to `3.2.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.6` to `3.6.0`
* Updated `org.apache.maven.plugins:maven-toolchains-plugin:3.2.0` to `3.3.0`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.7.3` to `1.8.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.21.0` to `2.23.0`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.7.0.6970` to `5.8.0.7211`
