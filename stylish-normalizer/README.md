# stylish-normalizer

## Description:
  * As I'm using streaming instead of linear execution, which allow to extract and transform and load the data in chunks from the beginning to the end of the pipeline.
  * Streams in node comes from unix, which is like using pipe (|) in the shell, where you will be able to pipe your data through multiple stages until it reach the final stage, where in each stage you can transform you data.
  * Also the stream doesn't buffer the entire content of the file, it just get a chunk of it when it's chunk available.
  * This approach allow us to process your data without hitting the memory or disk limit.

## Command
```
npm install
node server.js USD rates.csv
```
