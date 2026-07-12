# Cloudwatch Dashboard Examples 2.0.1, released 2026-??-??

Code name: Fixed vulnerabilities CVE-2026-54515, CVE-2026-59889

## Summary

This release fixes the following 2 vulnerabilities:

### CVE-2026-54515 (CWE-915) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.0:compile`
jackson-databind contains the general-purpose data-binding functionality and tree-model for Jackson Data Processor. From 2.8.0 until 2.18.9, 2.21.5, and 3.1.4, in BeanDeserializerBase.createContextual(), per-property @JsonIgnoreProperties exclusions are applied by _handleByNameInclusion(), producing a contextual deserializer whose BeanPropertyMap has the ignored properties removed. The subsequent per-property case-insensitivity block (triggered by @JsonFormat(ACCEPT_CASE_INSENSITIVE_PROPERTIES)) rebuilds from this._beanProperties (the original, unfiltered map) instead of contextual._beanProperties, then overwrites the filtered map â restoring every property _handleByNameInclusion had just removed. The ignored property becomes writable again. This vulnerability is fixed in 2.18.9, 2.21.5, and 3.1.4.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-54515?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-54515
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-5jmj-h7xm-6q6v

### CVE-2026-59889 (CWE-863) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.0:compile`
Jackson Databind -  Authorization bypass on JsonView Setter/Field
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-59889?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-59889
* https://github.com/FasterXML/jackson-databind/issues/6060
* https://github.com/FasterXML/jackson-databind/pull/6056

## Security

* #31: Fixed vulnerability CVE-2026-54515 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.0:compile`
* #32: Fixed vulnerability CVE-2026-59889 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.0:compile`

## Dependency Updates

### Compile Dependency Updates

* Updated `software.amazon.awscdk:aws-cdk-lib:2.260.0` to `2.261.0`

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter:6.1.0` to `6.1.2`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.6.2` to `5.7.3`
