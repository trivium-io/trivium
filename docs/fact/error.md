# description

Representation of a Error within trivium.

# fields

| type | name | description |
|------|------|-------------|
| String | message | error message |
| ErrorCategory | category | error category (either: TRANSIENT,PERSISTENT) |
| Class<? extends Typed> | sourceModule | class reference of the error source |

# sample

```json
{"io.trivium.extension.fact.Error": {
         "message": "an error has occured",
         "category": "TRANSIENT",
         "sourceModule": "io.trivium.task.ConsoleLogger"
            }
}
```

# github reference

**link**

[Error.java](https://github.com/trivium-io/trivium/blob/master/src/io/trivium/extension/fact/Error.java)

**last commits**

<div id='commits' data-path='src/io/trivium/extension/fact/Error.java'></div>
<script src='../../js/commits.js' async></script>
