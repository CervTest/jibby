# Jibby

A [Micronaut](micronaut.io) + [Jib](https://github.com/GoogleContainerTools/jib) mashup with extra bells and whistles for showing some hot CI/CD action.

## Behind the scenes

Some more gory details about how this template was put together

### Container Registry

The goal is to allow several different registries to work, with minimal effort to go nicely along with the no-nonsense Jib setup.

#### Google Container Registry

[GCR](https://cloud.google.com/container-registry/docs/) is admittedly deprecated in favor of Google's newer [Artifact Registry](https://cloud.google.com/artifact-registry/docs), but plenty of older guides and apps still use it. Converting from GCR to AR should be minimal when the basics work.

First you need to enable its API on your GCP project like other features. Take a look at the basic config and make sure it is set up to match your needs and comfort level.

To get a credential to work with GCR you need to make a [service account](https://console.cloud.google.com/iam-admin/serviceaccounts) (IAM - Service Accounts in [GCP](https://console.cloud.google.com/)) with the Storage Admin role.

After you have a suitable service account you need a JSON file representing the key and its details to use within Jenkins (or elsewhere). This can be created in the UI or via CLI, example used for this repo:

`gcloud iam service-accounts keys create mykey --iam-account=gcr-service-user@proto-client-ttf-832500.iam.gserviceaccount.com`

The `mykey` parameter becomes the json file written to disk in the active directory. Submit it as a Secret File credential in Jenkins and it'll be usable as within the `Jenkinsfile` in this repo, referred to by its credential id `gcr-service-user-proto-client-ttf`

## Micronaut 3.10.1 Documentation

- [User Guide](https://docs.micronaut.io/3.10.1/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.10.1/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.10.1/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
- [Micronaut Gradle Plugin documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/)
- [GraalVM Gradle Plugin documentation](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)
## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)


