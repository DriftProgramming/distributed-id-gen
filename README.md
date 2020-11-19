# Uidgenerator(Baidu) integrated with SpringBoot 2.x.x.x

#### Reference
- https://github.com/baidu/uid-generator

#### 1. Setup Multiple Envs:

- src/main/resources/application.properties : common settings for all envs.
- src/main/resources/application-local.properties: specific settings(e.g.: database) for `local` env.
- src/main/resources/application-uat.properties: specific settings(e.g.: database) for `uat` env.
- src/main/resources/application-prod.properties: specific settings(e.g.: database) for `prod` env.

#### 2. Uidgenerator Configuration.
```
snowflake.timeBits=31
snowflake.workerBits=20
snowflake.seqBits=12
snowflake.epochStr=2020-11-19
```

- snowflake.timeBits: the milliseconds start from `snowflake.epochStr` until now. Changing this bits to extend/shorten the time.
- snowflake.workerBits: worker id, e.g: 2^20 ~= 420W restart times
- snowflake.seqBits: total sequence bits at the same milliseconds time,same worker id.
- snowflake.epochStr: init date time. it's better to start from when you deploy this project. so that it means the total bits of time will start from small numbers.
- TotalBits should equal 63 bits: e.g.: 31+20+12=63, and the first bit is always `0`(sign of non-negative number), it is ignored by default.


#### 3. Build
- `mvn clean install`

#### 4. Run/Debug
- run in terminal: `ENV=local mvn spring-boot:run`
- Or directly run/debug by Intellij IDEA
- Get id from http://localhost:9090/id



#### 5. Notes
- One database(table name:worker_node) is required for this project.
- For `local` env the datatable is created automatically. But it's not for any other envs. If you want to change this behavior, please modify the `spring.jpa.hibernate.ddl-auto=update` in any related `*.properties` file.
- If you want to manually create the datatable, please refer to `src/main/resources/WORKER_NODE.sql`


