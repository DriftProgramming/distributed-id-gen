# Uidgenerator(Baidu) integrated with SpringBoot 2.x.x.x

#### Reference
- https://github.com/baidu/uid-generator

#### 1.Setup multiple envs:

- src/main/resources/application.properties : common settings for all envs.
- src/main/resources/application-local.properties: specific settings(e.g.: database) for `local` env.
- src/main/resources/application-uat.properties: specific settings(e.g.: database) for `uat` env.
- src/main/resources/application-prod.properties: specific settings(e.g.: database) for `prod` env.


#### 2. Build
- `mvn clean install`

#### 3. Run/Debug
- run in terminal: `ENV=local mvn spring-boot:run`
- Or directly run/debug by Intellij IDEA
- Get id from http://localhost:9090/id

#### Notes
- One database(table name:worker_node) is required for this project.
- For `local` env the datatable is created automatically. But it's not for any other envs. If you want to change this behavior, please modify the `spring.jpa.hibernate.ddl-auto=update` in any related `*.properties` file.
- If you want to manually create the datatable, please refer to `src/main/resources/WORKER_NODE.sql`
