var normalize = require('./lib/normalize');

function requiredParams() {
  if (process.argv.length <= 3) {
    console.log('Usage: ' + __filename + ' target_currency input_file');
    process.exit(-1);
  }
}

requiredParams();

var param = process.argv.slice(2);
normalize.transform(param[1], param[0]);
