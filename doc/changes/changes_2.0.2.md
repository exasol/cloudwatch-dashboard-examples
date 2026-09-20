# Cloudwatch Dashboard Examples 2.0.2, released 2026-??-??

Code name: Fixed vulnerabilities CVE-2026-68498, CVE-2026-91776, CVE-2026-91777, CVE-2026-83557

## Summary

This release fixes the following 4 vulnerabilities:

### CVE-2026-68498 (CWE-770) in dependency `com.fasterxml.jackson.core:jackson-core:jar:2.22.1:compile`
jackson-core - Allocation of Resources Without Limits or Throttling

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://guide.sonatype.com/vulnerability/CVE-2026-68498 for details
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-68498?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-core&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-68498
* https://github.com/FasterXML/jackson-core/security/advisories/GHSA-649p-m576-vr99

### CVE-2026-91776 (CWE-770) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
Jackson-databind - Unbounded growth of type id cache in TypeDeserializer
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-91776?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-91776
* https://github.com/FasterXML/jackson-databind/pull/6203

### CVE-2026-91777 (CWE-407) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
Jackson-databind - Denial of Service (DoS)
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-91777?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-91777
* https://github.com/FasterXML/jackson-databind/pull/6204

### CVE-2026-83557 (CWE-502) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
jackson-databind - Deserialization of untrusted data [CVE-2026-83557]
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-83557?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-83557
* https://github.com/FasterXML/jackson-databind/issues/6156
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-gx83-3vf8-gh7j

## Security

* #37: Fixed vulnerability CVE-2026-68498 in dependency `com.fasterxml.jackson.core:jackson-core:jar:2.22.1:compile`
* #38: Fixed vulnerability CVE-2026-91776 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #39: Fixed vulnerability CVE-2026-91777 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #40: Fixed vulnerability CVE-2026-83557 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`

## Dependency Updates

### Compile Dependency Updates

* Updated `software.amazon.awscdk:aws-cdk-lib:2.262.1` to `2.270.0`
* Updated `software.constructs:constructs:10.7.1` to `10.8.1`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter:6.1.2` to `6.1.3`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.7.4` to `5.7.6`
