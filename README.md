# Jibby

A [Micronaut](micronaut.io) + [Jib](https://github.com/GoogleContainerTools/jib) mashup with extra bells and whistles for showing some hot CI/CD action.

You can try out the basic tutorial at https://codelabs.developers.google.com/codelabs/cloud-micronaut-kubernetes to see a similar but dated approach.

## Behind the scenes

Some more gory details about how this template was put together

### Jib

In short Jib lets you automagically containerize a JVM web app through something like their [gradle plugin](https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin) without having to write or maintain a Dockerfile.

You can easily define what source image to use (it will otherwise default to something sensible) and what image it should produce and publish somewhere.

You can see other examples of Jib in action at https://github.com/GoogleContainerTools/jib/tree/master/examples

### Micronaut

For complete documentation see the later section with links. For the purposes of this template a basic `/hello` endpoint is defined that will return a message to the user. Micronaut environment handling is also enabled with an extremely simple but fully functional config-derrived value being part of the returned message.

By default the `application.yml` from `src/main/resources` is loaded, but you can toggle the dev environment settings by running the app with an environment variable `MICRONAUT_ENVIRONMENTS` set to `dev` to then load the `application-dev.yml` - you can make any environment you like and even activate multiple at once. See more at https://docs.micronaut.io/latest/guide/#environments

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
- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)


