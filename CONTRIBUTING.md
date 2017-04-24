# Contributing

## Steps

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request

## Publishing

We publish our Java SDK to Bintray's JCenter and Sonatype's Central Repository.  
That process is not automated yet and it requires to have special access, but here's the steps:

### 1. Dry run
* Set the following environment variables:
```bash
export SONATYPE_USER="<secret>"
export SONATYPE_PASSWORD="<secret>"
export BINTRAY_USER="<secret>"
export BINTRAY_KEY="<secret>"
```
* Adjust the VERSION variable in the following files:
	* `discovery/gradle.properties`
	* `discovery-model/gradle.properties` 
	* `discovery/src/main/java/com/ticketmaster/api/Version.java` 
	* `maven-example/pom.xml`
	* `README.md`
* Run: 
```
./gradlew clean install bintrayUpload -i
```

### 2. Publishing
**If the dry run went well, you can proceed**  

* Set the following environment variables:

```bash
export SONATYPE_USER="<secret>"
export SONATYPE_PASSWORD="<secret>"
export BINTRAY_USER="<secret>"
export BINTRAY_KEY="<secret>"
export BINTRAY_DRYRUN=0
export SONATYPE_CLOSE=1
```

* Run: 
```
./gradlew clean install bintrayUpload -i
```
* Validate that everything is successfully published to JCenter and the Central Repository (it can take some times)
